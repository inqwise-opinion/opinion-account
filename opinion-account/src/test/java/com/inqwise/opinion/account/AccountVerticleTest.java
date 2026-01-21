package com.inqwise.opinion.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.ext.web.client.WebClient;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

@ExtendWith(VertxExtension.class)
class AccountVerticleTest {
	private static final Logger logger = LogManager.getLogger(AccountVerticleTest.class);
	private WebClient client;
	
	@BeforeEach
	@DisplayName("Deploy a verticle")
	void setUp(Vertx vertx, VertxTestContext testContext) throws Exception {
		logger.debug("deploysVerticle");
		vertx
			.deployVerticle(AccountVerticle.class, new DeploymentOptions())
			.onComplete(testContext.succeedingThenComplete());
	}
	
	@Test
	@DisplayName("A http server response test")
	void http_server_response_check(Vertx vertx, VertxTestContext testContext) {
		client = WebClient.create(vertx);
		client.get(8080, "127.0.0.1", "/status")
		.send()
		.expecting(HttpResponseExpectation.SC_OK)
		.expecting(HttpResponseExpectation.JSON)
		.onComplete(testContext.succeeding(resp -> testContext.verify(() -> {
			var json = resp.bodyAsJsonObject();
			Assertions.assertTrue(json.isEmpty());
			testContext.completeNow();
	      })));
	}
}
