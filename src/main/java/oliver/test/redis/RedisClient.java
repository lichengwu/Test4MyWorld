/*
 * Copyright (c) 2010-2011 lichengwu
 * All rights reserved.
 * 
 */
package oliver.test.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * Redis Test
 *
 * @author lichengwu
 * @created 2012-8-31
 *
 * @version 1.0
 */
public class RedisClient {

	
	@Test
	public void test1(){
		Jedis jedis = new Jedis("192.168.0.221");
		jedis.set("oliver", "中文");
		System.out.println(jedis.get("oliver"));
		jedis.mset("k1","v1","k2","v2");
		System.out.println(jedis.mget("k1","k2"));
	}
}
