package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import com.google.common.base.MoreObjects;

@DataObject
public class ModifyRequest {
	
	private String uidToken;

	private ModifyRequest(Builder builder) {
		this.uidToken = builder.uidToken;
	}
	public static class Keys {
		public static final String UID = "uid";
	}

	public ModifyRequest(JsonObject json) {
		uidToken = json.getString(Keys.UID);
	}

	public String getUidToken() {
		return uidToken;
	}
	
	public JsonObject toJson() {
		var json = new JsonObject();
		if(null != uidToken) {
			json.put(Keys.UID, uidToken);
		}
		return json;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builderFrom(ModifyRequest modifyRequest) {
		return new Builder(modifyRequest);
	}

	public static final class Builder {
		private String uidToken;

		private Builder() {
		}

		private Builder(ModifyRequest modifyRequest) {
			this.uidToken = modifyRequest.uidToken;
		}

		public Builder withUidToken(String uidToken) {
			this.uidToken = uidToken;
			return this;
		}

		public ModifyRequest build() {
			return new ModifyRequest(this);
		}
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("uidToken", uidToken).toString();
	}
}
