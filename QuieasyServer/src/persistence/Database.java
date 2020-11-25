// Class handles database queries.

package persistence;

import actions.LoginAction;
import actions.RegisterAction;
import data.Message;
import data.UserData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Database {

	// Log user in if email and password match.
	public static Message login(String email, String password) {
        JDBC.connectMySQL();

        System.out.println("Database connection created!");
        return LoginAction.login(email, password);

	}
	
	// Register a new user i.e. create account
	public static Message register(String firstName, String lastName, String email, String password) {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();

        System.out.println("Database connection created!");
        return RegisterAction.register(firstName, lastName, email, password);
    }

}
