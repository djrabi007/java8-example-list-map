package com.rabi.java8.Test1;

import java.io.IOException;

public class LaptopShutdown {
	public static void main(String[] args) {
		// Delay time in seconds (1 minute = 60 seconds)
		// Shut Down after 9 hours
		int delayInSeconds = 60 * 60 * 9;
		shutDownLaptop(delayInSeconds);
	}

	private static void shutDownLaptop(int delayInSeconds) {
		try {
			// Command to shut down the system after the specified delay
			String shutdownCommand = "shutdown -s -t " + delayInSeconds;

			// Execute the shutdown command
			Process process = Runtime.getRuntime().exec(shutdownCommand);

			System.out.println("Shutdown command executed. System will shut down in " + delayInSeconds + " seconds.");
		} catch (IOException e) {
			System.out.println("An error occurred while trying to shut down the system.");
			e.printStackTrace();
		}
	}
}