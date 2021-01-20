// Class handles database queries.

package persistence;

import actions.*;

import data.ChoicesData;
import data.Message;
import data.QuestionData;

import java.util.ArrayList;
import java.util.List;


public class Request {

	// Log user in if email and password match.
	public static Message login(String email, String password) {
        JDBC.connectMySQL();
        return LoginAction.login(email, password);
	}
	
	// Register a new user i.e. create account
	public static Message register(String firstName, String lastName, String email, String password) {
        // create a database connection
        JDBC.connectMySQL();
        return RegisterAction.register(firstName, lastName, email, password);
    }

    /* Quiz related */
    public static Message createQuiz(String name, double threshold, boolean isPublic, String email, String course,int timer) {
        // create a database connection
        JDBC.connectMySQL();
        return CreateObjects.CreateQuiz(name,threshold,false,timer,email,course);
    }


    public static Message updateQuiz( String name, double threshold, boolean isPublic, String course) {
        // create a database connection
        JDBC.connectMySQL();
        return UpdateObjects.updateQuiz(name,threshold,false,course);
    }
    public static Message deleteQuiz(String quizName, String course) {
        // create a database connection
        JDBC.connectMySQL();
        return DeleteObjects.deleteQuiz(quizName,course);
    }
    //retrieve all quizzes for user
    public static Message retrieveUserQuizzes(String email) {
        // create a database connection
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveUserQuizzes(email);
    }
    //retrieve all quizzes
    public static Message retrieveQuizzes() {
        // create a database connection
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveQuizzes();
    }

    public static Message retrieveStudyPrograms(){
        // create a database connection
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveSP();
    }

    /* Quiestion related */
    public static Message retrieveQuestions(String courseName, String quizName) {
        // create a database connection
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveQuestions(courseName, quizName);
    }

    public static Message createQuestion(String questionText, int points, List<ChoicesData> questionChoicesList, String quizName,String email) {
        // create a database connection
        JDBC.connectMySQL();
        return CreateObjects.CreateQuestion(questionText, points, questionChoicesList, quizName,email);
    }
    /*public static Message updateQuestion(Long questionID, List<ChoicesData> choicesDataList, String questionText, int points, boolean isCorrect) {
        System.out.println("Database connection starting...");

        // create a database connection
        JDBC.connectMySQL();
        System.out.println("Database connection created!");
        //return UpdateObjects.updateQuestion(questionID, choicesDataList, questionText, points, isCorrect);
    }*/
    public static Message deleteQuestion(Long questionID) {
        // create a database connection
        JDBC.connectMySQL();
        return DeleteObjects.deleteQuestion(questionID);
    }

    /* Result related */
    public static Message createResult(int points, boolean isPassed, String quizName, String userEmail) {
        // create a database connection
        JDBC.connectMySQL();
        return CreateObjects.createResult(points, isPassed, quizName, userEmail);
    }

   // Request.retrieveResults(userData.getEmail());
    public static Message retrieveResults(String userEmail) {
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveResults(userEmail);
    }
}

