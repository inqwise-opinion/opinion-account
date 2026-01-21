package com.inqwise.opinion.account;

import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.inqwise.errors.ErrorCodes;
import com.inqwise.errors.ErrorTicket;
import com.inqwise.opinion.common.RestApiServerOptions;
import com.inqwise.opinion.common.Uid;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RequestBody;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.HttpException;
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

	@Inject
	private Vertx vertx;
	@Inject
	private AccountService accountService;
	@Inject 
	private RestApiServerOptions options;
	

	@Inject
	private AccountOpenApiRouterBuilder() {	
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
		routerBuilder.getRoute(CREATE_ACCOUNT).addHandler(this::createAccount);
		
		// show
		routerBuilder.getRoute("showAccountById").addHandler(this::showAccountByUid);
		
		// modify
		routerBuilder.getRoute("modifyAccountById").addHandler(this::modifyAccountByUid);
		
		// delete
		routerBuilder.getRoute("deleteAccountById").addHandler(this::deleteAccountByUid);
		
		// search
		routerBuilder.getRoute("searchAccounts").addHandler(this::searchAccounts);
		
		// status
		routerBuilder.getRoute("status").addHandler(this::getStatus);
		
		return routerBuilder;
	}

	private void createAccount(RoutingContext context) {
		logger.debug("cresteAccount");
		ValidatedRequest validatedRequest =
				context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);

		var body = validatedRequest.getBody();
		CreateRequest request = new CreateRequest(Objects.requireNonNull(body, "body is mandatory").getJsonObject());
		accountService.create(request)
			.compose(res -> context.json(res))
			.onFailure(context::fail);
		
	}

	private void getStatus(RoutingContext context) {
		logger.debug("getStatus");
		accountService.status()
			.compose(res -> context.json(res))
			.onFailure(context::fail);
		
	}

	private void searchAccounts(RoutingContext context) {
		logger.debug("searchAccounts");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var request = new SearchRequest(validatedRequest.getBody().getJsonObject());
		
		accountService.search(request)
			.compose(res -> context.json(res.toJson()))
			.onFailure(context::fail);
	}

	private void deleteAccountByUid(RoutingContext context) {
		logger.debug("deleteAccountByUid");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = Uid.parse(uidToken);
		var identity = AccountIdentity.builder()
				.withUidPrefix(uid.getPrefix())
				.withId(uid.getId())
				.build();
		
		accountService.delete(identity)
			.compose(res -> context.response().setStatusCode(204).end())
			.onFailure(context::fail);
	}

	private void modifyAccountByUid(RoutingContext context) {
		logger.debug("modifyAccountByUid");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = Uid.parse(uidToken);
		var request = new ModifyRequest(validatedRequest.getBody().getJsonObject());
		var builder = ModifyRequest.builderFrom(request);
		builder
		.withId(uid.getId())
		.withUidPrefix(uid.getPrefix());
		
		accountService.modify(builder.build())
			.compose(res -> context.response().setStatusCode(204).end())
			.onFailure(context::fail);
	}

	private void showAccountByUid(RoutingContext context) {
		logger.debug("showAccountByUid");
		ValidatedRequest validatedRequest =
			    context.get(RouterBuilder.KEY_META_DATA_VALIDATED_REQUEST);
		var uidToken = validatedRequest.getPathParameters().get("accountUid").getString();
		var uid = Uid.parse(uidToken);
		var identity = AccountIdentity.builder()
				.withUidPrefix(uid.getPrefix())
				.withId(uid.getId())
				.build();
		
		accountService.get(identity)
			.compose(res -> context.json(res.toJson()))
			.onFailure(context::fail);
	}

	private Router customizeRouter(Router router) {
		router.route()
		.failureHandler(new ExceptionNormalizerHandler())
		.failureHandler(new ExceptionLoggerHandler(Objects.requireNonNullElse(options.getLogErrorTickets(), false)))
		.failureHandler(new HttpErrorResponseHandler(Objects.requireNonNullElse(options.getPrintStacktrace(), false), Objects.requireNonNullElse(options.getSanitizeUnexpectedErrors(), false)));
		
		return router;
	}
}
