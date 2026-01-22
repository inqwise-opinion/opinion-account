package com.inqwise.opinion.account;

import com.google.common.base.MoreObjects;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class ModifyRequest implements AccountIdentifiable, AccountDetailsChangeSet, AccountBusinessDetailsChangeSet, AccountUserAssociationChangeSet, AccountOwnerChangeSet, AccountBalanceChangeSet {
	
	private String uidPrefix;
	private Long id;
	private Long sourceId;
	private Long backofficeUserId;
	private Long userId;
	private Long ownerId;
	private Long productId;
	private String businessCompanyName;
	private String businessFirstName;
	private String businessLastName;
	private String businessAddress1;
	private String businessAddress2;
	private String businessCity;
	private Integer businessCountryId;
	private Integer businessStateId;
	private String businessPostalCode;
	private String businessPhone1;
	private String comments;
	private Integer timezoneId;
	private String accountName;
	private Boolean isActive;
	private Boolean includeDepositBounds;
	private Integer minDepositAmount;
	private Integer maxDepositAmount;
	private Integer amount;
	private Integer accopTypeId;
	private String sourceGuid;
	private String sessionId;
	private String geoCountryCode;
	private String clientIp;

	private ModifyRequest(Builder builder) {
		this.uidPrefix = builder.uidPrefix;
		this.id = builder.id;
		this.sourceId = builder.sourceId;
		this.backofficeUserId = builder.backofficeUserId;
		this.userId = builder.userId;
		this.ownerId = builder.ownerId;
		this.productId = builder.productId;
		this.businessCompanyName = builder.businessCompanyName;
		this.businessFirstName = builder.businessFirstName;
		this.businessLastName = builder.businessLastName;
		this.businessAddress1 = builder.businessAddress1;
		this.businessAddress2 = builder.businessAddress2;
		this.businessCity = builder.businessCity;
		this.businessCountryId = builder.businessCountryId;
		this.businessStateId = builder.businessStateId;
		this.businessPostalCode = builder.businessPostalCode;
		this.businessPhone1 = builder.businessPhone1;
		this.comments = builder.comments;
		this.timezoneId = builder.timezoneId;
		this.accountName = builder.accountName;
		this.isActive = builder.isActive;
		this.includeDepositBounds = builder.includeDepositBounds;
		this.minDepositAmount = builder.minDepositAmount;
		this.maxDepositAmount = builder.maxDepositAmount;
		this.amount = builder.amount;
		this.accopTypeId = builder.accopTypeId;
		this.sourceGuid = builder.sourceGuid;
		this.sessionId = builder.sessionId;
		this.geoCountryCode = builder.geoCountryCode;
		this.clientIp = builder.clientIp;
	}

	public static class Keys {
		public static final String UID_PREFIX = "prefix";
		public static final String ID = "id";
		public static final String SOURCE_ID = "source_id";
		public static final String BACKOFFICE_USER_ID = "bo_user_id";
		public static final String USER_ID = "user_id";
		public static final String OWNER_ID = "owner_id";
		public static final String PRODUCT_ID = "product_id";	
		public static final String BUSINESS_COMPANY_NAME = "business_company_name";
		public static final String BUSINESS_FIRST_NAME = "business_first_name";
		public static final String BUSINESS_LAST_NAME = "business_last_name";
		public static final String BUSINESS_ADDRESS1 = "business_address1";
		public static final String BUSINESS_ADDRESS2 = "business_address2";
		public static final String BUSINESS_CITY = "business_city";
		public static final String BUSINESS_COUNTRY_ID = "business_country_id";
		public static final String BUSINESS_STATE_ID = "business_state_id";
		public static final String BUSINESS_POSTAL_CODE = "business_postal_code";
		public static final String BUSINESS_PHONE1 = "business_phone1";
		public static final String COMMENTS = "comments";
		public static final String TIMEZONE_ID = "timezone_id";
		public static final String ACCOUNT_NAME = "account_name";
		public static final String IS_ACTIVE = "is_active";
		public static final String INCLUDE_DEPOSIT_BOUNDS = "include_deposit_bounds";
		public static final String MIN_DEPOSIT_AMOUNT = "min_deposit_amount";
		public static final String MAX_DEPOSIT_AMOUNT = "max_deposit_amount";
		public static final String AMOUNT = "amount";
		public static final String ACCOP_TYPE_ID = "accop_type_id";
		public static final String SOURCE_GUID = "source_guid";
		public static final String SESSION_ID = "session_id";
		public static final String GEO_COUNTRY_CODE = "geo_country_code";
		public static final String CLIENT_IP = "client_ip";
	}
	
	public ModifyRequest(JsonObject json) {
		uidPrefix = json.getString(Keys.UID_PREFIX);
		id = json.getLong(Keys.ID);
		sourceId = json.getLong(Keys.SOURCE_ID);
		backofficeUserId = json.getLong(Keys.BACKOFFICE_USER_ID);
		userId = json.getLong(Keys.USER_ID);
		ownerId = json.getLong(Keys.OWNER_ID);
		productId = json.getLong(Keys.PRODUCT_ID);
		businessCompanyName = json.getString(Keys.BUSINESS_COMPANY_NAME);
		businessFirstName = json.getString(Keys.BUSINESS_FIRST_NAME);
		businessLastName = json.getString(Keys.BUSINESS_LAST_NAME);
		businessAddress1 = json.getString(Keys.BUSINESS_ADDRESS1);
		businessAddress2 = json.getString(Keys.BUSINESS_ADDRESS2);
		businessCity = json.getString(Keys.BUSINESS_CITY);
		businessCountryId = json.getInteger(Keys.BUSINESS_COUNTRY_ID);
		businessStateId = json.getInteger(Keys.BUSINESS_STATE_ID);
		businessPostalCode = json.getString(Keys.BUSINESS_POSTAL_CODE);
		businessPhone1 = json.getString(Keys.BUSINESS_PHONE1);
		comments = json.getString(Keys.COMMENTS);
		timezoneId = json.getInteger(Keys.TIMEZONE_ID);
		accountName = json.getString(Keys.ACCOUNT_NAME);
		isActive = json.getBoolean(Keys.IS_ACTIVE);
		includeDepositBounds = json.getBoolean(Keys.INCLUDE_DEPOSIT_BOUNDS);
		minDepositAmount = toInteger(json.getValue(Keys.MIN_DEPOSIT_AMOUNT));
		maxDepositAmount = toInteger(json.getValue(Keys.MAX_DEPOSIT_AMOUNT));
		amount = toInteger(json.getValue(Keys.AMOUNT));
		accopTypeId = json.getInteger(Keys.ACCOP_TYPE_ID);
		sourceGuid = json.getString(Keys.SOURCE_GUID);
		sessionId = json.getString(Keys.SESSION_ID);
		geoCountryCode = json.getString(Keys.GEO_COUNTRY_CODE);
		clientIp = json.getString(Keys.CLIENT_IP);
	}
	
	public JsonObject toJson() {
		var json = new JsonObject();
		if(null != uidPrefix) {
			json.put(Keys.UID_PREFIX, uidPrefix);
		}
		
		if(null != id) {
			json.put(Keys.ID, id);
		}
		
		if(null != sourceId) {
			json.put(Keys.SOURCE_ID, sourceId);
		}
		
		if(null != backofficeUserId) {
			json.put(Keys.BACKOFFICE_USER_ID, backofficeUserId);
		}
		
		if(null != userId) {
			json.put(Keys.USER_ID, userId);
		}
		
		if(null != ownerId) {
			json.put(Keys.OWNER_ID, ownerId);
		}
		
		if(null != productId) {
			json.put(Keys.PRODUCT_ID, productId);
		}

		if(null != businessCompanyName) {
			json.put(Keys.BUSINESS_COMPANY_NAME, businessCompanyName);
		}

		if(null != businessFirstName) {
			json.put(Keys.BUSINESS_FIRST_NAME, businessFirstName);
		}

		if(null != businessLastName) {
			json.put(Keys.BUSINESS_LAST_NAME, businessLastName);
		}

		if(null != businessAddress1) {
			json.put(Keys.BUSINESS_ADDRESS1, businessAddress1);
		}

		if(null != businessAddress2) {
			json.put(Keys.BUSINESS_ADDRESS2, businessAddress2);
		}

		if(null != businessCity) {
			json.put(Keys.BUSINESS_CITY, businessCity);
		}

		if(null != businessCountryId) {
			json.put(Keys.BUSINESS_COUNTRY_ID, businessCountryId);
		}

		if(null != businessStateId) {
			json.put(Keys.BUSINESS_STATE_ID, businessStateId);
		}

		if(null != businessPostalCode) {
			json.put(Keys.BUSINESS_POSTAL_CODE, businessPostalCode);
		}

		if(null != businessPhone1) {
			json.put(Keys.BUSINESS_PHONE1, businessPhone1);
		}
		
		if(null != comments) {
			json.put(Keys.COMMENTS, comments);
		}
		
		if(null != timezoneId) {
			json.put(Keys.TIMEZONE_ID, timezoneId);
		}
		
		if(null != accountName) {
			json.put(Keys.ACCOUNT_NAME, accountName);
		}
		
		if(null != isActive) {
			json.put(Keys.IS_ACTIVE, isActive);
		}
		
		if(null != includeDepositBounds) {
			json.put(Keys.INCLUDE_DEPOSIT_BOUNDS, includeDepositBounds);
		}
		
		if(null != minDepositAmount) {
			json.put(Keys.MIN_DEPOSIT_AMOUNT, minDepositAmount);
		}
		
		if(null != maxDepositAmount) {
			json.put(Keys.MAX_DEPOSIT_AMOUNT, maxDepositAmount);
		}
		
		if(null != amount) {
			json.put(Keys.AMOUNT, amount);
		}
		
		if(null != accopTypeId) {
			json.put(Keys.ACCOP_TYPE_ID, accopTypeId);
		}
		
		if(null != sourceGuid) {
			json.put(Keys.SOURCE_GUID, sourceGuid);
		}
		
		if(null != sessionId) {
			json.put(Keys.SESSION_ID, sessionId);
		}
		
		if(null != geoCountryCode) {
			json.put(Keys.GEO_COUNTRY_CODE, geoCountryCode);
		}
		
		if(null != clientIp) {
			json.put(Keys.CLIENT_IP, clientIp);
		}
		return json;
	}

	private static Integer toInteger(Object value) {
		if(value instanceof Number number) {
			return number.intValue();
		}
		
		if(value instanceof String str && !str.isBlank()) {
			return Integer.valueOf(str);
		}
		
		return null;
	}
	
	public String getUidPrefix() {
		return uidPrefix;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public Long getSourceId() {
		return sourceId;
	}
	
	@Override
	public Long getBackofficeUserId() {
		return backofficeUserId;
	}
	
	@Override
	public Long getUserId() {
		return userId;
	}
	
	@Override
	public Long getOwnerId() {
		return ownerId;
	}
	
	public Long getProductId() {
		return productId;
	}

	@Override
	public String getBusinessCompanyName() {
		return businessCompanyName;
	}

	@Override
	public String getBusinessFirstName() {
		return businessFirstName;
	}

	@Override
	public String getBusinessLastName() {
		return businessLastName;
	}

	@Override
	public String getBusinessAddress1() {
		return businessAddress1;
	}

	@Override
	public String getBusinessAddress2() {
		return businessAddress2;
	}

	@Override
	public String getBusinessCity() {
		return businessCity;
	}

	@Override
	public Integer getBusinessCountryId() {
		return businessCountryId;
	}

	@Override
	public Integer getBusinessStateId() {
		return businessStateId;
	}

	@Override
	public String getBusinessPostalCode() {
		return businessPostalCode;
	}

	@Override
	public String getBusinessPhone1() {
		return businessPhone1;
	}
	
	@Override
	public String getComments() {
		return comments;
	}
	
	@Override
	public Integer getTimezoneId() {
		return timezoneId;
	}
	
	@Override
	public String getAccountName() {
		return accountName;
	}
	
	@Override
	public Boolean getIsActive() {
		return isActive;
	}
	
	@Override
	public Boolean getIncludeDepositBounds() {
		return includeDepositBounds;
	}
	
	@Override
	public Integer getMinDepositAmount() {
		return minDepositAmount;
	}
	
	@Override
	public Integer getMaxDepositAmount() {
		return maxDepositAmount;
	}
	
	@Override
	public Integer getAmount() {
		return amount;
	}
	
	@Override
	public Integer getAccopTypeId() {
		return accopTypeId;
	}
	
	@Override
	public String getSourceGuid() {
		return sourceGuid;
	}
	
	@Override
	public String getSessionId() {
		return sessionId;
	}
	
	@Override
	public String getGeoCountryCode() {
		return geoCountryCode;
	}
	
	@Override
	public String getClientIp() {
		return clientIp;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builderFrom(ModifyRequest modifyRequest) {
		return new Builder(modifyRequest);
	}

	public static final class Builder {
		private String uidPrefix;
		private Long id;
		private Long sourceId;
		private Long backofficeUserId;
		private Long userId;
		private Long ownerId;
		private Long productId;
		private String businessCompanyName;
		private String businessFirstName;
		private String businessLastName;
		private String businessAddress1;
		private String businessAddress2;
		private String businessCity;
		private Integer businessCountryId;
		private Integer businessStateId;
		private String businessPostalCode;
		private String businessPhone1;
		private String comments;
		private Integer timezoneId;
		private String accountName;
		private Boolean isActive;
		private Boolean includeDepositBounds;
		private Integer minDepositAmount;
		private Integer maxDepositAmount;
		private Integer amount;
		private Integer accopTypeId;
		private String sourceGuid;
		private String sessionId;
		private String geoCountryCode;
		private String clientIp;

		private Builder() {
		}

		private Builder(ModifyRequest modifyRequest) {
			this.uidPrefix = modifyRequest.uidPrefix;
			this.id = modifyRequest.id;
			this.sourceId = modifyRequest.sourceId;
			this.backofficeUserId = modifyRequest.backofficeUserId;
			this.userId = modifyRequest.userId;
			this.ownerId = modifyRequest.ownerId;
			this.productId = modifyRequest.productId;
			this.businessCompanyName = modifyRequest.businessCompanyName;
			this.businessFirstName = modifyRequest.businessFirstName;
			this.businessLastName = modifyRequest.businessLastName;
			this.businessAddress1 = modifyRequest.businessAddress1;
			this.businessAddress2 = modifyRequest.businessAddress2;
			this.businessCity = modifyRequest.businessCity;
			this.businessCountryId = modifyRequest.businessCountryId;
			this.businessStateId = modifyRequest.businessStateId;
			this.businessPostalCode = modifyRequest.businessPostalCode;
			this.businessPhone1 = modifyRequest.businessPhone1;
			this.comments = modifyRequest.comments;
			this.timezoneId = modifyRequest.timezoneId;
			this.accountName = modifyRequest.accountName;
			this.isActive = modifyRequest.isActive;
			this.includeDepositBounds = modifyRequest.includeDepositBounds;
			this.minDepositAmount = modifyRequest.minDepositAmount;
			this.maxDepositAmount = modifyRequest.maxDepositAmount;
			this.amount = modifyRequest.amount;
			this.accopTypeId = modifyRequest.accopTypeId;
			this.sourceGuid = modifyRequest.sourceGuid;
			this.sessionId = modifyRequest.sessionId;
			this.geoCountryCode = modifyRequest.geoCountryCode;
			this.clientIp = modifyRequest.clientIp;
		}

		public Builder withUidPrefix(String uidPrefix) {
			this.uidPrefix = uidPrefix;
			return this;
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder withSourceId(Long sourceId) {
			this.sourceId = sourceId;
			return this;
		}
		
		public Builder withBackofficeUserId(Long backofficeUserId) {
			this.backofficeUserId = backofficeUserId;
			return this;
		}
		
		public Builder withUserId(Long userId) {
			this.userId = userId;
			return this;
		}
		
		public Builder withOwnerId(Long ownerId) {
			this.ownerId = ownerId;
			return this;
		}
		
		public Builder withProductId(Long productId) {
			this.productId = productId;
			return this;
		}

		public Builder withBusinessCompanyName(String businessCompanyName) {
			this.businessCompanyName = businessCompanyName;
			return this;
		}

		public Builder withBusinessFirstName(String businessFirstName) {
			this.businessFirstName = businessFirstName;
			return this;
		}

		public Builder withBusinessLastName(String businessLastName) {
			this.businessLastName = businessLastName;
			return this;
		}

		public Builder withBusinessAddress1(String businessAddress1) {
			this.businessAddress1 = businessAddress1;
			return this;
		}

		public Builder withBusinessAddress2(String businessAddress2) {
			this.businessAddress2 = businessAddress2;
			return this;
		}

		public Builder withBusinessCity(String businessCity) {
			this.businessCity = businessCity;
			return this;
		}

		public Builder withBusinessCountryId(Integer businessCountryId) {
			this.businessCountryId = businessCountryId;
			return this;
		}

		public Builder withBusinessStateId(Integer businessStateId) {
			this.businessStateId = businessStateId;
			return this;
		}

		public Builder withBusinessPostalCode(String businessPostalCode) {
			this.businessPostalCode = businessPostalCode;
			return this;
		}

		public Builder withBusinessPhone1(String businessPhone1) {
			this.businessPhone1 = businessPhone1;
			return this;
		}
		
		public Builder withComments(String comments) {
			this.comments = comments;
			return this;
		}
		
		public Builder withTimezoneId(Integer timezoneId) {
			this.timezoneId = timezoneId;
			return this;
		}
		
		public Builder withAccountName(String accountName) {
			this.accountName = accountName;
			return this;
		}
		
		public Builder withIsActive(Boolean isActive) {
			this.isActive = isActive;
			return this;
		}
		
		public Builder withIncludeDepositBounds(Boolean includeDepositBounds) {
			this.includeDepositBounds = includeDepositBounds;
			return this;
		}
		
		public Builder withMinDepositAmount(Integer minDepositAmount) {
			this.minDepositAmount = minDepositAmount;
			return this;
		}
		
		public Builder withMaxDepositAmount(Integer maxDepositAmount) {
			this.maxDepositAmount = maxDepositAmount;
			return this;
		}
		
		public Builder withAmount(Integer amount) {
			this.amount = amount;
			return this;
		}
		
		public Builder withAccopTypeId(Integer accopTypeId) {
			this.accopTypeId = accopTypeId;
			return this;
		}
		
		public Builder withSourceGuid(String sourceGuid) {
			this.sourceGuid = sourceGuid;
			return this;
		}
		
		public Builder withSessionId(String sessionId) {
			this.sessionId = sessionId;
			return this;
		}
		
		public Builder withGeoCountryCode(String geoCountryCode) {
			this.geoCountryCode = geoCountryCode;
			return this;
		}
		
		public Builder withClientIp(String clientIp) {
			this.clientIp = clientIp;
			return this;
		}

		public ModifyRequest build() {
			return new ModifyRequest(this);
		}
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("uidPrefix", uidPrefix).add("id", id)
				.add("sourceId", sourceId).add("backofficeUserId", backofficeUserId)
				.add("userId", userId).add("ownerId", ownerId).add("productId", productId)
				.add("businessCompanyName", businessCompanyName)
				.add("businessFirstName", businessFirstName).add("businessLastName", businessLastName)
				.add("businessAddress1", businessAddress1).add("businessAddress2", businessAddress2)
				.add("businessCity", businessCity).add("businessCountryId", businessCountryId)
				.add("businessStateId", businessStateId).add("businessPostalCode", businessPostalCode)
				.add("businessPhone1", businessPhone1).add("comments", comments)
				.add("timezoneId", timezoneId).add("accountName", accountName)
				.add("isActive", isActive).add("includeDepositBounds", includeDepositBounds)
				.add("minDepositAmount", minDepositAmount).add("maxDepositAmount", maxDepositAmount)
				.add("amount", amount).add("accopTypeId", accopTypeId).add("sourceGuid", sourceGuid)
				.add("sessionId", sessionId).add("geoCountryCode", geoCountryCode).add("clientIp", clientIp).toString();
	}	
}
