package com.rabi.java8.multithreading.executor;

public class Task implements Runnable {

	@Override
	public void run() {
		ThreadUtil.printThread();

	}



}
