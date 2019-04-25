package com.lesson.concurrent.conroutines;

import com.offbynull.coroutines.user.Continuation;
import com.offbynull.coroutines.user.Coroutine;
import com.offbynull.coroutines.user.CoroutineRunner;

/**
 * @author zhengshijun
 * @version created on 2019/4/25.
 */
public class CoroutineSample implements Coroutine {


	public static void main(String[] args) {
		CoroutineRunner r = new CoroutineRunner(new CoroutineSample());
		r.execute();
		r.execute();
		r.execute();
		r.execute();
	}

	@Override
	public void run(Continuation c) {
		System.out.println("started");
		for (int i = 0; i < 10; i++) {
			echo(c, i);
		}
	}

	private void echo(Continuation c, int x) {
		System.out.println(x);
		c.suspend();
	}
}
