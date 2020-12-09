package domain;

import application.ClientAgent;
import data.Message;
import data.Quiz;
import data.UserData;

public class QuizC {

    public static void createNewQuiz(Quiz newQuiz){
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        dataServer.Message saveQuizMsg = new Message();
        saveQuizMsg.task = "CREATE_QUIZ";

        // set user data so that server knows which user created this quiz
        saveQuizMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
                                            UserC.getCurrentUser().getLastName(),
                                            UserC.getCurrentUser().getEmail());

        Quiz quizzes = new Quiz();
        quizzes = newQuiz;
        saveQuizMsg.quizlist = quizzes;

        clientAgent.send(saveQuizMsg);
    }


}
