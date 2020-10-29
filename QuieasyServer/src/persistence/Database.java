// Handles database queries.

package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	// Return true if userName and password match.
	public static boolean login(String userName, String password) {
		
		Connection connection = null;
        try
        {
          System.out.println("Database connection starting...");
          
          // create a database connection
          connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\Quieasy.db");
          
          System.out.println("Database connection created!");
          
          Statement statement = connection.createStatement();

          ResultSet rs = statement.executeQuery("SELECT password FROM user WHERE email = \"" + userName + "\"");
          while(rs.next())
          {
        	  if(password.equals(rs.getString("password"))) {
        		  
        		  return true;
        		  
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
		
		return false;
		
	}
	
}
