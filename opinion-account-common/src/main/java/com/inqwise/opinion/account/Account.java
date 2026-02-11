package com.inqwise.opinion.account;

import com.inqwise.opinion.common.Formatters;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.google.common.base.MoreObjects;
import com.inqwise.opinion.common.OpinionEntityStatus;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * Account.
 */
@DataObject
public class Account {
	public static class Keys {
		
		public static final String UID = "uid";
		public static final String ID = "id";
		
		public static final String SERVICE_PACKAGE_ID = "service_package_id";
		public static final String SERVICE_PACKAGE_NAME = "service_package_name";
		public static final String PRODUCT_ID = "product_id";
		public static final String NAME = "name";
		public static final String DETAILS = "details";
		public static final String OWNER_ID = "owner_id";
		public static final String TIMEZONE = "timezone";
		public static final String CREATED_AT = "created_at";
		public static final String UPDATED_AT = "updated_at";
		public static final String ACTIVE = "active";
		public static final String STATUS_ID = "status_id";

		public static final String BALANCE = "balance";
		public static final String SUPPLY_DAYS_INTERVAL = "supply_days_interval";
		public static final String NEXT_SUPPLY_SESSION_SCREDIT = "next_supply_sessions_credit";
		public static final String LAST_SESSIONS_CREDITED_AT = "last_sessions_credited_at";
		public static final String TIMEZONE_OFFSET = "timezone_offset";
		public static final String MAX_USERS = "max_users";
		public static final String MIN_DEPOSIT_AMOUNT = "min_deposit_amount";
		public static final String MAX_DEPOSIT_AMOUNT = "max_deposit_amount";

	}
	
	private String uidToken;
	private Long id;
	private Integer servicePackageId;
	private LocalDate servicePackageExpiredOn;
	private String servicePackageName;
	private Integer productId;
	private String name;
	private String details;
	private Long ownerId;
	private String timezone;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean active;
	private Long balance;
	private Integer supplyDaysInterval;
	private Integer nextSupplySessionsCredit;
	private LocalDate lastSessionsCreditedAt;
	private Integer timezoneOffset;
	private Integer maxUsers;
	private Integer minDepositAmount;
	private Integer maxDepositAmount;
	private OpinionEntityStatus status;

	/**
	 * Constructs Account.
	 *
	 * @param builder source builder
	 */
	private Account(Builder builder) {
		this.uidToken = builder.uidToken;
		this.id = builder.id;
		this.servicePackageId = builder.servicePackageId;
		this.servicePackageExpiredOn = builder.servicePackageExpiredOn;
		this.servicePackageName = builder.servicePackageName;
		this.productId = builder.productId;
		this.name = builder.name;
		this.details = builder.details;
		this.ownerId = builder.ownerId;
		this.timezone = builder.timezone;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
		this.active = builder.active;
		this.balance = builder.balance;
		this.supplyDaysInterval = builder.supplyDaysInterval;
		this.nextSupplySessionsCredit = builder.nextSupplySessionsCredit;
		this.lastSessionsCreditedAt = builder.lastSessionsCreditedAt;
		this.timezoneOffset = builder.timezoneOffset;
		this.maxUsers = builder.maxUsers;
		this.minDepositAmount = builder.minDepositAmount;
		this.maxDepositAmount = builder.maxDepositAmount;
		this.status = builder.status;
	}

	/**
	 * Constructs Account.
	 *
	 * @param json source json
	 */
	public Account(JsonObject json) {
		uidToken = json.getString(Keys.UID);
		id = json.getLong(Keys.ID);
		name = json.getString(Keys.NAME);
		details = json.getString(Keys.DETAILS);
		ownerId = json.getLong(Keys.OWNER_ID);
		timezone = json.getString(Keys.TIMEZONE);
		createdAt = Optional.ofNullable(json.getString(Keys.CREATED_AT)).map(Formatters::parseDateTime).orElse(null);
		updatedAt = Optional.ofNullable(json.getString(Keys.UPDATED_AT)).map(Formatters::parseDateTime).orElse(null);
		active = json.getBoolean(Keys.ACTIVE);
		servicePackageId = json.getInteger(Keys.SERVICE_PACKAGE_ID);
		servicePackageName = json.getString(Keys.SERVICE_PACKAGE_NAME);
		productId = json.getInteger(Keys.PRODUCT_ID);
		balance = json.getLong(Keys.BALANCE);
		supplyDaysInterval = json.getInteger(Keys.SUPPLY_DAYS_INTERVAL);
		nextSupplySessionsCredit = json.getInteger(Keys.NEXT_SUPPLY_SESSION_SCREDIT);
		lastSessionsCreditedAt = Optional.ofNullable(json.getString(Keys.LAST_SESSIONS_CREDITED_AT)).map(Formatters::parseDate).orElse(null);
		timezoneOffset = json.getInteger(Keys.TIMEZONE_OFFSET);
		maxUsers = json.getInteger(Keys.MAX_USERS);
		minDepositAmount = json.getInteger(Keys.MIN_DEPOSIT_AMOUNT);
		maxDepositAmount = json.getInteger(Keys.MAX_DEPOSIT_AMOUNT);
		status = Optional.ofNullable(json.getInteger(Keys.STATUS_ID)).map(OpinionEntityStatus::valueOf).orElse(null);
	}
	
