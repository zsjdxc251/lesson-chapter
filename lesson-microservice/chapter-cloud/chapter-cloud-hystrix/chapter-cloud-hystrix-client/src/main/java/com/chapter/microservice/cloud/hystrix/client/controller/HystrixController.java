package com.chapter.microservice.cloud.hystrix.client.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/8/7.
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    private static final Random RANDOM = new Random();


    private static final Logger log = LoggerFactory.getLogger(HystrixController.class);

    @GetMapping("/ann/execute")
    @HystrixCommand(fallbackMethod = "errorContent" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "100")
    })
    public ResponseEntity<String> execute(){


        int sleepTime = RANDOM.nextInt(200);
        log.info("sleepTime:{}",sleepTime);
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.error(StringUtils.EMPTY,e);
        }

        return ResponseEntity.ok("成功调用");
    }



    public ResponseEntity<String> errorContent(){

        return ResponseEntity.ok("发生错误");
    }


    @GetMapping("/programme/execute")
    public ResponseEntity<String> programmeExecute(){

        log.info("programmeExecute");

        return new CustomHystrixCommand().execute();
    }

    /**
     * 编程式调用
     *
     */
    public class CustomHystrixCommand extends com.netflix.hystrix.HystrixCommand<ResponseEntity<String>> {

        public CustomHystrixCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("key-CustomHystrixCommand"),
                    100);
        }

        @Override
        protected ResponseEntity<String> run() throws Exception {
            int sleepTime = RANDOM.nextInt(200);
            log.info("sleepTime:{}",sleepTime);
            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                log.warn("errorMsg:{}",e.getMessage());
            }
            return ResponseEntity.ok("成功调用");
        }

        @Override
        protected ResponseEntity<String> getFallback() {
            return HystrixController.this.errorContent();
        }
    }



}
