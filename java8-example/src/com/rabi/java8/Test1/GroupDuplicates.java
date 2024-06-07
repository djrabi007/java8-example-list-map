package com.rabi.java8.Test1;

import java.util.LinkedHashMap;
import java.util.Map;

//Walmart interview question -Spoumya
public class GroupDuplicates {
	public static void main(String[] args) {
		String input = "hiktrhkhftr";
		String output = groupDuplicates(input);
		System.out.println(output);
	}

	public static String groupDuplicates(String input) {
		// Create a LinkedHashMap to count occurrences of each character and maintain
		// insertion order
		Map<Character, Integer> charCountMap = new LinkedHashMap<>();

		// Count the occurrences of each character
		for (char c : input.toCharArray()) {
			charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
		}

		// Create a StringBuilder to build the output string
		StringBuilder output = new StringBuilder();

		// Append each character based on its count in the map
		for (char c : charCountMap.keySet()) {
			int count = charCountMap.get(c);
			for (int i = 0; i < count; i++) {
				output.append(c);
			}
		}

		return output.toString();
	}
}