	/**
	 * getMaxDepositAmount.
	 *
	 * @return max deposit amount
	 */
	public Integer getMaxDepositAmount() {
		return maxDepositAmount;
	}
	
	/**
	 * getMinDepositAmount.
	 *
	 * @return min deposit amount
	 */
	public Integer getMinDepositAmount() {
		return minDepositAmount;
	}
	
	/**
	 * getMaxUsers.
	 *
	 * @return max users
	 */
	public Integer getMaxUsers() {
		return maxUsers;
	}
	
	/**
	 * getTimezoneOffset.
	 *
	 * @return timezone offset
	 */
	public Integer getTimezoneOffset() {
		return timezoneOffset;
	}
	
	/**
	 * getUidToken.
	 *
	 * @return uid token
	 */
	public String getUidToken() {
		return uidToken;
	}
	
	/**
	 * getId.
	 *
	 * @return account id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * getName.
	 *
	 * @return account name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getDetails.
	 *
	 * @return account details
	 */
	public String getDetails() {
		return details;
	}
	
	/**
	 * getOwnerId.
	 *
	 * @return owner id
	 */
	public Long getOwnerId() {
		return ownerId;
	}
	
	/**
	 * getTimezone.
	 *
	 * @return timezone
	 */
	public String getTimezone() {
		return timezone;
	}
	
	/**
	 * getCreatedAt.
	 *
	 * @return created timestamp
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * getUpdatedAt.
	 *
	 * @return updated timestamp
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	/**
	 * getActive.
	 *
	 * @return active flag
	 */
	public Boolean getActive() {
		return active;
	}
	
	/**
	 * getServicePackageId.
	 *
	 * @return service package id
	 */
	public Integer getServicePackageId() {
		return servicePackageId;
	}
	
	/**
	 * getBalance.
	 *
	 * @return balance
	 */
	public Long getBalance() {
		return balance;
	}
	
	/**
	 * getSupplyDaysInterval.
	 *
	 * @return supply days interval
	 */
	public Integer getSupplyDaysInterval() {
		return supplyDaysInterval;
	}
	
	/**
	 * getNextSupplySessionsCredit.
	 *
	 * @return next supply sessions credit
	 */
	public Integer getNextSupplySessionsCredit() {
		return nextSupplySessionsCredit;
	}
	
	/**
	 * getLastSessionsCreditedAt.
	 *
	 * @return last sessions credited date
	 */
	public LocalDate getLastSessionsCreditedAt() {
		return lastSessionsCreditedAt;
	}
	
	/**
	 * getProductId.
	 *
	 * @return product id
	 */
	public Integer getProductId() {
		return productId;
	}
	
	/**
	 * getServicePackageExpiredOn.
	 *
	 * @return service package expiry date
	 */
	public LocalDate getServicePackageExpiredOn() {
		return servicePackageExpiredOn;
	}
	
	/**
	 * getServicePackageName.
	 *
	 * @return service package name
	 */
	public String getServicePackageName() {
		return servicePackageName;
	}
	
	/**
	 * getStatus.
	 *
	 * @return entity status
	 */
	public OpinionEntityStatus getStatus() {
		return status;
	}
	
