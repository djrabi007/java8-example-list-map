package com.rabi.java8.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DateExample {

	private static final String INPUT_FILE = "D:\\WORKSAPCE\\POC\\input.csv";
	private static final String WORKS_FOR = ",";
	static List<String> listOfAllMondayToSunday = new LinkedList<>();
	static List<String> teamList = new LinkedList<>();
	private static final String FILE_LOCATION_OUTPUT = "D:\\WORKSAPCE\\POC\\outFinal.csv";

	public static void main(String[] args) throws Exception {
		// populateHardcodedTeam();
		int numberOfMonths = 15;
		// 1. Team list from input.csv file
		teamList = populateTeamListFromFile();

		// 2. Schedule List preparation based on numberOfMonths
		for (int i = 0; i < numberOfMonths; i++) {
			List<String> listOfMondayToSunday = populateMonthlySchedule(LocalDate.now().plusMonths(i));
		}
		// printSchedule(listOfAllMondayToSunday);

		// 3. #1 and #2 map
		List<String> rows = populateTeamToScheduleMap(listOfAllMondayToSunday, teamList);
		// 4. Write to outputFinal.csv file from #3
		prepareOutputFile(rows);

	}


	private static void prepareOutputFile(List<String> rows) throws IOException {
		FileWriter csvWriter = new FileWriter(FILE_LOCATION_OUTPUT);
		csvWriter.append("Team");
		csvWriter.append(",");
		csvWriter.append("Schedule(YYYY-mm-dd)");
		csvWriter.append("\n");

		for (String rowData : rows) {
			csvWriter.append(rowData);
			csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();
	}


	private static List<String> populateTeamToScheduleMap(List<String> listOfSchedule, List<String> listOfTeam) {
		List<String> listOfTeamToSchedule = new LinkedList<String>();
		for (int i = 0; i < listOfTeam.size(); i++) {
			if (i == listOfTeam.size() - 1) {
				break;
			}
			for (int j = 0; j < listOfSchedule.size(); j++) {
				if (i == listOfTeam.size()) {
					i = 0;
				}

				listOfTeamToSchedule.add(listOfTeam.get(i) + WORKS_FOR + listOfSchedule.get(j));
				System.out.println(listOfTeam.get(i) + WORKS_FOR + listOfSchedule.get(j));
				if (j == listOfSchedule.size()) {
					break;
				}
				i++;
			}
		}

		return listOfTeamToSchedule;
	}

	// August- 2022 has issue
	private static List<String> populateMonthlySchedule(final LocalDate localDateUpdate) {
		// List<String> listOfAllMondayToSunday = new ArrayList<>();
		final String monthName = String.valueOf(localDateUpdate.getMonth());
		final Year yearName = Year.of(localDateUpdate.getYear()); // Year.now();
		Month month = Month.valueOf(monthName.toUpperCase());



		System.out.printf("For the month of %s:%n", month);
		LocalDate dateMonday = yearName.atMonth(month).atDay(1)
				.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

		LocalDate dateSunday = yearName.atMonth(month).atDay(1)
				.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
		Month mi = dateMonday.getMonth();
		while (mi == month) {

			dateSunday = dateSunday.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
			// if (dateMonday.isBefore(dateSunday)) {
				listOfAllMondayToSunday.add(String.valueOf(dateMonday) + " TO " + String.valueOf(dateSunday));
			// }

			// System.out.printf("%s%n", date);
			dateMonday = dateMonday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			mi = dateMonday.getMonth();
		}
		return listOfAllMondayToSunday;
	}

	private static List<String> populateTeamListFromFile() throws FileNotFoundException {
		List<String> lstOfTeam = new ArrayList<>();
		// parsing a CSV file into Scanner class constructor
		Scanner sc = new Scanner(new File(INPUT_FILE));

		sc.useDelimiter(","); // sets the delimiter pattern
		while (sc.hasNext()) // returns a boolean value
		{
			// System.out.print(sc.next()); // find and returns the next complete token from
			// this scanner
			lstOfTeam.add(sc.next());
		}
		sc.close(); // closes the scanner

		return lstOfTeam;
	}

	private static void populateHardcodedTeam() {
		teamList.add("T1");
		teamList.add("T2");
		teamList.add("T3");
		teamList.add("T4");
		teamList.add("T5");
	}

	private static void printSchedule(List<String> listOfMonday) {
		for (String mondayDate : listOfMonday) {
			System.out.println(mondayDate);
		}
	}

}
