package com.lesson.distributed.redis.sample.jedis;

import com.google.common.collect.Maps;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 
 * @author zhengshijun
 * @version created on 2017/07/03.
 *
 */
public class RedisProxy implements MethodInterceptor {

	private static final Logger log = LoggerFactory.getLogger(RedisProxy.class);

	private static int timeout = 6000;

	private static int database = 1;

	private static String host = "192.168.1.20";

	private static int port = 6380;

	private static String password = "dmzdwzidhkakkjnmkvxiyzcsbyrspbadSmznltzvmkou2sjzhzjtxyoBydvkpbgbegxif7zxkcehacqnkazk]uqdzxmxdniwkocyhylnqxwnyqgwwxdewydpwkrfw";

	private Map<String, Method> methodCache = Maps.newConcurrentMap();

	private JedisPool pool = null;



	public RedisProxy() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxWaitMillis(3000);
		pool = new JedisPool(config,host,port, timeout, password, database);
	}

	private String generateKey(String methodName, Object[] args) {
		StringBuilder buf = new StringBuilder(methodName);
		buf.append("(");
		if ((args == null) || (args.length == 0)) {
			return buf.append(")").toString();
		}
		for (Object ars : args) {
			buf.append(ars.getClass().getName()).append(",");
		}
		return new StringBuilder(buf.substring(0, buf.length() - 1)).append(")").toString();
	}

	private Method getAndCacheMethod(Object src, String methodName, Object[] args) {

		String key = generateKey(methodName, args);
		log.info("key:{}", key);
		Method method = methodCache.get(key);
		if (method != null) {
			log.info("method cache:{}", method.getName());
			return method;
		}
		method = BeanUtils.findDeclaredMethod(src.getClass(), methodName, getArgsTypeArray(args, false));
		if (method == null) {
			method = BeanUtils.findDeclaredMethod(src.getClass(), methodName, getArgsTypeArray(args, true));
		}
		if (method != null) {
			log.info("set method cache:{}", method.getName());
			methodCache.put(key, method);
		} else {
			log.info("method is null");
		}
		return method;
	}

	private Class<?>[] getArgsTypeArray(Object[] args, boolean primaryType) {
		if ((args == null) || (args.length == 0)) {
			return new Class[0];
		}
		Class<?>[] clazzs = new Class[args.length];
		for (int i = 0; i < args.length; ++i) {
			clazzs[i] = getPrimaryType(args[i].getClass(), primaryType);
		}
		return clazzs;
	}

	private Class<?> getPrimaryType(Class<?> clazz, boolean primaryType) {
		if (primaryType) {
			if (clazz.equals(Long.class)) {
				return Long.TYPE;
			} else if (clazz.equals(Byte.class)) {
				return Byte.TYPE;
			} else if (clazz.equals(Integer.class)) {
				return Integer.TYPE;
			} else if (clazz.equals(Short.class)) {
				return Short.TYPE;
			} else if (clazz.equals(Float.class)) {
				return Float.TYPE;
			} else if (clazz.equals(Double.class)) {
				return Double.TYPE;
			} else if (clazz.equals(Character.class)) {
				return Character.TYPE;
			} else if (clazz.equals(Boolean.class)) {
				return Boolean.TYPE;
			}
		}
		return clazz;
	}



	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {

		Jedis jedis = pool.getResource();
		jedis.select(database);

		if (arg1.getReturnType() == Jedis.class){

			return jedis;
		}

		Method method = getAndCacheMethod(jedis, arg1.getName(), arg2);

		Object result = null;
		if (method == null) {
			result = arg1.invoke(new RedisApi(jedis), arg2);
		} else {
			result = method.invoke(jedis, arg2);
		}
		jedis.close();
		return result;
	}


	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Class<T> entity) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(entity);
		enhancer.setCallback(new RedisProxy());
		return (T) enhancer.create();
	}

	public static void main(String ages[]) throws Exception{

		RedisCommand command = RedisProxy.getProxy(RedisCommand.class);
//
//		Set<String> keys = command.keys("caicaicai:*");
//		System.out.println(keys.size());
//		keys.forEach(System.out::println);

//		Jedis jedis = command.getJedis();
//
//		jedis.watch("bizcard-chat:watch");
//		Transaction transaction = jedis.multi();
//		transaction.set("bizcard-chat:watch","2");
//		List<Object> list = transaction.exec();
//
//		log.info("list:{}",list);
//		System.out.println(jedis.get("bizcard-chat:watch"));;
//		jedis.close();

		//command.del("bizcard:backend:checkChangeTemplateCount:companyId:5b91f71fe3b3c14d22fa461f");
		//command.keys("*5b91f71fe3b3c14d22fa461f").forEach(System.out::println);




	}


}
