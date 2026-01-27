package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import com.google.common.base.MoreObjects;

/**
 * CreateResult.
 */
@DataObject
public class CreateResult {
	
	public static class Keys {
		public static String UID_TOKEN = "uid";
		public static final String ID = "id";
	}

	private final String uidToken;
	private final Long id;

	/**
	 * Constructs CreateResult.
	 */
	private CreateResult(Builder builder) {
		this.uidToken = builder.uidToken;
		this.id = builder.id;
	}
	
	/**
	 * Constructs CreateResult.
	 */
	public CreateResult(JsonObject json) {
		uidToken = json.getString(Keys.UID_TOKEN);
		id = json.getLong(Keys.ID);
	}

	/**
	 * getUidToken.
	 */
	public String getUidToken() {
		return uidToken;
	}
	
	/**
	 * getId.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * toJson.
	 */
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

	/**
	 * toString.
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("uidToken", uidToken).add("id", id).toString();
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
	public static Builder builderFrom(CreateResult createResult) {
		return new Builder(createResult);
	}

	public static final class Builder {
		private String uidToken;
		private Long id;

		/**
		 * Builder.
		 */
		private Builder() {
		}

		/**
		 * Builder.
		 */
		private Builder(CreateResult createResult) {
			this.uidToken = createResult.uidToken;
			this.id = createResult.id;
		}

		/**
		 * withUidToken.
		 */
		public Builder withUidToken(String uidToken) {
			this.uidToken = uidToken;
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
		 * build.
		 */
		public CreateResult build() {
			return new CreateResult(this);
		}
	}
}
