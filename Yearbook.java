import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Yearbook {
	// Create a HashMap with the Student object as the values
	Map<String, Student> happy = new HashMap<>();
	Student student = new Student();

	public void menu() {
		try (Scanner in = new Scanner(System.in)) {
			while (true) {
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
						System.exit(0);
					default:
						System.err.println("Please enter a valid input");
					}
				} catch (InputMismatchException e) {
					System.err.println("Invalid input. Please enter a number.");
					in.nextLine(); // Clears input stream
				}
			}
		}
	}

	// Reads in text file and creates new Student object to add to HashMap.
	// Recommended entry: "Entries.txt"
	public void scan(String filepath) {
	    try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] data = line.split("\t+");
	            String grade = data[0];
	            String firstName = data[1];
	            String lastName = data[2];
	            String photoLink = data[3];

	            Student student = new Student(grade, firstName, lastName, photoLink);
	            String fullName = student.getFirstName() + " " + student.getLastName();

	            // Checks if students are already in the system
	            if (!happy.containsKey(fullName)) {
	                happy.put(fullName, student);
	                System.out.println("Successfully added " + student.getFirstName() + " " + student.getLastName()
	                        + " from file.");
	            } else {
	                System.err.println(
	                        student.getFirstName() + " " + student.getLastName() + " is already in the system");
	            }
	        }
	    } catch (Exception e) {
	        System.err.println(e);
	    }
	}

	// Method that adds a Student's information to the HashMap as long as the same
	// student isn't already in the system
	public void addStudent(String[] data) {
		String grade = data[0];
		String firstName = data[1];
		String lastName = data[2];
		String photoLink = data[3];

		Student student = new Student(grade, firstName, lastName, photoLink);

		// Checks if students are already in the system
		if (!happy.containsValue(student)) {
			String fullName = firstName + " " + lastName;
			happy.put(fullName, student);
			System.out.println("Successfully added " + firstName + " " + lastName);
		} else {
			System.err.println(firstName + " " + lastName + " is already in the system.");
		}
	}

	// Method that matches the input to the key then prints the Student object
	// values
	public void studentSearch(String name) {

		if (happy.containsKey(name)) {
			Student student = happy.get(name);
			// Explicitly uses toString() method
			System.out.println(student.toString());
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
				// Explicitly uses toString() method
				System.out.println(values.toString());
			}
		}
	}
}
