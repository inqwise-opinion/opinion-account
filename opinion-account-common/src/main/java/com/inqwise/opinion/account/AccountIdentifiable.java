package com.inqwise.opinion.account;

import com.inqwise.opinion.common.Uid;

/**
 * AccountIdentifiable.
 */
public interface AccountIdentifiable {

	String getUidPrefix();

	Long getId();

	/**
	 * getUserId.
	 */
	default Long getUserId() {
		return null;
	}

	/**
	 * getUidToken.
	 */
	default String getUidToken() {
		return Uid.builder().withId(getId()).withPrefix(getUidPrefix()).build().toUidToken();
	}

}