package com.yc.util;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {
	private Jedis jedis;// 非切片客户端连接
	private JedisPool jedisPool;// 非切片连接池
	private ShardedJedis shardedJedis;// 切片客户端连接
	private ShardedJedisPool shardedJedisPool;// 切片连接池
	
	private static RedisClient client;
	
	public Jedis getJedis() {
		return jedis;
	}

	public ShardedJedis getShardedJedis() {
		return shardedJedis;
	}

	public static RedisClient getInstance(){
		if(client==null){
			client=new RedisClient();
		}
		return client;
	}

	private RedisClient() {
		if(   shardedJedisPool==null){
			initialShardedPool();
			shardedJedis = shardedJedisPool.getResource();
		}
		if( jedis==null){
			initialPool();
			jedis = jedisPool.getResource();
			jedis.auth("123456");
		}
	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setTestOnBorrow(false);
		jedisPool = new JedisPool(config, "127.0.0.1", 6379);
	}

	/**
	 * 初始化切片池
	 */
	private void initialShardedPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setTestOnBorrow(false);//保证每次获取有效的连接对象  true 每次获取连接的时候需要数据库验证有效性，所以在高并发情况下性能降低
		// slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));
		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}
 
}