	/**
	 * toJson.
	 *
	 * @return json representation
	 */
	public JsonObject toJson() {
		var json = new JsonObject();
		
		if(null != id) {
			json.put(Keys.ID, id);
		}
		
		if (null != uidToken) {
			json.put(Keys.UID, uidToken);
		}
		
		if (null != name) {
			json.put(Keys.NAME, name);
		}
		
		if (null != details) {
			json.put(Keys.DETAILS, details);
		}
		
		if(null != ownerId) {
			json.put(Keys.OWNER_ID, ownerId);
		}
		
		if(null != servicePackageId) {
			json.put(Keys.SERVICE_PACKAGE_ID, servicePackageId);
		}
		
		if(null != servicePackageName) {
			json.put(Keys.SERVICE_PACKAGE_NAME, servicePackageName);
		}
		
		if(null != productId) {
			json.put(Keys.PRODUCT_ID, productId);
		}
		
		if(null != timezone) {
			json.put(Keys.TIMEZONE, timezone);
		}
		
		if(null != createdAt) {
			json.put(Keys.CREATED_AT, Formatters.formatDateTime(createdAt));
		}

		if(null != updatedAt) {
			json.put(Keys.UPDATED_AT, Formatters.formatDateTime(updatedAt));
		}
		
		if(null != active) {
			json.put(Keys.ACTIVE, active);
		}
		
		if (null != balance) {
			json.put(Keys.BALANCE, balance);
		}
		
		if(null != supplyDaysInterval) {
			json.put(Keys.SUPPLY_DAYS_INTERVAL, supplyDaysInterval);
		}
		
		if(null != nextSupplySessionsCredit) {
			json.put(Keys.NEXT_SUPPLY_SESSION_SCREDIT, nextSupplySessionsCredit);
		}
		
		if (null != lastSessionsCreditedAt) {
			json.put(Keys.LAST_SESSIONS_CREDITED_AT, Formatters.formatDate(lastSessionsCreditedAt));
		}
		
		if(null != timezoneOffset) {
			json.put(Keys.TIMEZONE_OFFSET, timezoneOffset);
		}
		
		if(null != maxUsers) {
			json.put(Keys.MAX_USERS, maxUsers);
		}
		
		if(null != minDepositAmount) {
			json.put(Keys.MIN_DEPOSIT_AMOUNT, minDepositAmount);
		}
		
		if(null != maxDepositAmount) {
			json.put(Keys.MAX_DEPOSIT_AMOUNT, maxDepositAmount);
		}
		
		if (null != status) {
			json.put(Keys.STATUS_ID, status.value());
		}
		
		return json;
	}

	/**
	 * builder.
	 *
	 * @return new builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * builderFrom.
	 *
	 * @param account source account
	 * @return builder
	 */
	public static Builder builderFrom(Account account) {
		return new Builder(account);
	}

	public static final class Builder {
		private String uidToken;
		private Long id;
		private Integer servicePackageId;
		private LocalDate servicePackageExpiredOn;
		private String servicePackageName;
		private Integer productId;
		private String name;
		private String details;
		private Long ownerId;
		private String timezone;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
		private Boolean active;
		private Long balance;
		private Integer supplyDaysInterval;
		private Integer nextSupplySessionsCredit;
		private LocalDate lastSessionsCreditedAt;
		private Integer timezoneOffset;
		private Integer maxUsers;
		private Integer minDepositAmount;
		private Integer maxDepositAmount;
		private OpinionEntityStatus status;

		/**
		 * Builder.
		 */
		private Builder() {
		}

		/**
		 * Builder.
		 */
		private Builder(Account account) {
			this.uidToken = account.uidToken;
			this.id = account.id;
			this.servicePackageId = account.servicePackageId;
			this.servicePackageExpiredOn = account.servicePackageExpiredOn;
			this.servicePackageName = account.servicePackageName;
			this.productId = account.productId;
			this.name = account.name;
			this.details = account.details;
			this.ownerId = account.ownerId;
			this.timezone = account.timezone;
			this.createdAt = account.createdAt;
			this.updatedAt = account.updatedAt;
			this.active = account.active;
			this.balance = account.balance;
			this.supplyDaysInterval = account.supplyDaysInterval;
			this.nextSupplySessionsCredit = account.nextSupplySessionsCredit;
			this.lastSessionsCreditedAt = account.lastSessionsCreditedAt;
			this.timezoneOffset = account.timezoneOffset;
			this.maxUsers = account.maxUsers;
			this.minDepositAmount = account.minDepositAmount;
			this.maxDepositAmount = account.maxDepositAmount;
			this.status = account.status;
		}

		/**
		 * withUidToken.
		 *
		 * @param uidToken uid token
		 * @return builder
		 */
		public Builder withUidToken(String uidToken) {
			this.uidToken = uidToken;
			return this;
		}

		/**
		 * withId.
		 *
		 * @param id account id
		 * @return builder
		 */
		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * withServicePackageId.
		 *
		 * @param servicePackageId service package id
		 * @return builder
		 */
		public Builder withServicePackageId(Integer servicePackageId) {
			this.servicePackageId = servicePackageId;
			return this;
		}

		/**
		 * withServicePackageExpiredOn.
		 *
		 * @param servicePackageExpiredOn service package expiry date
		 * @return builder
		 */
		public Builder withServicePackageExpiredOn(LocalDate servicePackageExpiredOn) {
			this.servicePackageExpiredOn = servicePackageExpiredOn;
			return this;
		}

		/**
		 * withServicePackageName.
		 *
		 * @param servicePackageName service package name
		 * @return builder
		 */
		public Builder withServicePackageName(String servicePackageName) {
			this.servicePackageName = servicePackageName;
			return this;
		}

