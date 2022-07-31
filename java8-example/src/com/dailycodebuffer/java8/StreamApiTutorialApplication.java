package com.dailycodebuffer.java8;

public class StreamApiTutorialApplication {


    public static void main(String[] args) {

        //foreach
		Java8Utility.forEachExample();

		// map & collect
		Java8Utility.mapExample();

		// filter
		Java8Utility.filterExample();

		// FilterAndFindFirst
		Java8Utility.filterAndFindFirst();

		// Null or Empty value
		Java8Utility.filterAndFindFirstNull();

		// flatMap
		Java8Utility.flatMapExample();

		// short Circuit operations
		Java8Utility.shortCircuitExample();

		// Finite Data
		Java8Utility.shortCircuitExampleV2();

		// sorting
		Java8Utility.sortingExample();
		Java8Utility.sortingExampleV2();

		// min or max
		Java8Utility.minMaxExample(); // Highest Salary 1 person
		Java8Utility.minMaxExampleV2(); // Highest Salary Person (2 person)

		// reduce
		Java8Utility.reduceExample();

    }

}



