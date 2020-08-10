package com.rabi.java8.multithreading.executor;

public class ThreadUtil {

	public static void printThread() {
		System.out.println(" Running " + Thread.currentThread().getName());
	}

	public static String printThreadVal() {
		return Thread.currentThread().getName();
	}

}
