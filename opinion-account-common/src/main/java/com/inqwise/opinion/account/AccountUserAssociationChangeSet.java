package com.inqwise.opinion.account;

/**
 * AccountUserAssociationChangeSet.
 */
public interface AccountUserAssociationChangeSet {
	Long getSourceId();
	Long getUserId();
	Long getId();
	Long getBackofficeUserId();
}
