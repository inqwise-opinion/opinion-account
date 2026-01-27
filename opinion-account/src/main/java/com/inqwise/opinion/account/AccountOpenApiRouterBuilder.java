package com.inqwise.opinion.account;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.inqwise.errors.ErrorCodes;
import com.inqwise.errors.ErrorTicket;
import com.inqwise.errors.ErrorTickets;
import com.inqwise.opinion.common.RestApiServerOptions;
import com.inqwise.opinion.common.Uid;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RequestBody;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.HttpException;
import io.vertx.ext.web.openapi.router.RouterBuilder;
import io.vertx.openapi.contract.OpenAPIContract;
import io.vertx.openapi.validation.ValidatedRequest;

/**
 * Builds a Router instance backed by the OpenAPI description and wires handlers.
 */
/**
 * AccountOpenApiRouterBuilder.
 */
public final class AccountOpenApiRouterBuilder {
		
	private static final String CREATE_ACCOUNT = "createAccount";

	private static final String ACCOUNTS_YAML = "accounts.yaml";

	/**
	 * getLogger.
	 */
	private static final Logger logger = LogManager.getLogger(AccountOpenApiRouterBuilder.class);

	@Inject
	private Vertx vertx;
	@Inject
	private AccountService accountService;
	@Inject 
	private RestApiServerOptions options;
	

	/**
	 * Constructs AccountOpenApiRouterBuilder.
	 */
	@Inject
	private AccountOpenApiRouterBuilder() {	
	}

	/**
	 * createRouter.
	 */
	public Future<Router> createRouter() {
		return getContract()
			.map(contract -> RouterBuilder.create(vertx, contract))
			.map(this::registerHandlers)
			.map(RouterBuilder::createRouter)
			.map(this::customizeRouter);
	}

	/**
	 * getContract.
	 */
	private Future<OpenAPIContract> getContract() {
		return OpenAPIContract.from(vertx, ACCOUNTS_YAML);
	}

	/**
	 * registerHandlers.
	 */
	private RouterBuilder registerHandlers(RouterBuilder routerBuilder) {

		// create
		routerBuilder.getRoute(CREATE_ACCOUNT).addHandler(this::createAccount);
		
		// show
		routerBuilder.getRoute("showAccountById").addHandler(this::showAccountByUid);
		
		// show balance
		routerBuilder.getRoute("showAccountBalanceById").addHandler(this::showAccountBalanceByUid);
		
		// modify
		routerBuilder.getRoute("modifyAccountById").addHandler(this::modifyAccountByUid);
		
		// change balance
		routerBuilder.getRoute("changeAccountBalance").addHandler(this::changeAccountBalance);
		
		// change owner
		routerBuilder.getRoute("changeAccountOwner").addHandler(this::changeAccountOwner);
		
		// attach user
		routerBuilder.getRoute("attachAccountUser").addHandler(this::attachAccountUser);
		
		// detach user
		routerBuilder.getRoute("detachAccountUser").addHandler(this::detachAccountUser);
		
		// change service package
		routerBuilder.getRoute("changeAccountServicePackage").addHandler(this::changeAccountServicePackage);
		
		// business details
		routerBuilder.getRoute("getAccountBusinessDetails").addHandler(this::getAccountBusinessDetails);

		// delete
		routerBuilder.getRoute("deleteAccountById").addHandler(this::deleteAccountByUid);
		
		// search
		routerBuilder.getRoute("searchAccounts").addHandler(this::searchAccounts);
		
		// find by User and Product
		routerBuilder.getRoute("findAccountsByUserProduct").addHandler(this::findByUserProduct);
		
		// find by ServicePackage expire
		routerBuilder.getRoute("findAccountsByServicePackageExpire").addHandler(this::findByServicePackageExpire);
		
		// status
		routerBuilder.getRoute("status").addHandler(this::getStatus);
		
		return routerBuilder;
	}

	/**
	 * createAccount.
	 */
	private void createAccount(RoutingContext context) {
		logger.debug("cresteAccount");
		ValidatedRequest validatedRequest =
				context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);

