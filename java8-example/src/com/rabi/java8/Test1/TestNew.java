package com.rabi.java8.Test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestNew {

	public static void main(String[] args) {
		// sortByStringExample();
		// sortByEmployeeAscAndDesc();

//		Test test = new Test();
//		boolean b1 = false;
//		StringBuffer st = new StringBuffer("Bimal");
//		System.out.println("BEFORE b1=" + b1 + " st= " + st);
//		b1 = test.m1(st);
//		System.out.println("AFTER b1=" + b1 + " st= " + st);

		List<Employee> employeeList = populateEmployee();

		System.out.println("###############Before Sorting Employee ############");
		printEmployee(employeeList);

		System.out.println("@@@@@@@@@@@@@@@After Sorting using STREAM  Employee @@@@@@@@@@@@@@");
		List<Employee> employeeListNew = employeeList.stream()
				.sorted(Comparator.comparing(Employee::getfName).thenComparing(Employee::getAge))
				.collect(Collectors.toList());
		printEmployee(employeeListNew);

//		System.out.println("###############After Sorting using LAMBDA Employee ############");
//		// employeeList.sort((u1, u2) -> u1.getfName().compareTo(u2.getfName()));
//		employeeList.sort((u1, u2) -> u1.getAge() - u2.getAge());
//		printEmployee(employeeList);
		


	}

	private static void printEmployee(List<Employee> employeeList) {
		for (Employee ee : employeeList) {
			System.out.println("Employee Name : " + ee.getfName() + " :: Emplyee Age :" + ee.getAge());
		}
	}

	private static List<Employee> populateEmployee() {
		List<Employee> employeeList = new ArrayList<>();
		Employee e1 = new Employee();
		e1.setfName("Rabi");
		e1.setAge(33);

		Employee e2 = new Employee();
		e2.setfName("Swagata");
		e2.setAge(23);



		Employee e3 = new Employee();
		e3.setfName("Roddur");
		e3.setAge(5);

		Employee e3a = new Employee();
		e3a.setfName("Swagata");
		e3a.setAge(9);

		employeeList.add(e1);
		employeeList.add(e2);

		employeeList.add(e3);
		employeeList.add(e3a);
		return employeeList;
	}

	private boolean m1(StringBuffer st) {
		// TODO Auto-generated method stub
		st = st.append(" Rajaaa");
		boolean b1 = false;
		b1 = true;
		System.out.println("INSIDE  b1=" + b1 + " st= " + st);
		return b1;

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
