package com.inqwise.opinion.account;

public interface AccountDetailsChangeSet {
	Long getId();
	String getDetails();
	Integer getTimezoneId();
	String getName();
	Boolean getIsActive();
	Boolean getIncludeDepositBounds();
	Integer getMinDepositAmount();
	Integer getMaxDepositAmount();
	
	
}
