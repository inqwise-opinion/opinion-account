package com.inqwise.opinion.account;

import com.google.common.base.MoreObjects;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * AccountBusinessDetails.
 */
@DataObject
public class AccountBusinessDetails {
	public static class Keys {
		public static final String COMPANY_NAME = "business_company_name";
		public static final String FIRST_NAME = "business_first_name";
		public static final String LAST_NAME = "business_last_name";
		public static final String ADDRESS1 = "business_address1";
		public static final String ADDRESS2 = "business_address2";
		public static final String CITY = "business_city";
		public static final String COUNTRY_ID = "business_country_id";
		public static final String STATE_ID = "business_state_id";
		public static final String POSTAL_CODE = "business_postal_code";
		public static final String PHONE1 = "business_phone1";
	}

	private final String companyName;
	private final String firstName;
	private final String lastName;
	private final String address1;
	private final String address2;
	private final String city;
	private final Integer countryId;
	private final Integer stateId;
	private final String postalCode;
	private final String phone1;

	/**
	 * Constructs AccountBusinessDetails.
	 */
	private AccountBusinessDetails(Builder builder) {
		this.companyName = builder.companyName;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.address1 = builder.address1;
		this.address2 = builder.address2;
		this.city = builder.city;
		this.countryId = builder.countryId;
		this.stateId = builder.stateId;
		this.postalCode = builder.postalCode;
		this.phone1 = builder.phone1;
	}

	/**
	 * Constructs AccountBusinessDetails.
	 */
	public AccountBusinessDetails(JsonObject json) {
		companyName = json.getString(Keys.COMPANY_NAME);
		firstName = json.getString(Keys.FIRST_NAME);
		lastName = json.getString(Keys.LAST_NAME);
		address1 = json.getString(Keys.ADDRESS1);
		address2 = json.getString(Keys.ADDRESS2);
		city = json.getString(Keys.CITY);
		countryId = json.getInteger(Keys.COUNTRY_ID);
		stateId = json.getInteger(Keys.STATE_ID);
		postalCode = json.getString(Keys.POSTAL_CODE);
		phone1 = json.getString(Keys.PHONE1);
	}

	/**
	 * getCompanyName.
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * getFirstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * getLastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * getAddress1.
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * getAddress2.
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * getCity.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * getCountryId.
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * getStateId.
	 */
	public Integer getStateId() {
		return stateId;
	}

	/**
	 * getPostalCode.
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * getPhone1.
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * toJson.
	 */
	public JsonObject toJson() {
		var json = new JsonObject();
		if (null != companyName) {
			json.put(Keys.COMPANY_NAME, companyName);
		}
		if (null != firstName) {
			json.put(Keys.FIRST_NAME, firstName);
		}
		if (null != lastName) {
			json.put(Keys.LAST_NAME, lastName);
		}
		if (null != address1) {
			json.put(Keys.ADDRESS1, address1);
		}
		if (null != address2) {
			json.put(Keys.ADDRESS2, address2);
		}
		if (null != city) {
			json.put(Keys.CITY, city);
		}
		if (null != countryId) {
			json.put(Keys.COUNTRY_ID, countryId);
		}
		if (null != stateId) {
			json.put(Keys.STATE_ID, stateId);
		}
		if (null != postalCode) {
			json.put(Keys.POSTAL_CODE, postalCode);
		}
		if (null != phone1) {
			json.put(Keys.PHONE1, phone1);
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
	public static Builder builderFrom(AccountBusinessDetails details) {
		return new Builder(details);
	}

	public static final class Builder {
		private String companyName;
		private String firstName;
		private String lastName;
		private String address1;
		private String address2;
		private String city;
		private Integer countryId;
		private Integer stateId;
		private String postalCode;
		private String phone1;

		/**
		 * Builder.
		 */
		private Builder() {
		}

		/**
		 * Builder.
		 */
		private Builder(AccountBusinessDetails details) {
			this.companyName = details.companyName;
			this.firstName = details.firstName;
			this.lastName = details.lastName;
			this.address1 = details.address1;
			this.address2 = details.address2;
			this.city = details.city;
			this.countryId = details.countryId;
			this.stateId = details.stateId;
			this.postalCode = details.postalCode;
			this.phone1 = details.phone1;
		}

		/**
		 * withCompanyName.
		 */
		public Builder withCompanyName(String companyName) {
			this.companyName = companyName;
			return this;
		}

		/**
		 * withFirstName.
		 */
		public Builder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * withLastName.
		 */
		public Builder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		/**
		 * withAddress1.
		 */
		public Builder withAddress1(String address1) {
			this.address1 = address1;
			return this;
		}

		/**
		 * withAddress2.
		 */
		public Builder withAddress2(String address2) {
			this.address2 = address2;
			return this;
		}

		/**
		 * withCity.
		 */
		public Builder withCity(String city) {
			this.city = city;
			return this;
		}

		/**
		 * withCountryId.
		 */
		public Builder withCountryId(Integer countryId) {
			this.countryId = countryId;
			return this;
		}

		/**
		 * withStateId.
		 */
		public Builder withStateId(Integer stateId) {
			this.stateId = stateId;
			return this;
		}

		/**
		 * withPostalCode.
		 */
		public Builder withPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		/**
		 * withPhone1.
		 */
		public Builder withPhone1(String phone1) {
			this.phone1 = phone1;
			return this;
		}

		/**
		 * build.
		 */
		public AccountBusinessDetails build() {
			return new AccountBusinessDetails(this);
		}
	}

	/**
	 * toString.
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("companyName", companyName)
				.add("firstName", firstName)
				.add("lastName", lastName)
				.add("address1", address1)
				.add("address2", address2)
				.add("city", city)
				.add("countryId", countryId)
				.add("stateId", stateId)
				.add("postalCode", postalCode)
				.add("phone1", phone1)
				.toString();
	}
}
