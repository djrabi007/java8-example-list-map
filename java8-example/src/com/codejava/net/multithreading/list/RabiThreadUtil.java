package com.codejava.net.multithreading.list;

public class RabiThreadUtil {

	public static void goToSleep(int sleepingTime) {
		try {
			Thread.sleep(sleepingTime);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

}
