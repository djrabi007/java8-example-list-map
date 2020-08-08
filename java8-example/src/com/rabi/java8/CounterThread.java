package com.rabi.java8;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://www.youtube.com/watch?v=F9_-M-AuUmg
 * 
 * @author rabi0
 *
 */
public class CounterThread implements Runnable {

	private static final String DELIM = " ### ";
	AtomicInteger counter;
	static Integer i = 0;
	public CounterThread(AtomicInteger counter) {
		this.counter = counter;
	}


	@Override
	public void run() {

		// Initial value of Counter =13 (passing)
		/**
		 * increment and NEW value returned (13+1 onwards)
		 */
		int newCounter = counter.incrementAndGet();

		/**
		 * increment and OLD value returned(13 onwards)
		 */
		// int prevCounter = counter.getAndIncrement();

		String threadName = Thread.currentThread().getName();
		i = i + 1;

		StringBuilder sb = new StringBuilder();
		sb.append(threadName);
		sb.append(DELIM);
		sb.append("newCounter = " + newCounter);
		sb.append(DELIM);
		// sb.append("prevCounter= " + prevCounter);
		sb.append(DELIM);
		sb.append("i =" + i);

		System.out.println(sb);

	}

}
