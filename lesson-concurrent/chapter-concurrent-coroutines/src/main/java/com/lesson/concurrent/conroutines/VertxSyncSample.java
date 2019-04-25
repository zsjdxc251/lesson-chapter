package com.lesson.concurrent.conroutines;


import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.sync.Sync;

/**
 * @author zhengshijun
 * @version created on 2019/4/25.
 */
public class VertxSyncSample {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		EventBus eb = vertx.eventBus();

		Message<String> reply = Sync.awaitResult(h -> eb.send("someaddress", "ping", h));

		System.out.println("Received reply " + reply.body());

		long tid =Sync.awaitEvent(h -> vertx.setTimer(1000, h));

		System.out.println("Timer has now fired");
	}
}
