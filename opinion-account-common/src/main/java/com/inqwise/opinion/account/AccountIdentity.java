package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.inqwise.opinion.common.Uid;

@DataObject
public class AccountIdentity {
	private String uidPrefix;
	private Long id;
	private Long userId;

	private AccountIdentity(Builder builder) {
		this.uidPrefix = builder.uidPrefix;
		this.id = builder.id;
		this.userId = builder.userId;
	}

	public static class Keys {
		public static final String PREFIX = "prefix";
		public static final String ID = "id";
		public static final String USER_ID = "user_id";
	}
	
	public AccountIdentity(JsonObject json) {
		uidPrefix = json.getString(Keys.PREFIX);
		id = json.getLong(Keys.ID);
		userId = json.getLong(Keys.USER_ID);
	}
	
	public JsonObject toJson() {
		var json = new JsonObject();
		if(null != uidPrefix) {
			json.put(Keys.PREFIX, uidPrefix);
		}
		
		if(null != id) {
			json.put(Keys.ID, id);
		}
		
		if(null != userId) {
			json.put(Keys.USER_ID, userId);
		}
		return json;
	}
	
	public String getUidPrefix() {
		return uidPrefix;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builderFrom(AccountIdentity accountIdentity) {
		return new Builder(accountIdentity);
	}

	public static final class Builder {
		private String uidPrefix;
		private Long id;
		private Long userId;

		private Builder() {
		}

		private Builder(AccountIdentity accountIdentity) {
			this.uidPrefix = accountIdentity.uidPrefix;
			this.id = accountIdentity.id;
			this.userId = accountIdentity.userId;
		}

		public Builder withUidPrefix(String uidPrefix) {
			this.uidPrefix = uidPrefix;
			return this;
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder withUserId(Long userId) {
			this.userId = userId;
			return this;
		}

		public AccountIdentity build() {
			return new AccountIdentity(this);
		}
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("uidPrefix", uidPrefix).add("id", id).add("userId", userId).toString();
	}

	@GenIgnore
	public synchronized String getUidToken() {
		return Uid.builder().withId(getId()).withPrefix(getUidPrefix()).build().toUidToken();
	}
}
