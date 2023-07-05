package com.rabi.java8.skip;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

class Employee {
	private int empId;
	private String empName;
	private int salary;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Employee(int empId, String empName, int salary) {
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
	}

	public int getSalary() {
		return salary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", salary=" + salary + "]";
	}
}

public class NthHighestSalary {

	public static int findNthHighestSalary(List<Employee> employees, int n) {
		if (employees == null || employees.isEmpty() || n <= 0 || n > employees.size()) {
			throw new IllegalArgumentException("Invalid input");
		}

		Optional<Integer> nthHighestSalary = employees.stream().map(Employee::getSalary).distinct()
				.sorted(Comparator.reverseOrder()).skip(n - 1).findFirst();

		return nthHighestSalary.orElseThrow(() -> new NoSuchElementException("Nth highest salary not found"));
	}

	public static Employee findNthHighestSalaryWithEmployee(List<Employee> employees, int n) {
		if (employees == null || employees.isEmpty() || n <= 0 || n > employees.size()) {
			throw new IllegalArgumentException("Invalid input");
		}

		Employee nthHighestEmployee = employees.stream()
				// .map(Employee::getSalary).distinct()
				// .sorted(Comparator.reverseOrder())
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(n - 1).findFirst()
				.orElse(null);

		// highest salary not found"));
		if (nthHighestEmployee != null) {
			System.out.println("Nth Highest Salary: " + nthHighestEmployee.getSalary());
			System.out.println("Employee Name: " + nthHighestEmployee.getEmpName());
		} else {
			System.out.println("Invalid value of n or employee list is empty.");
		}
		return nthHighestEmployee;

	}

	public static void main(String[] args) {
		// Example usage
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(101, "John", 5000));
		employees.add(new Employee(102, "Alice", 8000));
		employees.add(new Employee(103, "Bob", 3000));
		employees.add(new Employee(104, "Mary", 6000));
		employees.add(new Employee(105, "Tom", 2000));
		employees.add(new Employee(106, "Jane", 7000));
		employees.add(new Employee(107, "Mike", 4000));

		int n = 3;
		int nthHighestSalary = findNthHighestSalary(employees, n);
		List<Integer> salList = employees.stream().map(Employee::getSalary).collect(Collectors.toList());
		System.out.println("****Employee List ****** = " + employees);
		System.out.println("********************** M-1  ********************************");
		System.out.println("****Salary List ****** = " + salList);
		System.out.println("The " + n + "th highest salary is: " + nthHighestSalary);

		Employee nthHighestSalaryEmployee = findNthHighestSalaryWithEmployee(employees, n);
		System.out.println("********************** M-2  ********************************");
		System.out.println("The " + n + "th highest salary  with Employee is: " + nthHighestSalaryEmployee);

	}
}
