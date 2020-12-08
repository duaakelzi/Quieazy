// Class handles database queries.

package persistence;

import actions.LoginAction;
import actions.RegisterAction;
import actions.CreateObjects;
import actions.RetrieveObjects;
import dataServer.Message;

public class Request {

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

    //create a quiz
    public static Message createQuiz(String name, int threshold, boolean isPublic, String email) {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        return CreateObjects.createQuiz(name, threshold, isPublic, email);
    }

    //retrieve all quizzes for user --> change to return message
    public static  Message retrieveQuizzes(String email) {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        return RetrieveObjects.retrieveQuizzes(email);
    }
}

