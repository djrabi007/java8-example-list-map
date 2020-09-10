package com.rabi.java8;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.rabi.java8.stream.Car;
import com.rabi.java8.stream.NewEmployee;
import com.rabi.java8.stream.StreamRabiUtil;

public class Java8Example {
	
	private static final String INITIAL_NEW_COUNTER_0_AND_I_0 = "#initial New counter=0 and i=0 ####";

	public static void main(String[] args) {
		
		// listExample();
		
		// mapExample();
		// atomicIntegerCounter();
		
		streamExample();

	}

	private static void streamExample() {
		// streamWithArray();
		// streamWithRange();
		// streamWithEmployeeObject();
		// streamWithCarObjectStatistics();

		imperativeVsDeclarative();
		functionalInterfaceExample(); // Functional Interfac :: With Return Type
		normalVsConsumerInterfaceExample(); // Consumer Interface :: No Return Type
		predicateExample(); // Predicate Interface :: boolean return Type

	}

	/**
	 * 1. test() 2. and()/or() 3. removeIf()
	 */
	private static void predicateExample() {
		List<Car> carLst = StreamRabiUtil.populateCarList();

		// *****************Define Predicate************************
		/**
		 * 1. RED Car 2. BLUE Car 3. NISSAN Car
		 */
		Predicate<Car> prdREDCar = car -> StreamRabiUtil.RED.equals(car.getColor());
		Predicate<Car> prdBLUECar = car -> StreamRabiUtil.BLUE.equals(car.getColor());
		Predicate<Car> prdNISSANCar = car -> StreamRabiUtil.NISSAN.equals(car.getMake());
		System.out.println("--------------Print Car by Predicate-test()------------");
		// printByPredCar(carLst, prdREDCar);
		// printByPredCar(carLst, prdBLUECar);
		printByPredCar(carLst, prdNISSANCar);
		System.out.println("--------------Print Car by Predicate-+filter()------------");
		List<Car> carLstREDFltr = filterCar(carLst, prdREDCar);
		List<Car> carLstNISSANFltr = filterCar(carLst, prdNISSANCar);
		// ****************REMOVE Red car from NISSAN****************
		// carLstNISSANFltr.removeIf(prdREDCar);
		// ***********Predicate Combination!!!****************
		List<Car> carLstRedNISSANFltr = filterCar(carLst, prdNISSANCar.and(prdREDCar));
		List<Car> carLstBlueNISSANFltr = filterCar(carLst, prdNISSANCar.and(prdBLUECar));
		System.out.println("\n NISSAN ONLY \n" + carLstNISSANFltr + "\n RED+NISSAN \n" + carLstRedNISSANFltr
				+ "\n BLUE+NISSAN \n" + carLstBlueNISSANFltr);

	}

	private static List<Car> filterCar(List<Car> carLst, Predicate<Car> prdREDCar) {
		List<Car> carLstFltr = carLst.stream().filter(prdREDCar).collect(Collectors.toList());
		return carLstFltr;
	}


	/**
	 * test()
	 * 
	 * @param carLst
	 * @param prdREDCar
	 */
	private static void printByPredCar(List<Car> carLst, Predicate<Car> prdREDCar) {
		for (Car car : carLst) {
			if (prdREDCar.test(car)) {
				System.out.println(car);
			}
		}
	}

	/**
	 * .accept()
	 */
	private static void normalVsConsumerInterfaceExample() {

		Car car1 = StreamRabiUtil.populateCarList().get(2);
		greetCarByNormalMethod(car1);
		System.out.println("------------Green by Consumer-accept() Call-----------------");
		greetByConsumerInterface.accept(car1);

	}

	private static void greetCarByNormalMethod(Car car) {
		System.out.println("------------Green by NORMAL Method Call-----------------");
		printGreetCar(car);

	}

	static Consumer<Car> greetByConsumerInterface = car -> printGreetCar(car);

	private static void printGreetCar(Car car) {
		System.out.println("Car with color= " + car.getColor() + " Make =" + car.getMake());
	}

