package com.inqwise.opinion.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.openapi.router.RouterBuilder;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import io.vertx.openapi.contract.OpenAPIContract;

@ExtendWith(VertxExtension.class)
@Disabled
class ColonPathMethodRoutingTest {
	private WebClient client;

	@BeforeEach
	void setUp(Vertx vertx) throws Exception {}

	@Test
	@DisplayName("Routes colon path segment only for expected HTTP method")
	void routesColonPathSegmentOnlyForExpectedMethod(Vertx vertx, VertxTestContext testContext) {
		var contract = new JsonObject()
			.put("openapi", "3.0.0")
			.put("info", new JsonObject()
				.put("title", "Probe API")
				.put("version", "1.0.0"))
			.put("paths", new JsonObject()
				.put("/probe:ping", new JsonObject()
					.put("post", new JsonObject()
						.put("operationId", "probePing")
						.put("responses", new JsonObject()
							.put("204", new JsonObject()
								.put("description", "Probe accepted"))))));

		OpenAPIContract.from(vertx, contract)
			.map(openApi -> RouterBuilder.create(vertx, openApi))
			.map(builder -> {
				builder.getRoute("probePing")
					.addHandler(ctx -> {
						System.out.println("probePing handler");
						ctx.response().setStatusCode(204).end();
					});
				return builder;
			})
			.map(RouterBuilder::createRouter)
			.compose(router -> startServer(vertx, router))
			.onComplete(testContext.succeeding(server -> {
				client = WebClient.create(vertx);
				client.post(server.actualPort(), "127.0.0.1", "/probe:ping")
					.send()
					.compose(postResp -> {
						testContext.verify(() -> Assertions.assertEquals(204, postResp.statusCode()));
						return client.post(server.actualPort(), "127.0.0.1", "/probe:pong").send();
					})
					.onComplete(ar -> {
						if (ar.failed()) {
							testContext.failNow(ar.cause());
							server.close();
							return;
						}
						testContext.verify(() -> Assertions.assertTrue(ar.result().statusCode() >= 400));
						testContext.completeNow();
						server.close();
					});
			}));
	}

	private static io.vertx.core.Future<io.vertx.core.http.HttpServer> startServer(Vertx vertx, Router router) {
		return vertx.createHttpServer().requestHandler(router).listen(0);
	}
}
