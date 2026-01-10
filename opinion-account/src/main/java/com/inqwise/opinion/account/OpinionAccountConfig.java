package com.inqwise.opinion.account;

import com.google.common.base.MoreObjects;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public final class OpinionAccountConfig {
	
	private final Integer httpPort;
	private final String httpHost;
	private final boolean configDefaultDeny;

	public static class Keys {
		public static final String HTTP_PORT = "http_port";
		public static final String HTTP_HOST = "http_host";
		public static final String CONFIG_DEFAULT_DENY = "config_default_deny";
	}
	
	public static final OpinionAccountConfig DEFAULTS = OpinionAccountConfig.builder().withHttpHost("localhost").withHttpPort(8080).withConfigDefaultDeny(false).build();

	private OpinionAccountConfig(Builder builder) {
		this.httpPort = builder.httpPort;
		this.httpHost = builder.httpHost;
		this.configDefaultDeny = builder.configDefaultDeny;
	}

	public OpinionAccountConfig(JsonObject json) {
		httpPort = json.getInteger(Keys.HTTP_PORT, DEFAULTS.httpPort());
		httpHost = json.getString(Keys.HTTP_HOST, DEFAULTS.httpHost());
		configDefaultDeny = json.getBoolean(Keys.CONFIG_DEFAULT_DENY, false);
	}

	public JsonObject toJson() {
		var json = new JsonObject();
		if(null != httpPort) {
			json.put(Keys.HTTP_PORT, httpPort);
		}
		
		if(null != httpHost) {
			json.put(Keys.HTTP_HOST, httpHost);
		}
		
		if(configDefaultDeny) {
			json.put(Keys.CONFIG_DEFAULT_DENY, true);
		}
		
		return json;
	}
		
	public int httpPort() {
		return httpPort;
	}

	public String httpHost() {
		
		return httpHost;
	}

	public boolean isConfigDefaultDeny() {
		return configDefaultDeny;
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static Builder builderFrom(OpinionAccountConfig opinionAccountConfig) {
		return new Builder(opinionAccountConfig);
	}

	public static final class Builder {
		private Integer httpPort;
		private String httpHost;
		private boolean configDefaultDeny;

		private Builder() {
		}

		private Builder(OpinionAccountConfig opinionAccountConfig) {
			this.httpPort = opinionAccountConfig.httpPort;
			this.httpHost = opinionAccountConfig.httpHost;
			this.configDefaultDeny = opinionAccountConfig.configDefaultDeny;
		}

		public Builder withHttpPort(Integer httpPort) {
			this.httpPort = httpPort;
			return this;
		}

		public Builder withHttpHost(String httpHost) {
			this.httpHost = httpHost;
			return this;
		}

		public Builder withConfigDefaultDeny(boolean configDefaultDeny) {
			this.configDefaultDeny = configDefaultDeny;
			return this;
		}

		public OpinionAccountConfig build() {
			return new OpinionAccountConfig(this);
		}
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("httpPort", httpPort).add("httpHost", httpHost)
				.add("configDefaultDeny", configDefaultDeny).toString();
	}
}
