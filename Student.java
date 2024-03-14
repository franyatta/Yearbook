
/*
 * Francine Vo
 * CS 252
 * Student UIN: 01253035
 * Assignment 6
 */
import java.io.*;
import java.util.*;

public class Student {

	// Create a HashMap with the Student object as the values
	Map<String, Student> happy = new HashMap<>();

	private String grade;
	private String firstName;
	private String lastName;
	private String photoLink;

	// No argument constructor that initializes values
	public Student() {

		grade = "freshman";
		firstName = "John";
		lastName = "Doe";
		photoLink = "www.photobucket.com";
	}

	public Student(String grade, String firstName, String lastName, String photoLink) {
		super();
		this.grade = grade;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photoLink = photoLink;
	}

	public void menu() {
		Scanner in = new Scanner(System.in);
		boolean quit = false;

		while (!quit) {
			try {
				System.out.println(
						"Please pick an option: \n\t1. Scan a file\n\t2. Add a student\n\t3. Print a student's information\n\t4. Print ALL students\n\t0. Quit");
				int response = in.nextInt();

				switch (response) {
				case 1:
					System.out.println("Please enter the path to file");
					String filepath = in.next();
					scan(filepath);
					break;
				case 2:
					in.nextLine();
					System.out.println(
							"Please enter the student's grade, first name, last name, and link to photo (with spaces in between)");
					String[] data = in.nextLine().split(" ");

					// Account for wrong amount of data in input
					if (data.length != 4) {
						System.err.println("Insufficient input.");
					} else {
						addStudent(data);
					}
					break;
				case 3:
					System.out.println("Who do you want to print out?");
					in.nextLine();
					String name = in.nextLine();
					studentSearch(name);
					break;
				case 4:
					printAllStudents();
					break;
				case 0:
					System.out.println("Goodbye!");
					quit = true;
					break;
				default:
					System.err.println("Please enter a valid input");
				}
			} catch (InputMismatchException e) {
				System.err.println("Invalid input. Please enter a number.");
				in.nextLine(); // Clears input stream
			}
		}
		in.close();
	}

	// Reads in text file and creates new Student object to add to HashMap.
	// Recommended entry: "Entries.txt"
	public void scan(String filepath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split("\t+");
				grade = data[0];
				firstName = data[1];
				lastName = data[2];
				photoLink = data[3];

				Student student = new Student(grade, firstName, lastName, photoLink);

				String fullName = firstName + " " + lastName;
				
				// Checks if students are already in the system
				if (!happy.containsValue(student)) {
					happy.put(fullName, student);
					System.out.println("Succesfully added " + firstName + " " + lastName + " from file.");
				} else {
					System.err.println(firstName + " " + lastName + " is already in the system");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method that adds a Student's information to the HashMap as long as the same
	// student isn't already in the system
	public void addStudent(String[] data) {

		grade = data[0];
		firstName = data[1];
		lastName = data[2];
		photoLink = data[3];

		Student student = new Student(grade, firstName, lastName, photoLink);

		// Checks if students are already in the system
		if (!happy.containsValue(student)) {
			String fullName = firstName + " " + lastName;
			happy.put(fullName, student);
			System.out.println("Succesfully added student.");
		} else {
			System.err.println("Student already in system.");
		}
	}

	// Method that matches the input to the key then prints the Student object
	// values
	public void studentSearch(String name) {

		if (happy.containsKey(name)) {
			Student student = happy.get(name);
			System.out.println(student);
		} else {
			System.err.println("Student does not exist.");
		}
	}

	// Method that displays all the current students in the system unless the
	// HashMap is empty
	public void printAllStudents() {
		if (happy.isEmpty()) {
			System.err.println("There are no students currently in the yearbook.");
		} else {
			for (Student values : happy.values()) {
				System.out.println(values);
			}
		}
	}

	// Override parent class methods in order to properly output results to console
	// as well as compare Object types.
	@Override
	public String toString() {
		String formattedGrade = String.format("%-9s", grade);
		String formattedName = String.format("%-20s", firstName + " " + lastName);

		String page = "|---------------------------|\n" + "|           |               |\n"
				+ "|           |               |\n" + "|           |               |\n" + "| " + formattedGrade
				+ " |               |\n" + "|           |               |\n" + "|           |               |\n"
				+ "|           |               |\n" + "|           |               |\n"
				+ "|           +---------------|\n" + "| " + formattedName + "      |\n"
				+ "|---------------------------|\n" + "Link: " + photoLink + "\n";
		return page;
	}

	@Override
	public boolean equals(Object x) {
		if (!(x instanceof Student))
			return false;
		else {
			Student student = (Student) x;
			return grade.equals(student.grade) && firstName.equals(student.firstName)
					&& lastName.equals(student.lastName) && photoLink.equals(student.photoLink);
		}
	}

	// Getters and Setters (if needed)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

}