package com.inqwise.opinion.account;

import com.inqwise.opinion.common.Uid;

public interface AccountIdentifiable {

	String getUidPrefix();

	Long getId();

	default Long getUserId() {
		return null;
	}

	default String getUidToken() {
		return Uid.builder().withId(getId()).withPrefix(getUidPrefix()).build().toUidToken();
	}

}