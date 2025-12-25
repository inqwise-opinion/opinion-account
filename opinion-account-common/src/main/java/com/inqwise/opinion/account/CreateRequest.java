package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class CreateRequest {
	
	public static class Keys {
	}

	public CreateRequest(JsonObject json) {
		
	}

	public JsonObject toJson() {
		var json = new JsonObject();

		return json;
	}
}
