package com.chapter.microservice.rest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;

public class SpringApiTest {


    private static final Logger log = LoggerFactory.getLogger(SpringApiTest.class);




    @Test
    public void multiValueMap(){

        LinkedMultiValueMap<String,String> linkedMultiValueMap = new LinkedMultiValueMap<>();



        linkedMultiValueMap.add("head","12");

        linkedMultiValueMap.add("head","333");


        log.info("linkedMultiValueMap:{}",linkedMultiValueMap);

    }
}
