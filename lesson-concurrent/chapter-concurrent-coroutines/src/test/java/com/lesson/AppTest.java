package com.lesson;

import static org.junit.Assert.assertTrue;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.sync.Sync;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();

        Message<String> reply = Sync.awaitResult(h -> eb.send("someaddress", "ping", h));

        System.out.println("Received reply " + reply.body());

        long tid =Sync.awaitEvent(h -> vertx.setTimer(1000, h));

        System.out.println("Timer has now fired");
    }
}
