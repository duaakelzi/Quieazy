// A serializable class for creating message objects to send and receive over network.
// The message content is stored as variables.

package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    /**
     * the class that holds all the serializable information
     * variables to store data
     * tell the server or client what to do with the data (if any). eg. "REGISTER" for account creation
     */
    public String task;// tell the server or client what to do with the data (if any). eg. "REGISTER" for account creation
    /**
     * status set by server to true if the work is done
     */
    public boolean status;
    /**
     * the data that we need to persist
     */
    public RegisterData registerData; // field values from "create account" form
    public UserData userData; // user email, first and last name
    public LoginData loginData; // user email and password
    public QuizData quizData; //for quizzes
    public ArrayList<QuestionData> questionData = new ArrayList<>();
    public ResultData resultData;
    public ArrayList<StudyProgramData> studyProgramData = new ArrayList<>();
    public ArrayList<QuizData> allQuizzes = new ArrayList<>();
}
