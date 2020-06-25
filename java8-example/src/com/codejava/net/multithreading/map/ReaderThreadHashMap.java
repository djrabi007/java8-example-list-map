package com.codejava.net.multithreading.map;

import java.util.Iterator;
import java.util.Map;

public class ReaderThreadHashMap extends Thread{
	private Map<Integer, String> map;
	private String name;

	public ReaderThreadHashMap(Map<Integer, String> map, String threadName) {
		// TODO Auto-generated constructor stub
		this.map = map;
		this.name = threadName;
	}

	public void run() {
		// oldRead();
		newRead();
	}

	private void newRead() {
		while (true) {

			String output = "\n" + super.getName() + ":";
			Iterator<Integer> iterator = map.keySet().iterator();

			while (iterator.hasNext()) {
				Integer next = iterator.next();
				output += " " + next;

				// fake processing time
				try {
					Thread.sleep(10);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

			}

			System.out.println("READ OLD HASHMAP " + output);
		}
	}

}
