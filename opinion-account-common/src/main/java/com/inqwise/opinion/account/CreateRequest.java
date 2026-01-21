package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class CreateRequest {
	
	private String clientIp;
	private Integer countryId;
	private String name;
	private Integer sourceId;
	private Integer productId;
	private Integer servicePackageId;
	private Long userId;
	private Long backofficeUserId;
	
	public static class Keys {
		public static final String CLIENT_IP = "client_ip";
		public static final String COUNTRY_ID = "country_id";
		public static final String NAME = "name";
		public static final String SOURCE_ID = "source_id";
		public static final String PRODUCT_ID = "product_id";
		public static final String SERVICE_PACKAGE_ID = "service_package_id";
		public static final String USER_ID = "user_id";
		public static final String BACKOFFICE_USER_ID = "bo_user_id";
	}

	public CreateRequest(JsonObject json) {
		clientIp = json.getString(Keys.CLIENT_IP);
		countryId = json.getInteger(Keys.COUNTRY_ID);
		name = json.getString(Keys.NAME);
		sourceId = json.getInteger(Keys.SOURCE_ID);
		productId = json.getInteger(Keys.PRODUCT_ID);
		servicePackageId = json.getInteger(Keys.PRODUCT_ID);
		userId = json.getLong(Keys.USER_ID);
		backofficeUserId = json.getLong(Keys.BACKOFFICE_USER_ID);
	}

	public JsonObject toJson() {
		var json = new JsonObject();
		if(null != clientIp) {
			json.put(Keys.CLIENT_IP, clientIp);
		}
		
		if(null != countryId) {
			json.put(Keys.COUNTRY_ID, countryId);
		}
		
		if(null != name) {
			json.put(Keys.NAME, name);
		}
		
		if(null != sourceId) {
			json.put(Keys.SOURCE_ID, sourceId);
		}
		
		if(null != productId) {
			json.put(Keys.PRODUCT_ID, productId);
		}
		
		if(null != servicePackageId) {
			json.put(Keys.SERVICE_PACKAGE_ID, servicePackageId);
		}
		
		if(null != userId) {
			json.put(Keys.USER_ID, userId);
		}
		
		if(null != backofficeUserId) {
			json.put(Keys.BACKOFFICE_USER_ID, backofficeUserId);
		}
		
		return json;
	}

	public String getClientIp() {
		return clientIp;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public String getName() {
		return name;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public Integer getProductId() {
		return productId;
	}

	public Integer getServicePackageId() {
		return servicePackageId;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getBackofficeUserId() {
		return backofficeUserId;
	}
}
