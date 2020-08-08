package com.rabi.java8.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadExample {

	public static void main(String[] args) {
		// oneThreadOneTask();
		// multipleThreadMultipleTask();
		// tenThreadThousandTask();
		cpuIntensiveTask();
	}
	
	private static void cpuIntensiveTask() {
		// int nThreads = 10;
		int nThreads = Runtime.getRuntime().availableProcessors();

		// Create Thread Pool
		ExecutorService es = Executors.newFixedThreadPool(nThreads);
		for (int i = 0; i < 1000; i++) {
			es.execute(new Task());
		}
		ThreadUtil.printThread();
	}

	private static void tenThreadThousandTask() {
		int nThreads = 10;

		// Create Thread Pool
		ExecutorService es = Executors.newFixedThreadPool(nThreads);
		for (int i = 0; i < 16; i++) {
			es.execute(new Task());
		}
		ThreadUtil.printThread();
	}

	private static void multipleThreadMultipleTask() {

		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Task());
			t.start();
		}
		ThreadUtil.printThread();
	}


	private static void oneThreadOneTask() {
		Thread t1 = new Thread(new Task());
		t1.start();
		ThreadUtil.printThread();
	}

}
