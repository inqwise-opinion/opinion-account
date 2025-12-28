package com.inqwise.opinion.account;

import com.inqwise.opinion.common.OpinionModule;

import io.vertx.core.Vertx;

class Module extends OpinionModule {
	public Module(Vertx vertx) {
		super(vertx);
	}
	
	@Override
	protected void configure() {
		bind(OpinionAccountConfig.class).toInstance(new OpinionAccountConfig(vertx.getOrCreateContext().config()));
		bind(AccountService.class).to(DefaultAccountService.class);
	}
}
