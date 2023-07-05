package com.rabi.java8.skip;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SkipExample {
	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		skipByExample(numbers);
		skipNthNumber(numbers, 8);

	}

	private static void skipByExample(List<Integer> numbers) {

		// Skip the first 5 elements
		List<Integer> skippedNumbers = numbers.stream().skip(5).collect(Collectors.toList());
		int fifthNumber = numbers.stream().skip(5 - 1).findFirst().orElse(0);
		List<Integer> firstFiveNumber = numbers.stream().limit(5).collect(Collectors.toList());
		System.out.println(skippedNumbers); // Output: [6, 7, 8, 9, 10]
		System.out.println("5th number = " + fifthNumber);
		System.out.println("Limit of 5  number = " + firstFiveNumber);
	}

	private static void skipNthNumber(List<Integer> numbers, int n) {

		// Skip the first 5 elements
		List<Integer> skippedNumbers = numbers.stream().skip(n).collect(Collectors.toList());
		int fifthNumber = numbers.stream().skip(n - 1).findFirst().orElse(0);
		List<Integer> firstFiveNumber = numbers.stream().limit(n).collect(Collectors.toList());
		System.out.println("********************* nth = " + n + " ***************");
		System.out.println("Full List=>" + numbers);
		System.out.println("Skip " + n + "th number ==>" + skippedNumbers);
		System.out.println("Limit of " + n + " number = " + firstFiveNumber);
	}
}

