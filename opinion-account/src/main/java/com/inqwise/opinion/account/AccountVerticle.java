package com.inqwise.opinion.account;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Guice;

import io.vertx.core.Future;
import io.vertx.core.VerticleBase;

public class AccountVerticle extends VerticleBase {
	private static final Logger logger = LogManager.getLogger(AccountVerticle.class);
	
	@Override
	public Future<?> start() throws Exception {
		logger.info("AccountVerticle - start");
		Guice.createInjector(List.of(new Module(vertx))).injectMembers(this);
		return super.start();
	}
}
