package com.codejava.net.multithreading.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * https://www.codejava.net/java-core/concurrency/java-concurrent-collection-concurrenthashmap-examples
 * 
 * Unlike Hastable or synchronizedMap which locks the entire map exclusively to
 * gain thread-safety feature, ConcurrentHashMap allows concurrent writer and
 * reader threads. That means it allows some threads to modify the map and other
 * threads to read values from the map at the same time, while Hashtable or
 * synchronizedMap allows only one thread to work on the map at a time. More
 * specifically, ConcurrentHashMap allows any number of concurrent reader
 * threads and a limited number of concurrent writer threads, and both reader
 * and writer threads can operate on the map simultaneously.
 * 
 * 
 * Iterators returned by ConcurrentHashMap are weakly consistent, meaning that
 * the iterator may not reflect latest update since it was constructed. An
 * iterator should be used by only one thread and no
 * ConcurrentModificationException will be thrown if the map is modified while
 * the iterator is being used.
 * 
 * @author rabi0
 *
 */

public class ConcurrentHashMapExamples {

	public static void main(String[] args) {
		// oldProcess(map);

		/**
		 * Case#1 using HashMap - GET ConcurrentModificationException
		 */

		Map<Integer, String> map = new HashMap<>();
		newProcessWithHashMap(map);

		/**
		 * Case#2 Fix of Case#1 using ConcurrentHashMap NO NO
		 * ConcurrentModificationException
		 */
//		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
//		newProcessWithConcurrentHashMap(map);


	}
	
	private static void newProcessWithHashMap(Map<Integer, String> map) {
		map.put(1, "1");
		map.put(2, "2");
		map.put(3, "3");
		map.put(4, "4");
		map.put(5, "5");
		new WriterThreadHashMap(map, "Writer-1", 1).start();
		new ReaderThreadHashMap(map, "Reader-" ).start();
	}
	
	private static void newProcessWithConcurrentHashMap(ConcurrentHashMap<Integer, String> map) {
		map.put(1, "1");
		map.put(2, "2");
		map.put(3, "3");
		map.put(4, "4");
		map.put(5, "5");
		new WriterThread(map, "Writer-1", 1).start();
		new ReaderThread(map, "Reader-" ).start();
	}

	private static void oldProcess(ConcurrentHashMap<Integer, String> map) {
		new WriterThread(map, "Writer-1", 1).start();
		new WriterThread(map, "Writer-2", 2).start();

		for (int i = 1; i <= 5; i++) {
			new ReaderThread(map, "Reader-" + i).start();
		}
	}
}