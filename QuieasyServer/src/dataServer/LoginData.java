// encapsulate user login credentials: email and password

package dataServer;

import java.io.Serializable;

public class LoginData implements Serializable {
	
	// variables to store data
	public String email, password;
	
	public LoginData (String email, String password) {
		
		this.email = email;
		this.password = password;
		
	}

}