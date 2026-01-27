package com.inqwise.opinion.account;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.inqwise.errors.Bug;
import com.inqwise.errors.ErrorCodes;
import com.inqwise.errors.ErrorTicket;
import com.inqwise.errors.ErrorTickets;
import com.inqwise.errors.NotFoundException;
import com.inqwise.opinion.common.OpinionEntityStatus;
import com.inqwise.opinion.common.UidPrefixGenerator;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.templates.SqlTemplate;

/**
 * DefaultAccountService.
 */
class DefaultAccountService implements AccountService {
	/**
	 * getLogger.
	 */
	private static final Logger logger = LogManager.getLogger(DefaultAccountService.class);
	
	@Inject
	private UidPrefixGenerator uidPrefixGenerator;
	
	@Inject
	private DefaultAccountService() {}
	
	@Inject
	private Provider<Pool> pooledClientProvider;
	
	/**
	 * close.
	 */
	@Override
	public Future<Void> close() {
		return Future.succeededFuture();
	}

	/**
	 * status.
	 */
	@Override
	public Future<JsonObject> status() {
		return Future.succeededFuture(new JsonObject());
	}

	/**
	 * create.
	 */
	@Override
	public Future<CreateResult> create(CreateRequest request) {
		logger.debug("create({})", request);
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.CREATE_TEMPLATE)
		.mapFrom(DaoMappers.getCreateParams(uidPrefixGenerator))
		.mapTo(DaoMappers.CREATE_RESULT_ROW)
		.execute(request)
		.map(rs -> {
			return rs.stream().findFirst().orElseThrow(() -> new NotFoundException("account not created"));
		});
	}
	
	/**
	 * get.
	 */
	@Override
	public Future<Account> get(AccountIdentity identity) {
		logger.debug("get({})", identity);
		return getInternal(identity, true);
	}

	/**
	 * getInternal.
	 */
	private Future<Account> getInternal(AccountIdentifiable identity, boolean includeDeleted) {
		
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.GET_TEMPLATE)
		.mapFrom(DaoMappers.IDENTITY_PARAMS)
		.mapTo(DaoMappers.ACCOUNT_ROW)
		.execute(identity)
		.map(rs -> {
			var result = rs.stream()
					.filter(a -> (null == identity.getUidPrefix() || a.getUidToken().equals(identity.getUidToken())))
					.filter(a -> includeDeleted || null == a.getStatus() || a.getStatus() != OpinionEntityStatus.Deleted)
					.findAny()
					.orElseThrow(() -> new NotFoundException("account"));
			
			return result;
		});
	}

	/**
	 * search.
	 */
	@Override
	public Future<SearchResult> search(SearchRequest request) {
		logger.debug("search({})", request);
		var template =
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.SEARCH_TEMPLATE)
		.mapFrom(DaoMappers.SEARCH_PARAMS)
		.mapTo(DaoMappers.ACCOUNT_ROW);
		
		
		return template
		.execute(request)
		.map(rs -> {
			return
			SearchResult.builder()
			.withItems(rs.stream().toList())
			.build();
		});
	}

	/**
	 * delete.
	 */
	@Override
	public Future<Void> delete(AccountIdentity identity) {
		logger.debug("delete({})", identity);
		return getInternal(identity, false).compose(account -> {
			return 
			SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.DELETE_TEMPLATE)
			.mapFrom(DaoMappers.IDENTITY_PARAMS)
			.execute(identity)
			.map(rs -> {
				if(rs.rowCount() == 0) {
					throw new Bug("account #{} not deleted", identity.getId());
				}
				return (Void)null;
			});
		});
	}

	/**
	 * modify.
	 */
	@Override
	public Future<Void> modify(ModifyRequest request) {
		logger.debug("modify({})", request);
		return getInternal(request, false).compose(account -> {
			var hasDetailsChanges = Stream.of(
					request.getDetails(), 
					request.getTimezoneId(), 
					request.getName(), 
					request.getIsActive(), 
					request.getOwnerId(), 
					request.getIncludeDepositBounds(), 
					request.getMinDepositAmount(), 
					request.getMaxDepositAmount())
			.filter(Objects::nonNull)
			.count() > 0l;

			var hasBusinessDetailsChanges = Stream.of(
					request.getBusinessCompanyName(),
					request.getBusinessFirstName(),
					request.getBusinessLastName(),
					request.getBusinessAddress1(),
					request.getBusinessAddress2(),
					request.getBusinessCity(),
					request.getBusinessCountryId(),
					request.getBusinessStateId(),
					request.getBusinessPostalCode(),
					request.getBusinessPhone1())
			.filter(Objects::nonNull)
			.count() > 0l;

			var hasAnyChanges = hasDetailsChanges
					|| hasBusinessDetailsChanges;

			if(!hasAnyChanges) {
				throw ErrorTicket.builder().withError(ErrorCodes.ArgumentWrong)
				.withDetails("no account changes provided")
				.build();
			}

			Future<Void> result = Future.succeededFuture();
			if(hasDetailsChanges) {
				result = result.compose(ignored -> modifyDetails(request));
			}

			if(hasBusinessDetailsChanges) {
				result = result.compose(ignored -> modifyBusinessDetails(request));
			}

			return result;
		});
	}

	/**
	 * attachUser.
	 */
	@Override
	public Future<Void> attachUser(ModifyRequest request) {
		logger.debug("attachUser({})", request);
		return getInternal(request, false).compose(account -> attachUser((AccountUserAssociationChangeSet)request));
	}

	/**
	 * detachUser.
	 */
	@Override
	public Future<Void> detachUser(ModifyRequest request) {
		logger.debug("detachUser({})", request);
		return getInternal(request, false).compose(account -> detachUser((AccountUserAssociationChangeSet)request));
	}

	/**
	 * changeBalance.
	 */
	@Override
	public Future<Void> changeBalance(ModifyRequest request) {
		logger.debug("changeBalance({})", request);
		return getInternal(request, false).compose(account -> changeBalance((AccountBalanceChangeSet)request));
	}

	/**
	 * changeOwner.
	 */
	@Override
	public Future<Void> changeOwner(ModifyRequest request) {
		logger.debug("changeOwner({})", request);
		return getInternal(request, false)
				.compose(account -> changeOwner(account, (AccountOwnerChangeSet)request));
	}
	
	/**
	 * changeServicePackage.
	 */
	@Override
	public Future<Void> changeServicePackage(ModifyRequest request) {
		logger.debug("changeServicePackage({})", request);
		Long accountId = request.getAccountId();
		if(null == accountId) {
			accountId = request.getId();
		}
		if(null == accountId) {
			throw ErrorTicket.builder().withError(ErrorCodes.ArgumentWrong)
				.withDetails("accountId is required")
				.build();
		}
		var identity = AccountIdentity.builder().withId(accountId).build();
		var builder = ModifyRequest.builderFrom(request).withAccountId(accountId);
		return getInternal(identity, false)
				.compose(account -> changeServicePackage(account, (AccountServicePackageChangeSet)builder.build()));
	}
	
	/**
	 * getBusinessDetails.
	 */
	@Override
	public Future<AccountBusinessDetails> getBusinessDetails(AccountIdentity identity) {
		logger.debug("getBusinessDetails({})", identity);
		return getInternal(identity, false).compose(account ->
			SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.GET_BUSINESS_DETAILS_TEMPLATE)
				.mapFrom(DaoMappers.GET_BUSINESS_DETAILS_PARAMS)
				.mapTo(DaoMappers.BUSINESS_DETAILS_ROW)
				.execute(identity)
				.map(rs -> rs.stream().findFirst()
					.orElseThrow(() -> new NotFoundException("account business details")))
		);
	}

	/**
	 * getBalance.
	 */
	@Override
	public Future<Integer> getBalance(AccountIdentity identity) {
		logger.debug("getBalance({})", identity);
		return 
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.GET_BALANCE_TEMPLATE)
			.mapFrom(DaoMappers.GET_BALANCE_PARAMS)
			.execute(identity)
			.map(rs -> rs.stream().findFirst()
				.map(row -> {
					Object value = row.getValue(0);
					if(value instanceof Number number) {
						return number.intValue();
					}
					return value == null ? null : Integer.valueOf(value.toString());
				})
				.orElseThrow(() -> new NotFoundException("account")));
	}

	/**
	 * findByServicePackageExpiry.
	 */
	@Override
	public Future<List<Account>> findByServicePackageExpiry(SearchRequest request) {
		logger.debug("findByServicePackageExpiry({})", request);
		return SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.LIST_EXPIRED_SERVICE_PACKAGE_TEMPLATE)
			.mapFrom(DaoMappers.EXPIRED_SERVICE_PACKAGE_PARAMS)
			.mapTo(DaoMappers.ACCOUNT_ROW)
			.execute(request)
			.map(rs -> rs.stream().toList());
	}

	/**
	 * findByUserAndProduct.
	 */
	@Override
	public Future<List<Account>> findByUserAndProduct(SearchRequest request) {
		logger.debug("findByUserAndProduct({})", request);
		return SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.FIND_BY_USER_AND_PRODUCT_TEMPLATE)
			.mapFrom(DaoMappers.FIND_BY_USER_AND_PRODUCT_PARAMS)
			.mapTo(DaoMappers.ACCOUNT_ROW)
			.execute(request)
			.map(rs -> rs.stream().toList());
	}

	/**
	 * modifyDetails.
	 */
	private Future<Void> modifyDetails(AccountDetailsChangeSet request) {
		logger.debug("modifyDetails");
		ErrorTickets.checkAnyNotNull(Arrays.asList(
				request.getDetails(), 
				request.getTimezoneId(), 
				request.getName(), 
				request.getIsActive(),
				request.getIncludeDepositBounds(), 
				request.getMinDepositAmount(), 
				request.getMaxDepositAmount()), 
				"no changes provided to modify details");
		return 
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.CHANGE_DETAILS_TEMPLATE)
		.mapFrom(DaoMappers.CHANGE_DETAILS_PARAMS)
		.execute(request)
		.mapEmpty();
	}

	/**
	 * modifyBusinessDetails.
	 */
	private Future<Void> modifyBusinessDetails(AccountBusinessDetailsChangeSet request) {
		logger.debug("modifyBusinessDetails");
		ErrorTickets.checkAnyNotNull(Arrays.asList(
				request.getBusinessCompanyName(),
				request.getBusinessFirstName(),
				request.getBusinessLastName(),
				request.getBusinessAddress1(),
				request.getBusinessAddress2(),
				request.getBusinessCity(),
				request.getBusinessCountryId(),
				request.getBusinessStateId(),
				request.getBusinessPostalCode(),
				request.getBusinessPhone1()),
				"no changes provided to modify business details");
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.CHANGE_BUSINESS_DETAILS_TEMPLATE)
		.mapFrom(DaoMappers.CHANGE_BUSINESS_DETAILS_PARAMS)
		.execute(request)
		.mapEmpty();
	}

	/**
	 * attachUser.
	 */
	private Future<Void> attachUser(AccountUserAssociationChangeSet request) {
		logger.debug("attachUser");
		ErrorTickets.checkAnyNotNull(Arrays.asList(
				request.getSourceId(),
				request.getUserId(),
				request.getId(),
				request.getBackofficeUserId()),
				"no changes provided to attach user");
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.ATTACH_USER_TEMPLATE)
		.mapFrom(DaoMappers.ATTACH_USER_PARAMS)
		.execute(request)
		.mapEmpty();
	}

	/**
	 * detachUser.
	 */
	private Future<Void> detachUser(AccountUserAssociationChangeSet request) {
		logger.debug("detachUser");
		ErrorTickets.checkAnyNotNull(Arrays.asList(
				request.getSourceId(),
				request.getUserId(),
				request.getId(),
				request.getBackofficeUserId()),
				"no changes provided to detach user");
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.DETACH_USER_TEMPLATE)
		.mapFrom(DaoMappers.DETACH_USER_PARAMS)
		.execute(request)
		.mapEmpty();
	}

	/**
	 * changeOwner.
	 */
	private Future<Void> changeOwner(Account account, AccountOwnerChangeSet request) {
		logger.debug("changeOwner");
		
		ErrorTickets.checkAnyNotNull(Arrays.asList(
				request.getSourceId(),
				request.getOwnerId(),
				request.getId(),
				request.getBackofficeUserId()),
				"no changes provided to change owner");
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.CHANGE_OWNER_TEMPLATE)
		.mapFrom(DaoMappers.CHANGE_OWNER_PARAMS)
		.execute(request)
		.mapEmpty();
	}
	
	/**
	 * changeServicePackage.
	 */
	private Future<Void> changeServicePackage(Account account, AccountServicePackageChangeSet request) {
		logger.debug("changeServicePackage");
		ErrorTickets.checkAnyNotNull(Arrays.asList(
				request.getAccountId(),
				request.getServicePackageId(),
				request.getExpiryAt(),
				request.getMaxUsers()),
				"no changes provided to change service package");
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.CHANGE_SERVICE_PACKAGE_TEMPLATE)
		.mapFrom(DaoMappers.CHANGE_SERVICE_PACKAGE_PARAMS)
		.execute(request)
		.mapEmpty();
	}

	/**
	 * changeBalance.
	 */
	private Future<Void> changeBalance(AccountBalanceChangeSet request) {
		logger.debug("changeBalance");
		ErrorTickets.checkAnyNotNull(Arrays.asList(
				request.getBackofficeUserId(),
				request.getDetails(),
				request.getAmount(),
				request.getOperationType(),
				request.getSourceId(),
				request.getSessionId(),
				request.getGeoCountryCode(),
				request.getClientIp()),
				"no changes provided to change balance");
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.CHANGE_BALANCE_TEMPLATE)
		.mapFrom(DaoMappers.CHANGE_BALANCE_PARAMS)
		.execute(request)
		.mapEmpty();
	}
}
