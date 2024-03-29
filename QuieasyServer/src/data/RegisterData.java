// contains field values from "create account" form

package data;

import java.io.Serializable;

/**
 * this class RegisterData holds the information that we need to register a new user
 * with constructor and all getter and setter
 */
public class RegisterData implements Serializable {
	
	// variables to store data
	private String firstName, lastName, email, password;

	/**
	 * constructor
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public RegisterData (String firstName, String lastName, String email, String password) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
