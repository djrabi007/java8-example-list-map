package com.codejava.net.multithreading.map;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ReaderThread extends Thread {

	private ConcurrentHashMap<Integer, String> map;
	private String name;

	public ReaderThread(ConcurrentHashMap<Integer, String> map, String threadName) {
		// TODO Auto-generated constructor stub
		this.map = map;
		this.name = threadName;
	}

	public void run() {
		//oldRead();
		 newRead();
	}

	private void newRead() {
		while (true)
	        {
	 
	            String output = "\n" + super.getName() + ":";
	 
	           // Iterator<Integer> iterator = list.iterator();
	            
	            ConcurrentHashMap.KeySetView<Integer, String> keySetView = map.keySet();
				Iterator<Integer> iterator = keySetView.iterator();
	 
	            while (iterator.hasNext()) {
	                Integer next = iterator.next();
	                output += " " + next;
	 
	                // fake processing time
	                try  {	Thread.sleep(10);	}
		            catch (InterruptedException ex) {  ex.printStackTrace(); }

	            }
	 
	            System.out.println(output);
	        }
	}

	private void oldRead() {
		while (true) {
			ConcurrentHashMap.KeySetView<Integer, String> keySetView = map.keySet();
			Iterator<Integer> iterator = keySetView.iterator();

			long time = System.currentTimeMillis();
			String output = time + ": " + name + ": ";

			while (iterator.hasNext()) {
				Integer key = iterator.next();
				String value = map.get(key);
				output += key + "=>" + value + "; ";
			}

			System.out.println(output);

			try {	Thread.sleep(300);	}
			catch (InterruptedException ex) {ex.printStackTrace();}
		}
	}
}