	/**
	 * 1. apply() 2. andThen()
	 */
	private static void functionalInterfaceExample() {

		int incrByMethod = incrementBy1(1);
		int incrByFunctionalInterface = incrementBy1Function.apply(5);
		int multiByFunctionalInterface = multiplyBy10Function.apply(5);
		int incrementAndMultiply = incrementBy1AndMultiplyBy10Function.apply(5);
		int incrBy1Mult = incrBy1MultFunction.apply(5, 7);

		System.out
				.println(">>>incrByMethod= " + incrByMethod + "\n >>> incrByFunctionalInterface = "
						+ incrByFunctionalInterface + "\n >> multiByFunctionalInterface =" + multiByFunctionalInterface
						+ "\n >>incrementAndMultiply = " + incrementAndMultiply + " \n >>incrBy1Mult = " + incrBy1Mult);

	}

	private static int incrementBy1(int i) {
		return i + 1;
	}

	static Function<Integer, Integer> incrementBy1Function = n -> n + 1;
	static Function<Integer, Integer> multiplyBy10Function = n -> n * 10;
	static Function<Integer, Integer> incrementBy1AndMultiplyBy10Function = incrementBy1Function
			.andThen(multiplyBy10Function); // .andThen()

	static BiFunction<Integer, Integer, Integer> incrBy1MultFunction = (a, b) -> (a + 1) * b;

	private static void streamWithCarObjectStatistics() {

		List<Car> lstCar = StreamRabiUtil.populateCarList();
		List<Car> lstCarEmpty = new ArrayList<>();
		statisticsAndGroup(lstCar, lstCarEmpty);

	}

	private static void imperativeVsDeclarative() {
		List<Car> lstCar = StreamRabiUtil.populateCarList();
		// ********************** Imperative Approach - Get Red Car
		List<Car> lstRedCar = new ArrayList<>();
		
		for (Car rCar : lstCar) {
			if (StreamRabiUtil.RED.equals(rCar.getColor())) {
				lstRedCar.add(rCar);
			}
		}
		System.out.println("############List Of RED car :: IMPERATIVE APPROACH #######");
		StreamRabiUtil.printCarList(lstRedCar);
		
		// ******************* Declarative Approach - Get Red Car
		System.out.println("############List Of RED car :: DECLARATIVE APPROACH #######");
		lstCar.stream().filter(rCar -> StreamRabiUtil.RED.equals(rCar.getColor())).forEach(System.out::println);
	}

	private static void statisticsAndGroup(List<Car> lstCar, List<Car> lstCarEmpty) {
		double avgCost = StreamRabiUtil.getAverageCarCost(lstCar);
		double avgCostEmpty = StreamRabiUtil.getAverageCarCost(lstCarEmpty);
		double minCost = StreamRabiUtil.getMinCarCost(lstCar);
		double maxCost = StreamRabiUtil.getMaxCarCost(lstCar);
		double sumCost = StreamRabiUtil.getSumCarCost(lstCar);
		double countOfCar = StreamRabiUtil.getCountOfCar(lstCar, "Green");

		DoubleSummaryStatistics allStat = StreamRabiUtil.getSummaryStat(lstCar);

		Map<String, List<Car>> groupingByColor = StreamRabiUtil.getGroupingBy(lstCar, StreamRabiUtil.COLOR);
		Map<String, List<Car>> groupingByMake = StreamRabiUtil.getGroupingBy(lstCar, StreamRabiUtil.MAKE);

		System.out.println("#############Original List of Car #######");
		StreamRabiUtil.printCarList(lstCar);
		System.out.println("-----------------M-1 (Statistics)-------------------");
		System.out.println(
				"avgCost =" + avgCost + "   >>>>> avgCostEmpty= " + avgCostEmpty + ">>> countOfCar =" + countOfCar);
		System.out.println("\n\n minCost =" + minCost + "   >>>>> maxCost= " + maxCost
				+ " >>>> sumCost= " + sumCost);
		System.out.println("-----------------M-2 (Statistics)-------------------");
		System.out.println(allStat);

		System.out.println("\n \n ---------------GROUP BY COLOR---------------------");
		StreamRabiUtil.printGroupCarMap(groupingByColor);
		System.out.println("--------------GROUP BY MODEL----------------------");
		StreamRabiUtil.printGroupCarMap(groupingByMake);
	}

