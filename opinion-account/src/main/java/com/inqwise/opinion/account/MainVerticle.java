package com.inqwise.opinion.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {
	private static final Logger logger = LogManager.getLogger(MainVerticle.class);
	private OpinionAccountConfig config;
	
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		logger.info("MainVerticle start");
		config = new OpinionAccountConfig(config());
		super.start(startPromise);
	}
	
}
