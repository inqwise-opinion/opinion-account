package com.inqwise.opinion.account;

public interface AccountBalanceChangeSet {
	Long getId();
	Long getBackofficeUserId();
	String getComments();
	Integer getAmount();
	Integer getAccopTypeId();
	String getSourceGuid();
	String getSessionId();
	String getGeoCountryCode();
	String getClientIp();
}
