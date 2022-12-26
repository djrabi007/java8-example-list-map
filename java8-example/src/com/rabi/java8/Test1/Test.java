package com.rabi.java8.Test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
		// sortByStringExample();
		sortByEmployeeAscAndDesc();

	}

	private static void sortByEmployeeAscAndDesc() {
		List<Employee> newLst = new ArrayList<>();
		List<Employee> lst = new ArrayList<>();
		lst = Arrays.asList(new Employee("Rabi", "Podder", 34), new Employee("Swagata", "Podder", 30),
				new Employee("Swagata", "Bose", 30), new Employee("Uma", "Saha", 70),
				new Employee("Pratima", "Chakraborty", 62));

		// M-1 Sort Employee ASCENDING Order by First Name
		// newLst = lst.stream().sorted((e1, e2) ->
		// e1.getfName().compareTo(e2.getfName())).collect(Collectors.toList());

		// M-1A Sort Employee DESCENDING Order by First Name
		// newLst = lst.stream().sorted((e1, e2) ->
		// e2.getfName().compareTo(e1.getfName())).collect(Collectors.toList());


		// M-2 Sort Employee Ascending Order by First Name
		// newLst =
		// lst.stream().sorted(Comparator.comparing(Employee::getfName)).collect(Collectors.toList());


		// M-2 Sort Employee Ascending Order by First Name +Last Name
		newLst = lst.stream().sorted(Comparator.comparing(Employee::getfName).thenComparing(Employee::getlName))
				.collect(Collectors.toList());

		// Just Print NEW LIST
		newLst.forEach(e1 -> System.out.println(e1.getfName() + " " + e1.getlName()));


		// Just Print OLD LIST
		// lst.forEach(e1 -> System.out.println(e1.getfName() + " " + e1.getlName()));
	}

	private static void sortByStringExample() {

		List<String> newlLst = new ArrayList<>();
		List<String> lst = new ArrayList<>();
		lst = Arrays.asList("how", "to", "do", "in", "java");

		// M-2 (Ascending Order)
		// newlLst =
		// lst.stream().sorted(String::compareTo).collect(Collectors.toList());

		// M-1 (Ascending Order)
		// newlLst = lst.stream().sorted((s1, s2) ->
		// s1.compareTo(s2)).collect(Collectors.toList());


		// M-1A (Descending Order)
		// newlLst = lst.stream().sorted((s1, s2) ->
		// s2.compareTo(s1)).collect(Collectors.toList());

		// Just Print - NEW String
		newlLst.forEach(System.out::println);


		// Just Print- OLD String
		// lst.forEach(System.out::println);
	}

}