	private static void streamWithEmployeeObject() {

		List<NewEmployee> lstEmp = StreamRabiUtil.populateNewEmpList();
		int maxsize = 5;
		System.out.println("#############Original List of Employee#######");
		StreamRabiUtil.printNewEmpList(lstEmp);

		System.out.println("#############Sorted List of Employee - Without Java8 Stream#######");
		List<NewEmployee> sortedEmpWithoutJava8 = StreamRabiUtil.fistThreeHighestSalWithoutJava8Stream(lstEmp, maxsize);
		StreamRabiUtil.printNewEmpList(sortedEmpWithoutJava8);
		System.out.println("\n #############Sorted List of Employee - With Java8 Stream#######");
		List<NewEmployee> sortedEmpWithJava8 = StreamRabiUtil.fistThreeHighestSalWithJava8Stream(lstEmp, maxsize);
		StreamRabiUtil.printNewEmpList(sortedEmpWithJava8);
	}





	// 1+2+3+....+10
	private static void streamWithRange() {
		int start_num = 1;
		int end_num = 10;
		streamExampleWithRange(start_num, end_num); // 1+2+3+....+10
	}

	private static void streamExampleWithRange(int start_num, int end_num) {
		int[] n = IntStream.rangeClosed(start_num, end_num).toArray();
		StreamRabiUtil.printArray(n); // print 1 to 100
		// Summation of 1 to 100 i.e (n/2)*(n+1)
		// M-1
		int sum_num = StreamRabiUtil.getSumOfNumber(n);
		// M-2
		long sum_num_stat = StreamRabiUtil.getStatisticsOfNumber(n).getSum();
		System.out.println("\n Summation of 1+2+3+...+100 = " + sum_num + "\n :: sum_num_stat= " + sum_num_stat);// n*(n+1)/2
	}

	private static void streamWithArray() {
		int[] numbs = StreamRabiUtil.populateIntegerArray();
		int[] emptyNumbs = StreamRabiUtil.populateEmptyIntegerArray();

		System.out.println("########Old Array###########");
		StreamRabiUtil.printArray(numbs);
		System.out.println("\n\n ######### M-1 (Without Statistics) ########");
		int min_num = StreamRabiUtil.getMinimumNumber(numbs);
		// int min_num = StreamRabiUtil.getMinimumNumber(emptyNumbs);
		int min_num_without_java8 = StreamRabiUtil.getMinimumNumberWithoutJava8(numbs);
		int max_num = StreamRabiUtil.getMaximumNumber(numbs);
		int sum_num = StreamRabiUtil.getSumOfNumber(numbs);
		double avg_num = StreamRabiUtil.getAverageNumber(numbs);
		long count_num = StreamRabiUtil.getCountOfNumber(numbs);

		System.out.println("min_num = " + min_num + "### OLD WAY ### min_num_without_java8= " + min_num_without_java8);
		System.out.println("max_num = " + max_num);
		System.out.println("sum_num = " + sum_num);
		System.out.println("avg_num = " + avg_num);
		System.out.println("count_num = " + count_num);

		IntSummaryStatistics intStat = StreamRabiUtil.getStatisticsOfNumber(numbs);

		System.out.println("\n ######### M-2 (With Statistics) ########");
		System.out.println("stat_min_num = " + intStat.getMin());
		System.out.println("stat_max_num = " + intStat.getMax());
		System.out.println("stat_sum_num = " + intStat.getSum());
		System.out.println("stat_avg_num = " + intStat.getAverage());
		System.out.println("stat_count_num = " + intStat.getCount());


		System.out.println("\n #######NEW Distint Array #######");
		StreamRabiUtil.printArray(StreamRabiUtil.getDistinctNumber(numbs));

		System.out.println("\n #######NEW Sorted  Array #######");
		StreamRabiUtil.printArray(StreamRabiUtil.getSortedNumber(numbs));

		StreamRabiUtil.printFirstLimitedNumber(numbs, 4);// 1st 4 number
		StreamRabiUtil.printDistinctSortedLimitedNumber(numbs, 4);
		StreamRabiUtil.printSortedLimitedNumberWithoutJava8(numbs, 4);
	}





