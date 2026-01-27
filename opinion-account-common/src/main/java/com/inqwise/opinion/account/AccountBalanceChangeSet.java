package com.inqwise.opinion.account;

public interface AccountBalanceChangeSet {
	Long getId();
	Long getBackofficeUserId();
	String getDetails();
	Integer getAmount();
	AccountOperationType getOperationType();
	Long getSourceId();
	String getSessionId();
	String getGeoCountryCode();
	String getClientIp();
}
