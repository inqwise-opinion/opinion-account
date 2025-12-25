package com.inqwise.opinion.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

@ExtendWith(VertxExtension.class)
class MainVerticleTest {
	private static final Logger logger = LogManager.getLogger(MainVerticleTest.class);
	
	@BeforeEach
	void setUp(Vertx vertx) throws Exception {
	}
	
	@Test
	void deploysVerticle(Vertx vertx, VertxTestContext testContext) {
		logger.debug("deploysVerticle");
		vertx
			.deployVerticle(new MainVerticle())
			.onComplete(testContext.succeedingThenComplete());
	}
}
