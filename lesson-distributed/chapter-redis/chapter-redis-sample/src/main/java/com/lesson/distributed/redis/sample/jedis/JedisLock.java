package com.lesson.distributed.redis.sample.jedis;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/9/28.
 */
public class JedisLock {

    private String lockKey;

    private Jedis jedis;


    private JedisLock(String lockKey){
        this.lockKey = lockKey;
    }


    public void acquireLock(long waitTime,long expireTime){

        long endTime = System.currentTimeMillis() + waitTime;

        String value = String.valueOf(Thread.currentThread().getId());
        while (waitTime ==0 || System.currentTimeMillis() < endTime) {
            if (jedis.setnx(lockKey,value) == 1){
                jedis.expire(lockKey, (int)TimeUnit.MILLISECONDS.toSeconds(expireTime));
                break;
            }
            if (jedis.ttl(lockKey) == -1) {
                jedis.expire(lockKey, (int)TimeUnit.MILLISECONDS.toSeconds(expireTime));
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void releaseLockByScript(){

        String script = "if redis.call('get',KEY[1]) == ARGV[1] then redis.call('del',KEY[1]) return 1 else return 0 end ";
        String value = String.valueOf(Thread.currentThread().getId());
        Integer result = (Integer) jedis.eval(script,1,lockKey,value);
        System.out.println(result);

    }

    public void releaseLock(){
        String value = String.valueOf(Thread.currentThread().getId());
        while (true) {
            jedis.watch(lockKey);
            if (!StringUtils.equals(jedis.get(lockKey),value)) {
                break;
            } else {
                Transaction transaction = jedis.multi();
                jedis.del(lockKey);
                if (transaction.exec().isEmpty()){
                    continue;
                }
            }
            jedis.unwatch();
            break;
        }
    }

}
