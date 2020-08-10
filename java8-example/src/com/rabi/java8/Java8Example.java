package com.rabi.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Java8Example {
	
	private static final String INITIAL_NEW_COUNTER_0_AND_I_0 = "#initial New counter=0 and i=0 ####";

	public static void main(String[] args) {
		
		// listExample();
		
		// mapExample();
		atomicIntegerCounter();
		
	}




	private static void atomicIntegerCounter() {
		int initialvalue = 0;
		AtomicInteger counter = new AtomicInteger(initialvalue);
		// runHardcodedThread(counter);// M-1 Hardcoded Thread
		// runThreadByLoop(counter);// M-1A
		runThreadByExecutorServiceThreadPool(counter); // M-2 - ExecutorService

	}




	private static void runThreadByExecutorServiceThreadPool(AtomicInteger counter) {
		ExecutorService es = Executors.newFixedThreadPool(5);
		System.out.println("##########Using 5 Threadpool+  10 task " + INITIAL_NEW_COUNTER_0_AND_I_0);
		for (int i = 1; i <= 10; i++) {
			es.execute(new CounterThreadTask(counter));
		}
	}




	private static void runThreadByLoop(AtomicInteger counter) {
		System.out.println("##########Using 10 thread 10 task###" + INITIAL_NEW_COUNTER_0_AND_I_0);
		for (int i = 1; i <= 10; i++) {
			new Thread(new CounterThreadTask(counter), "Thread-" + i).start();
		}
	}




	private static void runHardcodedThread(AtomicInteger counter) {
		System.out.println("##########Using Hardcoded 8 task+8 thread ###" + INITIAL_NEW_COUNTER_0_AND_I_0);
		new Thread(new CounterThreadTask(counter), "Thread-1").start();
		new Thread(new CounterThreadTask(counter), "Thread-2").start();
		new Thread(new CounterThreadTask(counter), "Thread-3").start();
		new Thread(new CounterThreadTask(counter), "Thread-4").start();
		new Thread(new CounterThreadTask(counter), "Thread-5").start();
		new Thread(new CounterThreadTask(counter), "Thread-6").start();
		new Thread(new CounterThreadTask(counter), "Thread-7").start();
		new Thread(new CounterThreadTask(counter), "Thread-8").start();
	}



	private static void mapExample() {
		Map<Integer, String> hm=new HashMap<>();
		hm.put(1, "rabi1");
		hm.put(2, "rabi2");
		hm.put(3, "rabi3");
		hm.put(4, "rabi4");
		hm.put(5, "rabi5");
		hm.put(34, "rabi3");
		//M-1
		hm.forEach((k,v)->System.out.println("Key= "+k +" Value= "+v));
		//M-2 
		hm.entrySet().stream().forEach(obj->System.out.println(obj));
		//M-3
		System.out.println("Filter Map using Java8 ");
		hm.entrySet().stream().filter(v->"rabi3".equals(v.getValue()))
		                .forEach(obj->System.out.println(obj));
	}

	private static void listExample() {
		List<String> lst= new ArrayList<>();
		lst.add("rabi1");
		lst.add("roddur");
		lst.add("ma");
		lst.add("baba");
		lst.add("swagata");
		
		//Without Java-8
		System.out.println("###Without Java8###");
		for(String s:lst) {
			System.out.println(s);
		}
		System.out.println("###With Java8###");
		lst.forEach(s->System.out.println(s));
		
		System.out.println("###With Java8 -Consumer Interface ###");
		Consumer<String> cns=s->System.out.println(s);
		for(String st:lst) {
			cns.accept(st);
		}
		
		System.out.println("###Without Java8 -Filter <Starts with ba i.e baba ###");
		for(String s:lst) {
			if(s.startsWith("ba")) {
			System.out.println(s);
			}
		}
		
		System.out.println("###With Java8 -Filter <Starts with ba i.e baba ###");
		lst.stream().filter(s->s.startsWith("ba"))
		             .forEach(s->System.out.println(s));
		
		
	}

}
