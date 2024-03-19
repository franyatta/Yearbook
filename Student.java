
/*
 * Francine Vo
 * CS 252
 * Student UIN: 01253035
 * Assignment 6
 */

public class Student {

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

		this.grade = grade;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photoLink = photoLink;
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
			return student.getGrade().equals(this.grade)
					&& student.getFirstName().equals(this.firstName)
					&& student.getLastName().equals(this.lastName)
					&& student.getPhotoLink().equals(this.photoLink);
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