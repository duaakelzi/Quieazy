// encapsulate user login credentials: email and password

package data;

import java.io.Serializable;

public class LoginData implements Serializable {

    /**
     * Login Data that holds the username- email and the password
     */
    private String email, password;

    public LoginData(String email, String password) {

        this.email = email;
        this.password = password;

    }

    /**
     * Getters and Setters for email and password member variable
     *
     * @return
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