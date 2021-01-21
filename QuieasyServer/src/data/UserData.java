// encapsulate user details: first name, last name and email address

package data;

import java.io.Serializable;

/**
 * this class UserData holds all the information about the user to be sent using messages
 * with constructor and all getter and setter
 */
public class UserData implements Serializable {
	
	// variables to store data
	private String firstName, lastName, email;
	
	public UserData (String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
	}
	//getters and setters
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
}
