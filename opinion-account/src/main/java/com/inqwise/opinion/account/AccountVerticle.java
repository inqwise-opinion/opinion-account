package com.inqwise.opinion.account;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.inqwise.errors.Throws;

import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.openapi.router.RouterBuilder;
import io.vertx.openapi.contract.OpenAPIContract;
import io.vertx.openapi.validation.RequestValidator;
import io.vertx.openapi.validation.ValidatedRequest;

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
		
		return routerBuilder().compose(router -> {
			server = vertx.createHttpServer(new HttpServerOptions().setPort(config.httpPort()).setHost(config.httpHost()));
	        return server.requestHandler(router).listen();
		});
		
	}
	
	@Override
	public Future<?> stop() throws Exception {
		return server.close();
	}
	
	private Future<Router> routerBuilder() {
		return getContract().
			map(contract -> RouterBuilder.create(vertx, contract))
			.map(this::customizeRouterBuilder)
			.map(RouterBuilder::createRouter)
			.map(this::customizeRouter);
	}

	private Router customizeRouter(Router router) {
		//TODO:
		//router.handleFailure(ctx -> {
			
		//});
		
		return router;
	}
	
	private RouterBuilder customizeRouterBuilder(RouterBuilder routerBuilder) {
		routerBuilder.getRoute("createAccount").addHandler(context -> {
			ValidatedRequest validatedRequest =
				    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
			CreateRequest request = new CreateRequest(validatedRequest.getBody().getJsonObject());
			
			logger.debug("create account request: {}", request);
			//TODO: create account logic
			
			return
			accountService.create(request)
			.compose(res -> {
				context
	            .response()
	            .setStatusCode(200)
	            .end();
			});
		});
		return routerBuilder;
	}
	
	private Future<OpenAPIContract> getContract() {
		return OpenAPIContract.from(vertx, "accounts.yaml");
	}
}
