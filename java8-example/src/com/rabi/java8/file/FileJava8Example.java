package com.rabi.java8.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileJava8Example {

	private static final String FILE_LOCATION = "D:\\abc";

	public static void main(String[] args) throws Exception {


		// File+Directory
		// showFileAndDirectory();


		// File only
		showFileOnly();

		
	}

	private static void showFileOnly() throws Exception {
		showJavaFileOnly();

	}

	private static void showJavaFileOnly() throws IOException {
		System.out.println("*M-1******File Only in present directory***********");
		//Files.list(Paths.get(FILE_LOCATION)).filter(Files::isRegularFile).forEach(System.out::println);

		Files.list(Paths.get(FILE_LOCATION)).filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".java"))
				.forEach(System.out::println);


		// File only
		System.out.println("*M-2******File only in present directory***********");
		//Files.newDirectoryStream(Paths.get(FILE_LOCATION), path -> path.toFile().isFile()).forEach(System.out::println);
		Files.newDirectoryStream(Paths.get(FILE_LOCATION), p -> p.toString().endsWith(".java"))
				.forEach(System.out::println);
	}

	private static void showFileAndDirectory() throws IOException {
		// File+Directory
		System.out.println("*M-1******File+Directory in present directory***********");
		Files.list(Paths.get(FILE_LOCATION)).forEach(System.out::println);

		// File+Directory
		System.out.println("*M-2******File+Directory in present directory***********");
		Files.newDirectoryStream(Paths.get(FILE_LOCATION)).forEach(System.out::println);
	}


}
