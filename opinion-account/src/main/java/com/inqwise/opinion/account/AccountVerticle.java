package com.inqwise.opinion.account;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.inqwise.opinion.common.RestApiServerOptions;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

public class AccountVerticle extends VerticleBase {

	private static final Logger logger = LogManager.getLogger(AccountVerticle.class);

	@Inject
	private RestApiServerOptions config;
	
	@Inject 
	private AccountOpenApiRouterBuilder routerBuilder;
	
	private HttpServer server;

	private ConfigRetriever retriever;

	@Override
	public Future<?> start() throws Exception {
		logger.info("AccountVerticle - start");
		
		retriever = ConfigRetriever.create(vertx);
		
		return retriever.getConfig().compose(configJson -> {
			logger.debug("config retrieved: '{}'", configJson);
			
			Guice.createInjector(List.of(new Module(vertx, configJson))).injectMembers(this);
			
			return routerBuilder
				.createRouter()
				.compose(router -> {
				server = vertx.createHttpServer(
					new HttpServerOptions()
						.setPort(Objects.requireNonNullElse(config.getHttpPort(), 8080))
						.setHost(Objects.requireNonNullElse(config.getHttpHost(), "127.0.0.1"))
				);
				return server.requestHandler(router).listen();
			});
		});
	}

	@Override
	public Future<?> stop() throws Exception {
		Future<Void> lastFuture = Future.succeededFuture();
		
		if(null != retriever) {
			lastFuture = lastFuture.compose(nil -> retriever.close());
		}
		
		if(null != server) {
			lastFuture = lastFuture.compose(nil -> server.close());
		}
		return lastFuture.recover(ex->{
			logger.error("Error on stop", ex);
			return Future.succeededFuture();
		});
	}
}
