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

	public String getClientIp() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getCountryId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getSourceId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getProductId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getServicePackageId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getBackofficeUserId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
