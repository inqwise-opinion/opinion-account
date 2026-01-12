package com.inqwise.opinion.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.http.PoolOptions;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

@ExtendWith(VertxExtension.class)
class AccountVerticleTest {
	private static final Logger logger = LogManager.getLogger(AccountVerticleTest.class);
	
	@BeforeEach
	@DisplayName("Deploy a verticle")
	void setUp(Vertx vertx, VertxTestContext testContext) throws Exception {
		logger.debug("deployVerticle");
		vertx
			.deployVerticle(AccountVerticle.class, new DeploymentOptions())
			.onSuccess(res -> {
				logger.debug("verticle deployed");
			})
			.onComplete(testContext.succeedingThenComplete());
	}
	
	@AfterEach
	void shutDown() {
		logger.debug("shutDown");
	}
	
	@Test
	@DisplayName("A http server response test")
	void http_server_response_check(Vertx vertx, VertxTestContext testContext) {
		logger.debug("http_server_response_check - start");
		var client = vertx.createHttpClient();
		logger.debug("create request");
		client.request(HttpMethod.GET, 8080, "127.0.0.1", "/status")
		.compose(req -> {
			logger.debug("send request");
			return req.send()
				.expecting(HttpResponseExpectation.SC_OK)
				.expecting(HttpResponseExpectation.JSON)
				.compose(HttpClientResponse::body)
				.onComplete(res->{
					logger.debug("send success");
				}, ex-> {
					logger.error("send failed", ex);
				});
		})
		.map(buffer -> {
			var json = buffer.toJsonObject();
			Assertions.assertTrue(json.isEmpty());
			return buffer;
		})
		.onComplete(testContext.succeedingThenComplete());
	}
}
