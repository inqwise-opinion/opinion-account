package com.inqwise.opinion.account;

import java.time.LocalDateTime;

/**
 * AccountServicePackageChangeSet.
 */
public interface AccountServicePackageChangeSet {
	Long getAccountId();
	Integer getServicePackageId();
	LocalDateTime getExpiryAt();
	Integer getMaxUsers();
}
