package com.chapter.microservice.reactive;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {

        WebClient.create("http://www.baidu.com/").get().exchange().subscribe(clientResponse -> {


            clientResponse.bodyToMono(String.class).subscribe(body->{

                System.out.println(body);
            });


        });


        Thread.currentThread().join();




    }
}
