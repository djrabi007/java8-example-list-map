package com.rabi.java8.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortUtil {

	public static final String F_NAME_L_NAME = "fName_lName";
	public static final String EMP_ID = "empId";
	public static final String L_NAME = "lName";
	public static final String F_NAME = "fName";

	public static void printEmpArray(Employee[] employeesArray) {
		for (Employee emp : employeesArray) {
			System.out.println(emp);
		}
	}

	public static void printEmployeeList(List<Employee> newSortedEmployees) {
		newSortedEmployees.forEach(System.out::println);
	}

	public static List<Employee> sortByProperty(List<Employee> employees, String fieldName, boolean isRevOrder) {

		switch (fieldName) {
		case F_NAME:
			// Sort all employees by first name
			// employees.sort(Comparator.comparing(e -> e.getFirstName()));
			// OR you can use below
			if (isRevOrder) {
				employees.sort(Comparator.comparing(Employee::getFirstName).reversed());
			} else {
				// employees.sort(Comparator.comparing(Employee::getFirstName));
				employees.sort((e1, e2) -> e1.getFirstName().compareTo(e2.getFirstName()));
			}
			break;

		case L_NAME:
			if (isRevOrder) {
				employees.sort(Comparator.comparing(Employee::getLastName).reversed());
			} else {
				employees.sort(Comparator.comparing(Employee::getLastName));
			}
			break;

		case EMP_ID:
			if (isRevOrder) {
				employees.sort(Comparator.comparing(Employee::getId).reversed());
			} else {
				employees.sort(Comparator.comparing(Employee::getId));
			}
			break;

		case F_NAME_L_NAME:
			if (isRevOrder) {
				employees.sort(
						Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName).reversed());
			} else {
				employees.sort(Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName));
			}
			break;

		default:
			break;

		}
		return employees;

	}

	public static List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(6, "Yash", "Chopra", 25));
		employees.add(new Employee(2, "Aman", "Sharma", 28));
		employees.add(new Employee(3, "Aakash", "Yaadav", 52));
		employees.add(new Employee(5, "David", "Kameron", 19));
		employees.add(new Employee(4, "James", "Hedge", 72));
		employees.add(new Employee(8, "Balaji", "Subbu", 88));
		employees.add(new Employee(7, "Karan", "Johar", 59));
		employees.add(new Employee(1, "Lokesh", "Gupta", 32));
		employees.add(new Employee(9, "Vishu", "Bissi", 33));
		employees.add(new Employee(10, "Lokesh", "Ramachandran", 60));
		employees.add(new Employee(11, "Rabi", "Podder", 25));
		employees.add(new Employee(12, "Balaji", "Konar", 60));
		employees.add(new Employee(11, "Rabi", "Das", 25));
		return employees;
	}

}
