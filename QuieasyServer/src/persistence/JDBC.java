package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {

    /**
     * this class to make JDBC connection
     */
    public static void connectMySQL() {
        String url="jdbc:mysql://myquieazyserver.mysql.database.azure.com:3306/quieazy?serverTimezone=UTC";
        String user ="kelzi@myquieazyserver";
        String pass="Sahadu123";
        try
        {
            System.out.println("Database connection starting...");
            Connection myConn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connection successful!!");
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }

    }
}
