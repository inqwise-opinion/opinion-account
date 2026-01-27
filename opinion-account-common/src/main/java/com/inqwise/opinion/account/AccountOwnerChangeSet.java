package com.inqwise.opinion.account;

/**
 * AccountOwnerChangeSet.
 */
public interface AccountOwnerChangeSet {
	Long getSourceId();
	Long getOwnerId();
	Long getId();
	Long getBackofficeUserId();
}
