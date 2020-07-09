package com.codejava.net.multithreading.list;

import java.util.List;

public class WriteThread extends Thread
{

	private List<Integer> list;
	public WriteThread(String name, List<Integer> list)
	{
		// TODO Auto-generated constructor stub
		 this.list = list;
	     super.setName(name);
	}
	
	 public void run() 
	 {
	        int count = 6;
	 
	        while (true)
	        {	 
			RabiThreadUtil.goToSleep(5000);
	 
	            list.add(count++);
	 
	            System.out.println(super.getName() + " done");
	        }
	    }

}
