package com.rabi.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Java8Example {
	
	public static void main(String[] args) {
		
		listExample();
		
		mapExample();
		
		
		
	}

	private static void mapExample() {
		Map<Integer, String> hm=new HashMap<>();
		hm.put(1, "rabi1");
		hm.put(2, "rabi2");
		hm.put(3, "rabi3");
		hm.put(4, "rabi4");
		hm.put(5, "rabi5");
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