		var body = validatedRequest.getBody();
		CreateRequest request = new CreateRequest(Objects.requireNonNull(body, "body is mandatory").getJsonObject());
		accountService.create(request)
			.compose(res -> {
				context.response().setStatusCode(201);
				return context.json(res.toJson());
			})
			.onFailure(context::fail);
		
	}

	/**
	 * getStatus.
	 */
	private void getStatus(RoutingContext context) {
		logger.debug("getStatus");
		accountService.status()
			.compose(res -> context.json(res))
			.onFailure(context::fail);
		
	}

	/**
	 * searchAccounts.
	 */
	private void searchAccounts(RoutingContext context) {
		logger.debug("searchAccounts");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var request = new SearchRequest(validatedRequest.getBody().getJsonObject());
		
		accountService.search(request)
			.compose(res -> context.json(res.toJson()))
			.onFailure(context::fail);
	}
	
	/**
	 * findByServicePackageExpire.
	 */
	private void findByServicePackageExpire(RoutingContext context) {
		logger.debug("findByServicePackageExpiry");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var request = new SearchRequest(validatedRequest.getBody().getJsonObject());
		
		accountService.findByServicePackageExpiry(request)
			.compose(res -> context.json(res.stream().map(Account::toJson)
					.collect(JsonArray::new, JsonArray::add, JsonArray::addAll)))
			.onFailure(context::fail);
	}
	
	/**
	 * findByUserProduct.
	 */
	private void findByUserProduct(RoutingContext context) {
		logger.debug("findByUserProduct");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var request = new SearchRequest(validatedRequest.getBody().getJsonObject());
		
		accountService.findByUserAndProduct(request)
			.compose(res -> context.json(res.stream().map(Account::toJson)
					.collect(JsonArray::new, JsonArray::add, JsonArray::addAll)))
			.onFailure(context::fail);
	}

	/**
	 * deleteAccountByUid.
	 */
	private void deleteAccountByUid(RoutingContext context) {
		logger.debug("deleteAccountByUid");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = parseUid(uidToken);
		var identity = AccountIdentity.builder()
				.withUidPrefix(uid.getPrefix())
				.withId(uid.getId())
				.build();
		
		accountService.delete(identity)
			.compose(res -> context.response().setStatusCode(204).end())
			.onFailure(context::fail);
	}

	/**
	 * modifyAccountByUid.
	 */
	private void modifyAccountByUid(RoutingContext context) {
		logger.debug("modifyAccountByUid");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = parseUid(uidToken);
		var request = new ModifyRequest(validatedRequest.getBody().getJsonObject());
		var builder = ModifyRequest.builderFrom(request);
		builder
		.withId(uid.getId())
		.withUidPrefix(uid.getPrefix());
		
		accountService.modify(builder.build())
			.compose(res -> context.response().setStatusCode(204).end())
			.onFailure(context::fail);
	}
	
	/**
	 * parseUid.
	 */
	static Uid parseUid(String uidToken) {
		ErrorTickets.checkNotNull(uidToken, "uid token is mandatory");
		Uid uid;
		
		try {
			uid = Uid.parse(uidToken);
		} catch (IndexOutOfBoundsException e) {
			throw ErrorTicket.builder()
			.withError(ErrorCodes.ArgumentWrong)
			.withDetails("account uid")
			.build();
		} catch(Throwable t) {
			throw t;
		}
		return uid;
	}
	
	/**
	 * attachAccountUser.
	 */
	private void attachAccountUser(RoutingContext context) {
		logger.debug("attachAccountUser");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = parseUid(uidToken);
		var request = new ModifyRequest(validatedRequest.getBody().getJsonObject());
		var builder = ModifyRequest.builderFrom(request);
		builder
		.withId(uid.getId())
		.withUidPrefix(uid.getPrefix());
		
		accountService.attachUser(builder.build())
			.compose(res -> context.response().setStatusCode(204).end())
			.onFailure(context::fail);
	}
	
	/**
	 * detachAccountUser.
	 */
	private void detachAccountUser(RoutingContext context) {
		logger.debug("detachAccountUser");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = parseUid(uidToken);
		var request = new ModifyRequest(validatedRequest.getBody().getJsonObject());
		var builder = ModifyRequest.builderFrom(request);
		builder
		.withId(uid.getId())
		.withUidPrefix(uid.getPrefix());
		
		accountService.detachUser(builder.build())
			.compose(res -> context.response().setStatusCode(204).end())
			.onFailure(context::fail);
	}
	
	/**
	 * changeAccountOwner.
	 */
	private void changeAccountOwner(RoutingContext context) {
		logger.debug("changeAccountOwner");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = parseUid(uidToken);
		var request = new ModifyRequest(validatedRequest.getBody().getJsonObject());
		var builder = ModifyRequest.builderFrom(request);
		builder
		.withId(uid.getId())
		.withUidPrefix(uid.getPrefix());
		
		accountService.changeOwner(builder.build())
			.compose(res -> context.response().setStatusCode(204).end())
			.onFailure(context::fail);
	}
	
	/**
	 * changeAccountBalance.
	 */
	private void changeAccountBalance(RoutingContext context) {
		logger.debug("changeAccountBalance");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = parseUid(uidToken);
		var request = new ModifyRequest(validatedRequest.getBody().getJsonObject());
		var builder = ModifyRequest.builderFrom(request);
		builder
		.withId(uid.getId())
		.withUidPrefix(uid.getPrefix());
		
		accountService.changeBalance(builder.build())
			.compose(res -> context.response().setStatusCode(204).end())
			.onFailure(context::fail);
	}
	
	/**
	 * changeAccountServicePackage.
	 */
	private void changeAccountServicePackage(RoutingContext context) {
		logger.debug("changeAccountServicePackage");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = Uid.parse(uidToken);
		var request = new ModifyRequest(validatedRequest.getBody().getJsonObject());
		var builder = ModifyRequest.builderFrom(request);
		builder
		.withAccountId(uid.getId())
		.withUidPrefix(uid.getPrefix());
		
		accountService.changeServicePackage(builder.build())
			.compose(res -> context.response().setStatusCode(204).end())
			.onFailure(context::fail);
	}
	
	/**
	 * getAccountBusinessDetails.
	 */
	private void getAccountBusinessDetails(RoutingContext context) {
		logger.debug("getAccountBusinessDetails");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = Uid.parse(uidToken);
		var identity = AccountIdentity.builder()
				.withUidPrefix(uid.getPrefix())
				.withId(uid.getId())
				.build();
		
		accountService.getBusinessDetails(identity)
			.compose(res -> context.json(res.toJson()))
			.onFailure(context::fail);
	}

	/**
	 * showAccountByUid.
	 */
	private void showAccountByUid(RoutingContext context) {
		logger.debug("showAccountByUid");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = parseUid(uidToken);
		var identity = AccountIdentity.builder()
				.withUidPrefix(uid.getPrefix())
				.withId(uid.getId())
				.build();
		
		accountService.get(identity)
			.compose(res -> context.json(res.toJson()))
			.onFailure(context::fail);
	}
	
	/**
	 * showAccountBalanceByUid.
	 */
	private void showAccountBalanceByUid(RoutingContext context) {
		logger.debug("showAccountBalanceByUid");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = parseUid(uidToken);
		var identity = AccountIdentity.builder()
				.withUidPrefix(uid.getPrefix())
				.withId(uid.getId())
				.build();
		
		accountService.getBalance(identity)
			.compose(res -> context.end(res.toString()))
			.onFailure(context::fail);
	}

	/**
	 * customizeRouter.
	 */
	private Router customizeRouter(Router router) {
		router.route()
		.failureHandler(new ExceptionNormalizerHandler())
		.failureHandler(new ExceptionLoggerHandler(Objects.requireNonNullElse(options.getLogErrorTickets(), false)))
		.failureHandler(new HttpErrorResponseHandler(Objects.requireNonNullElse(options.getPrintStacktrace(), false), Objects.requireNonNullElse(options.getSanitizeUnexpectedErrors(), false)));
		
		return router;
	}
}
