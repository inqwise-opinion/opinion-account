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
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), "CALL createUserAccount(#{$client_ip}, #{$country_id}, #{$account_name}, #{$uid_prefix}, #{$source_id}, #{$product_id}, #{$service_package_id}, #{$user_id}, #{$backoffice_user_id})")
		.mapFrom(Mappers.CREATE_REQUEST)
		.mapTo(Mappers.CREATE_RESULT_ROW)
		.execute(request)
		.map(rs -> {
			return rs.stream().findFirst().orElseThrow(() -> new NotFoundException("account not created"));
		});
	}
	
	@Override
	public Future<Account> get(AccountIdentity identity) {
		
		return
		SqlTemplate.forQuery(pooledClientProvider.get(), "CALL getAccount(#{id})")
		.mapFrom(Mappers.IDENTITY)
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
