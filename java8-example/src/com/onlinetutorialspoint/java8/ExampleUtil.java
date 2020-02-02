package com.onlinetutorialspoint.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExampleUtil {
	
	
	public static void concatenateTwoArrayofString() {
		
        
        System.out.println("############# 1st String[] ##########");
        String[] alphabets = populateArrayOfString();
        printStringArray(alphabets);
        System.out.println("############# 2nd String[] ##########");
        String[] numarics = populateArrayOfInteger();
        printStringArray(numarics);
        System.out.println("############# Combine String[] ##########");
        String[] both = Stream.concat(Arrays.stream(alphabets),Arrays.stream(numarics))
        						.toArray(String[]::new);
        printStringArray(both);
	}

	private static void printStringArray(String[] both) {
		for (String string : both) {
            System.out.println(string);
        }
	}
	
	public static void removeNullStringListExample() {
		System.out.println("############# List before remove Null from List<String> ##########");
		List<String> cityList= poupulateListOfStringWithNull();
		printListOfString(cityList);
		System.out.println("############# List after remove Null from List<String> ##########");
		List<String> cityAfterRemoveNullList= removeNullFromStringList(cityList);
		printListOfString(cityAfterRemoveNullList);
		
	}


	public static void removeNullDepartmentListExample() {
		System.out.println("&&&&&&&&&&&&&&&&&& List before remove Null from List<Department> &&&&&&&&&&&&");
		List<Department> deptList = populateNullDepartmentList();
		printListOfDepartMent(deptList);
		System.out.println("&&&&&&&&&&&&&&&&&& List After remove Null from List<Department> &&&&&&&&&&&&");
		List<Department> deptListAfterreoveNull =removeNullFromDeptList(deptList);
		printListOfDepartMent(deptListAfterreoveNull);
	}
	
	public static void filterListExample() {
		List<Department> deptList = populateWithoutNullDepartmentList();

		System.out.println("%%%%%%%%%%%%%%%%% List before Filter %%%%%%%%%%%%");
		printListOfDepartMent(deptList);
		System.out.println("%%%%%%%%%%%%%%%%% List After Filter  -Without Java 8 %%%%%%%%%%%%");
		List<Department> filterDeptListWithoutJava8 = filterListByDeptNameWithoutJava8(deptList, "Management");
		printListOfDepartMent(filterDeptListWithoutJava8);
		System.out.println("%%%%%%%%%%%%%%%%% List After Filter  -With Java 8 %%%%%%%%%%%%");
		List<Department> filterDeptList = filterListByDeptNameWithJava8(deptList, "Management");
		printListOfDepartMent(filterDeptList);

	}

	

	public static List<Department> filterListByDeptNameWithoutJava8(List<Department> deptList, String deptName) {

		List<Department> filterDeptList = new ArrayList<>();
		for (Department dept : deptList) {
			if (deptName.equals(dept.getDeptName())) {
				filterDeptList.add(dept);
			}
		}
		return filterDeptList;
	}

	public static List<Department> filterListByDeptNameWithJava8(List<Department> deptList, String deptName) {
		List<Department> filterDeptList = deptList.stream().filter(dept -> deptName.equals(dept.getDeptName()))
				.collect(Collectors.toList());
		return filterDeptList;
	}

	public static void searchInArrayExample() {
		System.out.println("$$$$$$$$$$$$$$  String[] to be seached $$$$$$$");
		String[] s = populateArrayOfString();
		printStringArray(s);
		System.out.println("$$$$$$$$$$$$$$  Search Result $$$$$$$");
		String searchResult1 = seachElementInStringArray(s, "DD");
		String searchResult2 = seachElementInStringArray(s, "PDD");
		System.out.println("searchResult1 = " + searchResult1 + "\n" + "searchResult2 = " + searchResult2);
	}


	public static String seachElementInStringArray(String[] stringArray, String searchElement) {
		if (Arrays.asList(stringArray).contains(searchElement)) {
			return "FOUND!";
		} else {
			return "NOT FOUND!!";
		}
	}

	public static void streamToListExample() {
		System.out.println("#### Stream-->List ####");
		List<String> cityList = poupulateListOfStringWithoutNull();
		printListOfString(cityList);
	}

	
	public static void removeDuplicateListExample() {
		System.out.println("####Before Removing Duplicate####");
		List<Department> deptList = populateWithoutNullDepartmentList();
		printListOfDepartMent(deptList);
		System.out.println("####After Removing Duplicate (Java8 Treeset+Comparator.comparing)####");
		Set<Department> deptSet = removeDuplicateFromList(deptList);
		printSetOfDept(deptSet);
	}




	public static Set<Department> removeDuplicateFromList(List<Department> deptList) {
		// Removing the Elements by assigning list to TreeSet
		Set<Department> deptSet = deptList.stream()
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Department::getDeptName))));
		return deptSet;
	}

	public static void filterMapExample() {
		Map<Integer, String> mobileMap = populateMobilePhoneHashMap();
		System.out.println("####Without Java8 -Filter By Value ####");
		filterMobileByValueWithoutJava8(mobileMap, "Samsung");
		System.out.println("####Without Java8 -Filter By Key ####");
		filterMapByKeyWithIntegerStringWithoutJava8(mobileMap, 2);

		System.out.println("@@@@@@With Java8 -Filter By Value ####");
		String resultVal = filterMapByValueWithIntegerStringJava8(mobileMap, "Samsung");
		System.out.println("Filtering With Value = " + resultVal);
		System.out.println("@@@@@@With Java8 -Filter By Key ####");
		String resultKey = filterMapByKeyWithIntegerStringJava8(mobileMap, 5);
		System.out.println("Filtering With Key = " + resultKey);
	}

	public static String filterMapByKeyWithIntegerStringJava8(Map<Integer, String> hpMap, int hpKey) {
		return hpMap.entrySet().stream().filter(map -> hpKey == map.getKey())
				.map(map -> map.getKey() + ":" + map.getValue()).collect(Collectors.joining());
	}

	public static String filterMapByValueWithIntegerStringJava8(Map<Integer, String> mobileMap, String mobileName) {
		return mobileMap.entrySet().stream().filter(map -> mobileName.equals(map.getValue()))
				.map(map -> map.getKey() + ":" + map.getValue()).collect(Collectors.joining());
	}

	public static void filterMapByKeyWithIntegerStringWithoutJava8(Map<Integer, String> mobileMap, int mobileKey) {
		for (Map.Entry<Integer, String> mobis : mobileMap.entrySet()) {
			if (mobileKey == mobis.getKey())
				printMapIntegerString(mobis);
		}
	}

	
	public static void filterMobileByValueWithoutJava8(Map<Integer, String> mobileMap, String mobileName) {
		for (Map.Entry<Integer, String> mobis : mobileMap.entrySet()) {
			if (mobileName.equals(mobis.getValue()))
				printMapIntegerString(mobis);
		}
	}

	public static void sortMapExample() {
		System.out.println("#######Before HashMap Sort####");
		Map<String, Integer> marks = populateSubjectHashMap();
		printMapStringInteger(marks);
		System.out.println("#######After HashMap Sort By Key####");
		Map<String, Integer> sortedMap = sortMapByKey(marks);
		printMapStringInteger(sortedMap);
	}

	public static Map<String, Integer> sortMapByKey(Map<String, Integer> marks) {
		Map<String, Integer> sortedMap = new LinkedHashMap<>();
		marks.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByKey())
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		return sortedMap;
	}

	public static void listToMapExample() {
		System.out.println("^^^^^^^^^^^^^^^^List Without Null from List<Department> ^^^^^^^^^^^^^^^^^^^^^^^");
		List<Department> deptWithoutNullList = populateWithoutNullDepartmentList();
		printListOfDepartMent(deptWithoutNullList);
		System.out.println("^^^^^^^^^^^^^^^^List With Null from List<Department> ^^^^^^^^^^^^^^^^^^^^^^^");
		List<Department> deptWithNullList = populateNullDepartmentList();
		printListOfDepartMent(deptWithNullList);
		System.out.println("************* Without Null check List<Department> -->HasMap  **********");
		Map<Integer, String> deptWithoutNullMap = listToMap(deptWithoutNullList);
		printMapIntegerString(deptWithoutNullMap);

		// ******NUllpointer Exception *********
		// Map<Integer, String> deptWithNullMap=listToMap(deptWithNullList);
		System.out.println("************* M-1 List-->MAP Null check  List<Department> -->HasMap V1**********");
		Map<Integer, String> deptWithNullMapV1 = listToMapNullCheckV1(deptWithNullList);
		printMapIntegerString(deptWithNullMapV1);
		System.out.println("************* M-2 List-->MAP  Null check List<Department> -->HasMap  V2**********");
		Map<Integer, String> deptWithNullMapV2 = listToMapNullCheckV2(deptWithNullList);
		printMapIntegerString(deptWithNullMapV2);
		System.out.println("************* Remove Null from Map List<Department> -->HasMap V3**********");
		Map<Integer, String> deptRemoveNullV3=removeNullFromMapIntegerString(deptWithNullMapV2);
		printMapIntegerString(deptRemoveNullV3);
	}

    public static List<String> removeNullFromStringList(List<String> stringList) {
    	return stringList.stream().filter(x -> x != null).collect(Collectors.toList());
    }
    public static List<Department> removeNullFromDeptList(List<Department> deptList) {
    	return deptList.stream().filter(x -> x.getDeptName() != null).collect(Collectors.toList());
    }
	private static Map<Integer, String> removeNullFromMapIntegerString(Map<Integer, String> map) {
		map.values().removeIf(Objects::isNull);
		return map;
	}

	

	/**
	 * List to Map using Department Id and Name
	 * 
	 * @param deptList
	 * @return
	 */
	public static Map<Integer, String> listToMap(List<Department> deptList) {
		Map<Integer, String> deptMap = deptList.stream()
				.collect(Collectors.toMap(Department::getDeptId, Department::getDeptName));
		return deptMap;
	}

	public static Map<Integer, String> listToMapNullCheckV1(List<Department> deptList) {
		Map<Integer, String> deptMap = new HashMap<>();
		deptList.forEach(dept -> deptMap.put(dept.getDeptId(), dept.getDeptName()));
		return deptMap;
	}

	public static Map<Integer, String> listToMapNullCheckV2(List<Department> deptList) {
		Map<Integer, String> deptMap = deptList.stream().collect(HashMap::new,
				(m, v) -> m.put(v.getDeptId(), v.getDeptName()), HashMap::putAll);
		return deptMap;
	}
	
	private static void printSetOfDept(Set<Department> deptSet) {
		deptSet.forEach(dept -> System.out.println("DeptId (" + dept.getDeptId() + ") Name :" + dept.getDeptName()));
	}

	
	public static void printListOfDepartMent(List<Department> deptList) {
		deptList.forEach(
				dept -> System.out.println("Deperatment  ID:: " + dept.getDeptId() + " Name ::" + dept.getDeptName()));
	}
	
	public static void printListOfString(List<String> stringList) {
		stringList.forEach(System.out::println);
	}

	public static void printMapIntegerString(Map.Entry<Integer, String> map) {
		System.out.println("Filtered Key : " + map.getKey() + ":: Value= " + map.getValue());
	}

	public static void printMapIntegerString(Map<Integer, String> map) {
		map.forEach((k, v) -> System.out.println("Key (" + k + ") Value :" + v));
	}
	
	public static void printMapStringInteger(Map<String, Integer> stringMap) {
		stringMap.forEach((k, v) -> System.out.println("Key =" + k + "::: Value =" + v));
	}

	public static Map<Integer, String> populateMobilePhoneHashMap() {
		Map<Integer, String> mobiles = new HashMap<>();
		mobiles.put(1, "iPhone 7");
		mobiles.put(2, "iPhone 6S");
		mobiles.put(3, "Samsung");
		mobiles.put(4, "1+");
		mobiles.put(5, "Nokia");

		return mobiles;
	}

	public static Map<String, Integer> populateSubjectHashMap() {
		Map<String, Integer> marks = new HashMap<>();
		marks.put("Maths", 95);
		marks.put("Chemistry", 84);
		marks.put("Physics", 92);
		marks.put("Languages", 94);
		return marks;
	}

	public static List<Department> populateWithoutNullDepartmentList() {
		List<Department> deptList = new ArrayList<Department>();
		deptList.add(new Department(1, "IT"));
		deptList.add(new Department(2, "HR"));
		deptList.add(new Department(3, "Management"));
		deptList.add(new Department(4, "Development"));
		deptList.add(new Department(5, "HR"));
		deptList.add(new Department(6, "Recruitment"));
		return deptList;
	}

	public static List<Department> populateNullDepartmentList() {
		List<Department> deptList = new ArrayList<Department>();
		deptList.add(new Department(1, "IT"));
		deptList.add(new Department(2, "HR"));
		deptList.add(new Department(3, null));
		deptList.add(new Department(4, "Development"));
		deptList.add(new Department(5, "HR"));
		deptList.add(new Department(6, "Recruitment"));
		return deptList;
	}

	private static List<String> poupulateListOfStringWithNull() {
		return Stream.of("Mumbai", null,"Banglore", "Delhi", "Vijayawada").collect(Collectors.toList());
	}
	
	private static List<String> poupulateListOfStringWithoutNull() {
		return Stream.of("Mumbai", "Banglore", "Delhi", "Vijayawada").collect(Collectors.toList());
	}

	private static String[] populateArrayOfString() {
		String[] s = new String[] { "AA", "BB", "CC", "DD" };
		return s;
	}
	private static String[] populateArrayOfInteger() {
		String[] s = new String[] { "1", "2", "33", "6" };
		return s;
	}
}
