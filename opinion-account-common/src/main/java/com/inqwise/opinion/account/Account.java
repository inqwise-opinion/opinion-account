package com.inqwise.opinion.account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.inqwise.opinion.common.OpinionEntityStatus;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import com.google.common.base.MoreObjects;

@DataObject
public class Account {
	public static class Keys {
		
		public static final String UID = "uid";
		public static final String ID = "id";
		
		public static final String SERVICE_PACKAGE_ID = "service_package_id";
		public static final String NAME = "account_name";
		public static final String OWNER_ID = "owner_id";
		public static final String TIMEZONE = "timezone";
		public static final String CREATED_AT = "created_at";
		public static final String UPDATED_AT = "updated_at";
		public static final String STATUS_ID = "status_id";

		public static final String BALANCE = "balance";
		public static final String SUPPLY_DAYS_INTERVAL = "supply_days_interval";
		public static final String NEXT_SUPPLY_SESSION_SCREDIT = "next_supply_sessions_credit";
		public static final String LAST_SESSIONS_CREDITED_AT = "last_sessions_credited_at";

	}
	
	private String uid;
	private Long id;
	private Integer servicePackageId;
	private String name;
	private Long ownerId;
	private String timezone;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private OpinionEntityStatus status;
	private Long balance;
	private Integer supplyDaysInterval;
	private Integer nextSupplySessionsCredit;
	private LocalDate lastSessionsCreditedAt;


	private Account(Builder builder) {
		this.uid = builder.uid;
		this.id = builder.id;
		this.servicePackageId = builder.servicePackageId;
		this.name = builder.name;
		this.ownerId = builder.ownerId;
		this.timezone = builder.timezone;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
		this.status = builder.status;
		this.balance = builder.balance;
		this.supplyDaysInterval = builder.supplyDaysInterval;
		this.nextSupplySessionsCredit = builder.nextSupplySessionsCredit;
		this.lastSessionsCreditedAt = builder.lastSessionsCreditedAt;
	}
	
	
	public Account(JsonObject json) {
		uid = json.getString(Keys.UID);
		id = json.getLong(Keys.ID);
		name = json.getString(Keys.NAME);
		ownerId = json.getLong(Keys.OWNER_ID);
		timezone = json.getString(Keys.TIMEZONE);
		createdAt = Optional.ofNullable(json.getString(Keys.CREATED_AT)).map(LocalDateTime::parse).orElse(null);
		updatedAt = Optional.ofNullable(json.getString(Keys.UPDATED_AT)).map(LocalDateTime::parse).orElse(null);
		status = Optional.ofNullable(json.getInteger(Keys.STATUS_ID)).map(OpinionEntityStatus::valueOf).orElse(null);
		servicePackageId = json.getInteger(Keys.SERVICE_PACKAGE_ID);
		
		balance = json.getLong(Keys.BALANCE);
		supplyDaysInterval = json.getInteger(Keys.SUPPLY_DAYS_INTERVAL);
		nextSupplySessionsCredit = json.getInteger(Keys.NEXT_SUPPLY_SESSION_SCREDIT);
		lastSessionsCreditedAt = Optional.ofNullable(json.getString(Keys.LAST_SESSIONS_CREDITED_AT)).map(LocalDate::parse).orElse(null);
	}
	
	public String getUid() {
		return uid;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Long getOwnerId() {
		return ownerId;
	}
	
	public String getTimezone() {
		return timezone;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public OpinionEntityStatus getStatus() {
		return status;
	}
	
	public Integer getServicePackageId() {
		return servicePackageId;
	}
	
	public Long getBalance() {
		return balance;
	}
	
	public Integer getSupplyDaysInterval() {
		return supplyDaysInterval;
	}
	
	public Integer getNextSupplySessionsCredit() {
		return nextSupplySessionsCredit;
	}
	
	public LocalDate getLastSessionsCreditedAt() {
		return lastSessionsCreditedAt;
	}
	
	public JsonObject toJson() {
		var json = new JsonObject();
		
		if(null != id) {
			json.put(Keys.ID, id);
		}
		
		if (null != uid) {
			json.put(Keys.UID, uid);
		}
		
		if (null != name) {
			json.put(Keys.NAME, name);
		}
		
		if(null != ownerId) {
			json.put(Keys.OWNER_ID, ownerId);
		}
		
		if(null != timezone) {
			json.put(Keys.TIMEZONE, timezone);
		}
		
		if(null != createdAt) {
			json.put(Keys.CREATED_AT, createdAt.toString());
		}
		
		if(null != updatedAt) {
			json.put(Keys.UPDATED_AT, updatedAt.toString());
		}
		
		if(null != status) {
			json.put(Keys.STATUS_ID, status.value());
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
			json.put(Keys.LAST_SESSIONS_CREDITED_AT, lastSessionsCreditedAt.toString());
		}
		
		return json;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builderFrom(Account account) {
		return new Builder(account);
	}

	public static final class Builder {
		private String uid;
		private Long id;
		private Integer servicePackageId;
		private String name;
		private Long ownerId;
		private String timezone;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
		private OpinionEntityStatus status;
		private Long balance;
		private Integer supplyDaysInterval;
		private Integer nextSupplySessionsCredit;
		private LocalDate lastSessionsCreditedAt;

		private Builder() {
		}

		private Builder(Account account) {
			this.uid = account.uid;
			this.id = account.id;
			this.servicePackageId = account.servicePackageId;
			this.name = account.name;
			this.ownerId = account.ownerId;
			this.timezone = account.timezone;
			this.createdAt = account.createdAt;
			this.updatedAt = account.updatedAt;
			this.status = account.status;
			this.balance = account.balance;
			this.supplyDaysInterval = account.supplyDaysInterval;
			this.nextSupplySessionsCredit = account.nextSupplySessionsCredit;
			this.lastSessionsCreditedAt = account.lastSessionsCreditedAt;
		}

		public Builder withUid(String uid) {
			this.uid = uid;
			return this;
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withServicePackageId(Integer servicePackageId) {
			this.servicePackageId = servicePackageId;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withOwnerId(Long ownerId) {
			this.ownerId = ownerId;
			return this;
		}

		public Builder withTimezone(String timezone) {
			this.timezone = timezone;
			return this;
		}

		public Builder withCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder withUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}

		public Builder withStatus(OpinionEntityStatus status) {
			this.status = status;
			return this;
		}

		public Builder withBalance(Long balance) {
			this.balance = balance;
			return this;
		}

		public Builder withSupplyDaysInterval(Integer supplyDaysInterval) {
			this.supplyDaysInterval = supplyDaysInterval;
			return this;
		}

		public Builder withNextSupplySessionsCredit(Integer nextSupplySessionsCredit) {
			this.nextSupplySessionsCredit = nextSupplySessionsCredit;
			return this;
		}

		public Builder withLastSessionsCreditedAt(LocalDate lastSessionsCreditedAt) {
			this.lastSessionsCreditedAt = lastSessionsCreditedAt;
			return this;
		}

		public Account build() {
			return new Account(this);
		}
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("uid", uid).add("id", id).add("servicePackageId", servicePackageId)
				.add("name", name).add("ownerId", ownerId).add("timezone", timezone).add("createdAt", createdAt)
				.add("updatedAt", updatedAt).add("status", status).add("balance", balance)
				.add("supplyDaysInterval", supplyDaysInterval).add("nextSupplySessionsCredit", nextSupplySessionsCredit)
				.add("lastSessionsCreditedAt", lastSessionsCreditedAt).toString();
	}
}
