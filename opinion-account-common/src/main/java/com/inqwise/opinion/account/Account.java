package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class Account {
	public static class Keys {
	}
	
	public Account(JsonObject json) {
			
	}
	
	public JsonObject toJson() {
		var json = new JsonObject();
		return json;
	}
}
