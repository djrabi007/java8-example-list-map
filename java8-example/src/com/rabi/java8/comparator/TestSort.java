package com.rabi.java8.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//https://howtodoinjava.com/java8/comparator-example-lambda/
public class TestSort {



	public static void main(String[] args) {

		sortStringOnly();
		sortCustomObject();

	}

	private static void sortStringOnly() {
		List<String> strings = Arrays.asList("how", "to", "do", "in", "java", "dot", "com");

		// M-1
		// List<String> sorted = strings.stream().sorted((s1, s2) ->
		// s1.compareTo(s2)).collect(Collectors.toList());
		// M-2 (method Reference)
		// List<String> sorted =
		// strings.stream().sorted(String::compareTo).collect(Collectors.toList());
		// System.out.println(sorted);
//M-3
		strings.stream().sorted().forEach(System.out::println);

	}

	private static void sortCustomObject() {
		boolean isRevOrder = false;
		List<Employee> oldEmployees = SortUtil.getEmployees();


		// List<Employee> newSortedEmployeesByFistName = sortByProperty(oldEmployees,
		// F_NAME, true);
		System.out.println("**********SORT BY First Name**************************************");
		isRevOrder = false;
		List<Employee> newSortedEmployeesByFistName = SortUtil.sortByProperty(oldEmployees, SortUtil.F_NAME,
				isRevOrder);
		SortUtil.printEmployeeList(newSortedEmployeesByFistName);
		System.out.println("***********SORT BY Last Name +Reverse order**************************************");
		isRevOrder = true;
		List<Employee> newSortedEmployeesByLastName = SortUtil.sortByProperty(oldEmployees, SortUtil.L_NAME,
				isRevOrder);
		SortUtil.printEmployeeList(newSortedEmployeesByLastName);

		System.out.println("***********SORT BY Emp ID**************************************");
		isRevOrder = false;
		List<Employee> newSortedEmployeesByEmpId = SortUtil.sortByProperty(oldEmployees, SortUtil.EMP_ID, isRevOrder);
		SortUtil.printEmployeeList(newSortedEmployeesByEmpId);

		System.out.println("***********SORT BY First Name +Last Name**************************************");
		isRevOrder = true;
		List<Employee> newSortedEmployeesByFirstAndLastName = SortUtil.sortByProperty(oldEmployees,
				SortUtil.F_NAME_L_NAME,
				isRevOrder);
		SortUtil.printEmployeeList(newSortedEmployeesByFirstAndLastName);

		System.out.println("***********Parallel SORT BY Array**************************************");
		// Parallel sorting
		Employee[] employeesArray = oldEmployees.toArray(new Employee[oldEmployees.size()]);
		Arrays.parallelSort(employeesArray,
				Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName));
		SortUtil.printEmpArray(employeesArray);
	}


}
