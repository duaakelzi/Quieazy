// A serializable class for creating message objects to send and receive over network.
// The message content is stored as variables.

package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

    // variables to store data
    public String task; // tell the server or client what to do with the data (if any). eg. "REGISTER" for account creation
    public boolean status; // will be set by the server to true, if successful
    public RegisterData registerData; // field values from "create account" form
    public UserData userData; // user email, first and last name
    public LoginData loginData; // user email and password
    public QuizData quizData; //for quizzes
    public ArrayList<QuestionData> questionData = new ArrayList<>();
    public ResultData resultData ;
    public ArrayList<StudyProgramData> studyProgramData = new ArrayList<>();
    public ArrayList<QuizData> allQuizzes  = new ArrayList<>();;
}
