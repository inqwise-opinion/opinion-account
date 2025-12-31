package com.inqwise.opinion.account;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Guice;
import com.google.inject.Inject;

import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

public class AccountVerticle extends VerticleBase {

    private static final Logger logger = LogManager.getLogger(AccountVerticle.class);

    @Inject
    private AccountService accountService;

    @Inject
    private OpinionAccountConfig config;

    private HttpServer server;

    @Override
    public Future<?> start() throws Exception {
        logger.info("AccountVerticle - start");
        
        Guice.createInjector(List.of(new Module(vertx))).injectMembers(this);

        return routerBuilder()
        	.createRouter()
        	.compose(router -> {
            server = vertx.createHttpServer(
                new HttpServerOptions()
                    .setPort(config.httpPort())
                    .setHost(config.httpHost())
            );
            return server.requestHandler(router).listen();
        });
    }

    @Override
    public Future<?> stop() throws Exception {
        return server.close();
    }

    private AccountOpenApiRouterBuilder routerBuilder() {
        return new AccountOpenApiRouterBuilder(
            vertx,
            accountService
        );
    }
}
