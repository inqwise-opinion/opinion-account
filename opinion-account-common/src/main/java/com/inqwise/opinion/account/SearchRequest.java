package com.inqwise.opinion.account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import com.google.common.base.MoreObjects;

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

	private static Long toLong(Object value) {
		if(value instanceof Number number) {
			return number.longValue();
		}
		
		if(value instanceof String str && !str.isBlank()) {
			return Long.valueOf(str);
		}
		
		return null;
	}

	public LocalDateTime getFrom() {
		return from;
	}
	
	public List<Long> getIds() {
		return ids;
	}
	
	public Boolean getIncludeNonActive() {
		return includeNonActive;
	}
	
	@Override
	public Integer getProductId() {
		return productId;
	}
	
	public LocalDateTime getTo() {
		return to;
	}
	public Integer getSize() {
		return top;
	}
	
	@Override
	public Integer getUserId() {
		return userId;
	}
	
	@Override
	public LocalDateTime getExpiryAt() {
		return expiryAt;
	}

	public static Builder builder() {
		return new Builder();
	}

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
		private List<Long> ids = Collections.emptyList();

		private Builder() {
		}

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

		public Builder withUserId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public Builder withProductId(Integer productId) {
			this.productId = productId;
			return this;
		}

		public Builder withIncludeNonActive(Boolean includeNonActive) {
			this.includeNonActive = includeNonActive;
			return this;
		}

		public Builder withTop(Integer top) {
			this.top = top;
			return this;
		}

		public Builder withFrom(LocalDateTime from) {
			this.from = from;
			return this;
		}

		public Builder withTo(LocalDateTime to) {
			this.to = to;
			return this;
		}

		public Builder withExpiryAt(LocalDateTime expiryAt) {
			this.expiryAt = expiryAt;
			return this;
		}

		public Builder withIds(List<Long> ids) {
			this.ids = ids;
			return this;
		}

		public SearchRequest build() {
			return new SearchRequest(this);
		}
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("userId", userId).add("productId", productId)
				.add("includeNonActive", includeNonActive).add("top", top).add("from", from).add("to", to)
				.add("expiryAt", expiryAt).add("ids", ids).toString();
	}
	
	
}
