// encapsulate user login credentials: email and password

package data;

import java.io.Serializable;
/**
 * this class LoginData holds the information that we need to make a log in request
 * with constructor and all getter and setter
 */
public class LoginData implements Serializable {
	
	// variables to store data
	private String email, password;

	/**
	 * constructor
	 * @param email
	 * @param password
	 */
	public LoginData (String email, String password) {
		
		this.email = email;
		this.password = password;
		
	}

	/**
	 * getter and setter
	 * @return email,password
	 */
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