// Class handles database queries.

package persistence;

import actions.*;

import data.ChoicesData;
import data.Message;
import data.QuestionData;

import java.util.ArrayList;
import java.util.List;


public class Request {

    /**
     * Intermediate hub to forward login request
     * @param email
     * @param password
     * @return Message
     */
	public static Message login(String email, String password) {
        JDBC.connectMySQL();
        return LoginAction.login(email, password);
	}
	
	// Register a new user i.e. create account

    /**
     * Intermediate hub to forward register request
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @return Message
     */
	public static Message register(String firstName, String lastName, String email, String password) {
        // create a database connection
        JDBC.connectMySQL();
        return RegisterAction.register(firstName, lastName, email, password);
    }

    /* Quiz related */

    /**
     * Intermediate hub to forward quiz creation request
     * @param name
     * @param threshold
     * @param isPublic
     * @param email
     * @param course
     * @param timer
     * @return Message
     */
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

    /**
     * Intermediate hub to forward retrieve all quizzes for user request
     * @param email
     * @return Message
     */
    public static Message retrieveUserQuizzes(String email) {
        // create a database connection
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveUserQuizzes(email);
    }
    //retrieve all quizzes

    /**
     * Intermediate hub to forward retrieve all quizzes request
     * @return Message
     */
    public static Message retrieveQuizzes() {
        // create a database connection
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveQuizzes();
    }

    /**
     * Intermediate hub to forward retrieve all study programs request
     * @return Message
     */
    public static Message retrieveStudyPrograms(){
        // create a database connection
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveSP();
    }

    /* Quiestion related */

    /**
     * Intermediate hub to forward retrieve all questions request
     * @param courseName
     * @param quizName
     * @return Message
     */
    public static Message retrieveQuestions(String courseName, String quizName) {
        // create a database connection
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveQuestions(courseName, quizName);
    }

    /**
     * Intermediate hub to forward create a question request
     * @param questionText
     * @param points
     * @param questionChoicesList
     * @param quizName
     * @param email
     * @return Message
     */
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

    /**
     * Intermediate hub to forward delete question request
     * @param questionID
     * @return Message
     */
    public static Message deleteQuestion(Long questionID) {
        // create a database connection
        JDBC.connectMySQL();
        return DeleteObjects.deleteQuestion(questionID);
    }

    /* Result related */

    /**
     * Intermediate hub to forward create result request
     * @param points
     * @param isPassed
     * @param quizName
     * @param userEmail
     * @return Message
     */
    public static Message createResult(int points, boolean isPassed, String quizName, String userEmail) {
        // create a database connection
        JDBC.connectMySQL();
        return CreateObjects.createResult(points, isPassed, quizName, userEmail);
    }

    /**
     * Intermediate hub to forward retrieve results for user request
     * @param userEmail
     * @return Message
     */
    public static Message retrieveResults(String userEmail) {
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveResults(userEmail);
    }

    /**
     * Intermediate hub to forward retrieve all questions request
     * @return Message
     */
    public static Message retrieveAllQuestions() {
        JDBC.connectMySQL();
        return RetrieveObjects.retrieveAllQuestions();
    }
}

