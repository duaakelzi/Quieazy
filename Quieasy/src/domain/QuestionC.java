package domain;

import application.ClientAgent;
import data.Message;
import data.QuestionData;
import data.QuizData;
import data.UserData;

import java.util.ArrayList;

public class QuestionC {

    private static ArrayList<QuestionData> quesntions;
    private UserData ownerQuiz;
    private QuizData name;
    private static Message saveQuiestionsMsg = new Message();

    public static void createnewQuestions(QuizData newQuizData) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        saveQuiestionsMsg.task = "CREATE_QUESTION";
        quesntions= newQuizData.getQuestions();
        saveQuiestionsMsg.questionData =quesntions;
        saveQuiestionsMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
                                            UserC.getCurrentUser().getLastName(),
                                            UserC.getCurrentUser().getEmail());

        saveQuiestionsMsg.quizlist= newQuizData;


    }


}
