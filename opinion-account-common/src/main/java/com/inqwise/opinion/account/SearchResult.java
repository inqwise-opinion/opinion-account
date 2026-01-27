package com.inqwise.opinion.account;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * SearchResult.
 */
@DataObject
public class SearchResult {
	
	public static class Keys {
		public static String ITEMS = "items";
		public static String SIZE = "size";
	}

	private List<Account> items;
	private Long size;

	/**
	 * Constructs SearchResult.
	 */
	private SearchResult(Builder builder) {
		this.items = builder.items;
		this.size = builder.size;
	}
	
	/**
	 * Constructs SearchResult.
	 */
	public SearchResult(JsonObject json) {
		items = Optional.ofNullable(json.getJsonArray(Keys.ITEMS)).map(arr -> arr.stream().map(o -> (JsonObject)o).map(Account::new).toList()).orElse(null);
		size = json.getLong(Keys.SIZE);
	}

	/**
	 * getItems.
	 */
	public List<Account> getItems() {
		return items;
	}
	
	/**
	 * getSize.
	 */
	public Long getSize() {
		return size;
	}
	
	/**
	 * toJson.
	 */
	public JsonObject toJson() {
		var json = new JsonObject();
		if(null != items) {
			json.put(Keys.ITEMS, Lists.transform(items, Account::toJson));
		}
		
		if(null != size) {
			json.put(Keys.SIZE, size);
		}
		
		return json;
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
	public static Builder builderFrom(SearchResult searchResult) {
		return new Builder(searchResult);
	}

	public static final class Builder {
		/**
		 * emptyList.
		 */
		private List<Account> items = Collections.emptyList();
		private Long size;

		/**
		 * Builder.
		 */
		private Builder() {
		}

		/**
		 * Builder.
		 */
		private Builder(SearchResult searchResult) {
			this.items = searchResult.items;
			this.size = searchResult.size;
		}

		/**
		 * withItems.
		 */
		public Builder withItems(List<Account> items) {
			this.items = items;
			return this;
		}

		/**
		 * withSize.
		 */
		public Builder withSize(Long size) {
			this.size = size;
			return this;
		}

		/**
		 * build.
		 */
		public SearchResult build() {
			return new SearchResult(this);
		}
	}

	/**
	 * toString.
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("items", items).add("size", size).toString();
	}
	
	
}
