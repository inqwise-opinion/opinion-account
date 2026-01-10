package com.inqwise.opinion.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.inqwise.opinion.common.OpinionModule;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLBuilder;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;

class Module extends OpinionModule {

	private static final Logger logger = LogManager.getLogger(Module.class);
	
	private static final String STORE_KEY = "store";
	private static final String MYSQL_KEY = "mysql";
	private static final long ONE_MINUTE = 60000l;
	private static final String SQL_CONNECT_OPTIONS = "SqlConnectOptions";

	public Module(Vertx vertx, JsonObject config) {
		super(vertx, config);
	}
	
	@Override
	protected void configure() {
		bind(OpinionAccountConfig.class).toInstance(new OpinionAccountConfig(config()));
		bind(AccountService.class).to(DefaultAccountService.class);
		bind(PoolOptions.class).toInstance(new PoolOptions().setMaxSize(5));
	}
	
	@Provides
	@Named(SQL_CONNECT_OPTIONS)
	@Singleton
	private ConfigRetriever provideConnectOptionsRetreaver() {
		logger.debug("config:'{}'", config());
		var mysqlJson = config().getJsonObject(MYSQL_KEY);
		Preconditions.checkNotNull(mysqlJson, "'%s' config is missing", MYSQL_KEY);
		var storeJson = mysqlJson.getJsonObject(STORE_KEY);
		var options = new ConfigRetrieverOptions()
				.setIncludeDefaultStores(false)
				.setScanPeriod(ONE_MINUTE);
		
		if(null == storeJson) {
			logger.debug("no '{}' section found. internal config is used", STORE_KEY);
			options.addStore(new ConfigStoreOptions().setType("json").setConfig(mysqlJson));
		} else {
			options.addStore(new ConfigStoreOptions(storeJson));
		}
		return ConfigRetriever.create(vertx, options);
	}
	
	@Provides
	@Singleton
	private Pool providePooledClient(@Named(SQL_CONNECT_OPTIONS) ConfigRetriever configRetriever, PoolOptions poolOptions) {
		return MySQLBuilder.pool()
			.using(vertx)
			.with(poolOptions)
			.connectingTo(() -> configRetriever.getConfig().map(MySQLConnectOptions::new))
			.build();
	}
	
	 
}
