package com.codejava.net.multithreading.map;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WriterThread extends Thread {

	private Map<Integer, String> map;
	private Random random;
	private String name;

	public WriterThread(Map<Integer, String> map, String threadName, long randomSeed) {
		// TODO Auto-generated constructor stub
		this.map = map;
		this.random = new Random(randomSeed);
		this.name = threadName;
	}

	public void run() {
		//oldAddRemove();
		newAdd();
		
	}

	private void newAdd() {
		int count = 6;
		String value = name;
		 
        while (true)
        {	 
            try  {	Thread.sleep(5000);	}
            catch (InterruptedException ex) {  ex.printStackTrace(); }
 
            map.putIfAbsent(count++,value);
 
            System.out.println(super.getName() + " done" +map);
        }
	}

	private void oldAddRemove() {
		while (true) {
			Integer key = random.nextInt(10);
			String value = name;

			if (map.putIfAbsent(key, value) == null) {
				long time = System.currentTimeMillis();
				String output = String.format("%d: %s has put [%d => %s]", time, name, key, value);
				System.out.println(output);
			}

			Integer keyToRemove = random.nextInt(10);

			if (map.remove(keyToRemove, value)) {
				long time = System.currentTimeMillis();
				String output = String.format("%d: %s has removed [%d => %s]", time, name, keyToRemove, value);
				System.out.println(output);
			}

			try { 	Thread.sleep(500); 	} catch (InterruptedException ex) { 	ex.printStackTrace(); 	}
		}
	}

}
