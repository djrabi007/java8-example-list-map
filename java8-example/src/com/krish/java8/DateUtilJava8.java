package com.krish.java8;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtilJava8 {
	
	

	public static void printZoneDate() {
		ZonedDateTime presentTimeZone=ZonedDateTime.now(); //India
		String dateTimeFormat= "dd-MM-yyyy HH :mm:ss";
		String PSTZoneId="US/Pacific";
		String ESTZoneId="US/Eastern";
		System.out.println("**********Print Zone Wise Date in format *******"+dateTimeFormat);
		String PSTTimeZone=  printZonewiseDateTime(presentTimeZone, dateTimeFormat, PSTZoneId);
		String ESTTimeZone=  printZonewiseDateTime(presentTimeZone, dateTimeFormat, ESTZoneId);
		System.out.println("PSTTimeZone ="+PSTTimeZone 
							+"\n ESTTimeZone= "+ESTTimeZone );
	}

	private static String printZonewiseDateTime(ZonedDateTime presentTimeZone,
			String dateTimeFormat, String OtherZoneId) {
		ZonedDateTime OtherTimeZone= presentTimeZone.withZoneSameInstant(ZoneId.of(OtherZoneId));
		DateTimeFormatter formatD=DateTimeFormatter.ofPattern(dateTimeFormat);
		String presentzonDateTime= presentTimeZone.format(formatD);
		System.out.println("presentzonDateTime="+presentzonDateTime);
		return OtherTimeZone.format(formatD);
	}

	public static void printRepublicDate() {
		String date_pattern2="dd/MM/YYYY";
		int year=1950;
		Month month=Month.JANUARY;
		int day=26;
		String repDateSt =printSpecificDate(date_pattern2, year, month, day);
		System.out.println("****************Specific/Republic Date *****************");
		System.out.println("repDateSt= "+repDateSt +" with pattern = "+date_pattern2);
	}

	private static String printSpecificDate(String date_pattern2, int year, Month month, int day) {
		LocalDate republicDate=LocalDate.of(year,month,day);
		DateTimeFormatter format=DateTimeFormatter.ofPattern(date_pattern2);
		return republicDate.format(format);
		
	}

	public static void printPresentDate() {
		String date_pattern1="dd-MM-YYYY HH:mm:ss";
		String date_pattern2="dd/MM/YYYY";
		String date_pattern3="MM/dd/YYYY";
		String formatedDate1=printPresentDate(date_pattern1);
		String formatedDate2=printPresentDate(date_pattern2);
		String formatedDate3=printPresentDate(date_pattern3);
		System.out.println("********Present Date*******************");
		System.out.println("formatedDate1= "+formatedDate1 +" format= "+date_pattern1
							+"\n formatedDate2= "+formatedDate2 +" format= "+date_pattern2
							+"\n date_pattern3 = "+formatedDate3+" format= "+date_pattern3);
	}

	private static String printPresentDate(String date_pattern) {
		LocalDateTime dateTimeNow=LocalDateTime.now();
		DateTimeFormatter format=DateTimeFormatter.ofPattern(date_pattern);
		return dateTimeNow.format(format);
		
	}

	public static void compareTime() {
		int additonalHours=1;
		LocalTime futureTime= addTime(additonalHours);
		LocalTime pastTime= minusTime(additonalHours);
		/*futureTime = 07:38:14.824
		 pastTime= 21:38:14.824
		 present Time =02:38:14.824*/
		System.out.println("**********Compare Time*****************");
		System.out.println("futureTime = "+futureTime 
					+"\n pastTime= "+pastTime
					+"\n present Time ="+LocalTime.now());
		
		timeGap(futureTime, pastTime);  
	}

	//Wrong Result if Time is 2:40 AM and before 5 hours it is 9 PM previous Day !!!
	private static void timeGap(LocalTime futureTime, LocalTime pastTime) {
		Duration gapTime= Duration.between(futureTime, pastTime);
		System.out.println("gapTime = "+gapTime);
	}

	public static LocalTime addTime(int additonalHours) {
		Duration ADD_HOURS=Duration.ofHours(additonalHours); 
		LocalTime timeNow=LocalTime.now();
		LocalTime timeFuture=timeNow.plus(ADD_HOURS);
		return timeFuture;
	}
	
	public static LocalTime minusTime(int additonalHours) {
		Duration ADD_HOURS=Duration.ofHours(additonalHours); 
		LocalTime timeNow=LocalTime.now();
		LocalTime timePast=timeNow.minus(ADD_HOURS);
		return timePast;
	}

	public static void compareDate() {
		LocalDate dateOld=LocalDate.of(2014,Month.OCTOBER,12);
		LocalDate dateFuture=LocalDate.of(2034,Month.OCTOBER,12);
		Period gapPast= populateDateDifference( dateOld); //PRESENT &  PAST
		Period gapFuture= populateDateDifference(dateFuture);//PRESENT  & FUTURE  
		
		//  P-5Y-2M-22D   5 Year 2 Months 22 Days
		//	 P-14Y-9M-9D
		System.out.println("***********Compare Date****************");
			System.out.println("gapPast ="+gapPast 
					+ " \n gapFuture = "+gapFuture);
	}

	private static Period populateDateDifference(LocalDate dateOther) {
		LocalDate dateNow=LocalDate.now(); //Present Date
		Period dateDiffPast= Period.between(dateNow, dateOther);
		return  dateDiffPast;
	}

}
