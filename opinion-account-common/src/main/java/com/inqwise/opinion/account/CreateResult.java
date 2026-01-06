package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import com.google.common.base.MoreObjects;

@DataObject
public class CreateResult {
	
	public static class Keys {
		public static String UID_TOKEN = "uid";
		public static final String ID = "id";
	}

	private final String uidToken;
	private final Long id;

	private CreateResult(Builder builder) {
		this.uidToken = builder.uidToken;
		this.id = builder.id;
	}
	
	public CreateResult(JsonObject json) {
		uidToken = json.getString(Keys.UID_TOKEN);
		id = json.getLong(Keys.ID);
	}

	public String getUidToken() {
		return uidToken;
	}
	
	public Long getId() {
		return id;
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

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("uidToken", uidToken).add("id", id).toString();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builderFrom(CreateResult createResult) {
		return new Builder(createResult);
	}

	public static final class Builder {
		private String uidToken;
		private Long id;

		private Builder() {
		}

		private Builder(CreateResult createResult) {
			this.uidToken = createResult.uidToken;
			this.id = createResult.id;
		}

		public Builder withUidToken(String uidToken) {
			this.uidToken = uidToken;
			return this;
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public CreateResult build() {
			return new CreateResult(this);
		}
	}
}
