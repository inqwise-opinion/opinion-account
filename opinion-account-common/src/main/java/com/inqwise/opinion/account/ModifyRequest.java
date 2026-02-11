package com.inqwise.opinion.account;

import com.inqwise.opinion.common.Formatters;
import com.google.common.base.MoreObjects;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * ModifyRequest.
 */
@DataObject
public class ModifyRequest implements AccountIdentifiable, AccountDetailsChangeSet, AccountBusinessDetailsChangeSet, AccountUserAssociationChangeSet, AccountOwnerChangeSet, AccountBalanceChangeSet, AccountServicePackageChangeSet {
	
	private String uidPrefix;
	private Long id;
	private Long sourceId;
	private Long backofficeUserId;
	private Long userId;
	private Long ownerId;
	private Long productId;
	private Long accountId;
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
	private String details;
	private Integer timezoneId;
	private String name;
	private Boolean isActive;
	private Boolean includeDepositBounds;
	private Integer minDepositAmount;
	private Integer maxDepositAmount;
	private Integer amount;
	private AccountOperationType operationType;
	private String sessionId;
	private String geoCountryCode;
	private String clientIp;
	private Integer servicePackageId;
	private LocalDateTime expiryAt;
	private Integer maxUsers;

	/**
	 * Constructs ModifyRequest.
	 */
	private ModifyRequest(Builder builder) {
		this.uidPrefix = builder.uidPrefix;
		this.id = builder.id;
		this.sourceId = builder.sourceId;
		this.backofficeUserId = builder.backofficeUserId;
		this.userId = builder.userId;
		this.ownerId = builder.ownerId;
		this.productId = builder.productId;
		this.accountId = builder.accountId;
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
		this.details = builder.details;
		this.timezoneId = builder.timezoneId;
		this.name = builder.name;
		this.isActive = builder.isActive;
		this.includeDepositBounds = builder.includeDepositBounds;
		this.minDepositAmount = builder.minDepositAmount;
		this.maxDepositAmount = builder.maxDepositAmount;
		this.amount = builder.amount;
		this.operationType = builder.operationType;
		this.sessionId = builder.sessionId;
		this.geoCountryCode = builder.geoCountryCode;
		this.clientIp = builder.clientIp;
		this.servicePackageId = builder.servicePackageId;
		this.expiryAt = builder.expiryAt;
		this.maxUsers = builder.maxUsers;
	}

	public static class Keys {
		public static final String UID_PREFIX = "prefix";
		public static final String ID = "id";
		public static final String SOURCE_ID = "source_id";
		public static final String BACKOFFICE_USER_ID = "bo_user_id";
		public static final String USER_ID = "user_id";
		public static final String OWNER_ID = "owner_id";
		public static final String PRODUCT_ID = "product_id";
		public static final String ACCOUNT_ID = "account_id";
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
		public static final String DETAILS = "details";
		public static final String TIMEZONE_ID = "timezone_id";
		public static final String NAME = "name";
		public static final String IS_ACTIVE = "is_active";
		public static final String INCLUDE_DEPOSIT_BOUNDS = "include_deposit_bounds";
		public static final String MIN_DEPOSIT_AMOUNT = "min_deposit_amount";
		public static final String MAX_DEPOSIT_AMOUNT = "max_deposit_amount";
		public static final String AMOUNT = "amount";
		public static final String OPERATION_TYPE_ID = "operation_type_id";
		public static final String SESSION_ID = "session_id";
		public static final String GEO_COUNTRY_CODE = "geo_country_code";
		public static final String CLIENT_IP = "client_ip";
		public static final String SERVICE_PACKAGE_ID = "service_package_id";
		public static final String EXPIRY_AT = "expiry_at";
		public static final String MAX_USERS = "max_users";
	}
	
	/**
	 * Constructs ModifyRequest.
	 */
	public ModifyRequest(JsonObject json) {
		uidPrefix = json.getString(Keys.UID_PREFIX);
		id = json.getLong(Keys.ID);
		sourceId = json.getLong(Keys.SOURCE_ID);
		backofficeUserId = json.getLong(Keys.BACKOFFICE_USER_ID);
		userId = json.getLong(Keys.USER_ID);
		ownerId = json.getLong(Keys.OWNER_ID);
		productId = json.getLong(Keys.PRODUCT_ID);
		accountId = json.getLong(Keys.ACCOUNT_ID);
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
		details = json.getString(Keys.DETAILS);
		timezoneId = json.getInteger(Keys.TIMEZONE_ID);
		name = json.getString(Keys.NAME);
		isActive = json.getBoolean(Keys.IS_ACTIVE);
		includeDepositBounds = json.getBoolean(Keys.INCLUDE_DEPOSIT_BOUNDS);
		minDepositAmount = toInteger(json.getValue(Keys.MIN_DEPOSIT_AMOUNT));
		maxDepositAmount = toInteger(json.getValue(Keys.MAX_DEPOSIT_AMOUNT));
		amount = toInteger(json.getValue(Keys.AMOUNT));
		operationType = AccountOperationType.fromInt(json.getInteger(Keys.OPERATION_TYPE_ID));
		sessionId = json.getString(Keys.SESSION_ID);
		geoCountryCode = json.getString(Keys.GEO_COUNTRY_CODE);
		clientIp = json.getString(Keys.CLIENT_IP);
		servicePackageId = json.getInteger(Keys.SERVICE_PACKAGE_ID);
		expiryAt = Optional.ofNullable(json.getString(Keys.EXPIRY_AT)).map(Formatters::parseDateTime).orElse(null);
		maxUsers = json.getInteger(Keys.MAX_USERS);
	}
	
