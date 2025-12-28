package com.inqwise.opinion.account;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public final class OpinionAccountConfig {
	
	private final Integer httpPort;
	private final String httpHost;

	private OpinionAccountConfig(Builder builder) {
		this.httpPort = builder.httpPort;
		this.httpHost = builder.httpHost;
	}

	public static class Keys {
		public static final String HTTP_PORT = "http_port";
		public static final String HTTP_HOST = "http_host";
	}
	
	public static final OpinionAccountConfig DEFAULTS = OpinionAccountConfig.builder().withHttpHost("localhost").withHttpPort(8080).build();

	public OpinionAccountConfig(JsonObject json) {
		httpPort = json.getInteger(Keys.HTTP_PORT, DEFAULTS.httpPort());
		httpHost = json.getString(Keys.HTTP_HOST, DEFAULTS.httpHost());
	}

	public JsonObject toJson() {
		var json = new JsonObject();
		if(null != httpPort) {
			json.put(Keys.HTTP_PORT, httpPort);
		}
		
		if(null != httpHost) {
			json.put(Keys.HTTP_HOST, httpHost);
		}
		return json;
	}
		
	public int httpPort() {
		return httpPort;
	}

	public String httpHost() {
		
		return httpHost;
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

		private Builder() {
		}

		private Builder(OpinionAccountConfig opinionAccountConfig) {
			this.httpPort = opinionAccountConfig.httpPort;
			this.httpHost = opinionAccountConfig.httpHost;
		}

		public Builder withHttpPort(Integer httpPort) {
			this.httpPort = httpPort;
			return this;
		}

		public Builder withHttpHost(String httpHost) {
			this.httpHost = httpHost;
			return this;
		}

		public OpinionAccountConfig build() {
			return new OpinionAccountConfig(this);
		}
	}
}
