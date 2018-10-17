package com.lesson.distributed.demotion;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/10/17.
 */
public class TokenSample {

    private RateLimiter rateLimiter;

    private int qps;

    private int countOfReq;

    private String name;


    public TokenSample(int qps, int countOfReq,String name) {
        this.qps = qps;
        this.countOfReq = countOfReq;
        this.name = name;
    }

    public TokenSample processWithTokenBucket(){
        rateLimiter = RateLimiter.create(qps,0, TimeUnit.MILLISECONDS);

        return this;
    }

    public TokenSample processWithLeakyBucket(){
        rateLimiter = RateLimiter.create(qps,0, TimeUnit.MILLISECONDS);

        return this;
    }


    public void processRequest(){


        System.out.println("processRequest:{}"+name);

        long start = System.currentTimeMillis();
        for (int i=0;i<countOfReq;i++){
            // 获取令牌
            rateLimiter.acquire();
        }
        long end = System.currentTimeMillis()-start;

        System.out.println(
                "处理请求数量："+countOfReq+","
                + "耗时："+end+",qps:"+rateLimiter.getRate()
                +",实际qps："
                + Math.ceil(countOfReq/(end/1000.00))


        );

    }

    public void doProcessor() throws InterruptedException{

        for (int i=0;i<20;i=i+5) {

            TimeUnit.SECONDS.sleep(i);
            processRequest();


        }

    }

    public static void main(String[] args) throws Exception{

        new TokenSample(50,10,"令牌桶").processWithLeakyBucket().doProcessor();

        new TokenSample(50,10,"漏桶").processWithTokenBucket().doProcessor();
    }



}
