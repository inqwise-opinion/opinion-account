package com.inqwise.opinion.account;

import io.vertx.core.json.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public final class OpinionAccountConfig {

  private static final Logger logger = LogManager.getLogger(OpinionAccountConfig.class);

  private final JsonObject delegate;

  public OpinionAccountConfig(JsonObject json) {
    this.delegate = Objects.requireNonNull(json, "json").copy();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(JsonObject json) {
    return builder().fromJson(json);
  }

  public JsonObject toJson() {
    return delegate.copy();
  }

  public static final class Builder {

    private static final Logger logger = LogManager.getLogger(Builder.class);

    private final JsonObject data = new JsonObject();

    private Builder() {
    }

    public Builder fromJson(JsonObject source) {
      if (source != null) {
        data.mergeIn(source.copy(), true);
      } else {
        logger.debug("Received null source JsonObject while building OpinionAccountConfig");
      }
      return this;
    }

    public Builder put(String key, Object value) {
      data.put(key, value);
      return this;
    }

    public OpinionAccountConfig build() {
      return new OpinionAccountConfig(data);
    }
  }
}
