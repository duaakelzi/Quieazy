// encapsulate user login credentials: email and password

package data;

import java.io.Serializable;

public class LoginData implements Serializable {
	
	// variables to store data
	private String email, password;
	
	public LoginData (String email, String password) {
		
		this.email = email;
		this.password = password;
		
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