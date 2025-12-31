package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import com.google.common.base.MoreObjects;

@DataObject
public class AccountIdentity {
	private String uidToken;

	private AccountIdentity(Builder builder) {
		this.uidToken = builder.uidToken;
	}
	
	public static class Keys {
		public static final String UID = "uid";
	}
	
	public AccountIdentity(JsonObject json) {
		uidToken = json.getString(Keys.UID);
	}
	
	public JsonObject toJson() {
		var json = new JsonObject();
		if(null != uidToken) {
			json.put(Keys.UID, uidToken);
		}
		return json;
	}
	
	public String getUidToken() {
		return uidToken;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builderFrom(AccountIdentity accountIdentity) {
		return new Builder(accountIdentity);
	}

	public static final class Builder {
		private String uidToken;

		private Builder() {
		}

		private Builder(AccountIdentity accountIdentity) {
			this.uidToken = accountIdentity.uidToken;
		}

		public Builder withUidToken(String uidToken) {
			this.uidToken = uidToken;
			return this;
		}

		public AccountIdentity build() {
			return new AccountIdentity(this);
		}
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("uidToken", uidToken).toString();
	}
	
}