	private static void atomicIntegerCounter() {
		int initialvalue = 0;
		AtomicInteger counter = new AtomicInteger(initialvalue);
		// runHardcodedThread(counter);// M-1 Hardcoded Thread
		// runThreadByLoop(counter);// M-1A
		runThreadByExecutorServiceThreadPool(counter); // M-2 - ExecutorService

	}




	private static void runThreadByExecutorServiceThreadPool(AtomicInteger counter) {
		ExecutorService es = Executors.newFixedThreadPool(5);
		System.out.println("##########Using 5 Threadpool+  10 task " + INITIAL_NEW_COUNTER_0_AND_I_0);
		for (int i = 1; i <= 10; i++) {
			es.execute(new CounterThreadTask(counter));
		}
	}




	private static void runThreadByLoop(AtomicInteger counter) {
		System.out.println("##########Using 10 thread 10 task###" + INITIAL_NEW_COUNTER_0_AND_I_0);
		for (int i = 1; i <= 10; i++) {
			new Thread(new CounterThreadTask(counter), "Thread-" + i).start();
		}
	}




	private static void runHardcodedThread(AtomicInteger counter) {
		System.out.println("##########Using Hardcoded 8 task+8 thread ###" + INITIAL_NEW_COUNTER_0_AND_I_0);
		new Thread(new CounterThreadTask(counter), "Thread-1").start();
		new Thread(new CounterThreadTask(counter), "Thread-2").start();
		new Thread(new CounterThreadTask(counter), "Thread-3").start();
		new Thread(new CounterThreadTask(counter), "Thread-4").start();
		new Thread(new CounterThreadTask(counter), "Thread-5").start();
		new Thread(new CounterThreadTask(counter), "Thread-6").start();
		new Thread(new CounterThreadTask(counter), "Thread-7").start();
		new Thread(new CounterThreadTask(counter), "Thread-8").start();
	}



	private static void mapExample() {
		Map<Integer, String> hm=new HashMap<>();
		hm.put(1, "rabi1");
		hm.put(2, "rabi2");
		hm.put(3, "rabi3");
		hm.put(4, "rabi4");
		hm.put(5, "rabi5");
		hm.put(34, "rabi3");
		//M-1
		hm.forEach((k,v)->System.out.println("Key= "+k +" Value= "+v));
		//M-2 
		hm.entrySet().stream().forEach(obj->System.out.println(obj));
		//M-3
		System.out.println("Filter Map using Java8 ");
		hm.entrySet().stream().filter(v->"rabi3".equals(v.getValue()))
		                .forEach(obj->System.out.println(obj));
	}

	private static void listExample() {
		List<String> lst= new ArrayList<>();
		lst.add("rabi1");
		lst.add("roddur");
		lst.add("ma");
		lst.add("baba");
		lst.add("swagata");
		
		//Without Java-8
		System.out.println("###Without Java8###");
		for(String s:lst) {
			System.out.println(s);
		}
		System.out.println("###With Java8###");
		lst.forEach(s->System.out.println(s));
		
		System.out.println("###With Java8 -Consumer Interface ###");
		Consumer<String> cns=s->System.out.println(s);
		for(String st:lst) {
			cns.accept(st);
		}
		
		System.out.println("###Without Java8 -Filter <Starts with ba i.e baba ###");
		for(String s:lst) {
			if(s.startsWith("ba")) {
			System.out.println(s);
			}
		}
		
		System.out.println("###With Java8 -Filter <Starts with ba i.e baba ###");
		lst.stream().filter(s->s.startsWith("ba"))
		             .forEach(s->System.out.println(s));
		
		
	}

}
