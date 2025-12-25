package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class ModifyRequest {
	
	public static class Keys {
	}

	public ModifyRequest(JsonObject json) {
		
	}

	public JsonObject toJson() {
		var json = new JsonObject();

		return json;
	}
}
