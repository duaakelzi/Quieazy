package requests;
import application.ClientAgent;
import data.Message;
import data.QuestionData;
import data.QuizData;
import data.UserData;

import java.util.ArrayList;

public class QuestionC {

    private static ArrayList<QuestionData> questions;
    private UserData ownerQuiz;
    private QuizData name;
    private static Message saveQuestionsMsg = new Message();

//    public static void createnewQuestions(QuizData newQuizData) {
//        ClientAgent clientAgent = ClientAgent.getClientAgent();
//
//        saveQuiestionsMsg.task = "CREATE_QUESTION";
//        questions = newQuizData.getQuestions();
//        saveQuiestionsMsg.questionData = questions;
////        saveQuiestionsMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
////                                            UserC.getCurrentUser().getLastName(),
////                                            UserC.getCurrentUser().getEmail());
//
//        saveQuiestionsMsg.quizlist= newQuizData;
//        clientAgent.send(saveQuiestionsMsg);
//    }

    //experiment
    public static void createnewQuestions(QuizData newQuizData, QuestionData newQuestionData) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        saveQuestionsMsg.task = "CREATE_QUESTION";
        questions = new ArrayList<>();
        questions.add(newQuestionData);
        saveQuestionsMsg.questionData = questions;
//        saveQuiestionsMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
//                                            UserC.getCurrentUser().getLastName(),
//                                            UserC.getCurrentUser().getEmail());

        saveQuestionsMsg.quizlist= newQuizData;
        clientAgent.send(saveQuestionsMsg);
    }

}
