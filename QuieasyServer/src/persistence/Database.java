// Class handles database queries.

package persistence;

import data.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	// Log user in if email and password match.
	public static Message login(String email, String password) {
		
		Message message = new Message();
		
		Connection connection = null;
        try
        {
          System.out.println("Database connection starting...");
          
          // create a database connection
          connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\Quieasy.db");
          
          System.out.println("Database connection created!");
          
          Statement statement = connection.createStatement();

          ResultSet rs = statement.executeQuery("SELECT first_name, last_name, password FROM user WHERE email = \"" + email + "\"");
          
          while(rs.next())
          {
        	  if(password.equals(rs.getString("password"))) {
        		  
            	  message.task = "LOGIN_OK";
            	  message.userData = new UserData(rs.getString("first_name"), rs.getString("last_name"), email);
            	  return message;
        		  
        	  }
        	  
          }
          
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
		
        message.task = "LOGIN_FAILED";
		return message;
		
	}
	
	// Register a new user i.e. create account
	public static Message register(String firstName, String lastName, String email, String password) {
		
		Message message = new Message();
		
		Connection connection = null;
        try
        {
          System.out.println("Database connection starting...");
          
          // create a database connection
          connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\Quieasy.db");
          
          System.out.println("Database connection created!");
          
          Statement statement = connection.createStatement();
          
          ResultSet rs = statement.executeQuery("SELECT email FROM user WHERE email = '" + email + "'");
          
          if(rs.next()) // email already in use
          {
        	  message.task = "EMAIL_IN_USE";
        	  return message;
          }
          
          int rows = statement.executeUpdate("INSERT INTO user (first_name, last_name, email, password) VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', '" + password + "')");

          if (rows == 1) { // account created successfully
        	  
        	  message.task = "LOGIN_OK";
        	  message.userData = new UserData(firstName, lastName, email);
        	  return message;
        	  
          }
          
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
		
		return message;
		
	}
	
}
