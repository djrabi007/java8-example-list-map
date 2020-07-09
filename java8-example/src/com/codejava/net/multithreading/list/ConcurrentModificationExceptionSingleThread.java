package com.codejava.net.multithreading.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * https://techvidvan.com/tutorials/java-concurrentmodificationexception/
 * 
 * @author rabi0
 *
 */
public class ConcurrentModificationExceptionSingleThread {
	public static void main(String[] args) {
		/**
		 * Case#1 ConcurrentModificationException using ArrayList
		 */
		// ArrayList<Integer> list = new ArrayList<>();

		/**
		 * Case#2 Fix of Case#1 ++++ use of CopyOnWriteArrayList
		 */
		CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.forEach(System.out::println);

		Iterator<Integer> itr = list.iterator();
		while (itr.hasNext()) {
			Integer value = itr.next();
			if (value.equals(3)) {// 3 will be removed
				list.remove(value); /// It will throw ConcurrentModificationException!!!
				// list.add(7);
				// itr.remove(); // Fix for ArrayList. UnsupportedException for COWAL !!!
			}
		}
		System.out.println("*****Modified  List and 3 removed!!!**********");
		list.forEach(System.out::println);

//		
//		
//		list.stream().filter(s -> !(s == 3)).forEach(System.out::println);


	}

}