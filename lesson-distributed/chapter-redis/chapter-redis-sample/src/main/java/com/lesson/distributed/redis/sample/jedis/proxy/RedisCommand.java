package com.lesson.distributed.redis.sample.jedis.proxy;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @author zhengshijun
 * @version created on 2017/07/03.
 *
 */
public abstract class RedisCommand {

	protected Jedis jedis = null;

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}


	public Jedis getJedis() {
		throw new IllegalArgumentException();
	}

	/**
	 * list 删除集合指定值 和 行数
	 * 
	 * @param key
	 *            键值
	 * @param count
	 *            行数
	 * @param value
	 *            值
	 * @return
	 */
	public long lrem(String key, long count, String value) {

		throw new IllegalArgumentException();
	}

	/**
	 * list 使用值插入列表第一行
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long lpush(String key, String... value) {

		throw new IllegalArgumentException();
	}

	/**
	 * list 移出并获取列表的第一个元素
	 * 
	 * @param key
	 * @return
	 */
	public String lpop(String key) {

		throw new IllegalArgumentException();
	}

	/**
	 * list 获得集合长度
	 * 
	 * @param key
	 * @return
	 */
	public long llen(String key) {

		throw new IllegalArgumentException();
	}

	/**
	 * list 移除并获取列表最后一个元素
	 * 
	 * @param key
	 * @return
	 */
	public String rpop(String key) {

		throw new IllegalArgumentException();
	}

	/**
	 * list 通过索引添加元素值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 */
	public String lset(String key, int index, String value) {

		throw new IllegalArgumentException();
	}

	/**
	 * list 将一个或多个值插入到列表的尾部
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long rpush(String key, String... value) {

		throw new IllegalArgumentException();
	}

	/**
	 * key 删除指定键
	 * 
	 * @param key
	 * @return
	 */
	public long del(String key) {

		throw new IllegalArgumentException();
	}

	/**
	 * 获取匹配的key
	 * 
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern) {

		throw new IllegalArgumentException();
	}

	

	/**
	 * hash 存储哈希
	 * 
	 * @param key
	 * @param hash
	 * @return
	 */
	public String hmset(String key, Map<String, String> hash) {

		throw new IllegalArgumentException();
	}

	/**
	 * hash 检查哈希列表值内某字段是否存在
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public boolean hexists(String key, String field) {
		throw new IllegalArgumentException();
	}

	/**
	 * hash 获取在哈希表中指定 key 的所有字段和值
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetall(String key) {
		throw new IllegalArgumentException();
	}

	/**
	 * hash 删除一个或多个哈希表字段
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	public Long hdel(String key, String... fields) {
		throw new IllegalArgumentException();
	}

	/**
	 * hash 获取存储在哈希表中指定字段的值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field) {

		throw new IllegalArgumentException();
	}
	
	/**
	 * list 检查某值是否存在
	 * @param key
	 * @param value
	 * @return
	 */
	public abstract boolean lexist(String key, String value);
	
	public abstract void subscribe(String channels);
	
	public abstract Long publish(String channels, String message);

	/**
	 * 设置字符串
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key , String value){
		throw new IllegalArgumentException();
	}

	public String get(String key){
		throw new IllegalArgumentException();
	}

	/**
	 * 获取字符串指定范围值
	 * @param key
	 * @param startOffset 从开始选择
	 * @param endOffset   结尾
	 * @return
	 */
	public String getrange(String key, long startOffset, long endOffset){
		throw new IllegalArgumentException();
	}


	/**
	 * 如果没有该key   则可以设置值
	 * @param key
	 * @param value
	 * @return
	 */
	public long setnx(String key , String value) {
		throw new IllegalArgumentException();
	}

	public long del(String...keys){
		throw new IllegalArgumentException();
	}

	public abstract long mdel(String...keys);

	/**
	 * Redis Watch 命令用于监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断
	 * @param keys
	 * @return
	 */
	public String watch(String...keys) {
		throw new IllegalArgumentException();
	}

}
