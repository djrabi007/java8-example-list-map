package com.rabi.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamRabiUtil {

	/**
	 * Hardcoded List of Employee
	 * 
	 * @return
	 */
	public static List<NewEmployee> populateNewEmpList() {
		List<NewEmployee> lstEmp = new ArrayList<>();

		lstEmp.add(new NewEmployee(1345, "Raja", 24000));
		lstEmp.add(new NewEmployee(1235, "Soumya", 13000));
		lstEmp.add(new NewEmployee(1545, "Tinku", 34000));
		lstEmp.add(new NewEmployee(1255, "Shourjo", 10000));
		lstEmp.add(new NewEmployee(3560, "Rabi", 36000));
		lstEmp.add(new NewEmployee(2300, "Piku", 15000));
		lstEmp.add(new NewEmployee(2300, "Satya", 34000));
		lstEmp.add(new NewEmployee(1546, "Tinku", 34001));
		return lstEmp;
	}

	/**
	 * Print List for NewEmployee (Name+Salary)
	 * 
	 * @param lstEmp
	 */
	public static void printNewEmpList(List<NewEmployee> lstEmp) {
		lstEmp.forEach(emp -> System.out.println(emp.getEmpName() + "::" + emp.getEmpId() + "::: " + emp.getSalary()));
	}

	/**
	 * 1. Sorted using Comparator.comparingInt 2. limit () to allow only 1st 3
	 * employee 3. collect() to get List ( Terminal Operation)
	 * 
	 * @param lstEmp
	 * @param maxsize
	 * @return
	 */
	public static List<NewEmployee> fistThreeHighestSalWithJava8Stream(List<NewEmployee> lstEmp, int maxsize) {
		List<NewEmployee> lstEmpV2 = lstEmp.stream()
				.sorted(Comparator.comparingInt(NewEmployee::getSalary).reversed())
				.limit(maxsize).collect(Collectors.toList());

		return lstEmpV2;
	}

	/**
	 * 1. create Copy of List to avoid mutating 2. Sort in descending by e2.getXX()
	 * -e1.getXX() 3. 1st 3 employy by for() loop
	 * 
	 * @param lstEmp
	 * @param maxsize
	 * @return
	 */
	public static List<NewEmployee> fistThreeHighestSalWithoutJava8Stream(List<NewEmployee> lstEmp, int maxsize) {
		List<NewEmployee> copyLstEmp = new ArrayList<>(lstEmp); // Avoid mutating Original Array
		List<NewEmployee> returnLstEmp = new ArrayList<>();
		// copyLstEmp.sort((e1, e2) -> e1.getSalary() - e2.getSalary()); //Ascending
		copyLstEmp.sort((e1, e2) -> e2.getSalary() - e1.getSalary()); // Descending ORder
		// Get 1st 3
		for (int i = 0; i < maxsize; i++) {

			NewEmployee ne = copyLstEmp.get(i);
			returnLstEmp.add(ne);
		}
		return returnLstEmp;
	}
	public static int[] populateIntegerArray() {
		int[] numbs = { 10, 3, 45, 3, 21, 11, 45, 9 };
		return numbs;
	}

	public static int[] populateEmptyIntegerArray() {
		int[] numbs = {};
		return numbs;
	}


	/**
	 * 1. use min() 2. then getAsInt() Will Throw exception if Array is empty.
	 * 
	 * @param numbs
	 * @return
	 */
	public static int getMinimumNumber(int[] numbs) {
		return IntStream.of(numbs).min().getAsInt();
		// return IntStream.of(numbs).min().ifPresent(m -> System.out.println(m));
	}

	/**
	 * Old way to get minimum number from Array
	 * 
	 * @param numbs
	 * @return
	 */
	public static int getMinimumNumberWithoutJava8(int[] numbs) {
		int min = numbs[0];
		for (int i = 1; i < numbs.length; i++) {
			if (min > numbs[i]) {
				min = numbs[i];
			}
		}
		return min;
	}

	public static int getMaximumNumber(int[] numbs) {
		return IntStream.of(numbs).max().getAsInt();
	}

	public static double getAverageNumber(int[] numbs) {
		return IntStream.of(numbs).average().getAsDouble();
	}

	public static int getSumOfNumber(int[] numbs) {
		return IntStream.of(numbs).sum();
	}

	public static long getCountOfNumber(int[] numbs) {
		return IntStream.of(numbs).count();
	}

	/**
	 * summaryStatistics() will give min(),max(),average(),sum(),count() ...
	 * 
	 * @param numbs
	 * @return
	 */
	public static IntSummaryStatistics getStatisticsOfNumber(int[] numbs) {
		return IntStream.of(numbs).summaryStatistics();

	}

	/**
	 * distinct()
	 * 
	 * @param numbs
	 * @return
	 */
	public static int[] getDistinctNumber(int[] numbs) {
		return IntStream.of(numbs).distinct().toArray();
	}

	public static int[] getSortedNumber(int[] numbs) {
		return IntStream.of(numbs).sorted().toArray();
	}

	/**
	 * limit()
	 * 
	 * @param numbs
	 * @param maxSize
	 */
	public static void printFirstLimitedNumber(int[] numbs, int maxSize) {
		System.out.println("\n ### 1st <maxsize> number only ###");
		IntStream.of(numbs).limit(maxSize).forEach(System.out::println);
	}

	/**
	 * Original Array is not MUTATED ...so no need to clone the Array !!! / Pipeline
	 * ALGO::: //
	 * array-->stream-->distinct-->sort-->limit-->sum/min/max/count/average
	 * 
	 * @param numbs
	 * @param maxSize
	 */

	public static void printDistinctSortedLimitedNumber(int[] numbs, int maxSize) {
		System.out.println("\n ### 1st distinct+Sorted+<maxsize> number only - With Java8  ###");
		IntStream.of(numbs).distinct().sorted().limit(maxSize).forEach(System.out::println);
	}

	/**
	 * 1. take copy of Array 2. sort #1 3. for loop for 3 element
	 * 
	 * @param numbs
	 * @param maxSize
	 */
	public static void printSortedLimitedNumberWithoutJava8(int[] numbs, int maxSize) {
		System.out.println("\n ### 1st Sorted+<maxsize> number only  -Without Java8 ###");
		int[] copyNumbs = Arrays.copyOf(numbs, numbs.length);
		Arrays.sort(copyNumbs);// Avoid Mutating Original Array
		for (int i = 0; i < maxSize; i++) {
			System.out.println(copyNumbs[i]);
		}

	}

	/**
	 * print array with "," separated
	 * 
	 * @param numbs
	 */
	public static void printArray(int[] numbs) {
		Arrays.stream(numbs).forEach(e -> System.out.print(e + ", "));
	}

}
