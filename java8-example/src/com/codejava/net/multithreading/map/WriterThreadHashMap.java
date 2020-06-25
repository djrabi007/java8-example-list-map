package com.codejava.net.multithreading.map;

import java.util.Map;
import java.util.Random;

public class WriterThreadHashMap  extends Thread{
	private Map<Integer, String> map;
	private Random random;
	private String name;

	public WriterThreadHashMap(Map<Integer, String> map, String threadName, long randomSeed) {
		// TODO Auto-generated constructor stub
		this.map = map;
		this.random = new Random(randomSeed);
		this.name = threadName;
	}

	public void run() {
		newAdd();

	}

	private void newAdd() {
		int count = 6;
		String value = name;

		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

			map.put(count++, value);

			System.out.println("OLD Hashmap :::" + super.getName() + " done" + map);
		}
	}

}
