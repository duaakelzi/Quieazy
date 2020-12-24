package requests;
import application.ClientAgent;
import data.Message;
import data.QuestionData;
import data.QuizData;

import java.util.ArrayList;

public class QuestionC {
    // private UserData ownerQuiz;
    private static Message saveQuestionsMsg = new Message();

    //experiment
    //this method should: 1) persist new questions; 2) ask the message to add questions to quiz 3) maybe update quiz to add
    // new questions there on class level
    public static void persistNewQuestions(QuizData quiz, ArrayList<QuestionData> questionsToPersist) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        saveQuestionsMsg.task = "CREATE_QUESTIONS";
        saveQuestionsMsg.quizData = quiz;
        saveQuestionsMsg.questionData = questionsToPersist;
//        saveQuiestionsMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
//                                            UserC.getCurrentUser().getLastName(),
//                                            UserC.getCurrentUser().getEmail());
        clientAgent.send(saveQuestionsMsg);
    }

}