		/**
		 * withProductId.
		 *
		 * @param productId product id
		 * @return builder
		 */
		public Builder withProductId(Integer productId) {
			this.productId = productId;
			return this;
		}

		/**
		 * withName.
		 *
		 * @param name account name
		 * @return builder
		 */
		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * withDetails.
		 *
		 * @param details account details
		 * @return builder
		 */
		public Builder withDetails(String details) {
			this.details = details;
			return this;
		}

		/**
		 * withOwnerId.
		 *
		 * @param ownerId owner id
		 * @return builder
		 */
		public Builder withOwnerId(Long ownerId) {
			this.ownerId = ownerId;
			return this;
		}

		/**
		 * withTimezone.
		 *
		 * @param timezone timezone id
		 * @return builder
		 */
		public Builder withTimezone(String timezone) {
			this.timezone = timezone;
			return this;
		}

		/**
		 * withCreatedAt.
		 *
		 * @param createdAt created timestamp
		 * @return builder
		 */
		public Builder withCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		/**
		 * withUpdatedAt.
		 *
		 * @param updatedAt updated timestamp
		 * @return builder
		 */
		public Builder withUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}

		/**
		 * withActive.
		 *
		 * @param active active flag
		 * @return builder
		 */
		public Builder withActive(Boolean active) {
			this.active = active;
			return this;
		}

		/**
		 * withBalance.
		 *
		 * @param balance account balance
		 * @return builder
		 */
		public Builder withBalance(Long balance) {
			this.balance = balance;
			return this;
		}

		/**
		 * withSupplyDaysInterval.
		 *
		 * @param supplyDaysInterval supply days interval
		 * @return builder
		 */
		public Builder withSupplyDaysInterval(Integer supplyDaysInterval) {
			this.supplyDaysInterval = supplyDaysInterval;
			return this;
		}

		/**
		 * withNextSupplySessionsCredit.
		 *
		 * @param nextSupplySessionsCredit next supply sessions credit
		 * @return builder
		 */
		public Builder withNextSupplySessionsCredit(Integer nextSupplySessionsCredit) {
			this.nextSupplySessionsCredit = nextSupplySessionsCredit;
			return this;
		}

		/**
		 * withLastSessionsCreditedAt.
		 *
		 * @param lastSessionsCreditedAt last sessions credited date
		 * @return builder
		 */
		public Builder withLastSessionsCreditedAt(LocalDate lastSessionsCreditedAt) {
			this.lastSessionsCreditedAt = lastSessionsCreditedAt;
			return this;
		}

		/**
		 * withTimezoneOffset.
		 *
		 * @param timezoneOffset timezone offset
		 * @return builder
		 */
		public Builder withTimezoneOffset(Integer timezoneOffset) {
			this.timezoneOffset = timezoneOffset;
			return this;
		}

		/**
		 * withMaxUsers.
		 *
		 * @param maxUsers max users
		 * @return builder
		 */
		public Builder withMaxUsers(Integer maxUsers) {
			this.maxUsers = maxUsers;
			return this;
		}

		/**
		 * withMinDepositAmount.
		 *
		 * @param minDepositAmount min deposit amount
		 * @return builder
		 */
		public Builder withMinDepositAmount(Integer minDepositAmount) {
			this.minDepositAmount = minDepositAmount;
			return this;
		}

		/**
		 * withMaxDepositAmount.
		 *
		 * @param maxDepositAmount max deposit amount
		 * @return builder
		 */
		public Builder withMaxDepositAmount(Integer maxDepositAmount) {
			this.maxDepositAmount = maxDepositAmount;
			return this;
		}

		/**
		 * withStatus.
		 *
		 * @param status entity status
		 * @return builder
		 */
		public Builder withStatus(OpinionEntityStatus status) {
			this.status = status;
			return this;
		}

		/**
		 * build.
		 *
		 * @return account instance
		 */
		public Account build() {
			return new Account(this);
		}
	}

	/**
	 * toString.
	 *
	 * @return string representation
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("uidToken", uidToken).add("id", id)
				.add("servicePackageId", servicePackageId).add("servicePackageExpiredOn", servicePackageExpiredOn)
				.add("servicePackageName", servicePackageName).add("productId", productId).add("name", name)
				.add("details", details).add("ownerId", ownerId).add("timezone", timezone)
				.add("createdAt", createdAt).add("updatedAt", updatedAt).add("active", active).add("balance", balance)
				.add("supplyDaysInterval", supplyDaysInterval).add("nextSupplySessionsCredit", nextSupplySessionsCredit)
				.add("lastSessionsCreditedAt", lastSessionsCreditedAt).add("timezoneOffset", timezoneOffset)
				.add("maxUsers", maxUsers).add("minDepositAmount", minDepositAmount)
				.add("maxDepositAmount", maxDepositAmount).add("status", status).toString();
	}
	
	
}
