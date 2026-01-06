package com.inqwise.opinion.account;

import com.google.common.base.MoreObjects;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class ModifyRequest {
	
	private String uidToken;
	private Long id;

	private ModifyRequest(Builder builder) {
		this.uidToken = builder.uidToken;
		this.id = builder.id;
	}

	public static class Keys {
		public static final String UID_TOKEN = "uid";
		public static final String ID = "id";
	}
	
	public ModifyRequest(JsonObject json) {
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

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("uidToken", uidToken).add("id", id).toString();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builderFrom(ModifyRequest modifyRequest) {
		return new Builder(modifyRequest);
	}

	public static final class Builder {
		private String uidToken;
		private Long id;

		private Builder() {
		}

		private Builder(ModifyRequest modifyRequest) {
			this.uidToken = modifyRequest.uidToken;
			this.id = modifyRequest.id;
		}

		public Builder withUidToken(String uidToken) {
			this.uidToken = uidToken;
			return this;
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public ModifyRequest build() {
			return new ModifyRequest(this);
		}
	}

	
}
