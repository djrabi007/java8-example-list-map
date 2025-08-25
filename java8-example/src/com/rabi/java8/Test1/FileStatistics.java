package com.rabi.java8.Test1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileStatistics {

	public static void main(String[] args) {
		// Example directory input (update as per your environment)
		// String input = "N:\\FTM4Cv4\\dev2";
		String input = "D:\\Video\\2024\\Dance Class";
		String output = "D:\\WORKSAPCE\\output.txt"; // Output file path

		printFileStatistics(input, output);
	}

	private static void printFileStatistics(String input, String output) {
		StringBuilder sb = new StringBuilder();
		Path startDir = Paths.get(input);

		// Validate that the provided path is a valid directory.
		if (!Files.isDirectory(startDir)) {
			System.err.println("Error: The provided path is not a directory.");
			return;
		}

		// Print header for the output table.
		System.out.println("Scanning directory: " + startDir.toAbsolutePath());
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
		System.out.printf("%-80s | %-25s | %-20s | %-20s | %-15s%n", "File Name", "Owner", "Created Date",
				"Modified Date", "Size (bytes)");
		System.out.println(
				"----------------------------------------------------------------------------------------------------");

		// Append to StringBuilder for writing into file
		sb.append("Scanning directory: ").append(startDir.toAbsolutePath()).append("\n");
		sb.append(
				"----------------------------------------------------------------------------------------------------\n");
		sb.append(String.format("%-80s | %-25s | %-20s | %-20s | %-15s%n", "File Name", "Owner", "Created Date",
				"Modified Date", "Size (bytes)"));
		sb.append(
				"----------------------------------------------------------------------------------------------------\n");

		try {
			// Walk the file tree starting from the specified directory.
			Files.walkFileTree(startDir, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (attrs.isRegularFile()) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						// Retrieve file attributes
						long size = attrs.size();
						String createdDate = sdf.format(new Date(attrs.creationTime().toMillis()));
						String modifiedDate = sdf.format(new Date(attrs.lastModifiedTime().toMillis()));
						String fileName = file.toAbsolutePath().toString();
						String ownerName = Files.getOwner(file).getName();

						// Print formatted row to console
						System.out.printf("%-80s | %-25s | %-20s | %-20s | %-15d%n", fileName, ownerName, createdDate,
								modifiedDate, size);

						// Append same row to output
						sb.append(String.format("%-80s | %-25s | %-20s | %-20s | %-15d%n", fileName, ownerName,
								createdDate, modifiedDate, size));
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					System.err.println("Failed to access: " + file.toAbsolutePath() + " - " + exc.getMessage());
					return FileVisitResult.CONTINUE;
				}
			});

			// Write to the output file
			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(output))) {
				writer.write(sb.toString());
			}

			System.out.println("File information successfully written to: " + output);

		} catch (IOException e) {
			System.err.println("An error occurred during file traversal: " + e.getMessage());
		}
	}
}
