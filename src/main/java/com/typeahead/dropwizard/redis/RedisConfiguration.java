package com.typeahead.dropwizard.redis;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Creates a new RedisConfiguration
 * @author rickcrawford
 *
 */
public class RedisConfiguration {

	@JsonProperty
	@NotEmpty
	private String host;
	
	@JsonProperty
	@Min(6300)
	@Max(6399)
	private int port = 6379;

	@Min(2)
	@Max(60)
	@JsonProperty
	private int timeout = 2;
	
	
	public RedisConfiguration() {
		
	}
	
	public RedisConfiguration(String host, int port, int timeout) {
		this.timeout = timeout;
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}

	public int getTimeout() {
		return timeout;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
