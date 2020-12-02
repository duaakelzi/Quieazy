package domain;

import application.ClientAgent;
import data.Message;
import data.UserData;
import data.Quiz;

import java.util.ArrayList;

public class QuizC {

    public static void createNewQuiz(Quiz newQuiz){
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        Message saveQuizMsg = new Message();
        saveQuizMsg.task = "CREATE_QUIZ";

        // set user data so that server knows which user created this quiz
        saveQuizMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
                                            UserC.getCurrentUser().getLastName(),
                                            UserC.getCurrentUser().getEmail());

        ArrayList<Quiz> quizzes = new ArrayList<>();
        quizzes.add(newQuiz);
        saveQuizMsg.quizlist = quizzes;

        clientAgent.send(saveQuizMsg);
    }
}