	/**
	 * toJson.
	 */
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
		
		if(null != accountId) {
			json.put(Keys.ACCOUNT_ID, accountId);
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
		
		if(null != details) {
			json.put(Keys.DETAILS, details);
		}
		
		if(null != timezoneId) {
			json.put(Keys.TIMEZONE_ID, timezoneId);
		}
		
		if(null != name) {
			json.put(Keys.NAME, name);
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
		
		if(null != operationType) {
			json.put(Keys.OPERATION_TYPE_ID, operationType.getValueOrNullWhenUndefined());
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
		
		if(null != servicePackageId) {
			json.put(Keys.SERVICE_PACKAGE_ID, servicePackageId);
		}
		
		if(null != expiryAt) {
			json.put(Keys.EXPIRY_AT, Formatters.formatDateTime(expiryAt));
		}
		
		if(null != maxUsers) {
			json.put(Keys.MAX_USERS, maxUsers);
		}
		return json;
	}

	/**
	 * toInteger.
	 */
	private static Integer toInteger(Object value) {
		if(value instanceof Number number) {
			return number.intValue();
		}
		
		if(value instanceof String str && !str.isBlank()) {
			return Integer.valueOf(str);
		}
		
		return null;
	}
	
	/**
	 * getUidPrefix.
	 */
	public String getUidPrefix() {
		return uidPrefix;
	}
	
	/**
	 * getId.
	 */
	@Override
	public Long getId() {
		return id;
	}
	
	/**
	 * getSourceId.
	 */
	@Override
	public Long getSourceId() {
		return sourceId;
	}
	
	/**
	 * getBackofficeUserId.
	 */
	@Override
	public Long getBackofficeUserId() {
		return backofficeUserId;
	}
	
	/**
	 * getUserId.
	 */
	@Override
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * getOwnerId.
	 */
	@Override
	public Long getOwnerId() {
		return ownerId;
	}
	
	/**
	 * getProductId.
	 */
	public Long getProductId() {
		return productId;
	}
	
	/**
	 * getAccountId.
	 */
	@Override
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * getBusinessCompanyName.
	 */
	@Override
	public String getBusinessCompanyName() {
		return businessCompanyName;
	}

	/**
	 * getBusinessFirstName.
	 */
	@Override
	public String getBusinessFirstName() {
		return businessFirstName;
	}

	/**
	 * getBusinessLastName.
	 */
	@Override
	public String getBusinessLastName() {
		return businessLastName;
	}

	/**
	 * getBusinessAddress1.
	 */
	@Override
	public String getBusinessAddress1() {
		return businessAddress1;
	}

	/**
	 * getBusinessAddress2.
	 */
	@Override
	public String getBusinessAddress2() {
		return businessAddress2;
	}

	/**
	 * getBusinessCity.
	 */
	@Override
	public String getBusinessCity() {
		return businessCity;
	}

	/**
	 * getBusinessCountryId.
	 */
	@Override
	public Integer getBusinessCountryId() {
		return businessCountryId;
	}

	/**
	 * getBusinessStateId.
	 */
	@Override
	public Integer getBusinessStateId() {
		return businessStateId;
	}

	/**
	 * getBusinessPostalCode.
	 */
	@Override
	public String getBusinessPostalCode() {
		return businessPostalCode;
	}

	/**
	 * getBusinessPhone1.
	 */
	@Override
	public String getBusinessPhone1() {
		return businessPhone1;
	}
	
	/**
	 * getDetails.
	 */
	@Override
	public String getDetails() {
		return details;
	}
	
	/**
	 * getTimezoneId.
	 */
	@Override
	public Integer getTimezoneId() {
		return timezoneId;
	}
	
	/**
	 * getName.
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * getIsActive.
	 */
	@Override
	public Boolean getIsActive() {
		return isActive;
	}
	
	/**
	 * getIncludeDepositBounds.
	 */
	@Override
	public Boolean getIncludeDepositBounds() {
		return includeDepositBounds;
	}
	
	/**
	 * getMinDepositAmount.
	 */
	@Override
	public Integer getMinDepositAmount() {
		return minDepositAmount;
	}
	
	/**
	 * getMaxDepositAmount.
	 */
	@Override
	public Integer getMaxDepositAmount() {
		return maxDepositAmount;
	}
	
	/**
	 * getAmount.
	 */
	@Override
	public Integer getAmount() {
		return amount;
	}
	
	/**
	 * getOperationType.
	 */
	@Override
	public AccountOperationType getOperationType() {
		return operationType;
	}
	
	/**
	 * getSessionId.
	 */
	@Override
	public String getSessionId() {
		return sessionId;
	}
	
	/**
	 * getGeoCountryCode.
	 */
	@Override
	public String getGeoCountryCode() {
		return geoCountryCode;
	}
	
	/**
	 * getClientIp.
	 */
	@Override
	public String getClientIp() {
		return clientIp;
	}
	
	/**
	 * getServicePackageId.
	 */
	@Override
	public Integer getServicePackageId() {
		return servicePackageId;
	}
	
	/**
	 * getExpiryAt.
	 */
	@Override
	public LocalDateTime getExpiryAt() {
		return expiryAt;
	}
	
	/**
	 * getMaxUsers.
	 */
	@Override
	public Integer getMaxUsers() {
		return maxUsers;
	}

	/**
	 * builder.
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * builderFrom.
	 */
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
		private Long accountId;
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
		private String details;
		private Integer timezoneId;
		private String name;
		private Boolean isActive;
		private Boolean includeDepositBounds;
		private Integer minDepositAmount;
		private Integer maxDepositAmount;
		private Integer amount;
		private AccountOperationType operationType;
		private String sessionId;
		private String geoCountryCode;
		private String clientIp;
		private Integer servicePackageId;
		private LocalDateTime expiryAt;
		private Integer maxUsers;

		/**
		 * Builder.
		 */
		private Builder() {
		}

		/**
		 * Builder.
		 */
		private Builder(ModifyRequest modifyRequest) {
			this.uidPrefix = modifyRequest.uidPrefix;
			this.id = modifyRequest.id;
			this.sourceId = modifyRequest.sourceId;
			this.backofficeUserId = modifyRequest.backofficeUserId;
			this.userId = modifyRequest.userId;
			this.ownerId = modifyRequest.ownerId;
			this.productId = modifyRequest.productId;
			this.accountId = modifyRequest.accountId;
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
			this.details = modifyRequest.details;
			this.timezoneId = modifyRequest.timezoneId;
			this.name = modifyRequest.name;
			this.isActive = modifyRequest.isActive;
			this.includeDepositBounds = modifyRequest.includeDepositBounds;
			this.minDepositAmount = modifyRequest.minDepositAmount;
			this.maxDepositAmount = modifyRequest.maxDepositAmount;
			this.amount = modifyRequest.amount;
			this.operationType = modifyRequest.operationType;
			this.sessionId = modifyRequest.sessionId;
			this.geoCountryCode = modifyRequest.geoCountryCode;
			this.clientIp = modifyRequest.clientIp;
			this.servicePackageId = modifyRequest.servicePackageId;
			this.expiryAt = modifyRequest.expiryAt;
			this.maxUsers = modifyRequest.maxUsers;
		}

		/**
		 * withUidPrefix.
		 */
		public Builder withUidPrefix(String uidPrefix) {
			this.uidPrefix = uidPrefix;
			return this;
		}

		/**
		 * withId.
		 */
		public Builder withId(Long id) {
			this.id = id;
			return this;
		}
		
		/**
		 * withSourceId.
		 */
		public Builder withSourceId(Long sourceId) {
			this.sourceId = sourceId;
			return this;
		}
		
		/**
		 * withBackofficeUserId.
		 */
		public Builder withBackofficeUserId(Long backofficeUserId) {
			this.backofficeUserId = backofficeUserId;
			return this;
		}
		
		/**
		 * withUserId.
		 */
		public Builder withUserId(Long userId) {
			this.userId = userId;
			return this;
		}
		
		/**
		 * withOwnerId.
		 */
		public Builder withOwnerId(Long ownerId) {
			this.ownerId = ownerId;
			return this;
		}
		
		/**
		 * withProductId.
		 */
		public Builder withProductId(Long productId) {
			this.productId = productId;
			return this;
		}
		
		/**
		 * withAccountId.
		 */
		public Builder withAccountId(Long accountId) {
			this.accountId = accountId;
			return this;
		}

		/**
		 * withBusinessCompanyName.
		 */
		public Builder withBusinessCompanyName(String businessCompanyName) {
			this.businessCompanyName = businessCompanyName;
			return this;
		}

		/**
		 * withBusinessFirstName.
		 */
		public Builder withBusinessFirstName(String businessFirstName) {
			this.businessFirstName = businessFirstName;
			return this;
		}

		/**
		 * withBusinessLastName.
		 */
		public Builder withBusinessLastName(String businessLastName) {
			this.businessLastName = businessLastName;
			return this;
		}

		/**
		 * withBusinessAddress1.
		 */
		public Builder withBusinessAddress1(String businessAddress1) {
			this.businessAddress1 = businessAddress1;
			return this;
		}

		/**
		 * withBusinessAddress2.
		 */
		public Builder withBusinessAddress2(String businessAddress2) {
			this.businessAddress2 = businessAddress2;
			return this;
		}

		/**
		 * withBusinessCity.
		 */
		public Builder withBusinessCity(String businessCity) {
			this.businessCity = businessCity;
			return this;
		}

		/**
		 * withBusinessCountryId.
		 */
		public Builder withBusinessCountryId(Integer businessCountryId) {
			this.businessCountryId = businessCountryId;
			return this;
		}

		/**
		 * withBusinessStateId.
		 */
		public Builder withBusinessStateId(Integer businessStateId) {
			this.businessStateId = businessStateId;
			return this;
		}

		/**
		 * withBusinessPostalCode.
		 */
		public Builder withBusinessPostalCode(String businessPostalCode) {
			this.businessPostalCode = businessPostalCode;
			return this;
		}

		/**
		 * withBusinessPhone1.
		 */
		public Builder withBusinessPhone1(String businessPhone1) {
			this.businessPhone1 = businessPhone1;
			return this;
		}
		
		/**
		 * withDetails.
		 */
		public Builder withDetails(String details) {
			this.details = details;
			return this;
		}
		
		/**
		 * withTimezoneId.
		 */
		public Builder withTimezoneId(Integer timezoneId) {
			this.timezoneId = timezoneId;
			return this;
		}
		
		/**
		 * withName.
		 */
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		/**
		 * withIsActive.
		 */
		public Builder withIsActive(Boolean isActive) {
			this.isActive = isActive;
			return this;
		}
		
		/**
		 * withIncludeDepositBounds.
		 */
		public Builder withIncludeDepositBounds(Boolean includeDepositBounds) {
			this.includeDepositBounds = includeDepositBounds;
			return this;
		}
		
		/**
		 * withMinDepositAmount.
		 */
		public Builder withMinDepositAmount(Integer minDepositAmount) {
			this.minDepositAmount = minDepositAmount;
			return this;
		}
		
		/**
		 * withMaxDepositAmount.
		 */
		public Builder withMaxDepositAmount(Integer maxDepositAmount) {
			this.maxDepositAmount = maxDepositAmount;
			return this;
		}
		
		/**
		 * withAmount.
		 */
		public Builder withAmount(Integer amount) {
			this.amount = amount;
			return this;
		}
		
		/**
		 * withOperationType.
		 */
		public Builder withOperationType(AccountOperationType operationType) {
			this.operationType = operationType;
			return this;
		}
		
		/**
		 * withSessionId.
		 */
		public Builder withSessionId(String sessionId) {
			this.sessionId = sessionId;
			return this;
		}
		
		/**
		 * withGeoCountryCode.
		 */
		public Builder withGeoCountryCode(String geoCountryCode) {
			this.geoCountryCode = geoCountryCode;
			return this;
		}
		
		/**
		 * withClientIp.
		 */
		public Builder withClientIp(String clientIp) {
			this.clientIp = clientIp;
			return this;
		}
		
		/**
		 * withServicePackageId.
		 */
		public Builder withServicePackageId(Integer servicePackageId) {
			this.servicePackageId = servicePackageId;
			return this;
		}
		
		/**
		 * withExpiryAt.
		 */
		public Builder withExpiryAt(LocalDateTime expiryAt) {
			this.expiryAt = expiryAt;
			return this;
		}
		
		/**
		 * withMaxUsers.
		 */
		public Builder withMaxUsers(Integer maxUsers) {
			this.maxUsers = maxUsers;
			return this;
		}

		/**
		 * build.
		 */
		public ModifyRequest build() {
			return new ModifyRequest(this);
		}
	}

	/**
	 * toString.
	 */
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
				.add("businessPhone1", businessPhone1).add("details", details)
				.add("timezoneId", timezoneId).add("name", name)
				.add("isActive", isActive).add("includeDepositBounds", includeDepositBounds)
				.add("minDepositAmount", minDepositAmount).add("maxDepositAmount", maxDepositAmount)
				.add("amount", amount).add("operationType", operationType)
				.add("sessionId", sessionId).add("geoCountryCode", geoCountryCode).add("clientIp", clientIp)
				.add("servicePackageId", servicePackageId).add("expiryAt", expiryAt).add("maxUsers", maxUsers)
				.toString();
	}	
}
