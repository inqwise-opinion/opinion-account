package com.inqwise.opinion.account;

public interface AccountDetailsChangeSet {
	Long getId();
	String getComments();
	Integer getTimezoneId();
	String getAccountName();
	Boolean getIsActive();
	Long getOwnerId();
	Boolean getIncludeDepositBounds();
	Integer getMinDepositAmount();
	Integer getMaxDepositAmount();
}
