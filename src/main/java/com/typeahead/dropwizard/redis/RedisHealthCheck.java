package com.typeahead.dropwizard.redis;

import com.yammer.metrics.core.HealthCheck;

/**
 * HealthCheck for a managed redis client
 * @author rickcrawford
 *
 */
public class RedisHealthCheck extends HealthCheck {
	
	private final Redis conn;
	/**
	 * Creates a RedisHealthCheck
	 * @param conn - current redis connection
	 * @param name - name of the connection
	 */
	public RedisHealthCheck(Redis conn, String name) {
		super(name + "-redis");
		this.conn = conn;
	}

	@Override
	protected Result check() throws Exception {
		try {
			conn.ping();
			return Result.healthy();
		}
		catch (Exception ex) {
			return Result.unhealthy(ex);
		}
	}



}
