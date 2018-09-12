package com.lesson.distributed.redis.sample.jedis;

import org.apache.commons.lang3.ArrayUtils;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Pipeline;

/**
 * 
 * @author zhengshijun
 * @version created on 2017/07/03.
 *
 */
public class RedisApi extends RedisCommand {

	private JedisPubSub jedisPubSub = new JedisPubSubSimple();
	
	public RedisApi(Jedis jedis) {
		super.setJedis(jedis);
	}


	@Override
	public boolean lexist(String key, String value) {

		StringBuilder defaultValue = new StringBuilder("def_value");
		defaultValue.append(System.currentTimeMillis());

		Long result = jedis.linsert(key, BinaryClient.LIST_POSITION.AFTER, value, defaultValue.toString());
		if (result > -1) {
			jedis.lrem(key, 1, defaultValue.toString());
			return true;
		}
		return false;
	}

	@Override
	public void subscribe(String channels) {

		jedis.subscribe(jedisPubSub, channels);
	}

	@Override
	public Long publish(String channels, String message) {

		Long result = jedis.publish(channels, message);

		return result;
	}

	@Override
	public long mdel(String... keys) {
		if (ArrayUtils.isEmpty(keys)){
			return 0;
		}
		Pipeline pipeline = jedis.pipelined();
		for (String key : keys ) {
			pipeline.del(key);
		}
		pipeline.sync();
		return 0;
	}

	@Override
	public String watch(String... keys) {

		return null;
	}
}
