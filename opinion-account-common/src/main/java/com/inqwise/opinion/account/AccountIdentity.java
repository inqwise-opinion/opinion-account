package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import com.google.common.base.MoreObjects;

@DataObject
public class AccountIdentity {
	private String uidToken;
	private Long id;

	private AccountIdentity(Builder builder) {
		this.uidToken = builder.uidToken;
		this.id = builder.id;
	}

	public static class Keys {
		public static final String UID_TOKEN = "uid";
		public static final String ID = "id";
	}
	
	public AccountIdentity(JsonObject json) {
		uidToken = json.getString(Keys.UID_TOKEN);
		id = json.getLong(Keys.ID);
	}
	
	public JsonObject toJson() {
		var json = new JsonObject();
		if(null != uidToken) {
			json.put(Keys.UID_TOKEN, uidToken);
		}
		
		if(null != id) {
			json.put(Keys.ID, id);
		}
		return json;
	}
	
	public String getUidToken() {
		return uidToken;
	}
	
	public Long getId() {
		return id;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builderFrom(AccountIdentity accountIdentity) {
		return new Builder(accountIdentity);
	}

	public static final class Builder {
		private String uidToken;
		private Long id;

		private Builder() {
		}

		private Builder(AccountIdentity accountIdentity) {
			this.uidToken = accountIdentity.uidToken;
			this.id = accountIdentity.id;
		}

		public Builder withUidToken(String uidToken) {
			this.uidToken = uidToken;
			return this;
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public AccountIdentity build() {
			return new AccountIdentity(this);
		}
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("uidToken", uidToken).add("id", id).toString();
	}
	
}
