package domain;

import application.ClientAgent;
import data.Message;
import data.Question;
import data.Quiz;
import data.UserData;

import java.util.ArrayList;

public class QuestionC {

    private static ArrayList<Question> quesntions;
    private UserData ownerQuiz;
    private Quiz name;
    private static Message saveQuiestionsMsg = new Message();

    public static void createnewQuestions(Quiz newQuiz) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        saveQuiestionsMsg.task = "CREATE_QUESTION";
        quesntions= newQuiz.getQuestions();
        saveQuiestionsMsg.questions=quesntions;
        saveQuiestionsMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
                                            UserC.getCurrentUser().getLastName(),
                                            UserC.getCurrentUser().getEmail());

        saveQuiestionsMsg.quizlist=newQuiz;


    }


}
