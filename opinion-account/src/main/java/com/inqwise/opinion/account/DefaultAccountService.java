package com.inqwise.opinion.account;

import com.google.inject.Inject;
import com.inqwise.errors.Throws;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

class DefaultAccountService implements AccountService {

	@Inject
	private DefaultAccountService() {}
	
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
		throw Throws.notImplemented("get");
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
