package com.inqwise.opinion.account;

import java.util.List;

import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

/**
 * AccountService.
 */
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
	
	Future<Void> attachUser(ModifyRequest request);
	
	Future<Void> detachUser(ModifyRequest request);
	
	Future<Void> changeBalance(ModifyRequest request);
	
	Future<Void> changeOwner(ModifyRequest request);

	Future<Void> changeServicePackage(ModifyRequest request);
	
	Future<AccountBusinessDetails> getBusinessDetails(AccountIdentity identity);
	
	Future<Integer> getBalance(AccountIdentity identity);
	
	Future<List<Account>> findByServicePackageExpiry(SearchRequest request);
	
	Future<List<Account>> findByUserAndProduct(SearchRequest request);
	
}
