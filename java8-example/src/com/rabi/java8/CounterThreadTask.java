package com.rabi.java8;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://www.youtube.com/watch?v=F9_-M-AuUmg
 * 
 * @author rabi0
 *
 */
public class CounterThreadTask implements Runnable {

	AtomicInteger counter;
	static Integer i = 0;
	public CounterThreadTask(AtomicInteger counter) {
		this.counter = counter;
	}


	@Override
	public void run() {

		int newCounter = counter.incrementAndGet(); // New Value
		// int prevCounter = counter.getAndIncrement(); //Old Value

		String threadName = Thread.currentThread().getName();
		i = i + 1;

		StringBuilder sb = new StringBuilder();
		sb.append(threadName);
		sb.append(StringConstant.DELIM);
		sb.append("newCounter = " + newCounter);
		sb.append(StringConstant.DELIM);
		// sb.append("prevCounter= " + prevCounter);
		sb.append(StringConstant.DELIM);
		sb.append("i =" + i);

		System.out.println(sb);

	}

}
