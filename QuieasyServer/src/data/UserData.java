// encapsulate user details: first name, last name and email address

package data;

import java.io.Serializable;

public class UserData implements Serializable {
	
	// variables to store data
	public String firstName, lastName, email;
	
	public UserData (String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
	}

}
