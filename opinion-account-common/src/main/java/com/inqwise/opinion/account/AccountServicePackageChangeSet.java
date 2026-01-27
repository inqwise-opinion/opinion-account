package com.inqwise.opinion.account;

import java.time.LocalDateTime;

public interface AccountServicePackageChangeSet {
	Long getAccountId();
	Integer getServicePackageId();
	LocalDateTime getExpiryAt();
	Integer getMaxUsers();
}
