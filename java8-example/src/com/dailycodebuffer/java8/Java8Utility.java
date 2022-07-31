package com.dailycodebuffer.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Utility {

	static List<Employee> employees = new ArrayList<>();
	static List<String> lstOfProject1 = new ArrayList<>();
	static List<String> lstOfProject2 = new ArrayList<>();
	static List<String> lstOfProject3 = new ArrayList<>();

	static {
		lstOfProject1.add("Project 1");
		lstOfProject1.add("Project 2");
		lstOfProject2.add("Project 1");
		lstOfProject2.add("Project 3");
		lstOfProject3.add("Project 3");
		lstOfProject3.add("Project 4");

		employees.add(new Employee("Shabbir", "Dawoodi", 5000.0, lstOfProject1));

		employees.add(new Employee("Nikhil", "Gupta", 6000.0, lstOfProject2));

		employees.add(new Employee("Shivam", "Kumar", 5500.0, lstOfProject3));
		employees.add(new Employee("Rabi", "Podder", 9000.0, lstOfProject3));
		employees.add(new Employee("Nikhil", "Banerjee", 9000.0, lstOfProject3));


	}

	public static void shortCircuitExampleV2() {
		Stream.generate(Math::random).limit(3).forEach(value -> System.out.println(value));
	}

	// Summation of Salary for all Employee in the list!! ..aggregate function
	// sum/max/min
	public static void reduceExample() {
		Double totalSal = employees.stream().map(employee -> employee.getSalary()).reduce(0.0, Double::sum);
		System.out.println(totalSal);
	}

	// ISSUE::if 2 employee have highest salary i.e. 9000 . it would give 1st!!
	public static void minMaxExample() {
		Employee emp = employees.stream().max(Comparator.comparing(Employee::getSalary))
				.orElseThrow(NoSuchElementException::new);
		System.out.println(emp);
	}

	// Descending order...highest 1st.... lowest last
	public static void minMaxExampleV2() {
		List<Employee> empList = employees.stream().sorted((e1, e2) -> e2.getSalary().compareTo(e1.getSalary()))
				.limit(2)
				.collect(Collectors.toList());
		printEmployee(empList);
	}

	public static void sortingExample() {
		List<Employee> sortedEmployees = employees.stream()
				.sorted((o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName()))
				.collect(Collectors.toList());
		printEmployee(sortedEmployees);
		// System.out.println(sortedEmployees);

	}

	private static void printEmployee(List<Employee> sortedEmployees) {
		sortedEmployees.forEach(emp -> System.out.println(emp));
	}

	// Multiple Compare!!!!
	public static void sortingExampleV2() {

		List<Employee> sortedEmployees = employees.stream()
				.sorted(Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName))
				.collect(Collectors.toList());

		printEmployee(sortedEmployees);
		// System.out.println(sortedEmployees);
	}


	public static void shortCircuitExample() {
		List<Employee> shortCircuit = employees.stream().skip(1) // 1st Element removed... it would show 2nd and 3rd
				.limit(1) // it would show 1 element only.
				.collect(Collectors.toList());
		System.out.println(shortCircuit);
	}

	public static void flatMapExample() {
		String projects = employees.stream().map(employee -> employee.getProjects()) // return Stream<List<String>>
				.flatMap(strings -> strings.stream()) // return Stream<String>
				.collect(Collectors.joining(","));
		System.out.println(projects); // Stream of List of Projects not Employee object. ..it will retrun String (Not
										// Employee)
	}

	public static void filterAndFindFirstNull() {
		Employee firstEmployee2 = employees.stream().filter(employee -> employee.getSalary() > 7000.0) //
				.map(employee -> new Employee(employee.getFirstName(), employee.getLastName(),
						employee.getSalary() * 1.10, employee.getProjects()))
				.findFirst() // No Employee have >7k .... so, it will return null
				.orElse(null);
		System.out.println(firstEmployee2); // findFirst is the terminal operation ...
	}

	public static void filterAndFindFirst() {
		Employee firstEmployee = employees.stream().filter(employee -> employee.getSalary() > 5000.0) //
				.map(employee -> new Employee(employee.getFirstName(), employee.getLastName(),
						employee.getSalary() * 1.10, employee.getProjects()))
				.findFirst() // 1st employee whose salary is >5k .... retruning Employee object only (Not
								// List)
				.orElse(null);
		System.out.println(firstEmployee); // findFirst is the terminal operation ...
	}

	// filter is taking predicate i.e.if/else...
	public static void filterExample() {
		List<Employee> filterEmployee = employees.stream().filter(employee -> employee.getSalary() > 5000.0) // filter

				.map(employee -> new Employee(employee.getFirstName(), employee.getLastName(),
						employee.getSalary() * 1.10, employee.getProjects()))
				.collect(Collectors.toList());

		System.out.println(filterEmployee); // Filter and map are both intermediate operation ..(. Any operation i.e
											// returning Stream itself)
	}

	// map
	// collect
	public static void mapExample() {
		Set<Employee> increasedSal = employees.stream().map(employee -> new Employee(employee.getFirstName(),
				employee.getLastName(), employee.getSalary() * 1.10, employee.getProjects()))
				.collect(Collectors.toSet());
		System.out.println(increasedSal); // Increasing Salary of each employee by 10%
	}

	public static void forEachExample() {
		employees.stream().forEach(employee -> System.out.println(employee)); // Terminal operation ...You can not do
																				// any
																				// operation after that
	}

}
