package com.inqwise.opinion.account;

import com.inqwise.opinion.common.Formatters;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import com.google.common.base.MoreObjects;

/**
 * SearchRequest.
 */
@DataObject
public class SearchRequest implements AccountUserProductCriteria, ServicePackageExpiryCriteria {
	
	private Integer userId;
	private Integer productId;
	private Boolean includeNonActive;
	private Integer top;
	private LocalDateTime from;
	private LocalDateTime to;
	private LocalDateTime expiryAt;
	private List<Long> ids;

	/**
	 * Constructs SearchRequest.
	 */
	private SearchRequest(Builder builder) {
		this.userId = builder.userId;
		this.productId = builder.productId;
		this.includeNonActive = builder.includeNonActive;
		this.top = builder.top;
		this.from = builder.from;
		this.to = builder.to;
		this.expiryAt = builder.expiryAt;
		this.ids = builder.ids;
	}

	public static class Keys {
		public static final String USER_ID = "user_id";
		public static final String PRODUCT_ID = "product_id";
		public static final String INCLUDE_NON_ACTIVE = "include_non_active";
		public static final String TOP = "top";
		public static final String FROM = "from";
		public static final String TO = "to";
		public static final String IDS = "ids";
		public static final String EXPIRY_AT = "expiry_at";
	}

	/**
	 * Constructs SearchRequest.
	 */
	public SearchRequest(JsonObject json) {
		userId = json.getInteger(Keys.USER_ID);
		productId = json.getInteger(Keys.PRODUCT_ID);
		includeNonActive = json.getBoolean(Keys.INCLUDE_NON_ACTIVE);
		top = json.getInteger(Keys.TOP);
		from = Optional.ofNullable(json.getString(Keys.FROM)).map(Formatters::parseDateTime).orElse(null);
		to = Optional.ofNullable(json.getString(Keys.TO)).map(Formatters::parseDateTime).orElse(null);
		var idsArray = json.getJsonArray(Keys.IDS);
		if(null != idsArray) {
			ids = idsArray.stream()
				.map(SearchRequest::toLong)
				.filter(Objects::nonNull)
				.toList();
		}
		expiryAt = Optional.ofNullable(json.getString(Keys.EXPIRY_AT)).map(Formatters::parseDateTime).orElse(null);
	}

	/**
	 * toJson.
	 */
	public JsonObject toJson() {
		var json = new JsonObject();
		
		if(null != userId) {
			json.put(Keys.USER_ID, userId);
		}
		
		if(null != productId) {
			json.put(Keys.PRODUCT_ID, productId);
		}
		
		if(null != includeNonActive) {
			json.put(Keys.INCLUDE_NON_ACTIVE, includeNonActive);
		}
		
		if(null != top) {
			json.put(Keys.TOP, top);
		}
		
		if(null != from) {
			json.put(Keys.FROM, Formatters.formatDateTime(from));
		}
		
		if(null != to) {
			json.put(Keys.TO, Formatters.formatDateTime(to));
		}
		
		if(null != ids && !ids.isEmpty()) {
			json.put(Keys.IDS, ids);
		}
		
		if(null != expiryAt) {
			json.put(Keys.EXPIRY_AT, Formatters.formatDateTime(expiryAt));
		}
		
		return json;
	}

	/**
	 * toLong.
	 */
	private static Long toLong(Object value) {
		if(value instanceof Number number) {
			return number.longValue();
		}
		
		if(value instanceof String str && !str.isBlank()) {
			return Long.valueOf(str);
		}
		
		return null;
	}

	/**
	 * getFrom.
	 */
	public LocalDateTime getFrom() {
		return from;
	}
	
	/**
	 * getIds.
	 */
	public List<Long> getIds() {
		return ids;
	}
	
	/**
	 * getIncludeNonActive.
	 */
	public Boolean getIncludeNonActive() {
		return includeNonActive;
	}
	
	/**
	 * getProductId.
	 */
	@Override
	public Integer getProductId() {
		return productId;
	}
	
	/**
	 * getTo.
	 */
	public LocalDateTime getTo() {
		return to;
	}
	/**
	 * getSize.
	 */
	public Integer getSize() {
		return top;
	}
	
	/**
	 * getUserId.
	 */
	@Override
	public Integer getUserId() {
		return userId;
	}
	
	/**
	 * getExpiryAt.
	 */
	@Override
	public LocalDateTime getExpiryAt() {
		return expiryAt;
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
	public static Builder builderFrom(SearchRequest searchRequest) {
		return new Builder(searchRequest);
	}

	public static final class Builder {
		private Integer userId;
		private Integer productId;
		private Boolean includeNonActive;
		private Integer top;
		private LocalDateTime from;
		private LocalDateTime to;
		private LocalDateTime expiryAt;
		/**
		 * emptyList.
		 */
		private List<Long> ids = Collections.emptyList();

		/**
		 * Builder.
		 */
		private Builder() {
		}

		/**
		 * Builder.
		 */
		private Builder(SearchRequest searchRequest) {
			this.userId = searchRequest.userId;
			this.productId = searchRequest.productId;
			this.includeNonActive = searchRequest.includeNonActive;
			this.top = searchRequest.top;
			this.from = searchRequest.from;
			this.to = searchRequest.to;
			this.expiryAt = searchRequest.expiryAt;
			this.ids = searchRequest.ids;
		}

		/**
		 * withUserId.
		 */
		public Builder withUserId(Integer userId) {
			this.userId = userId;
			return this;
		}

		/**
		 * withProductId.
		 */
		public Builder withProductId(Integer productId) {
			this.productId = productId;
			return this;
		}

		/**
		 * withIncludeNonActive.
		 */
		public Builder withIncludeNonActive(Boolean includeNonActive) {
			this.includeNonActive = includeNonActive;
			return this;
		}

		/**
		 * withTop.
		 */
		public Builder withTop(Integer top) {
			this.top = top;
			return this;
		}

		/**
		 * withFrom.
		 */
		public Builder withFrom(LocalDateTime from) {
			this.from = from;
			return this;
		}

		/**
		 * withTo.
		 */
		public Builder withTo(LocalDateTime to) {
			this.to = to;
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
		 * withIds.
		 */
		public Builder withIds(List<Long> ids) {
			this.ids = ids;
			return this;
		}

		/**
		 * build.
		 */
		public SearchRequest build() {
			return new SearchRequest(this);
		}
	}

	/**
	 * toString.
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("userId", userId).add("productId", productId)
				.add("includeNonActive", includeNonActive).add("top", top).add("from", from).add("to", to)
				.add("expiryAt", expiryAt).add("ids", ids).toString();
	}
	
	
}
