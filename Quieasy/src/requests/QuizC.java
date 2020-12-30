package requests;

import application.ClientAgent;
import data.Message;
import data.QuizData;

public class QuizC {

    public static void createNewQuiz(QuizData newQuizData){
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        Message saveQuizMsg = new Message();
        saveQuizMsg.task = "CREATE_QUIZ";

        // set user data so that server knows which user created this quiz
       /* saveQuizMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
                                            UserC.getCurrentUser().getLastName(),
                                            UserC.getCurrentUser().getEmail());*/

        QuizData quizzes = new QuizData();
        quizzes = newQuizData;
        //first time you make an empty quiz persistent
        saveQuizMsg.quizData = new QuizData(quizzes.getProgram(),quizzes.getCourse(),quizzes.getName(),quizzes.getThreshold(),quizzes.getTimer(),quizzes.getQuestions());
        System.out.println(saveQuizMsg.quizData.getName());
        clientAgent.send(saveQuizMsg);
    }


}
