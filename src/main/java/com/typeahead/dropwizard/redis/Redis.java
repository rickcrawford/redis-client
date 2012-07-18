package com.typeahead.dropwizard.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


import com.yammer.dropwizard.lifecycle.Managed;
import com.yammer.dropwizard.logging.Log;

/**
 * Redis client managed by DropWizard
 * @author rickcrawford
 *
 */
public class Redis extends JedisPool implements Managed {
	
	private static final Log LOG = Log.forClass(Redis.class);
	
	/**
	 * create a new Redis client
	 * @param config - JedisPoolConfig settings
	 * @param host - redis host
	 * @param port - redis port
	 * @param timeout - timeout for connections
	 */
	public Redis(JedisPoolConfig config, String host, int port, int timeout) {
		super(config, host, port, timeout);
		LOG.debug("initializing redis {}", config, host, port, timeout);
		
	}
	
	/**
	 * health check for the redis instance...
	 * @throws Exception
	 */
	public void ping() throws Exception {
		final Jedis conn = this.getResource();
		try {
			conn.ping();
		}
		finally {
			returnResource(conn);
		}
	}
	
	public void start() throws Exception {
		LOG.debug("starting!");		
	}

	public void stop() throws Exception {
		LOG.debug("stopping");
		this.destroy();
	}

	
	
}
