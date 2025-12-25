package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

@ProxyGen
public interface AccountService {
	String SERVICE_NAME = "account-eb-service";
	String SERVICE_ADDRESS = "service.account";
	
	@ProxyClose
	Future<Void> close();
	
	Future<JsonObject> status();
	
	Future<CreateResult> create(CreateRequest request);
	
	Future<Account> get(AccountIdentity identity);
	
	Future<SearchResult> search(SearchRequest request);
	
	Future<Void> delete(AccountIdentity identity);
	
	Future<Void> modify(ModifyRequest request);
}
