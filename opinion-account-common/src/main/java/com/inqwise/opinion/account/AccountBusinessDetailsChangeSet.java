package com.inqwise.opinion.account;

public interface AccountBusinessDetailsChangeSet {
	Long getId();
	String getBusinessCompanyName();
	String getBusinessFirstName();
	String getBusinessLastName();
	String getBusinessAddress1();
	String getBusinessAddress2();
	String getBusinessCity();
	Integer getBusinessCountryId();
	Integer getBusinessStateId();
	String getBusinessPostalCode();
	String getBusinessPhone1();
}
