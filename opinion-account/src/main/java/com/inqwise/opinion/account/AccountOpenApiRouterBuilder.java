package com.inqwise.opinion.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.inqwise.opinion.common.Uid;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.openapi.router.RouterBuilder;
import io.vertx.openapi.contract.OpenAPIContract;
import io.vertx.openapi.validation.ValidatedRequest;

/**
 * Builds a Router instance backed by the OpenAPI description and wires handlers.
 */
public final class AccountOpenApiRouterBuilder {
	private static final String CREATE_ACCOUNT = "createAccount";

	private static final String ACCOUNTS_YAML = "accounts.yaml";

	private static final Logger logger = LogManager.getLogger(AccountOpenApiRouterBuilder.class);

	private final Vertx vertx;
	private final AccountService accountService;

	public AccountOpenApiRouterBuilder(Vertx vertx, AccountService accountService) {
		this.vertx = vertx;
		this.accountService = accountService;
	}

	public Future<Router> createRouter() {
		return getContract()
			.map(contract -> RouterBuilder.create(vertx, contract))
			.map(this::registerHandlers)
			.map(RouterBuilder::createRouter)
			.map(this::customizeRouter);
	}

	private Future<OpenAPIContract> getContract() {
		return OpenAPIContract.from(vertx, ACCOUNTS_YAML);
	}

	private RouterBuilder registerHandlers(RouterBuilder routerBuilder) {

		// create
		routerBuilder.getRoute(CREATE_ACCOUNT).addHandler(context -> {
			CreateRequest request = new CreateRequest(context.body().asJsonObject());
			accountService.create(request)
				.compose(res -> context.json(res))
				.onFailure(context::fail);
		});
		
		// show
		routerBuilder.getRoute("showAccountById").addHandler(context -> {
			ValidatedRequest validatedRequest =
				    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
			var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
			
			var identity = AccountIdentity.builder().withUidToken(uidToken).build();
			
			accountService.get(identity)
				.compose(res -> context.json(res.toJson()))
				.onFailure(context::fail);
		});
		
		// modify
		routerBuilder.getRoute("modifyAccountById").addHandler(context -> {
			ValidatedRequest validatedRequest =
				    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
			var request = new ModifyRequest(validatedRequest.getBody().getJsonObject());
						
			accountService.modify(request)
				.compose(res -> context.response().setStatusCode(204).end())
				.onFailure(context::fail);
		});
		
		// delete
		routerBuilder.getRoute("deleteAccountById").addHandler(context -> {
			ValidatedRequest validatedRequest =
				    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
			var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
			
			var identity = AccountIdentity.builder().withUidToken(uidToken).build();
			
			accountService.delete(identity)
				.compose(res -> context.response().setStatusCode(204).end())
				.onFailure(context::fail);
		});
		
		// search
		routerBuilder.getRoute("searchAccounts").addHandler(context -> {
			ValidatedRequest validatedRequest =
				    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
			var request = new SearchRequest(validatedRequest.getBody().getJsonObject());
			
			accountService.search(request)
				.compose(res -> context.json(res.toJson()))
				.onFailure(context::fail);
		});
		
		// status
		routerBuilder.getRoute("status").addHandler(context -> {
			logger.debug("status");
			accountService.status()
				.compose(res -> context.json(res))
				.onFailure(context::fail);
		});
		
		return routerBuilder;
	}

	private Router customizeRouter(Router router) {
		return router;
	}
}
