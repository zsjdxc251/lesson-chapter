package com.lesson.distributed.redis.sample.jedis.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

public class JedisPubSubSimple extends JedisPubSub {

	private static final Logger LOG = LoggerFactory.getLogger(JedisPubSubSimple.class);
	
	@Override
	public void onMessage(String channel, String message) {
		
		super.onMessage(channel, message);
		LOG.info("onMessage:{}",channel);
	}
	
	@Override
	public void onPMessage(String pattern, String channel, String message) {
		
		super.onPMessage(pattern, channel, message);
		LOG.info("onPMessage");
	}
	
	@Override
	public void onPong(String pattern) {
		
		super.onPong(pattern);
		LOG.info("onPong");
	}
	
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		
		super.onPSubscribe(pattern, subscribedChannels);
		LOG.info("onPSubscribe");
	}
	
	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		
		super.onPUnsubscribe(pattern, subscribedChannels);
		LOG.info("onPUnsubscribe");
	}
	
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		
		super.onSubscribe(channel, subscribedChannels);
		LOG.info("onSubscribe");
	}
	
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		
		super.onUnsubscribe(channel, subscribedChannels);
		LOG.info("onUnsubscribe");
	}
	
}
