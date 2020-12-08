// Class handles database queries.

package persistence;

import actions.*;
import data.Message;
import domainServer.*;
import org.hibernate.sql.Delete;

import java.util.Set;


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
    public static Message createQuiz(String name, int threshold, boolean isPublic, String email,String course) {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        return CreateObjects.CreateQuiz(name,threshold,false,email,course);
    }

    public static Message updateQuiz(Long id,String name, int threshold, boolean isPublic, String course)
    {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        return UpdateObjects.updateQuiz(id,name,threshold,false,course);
    }
    public static Message deleteQuiz(Long id, String course) {
        System.out.println("Database connection starting...");
        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        return DeleteObjects.deleteQuiz(id,course);
    }
    //retrieve all quizzes for user
    public static Message retrieveQuizzes(String email) {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        return RetrieveObjects.retrieveQuizzes(email);
    }
    //CRUD for questions
    public static Message retrieveQuestions(Long quizID) {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        return RetrieveObjects.retrieveQuestions(quizID);
    }
    public static Message createQuestion(String questionText,int points, String email) {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        return CreateObjects.CreateQuestion(questionText, points, email);
    }
    public static Message updateQuestion(Long questionID, Set<QuestionChoice> choicesList, String questionText, int points, boolean isCorrect) {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        return UpdateObjects.updateQuestion(questionID, choicesList, questionText, points, isCorrect);
    }
    public static Message deleteQuestion(Long questionID) {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        return DeleteObjects.deleteQuestion(questionID);
    }
}

