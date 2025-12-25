package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class AccountIdentity {

	public static class Keys {
	}
	
	public AccountIdentity(JsonObject json) {
		
	}
	
	public JsonObject toJson() {
		var json = new JsonObject();
	
		return json;
	}
}
