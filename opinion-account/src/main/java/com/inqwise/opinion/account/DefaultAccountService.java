package com.inqwise.opinion.account;

import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Predicates;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.inqwise.errors.Bug;
import com.inqwise.errors.NotFoundException;
import com.inqwise.errors.Throws;
import com.inqwise.opinion.common.UidPrefixGenerator;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.templates.SqlTemplate;

class DefaultAccountService implements AccountService {
	private static final Logger logger = LogManager.getLogger(DefaultAccountService.class);
	
	@Inject
	private UidPrefixGenerator uidPrefixGenerator;
	
	@Inject
	private DefaultAccountService() {}
	
	@Inject
	private Provider<Pool> pooledClientProvider;
	
	@Override
	public Future<Void> close() {
		return Future.succeededFuture();
	}

	@Override
	public Future<JsonObject> status() {
		return Future.succeededFuture(new JsonObject());
	}

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
	
	@Override
	public Future<Account> get(AccountIdentity identity) {
		logger.debug("get({})", identity);
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.GET_TEMPLATE)
		.mapFrom(DaoMappers.IDENTITY_PARAMS)
		.mapTo(DaoMappers.ACCOUNT_ROW)
		.execute(identity)
		.map(rs -> {
			return rs.stream()
					.filter(a -> (null == identity.getUidPrefix() || a.getUidToken().equals(identity.getUidToken())))
					.findAny()
					.orElseThrow(() -> new NotFoundException("account"));
		});
	}

	@Override
	public Future<SearchResult> search(SearchRequest request) {
		logger.debug("search({})", request);
		var template =
		SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.SEARCH_TEMPLATE)
		.mapFrom(DaoMappers.SEARCH_REQUEST)
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

	@Override
	public Future<Void> delete(AccountIdentity identity) {
		logger.debug("delete({})", identity);
		return get(identity).compose(account -> {
			return 
			SqlTemplate.forQuery(pooledClientProvider.get(), DaoMappers.DELETE_TEMPLATE)
			.mapFrom(DaoMappers.IDENTITY_PARAMS)
			.mapTo(DaoMappers.ACCOUNT_ROW)
			.execute(identity)
			.map(rs -> {
				if(rs.rowCount() == 0) {
					throw new Bug("account #{} not deleted", identity.getId());
				}
				return (Void)null;
			});
		});
	}

	@Override
	public Future<Void> modify(ModifyRequest request) {
		//return get(AccountIdentity.builder().withId(request.getId()).withUidPrefix(request.getUidPrefix()).build());
				
		throw Throws.notImplemented("modify");
	}
}
