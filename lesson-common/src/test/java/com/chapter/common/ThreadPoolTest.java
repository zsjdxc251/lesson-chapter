package com.chapter.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ThreadPoolTest {

    
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolTest.class);

    public static void main(String[] args){


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,10,3000, TimeUnit.SECONDS,new ArrayBlockingQueue<>(200));



        Future<String> future = threadPoolExecutor.submit(()->{



            execute();
            log.info("休眠完了");


            return "zsjdxc251";
        });






        String result = null;
//        try {
//            result = future.get(100,TimeUnit.MILLISECONDS);
//        } catch (InterruptedException | TimeoutException | ExecutionException e) {
//            log.error(StringUtils.EMPTY,e);
//            future.cancel(true);
//        }


        log.info("result：{}",result);





        threadPoolExecutor.shutdownNow();

    }


    public static void execute(){

        try {

            TimeUnit.MILLISECONDS.sleep(500);
        }catch (InterruptedException e) {
            log.error(StringUtils.EMPTY,e);

        }
        log.info("继续执行");


    }
}
