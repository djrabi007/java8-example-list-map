package com.onlinetutorialspoint.java8;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * https://www.onlinetutorialspoint.com/java-8
 * 
 * @author Rabi
 *
 */
public class Java8Collection {

	public static void main(String[] args) {
		
		//Map related
		ExampleUtil.listToMapExample();
		ExampleUtil.sortMapExample();
		ExampleUtil.filterMapExample();
		
		//List Related
		ExampleUtil.streamToListExample();
		ExampleUtil.removeDuplicateListExample();
		ExampleUtil.filterListExample();
		ExampleUtil.removeNullDepartmentListExample();
		ExampleUtil.removeNullStringListExample();
		
		//Array Related
		ExampleUtil.concatenateTwoArrayofString();
		ExampleUtil.searchInArrayExample();

	}


}
