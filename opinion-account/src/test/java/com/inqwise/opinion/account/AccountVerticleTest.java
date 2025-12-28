package com.inqwise.opinion.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.vertx.core.Deployable;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

@ExtendWith(VertxExtension.class)
class AccountVerticleTest {
	private static final Logger logger = LogManager.getLogger(AccountVerticleTest.class);
	
	@BeforeEach
	void setUp(Vertx vertx) throws Exception {
	}
	
	@Test
	void deploysVerticle(Vertx vertx, VertxTestContext testContext) {
		logger.debug("deploysVerticle");
		vertx
			.deployVerticle(AccountVerticle.class, new DeploymentOptions())
			.onComplete(testContext.succeedingThenComplete());
	}
}
