package com.krish.java8;

import java.text.ParseException;

public class DateApplication {

	public static void main(String[] args)throws Exception {
		DateUtilJava8.compareDate();
		DateUtilJava8.compareTime();
		DateUtilJava8.printPresentDate();
		DateUtilJava8.printRepublicDate();
		DateUtilJava8.printZoneDate();
		javaToSQLDate();
		javaToSQLDateWithTime();
		
	}

	private static void javaToSQLDate() throws ParseException {
		String stringJavaDate= "2018-08-08"; //8thAug,2018
		String javaToSQLDate= DateUtilJava8.convertJavaToSQLDate(stringJavaDate);
		System.out.println("##1#Java Date= "+stringJavaDate
				+" ***converted SQL Date= "+javaToSQLDate);
	}
	private static void javaToSQLDateWithTime() throws ParseException {
		String stringJavaDate= "2018-08-08 14:23:21"; //8thAug,2018
		String javaToSQLDate= DateUtilJava8.convertJavaToSQLDateForTimestamp(stringJavaDate);
		System.out.println("##2#Java Date with Time= "+stringJavaDate
				+" ***converted SQL Date= "+javaToSQLDate);
	}
}
