package com.typeahead.dropwizard.redis;

import redis.clients.jedis.JedisPoolConfig;

import com.yammer.dropwizard.config.Environment;

/**
 * Factory for creating a managed Redis connection
 * @author rickcrawford
 *
 */
public class RedisFactory {

	private Environment environment;
	/**
	 * Create a new RedisFactory with the dropwizard environment
	 * @param environment
	 */
	public RedisFactory(Environment environment) {
		this.environment = environment;
	}
	
	/**
	 * build the connection and assign it as managed
	 * @param configuration
	 * @param name
	 * @return
	 */
	public Redis build(RedisConfiguration configuration, String name)  {
		final String host = configuration.getHost();
		final int port = configuration.getPort();
		final int timeout = configuration.getTimeout();
		final JedisPoolConfig config = new JedisPoolConfig();
		final Redis conn = new Redis(config, host, port, timeout);
		
		environment.manage(conn);
		environment.addHealthCheck(new RedisHealthCheck(conn, name));
		
		return conn;
	}
	
}
