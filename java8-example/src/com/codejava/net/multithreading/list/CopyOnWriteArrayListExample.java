package com.codejava.net.multithreading.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * https://www.codejava.net/java-core/concurrency/java-concurrent-collection-copyonwritearraylist-examples
 * @author rabi0
 *
 */
/***
 * A CopyOnWriteArrayList makes a new copy of its elements for every write
 * operation and its iterator holds a different copy (snapshot) so it enables
 * sequential writes and concurrent reads: only one thread can execute write
 * operation and multiple threads can execute read operations at the same time.
 * And its iterator doesn’t throw ConcurrentModification.
 * 
 * @author rabi0
 *
 */
public class CopyOnWriteArrayListExample {
	public static void main(String[] args) {
		multiThreadListExample();

		/**
		 * Thread Safe and prevent Duplication!!
		 */
//		addIfAbsentExample();
//		withoutAddIfAbsentExample();
//		System.out.println("*****************************");
//		addAllAbsentExample();
//		withoutAddAllAbsentExample();

	}

	private static void withoutAddAllAbsentExample() {
		List<String> list1 = new ArrayList<>();

		list1.add("A");
		list1.add("B");

		List<String> list2 = Arrays.asList("C", "B");

		list1.addAll(list2);

		System.out.println("2222 AL:: list1= " + list1);
	}

	private static void addAllAbsentExample() {
		CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<>();

		list1.add("A");
		list1.add("B");

		List<String> list2 = Arrays.asList("C", "B");

		int result = list1.addAllAbsent(list2);

		System.out.println("111 COWAL:: list1= " + list1);
	}

	private static void addIfAbsentExample() {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Orange");

		if (list.addIfAbsent("Orange")) {
			System.out.println("Orange was added");
		}
		System.out.println("&&&& COWAL Fruit:: list= " + list);
	}

	private static void withoutAddIfAbsentExample() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Orange");

		if (list.add("Orange")) {
			System.out.println("Orange was added");
		}
		System.out.println("$$$$$ ArrayList  Fruit:: list= " + list);
	}

	private static void multiThreadListExample() {
		/**
		 * Case#1 No ConcurrentModificationException as the writer thread adds a new
		 * element to the list. The reader thread die and only the writer thread alive.
		 * CopyOnWriteArrayList can be used as a thread safe alternative to ArrayList
		 */
		List<Integer> list = new CopyOnWriteArrayList<>();

		/**
		 * Case#2 ConcurrentModificationException
		 */
		// List<Integer> list = new ArrayList<>();

		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		new WriteThread("Writer", list).start();

		new ReadThread("Reader", list).start();
	}

}
