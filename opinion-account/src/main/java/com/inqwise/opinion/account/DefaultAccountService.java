package com.inqwise.opinion.account;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.inqwise.errors.NotFoundException;
import com.inqwise.errors.Throws;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.templates.SqlTemplate;

class DefaultAccountService implements AccountService {

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
		throw Throws.notImplemented("create");
	}
	
	@Override
	public Future<Account> get(AccountIdentity identity) {
		
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), "CALL getAccount(#{id})")
		.mapFrom(Mappers.ACCOUNT_IDENTITY)
		.mapTo(Mappers.ACCOUNT_ROW)
		.execute(identity)
		.map(rs -> {
			return rs.stream().findFirst().orElseThrow(() -> new NotFoundException("account not found"));
		});
	}

	@Override
	public Future<SearchResult> search(SearchRequest request) {
		throw Throws.notImplemented("search");
		
	}

	@Override
	public Future<Void> delete(AccountIdentity identity) {
		throw Throws.notImplemented("delete");
	}

	@Override
	public Future<Void> modify(ModifyRequest request) {
		throw Throws.notImplemented("modify");
	}
}
