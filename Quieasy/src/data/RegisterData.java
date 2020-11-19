// contains field values from "create account" form

package data;

import java.io.Serializable;

public class RegisterData implements Serializable {
	
	// variables to store data
	public String firstName, lastName, email, password;
	
	public RegisterData (String firstName, String lastName, String email, String password) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		
	}

}
