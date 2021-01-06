// Decodes the message received from the client and forwards it to the concerned class for execution.

package application;

import data.*;
import persistence.Request;

import java.util.ArrayList;

public class ServerDecoder {
    private static Message response;
    //can we get rid of the if-else??
    public static Message decode(Message message) {
        //init response here
        //set task here
        response = new Message();

        if (message.task.equals("LOG_IN")) {

            LoginData data = message.loginData;
            response = Request.login(data.getEmail(), data.getPassword());
            response.task = message.task;
            return response;

        } else if (message.task.equals("REGISTER")) {

            RegisterData data = message.registerData;

            response =  Request.register(data.getFirstName(), data.getLastName(), data.getEmail(), data.getPassword());
            response.task = message.task;
            return response;

        } else if (message.task.equals("CREATE_QUIZ")) {
            data.QuizData quiz = message.quizData;
            data.UserData userData = message.userData;
            response = Request.createQuiz(quiz.getName(), quiz.getThreshold(), false, "user@mail.com", quiz.getCourse(),quiz.getTimer()); //should i return quiz data immediately?
            response.task = message.task;
            return response;
        } else if (message.task.equals("UPDATE_QUIZ")) {
            data.QuizData quiz = message.quizData;

            return Request.updateQuiz( quiz.getName(), quiz.getThreshold(), false, quiz.getCourse());
        } else if (message.task.equals("DELETE_QUIZ")) {
            QuizData quiz = message.quizData;
            return Request.deleteQuiz(quiz.getId(), quiz.getCourse());

        } else if (message.task.equals("FETCH_ALL_QUIZZES")) {
            UserData userData = message.userData;
            return Request.retrieveQuizzes(userData.getEmail());

        } else if (message.task.equals("CREATE_QUESTIONS")) {
            System.out.println("server: create questions method entered >> ");
            response = new Message();
            QuizData quiz = message.quizData;
            ArrayList<QuestionData> questionData = message.questionData;

            //go through array of new questions and make each persistent
            int i;
            for (i=0; i<questionData.size(); i++) {
                Request.createQuestion(questionData.get(i).getQuestion(), questionData.get(i).getPoints(),questionData.get(i).getAnswers(),quiz.getName(),"user@mail.com");
            }

            if (i != questionData.size()) {
                response.status = false;
            }else {
                response.status = true; //should i return quiz data immediately?
            }
            response.task = message.task;
            return response;

        } else if (message.task.equals("SAVE_QUESTION_EDITS")) {
            //to be added
        } else if (message.task.equals("ADD_OLD_QUESTIONS")) {
            //to be added
        } else if (message.task.equals("DELETE_QUESTION")) {
            QuizData quiz = message.quizData;
            ArrayList<QuestionData> questionData = message.questionData;
            int i;
            for (i=0; i<questionData.size(); i++) {
                Request.deleteQuestion((long)i);   //this is only to make the comp. happy. figure out if id's will be there
            }
            if (i != questionData.size()) {
                message.task = "DELETE_QUESTIONS_FAILED";
            }else {
                message.task = "DELETE_QUESTIONS_SUCCESSFUL";
            }
            return message;
        } else if (message.task.equals("FETCH_ALL_QUESTIONS")) {
            QuizData quiz = message.quizData;
            response = new Message();
            response = Request.retrieveQuestions(quiz.getId());
            response.task = message.task;
            return  response;
        }else if (message.task.equals("SAVE_RESULT")) {
            UserData userData = message.userData;
            QuizData quizData = message.quizData;
            ResultData resultData = message.resultData;

            //go through array of results and make each persistent

                //ideally, we should be working with IDs here
                //session closed after each call to persist. Might be interfering
                Request.createResult(resultData.getPoints(), resultData.isPassed(), quizData.getName(), "user@mail.com");


//            if (i != resultData.size()) {
//                message.task = "CREATE_RESULT_FAILED";
//            }else {
//                message.task = "CREATE_RESULT_SUCCESSFUL";
//            }
            return message;
        }else if (message.task.equals("FETCH_RESULTS")) {
            UserData userData = message.userData;
            return Request.retrieveResults(userData.getEmail());
        }else if (message.task.equals("UPDATE_RESULT")) {
            //to be added once repeat quiz is ready
        }else if (message.task.equals("FETCH_STUDY_PROGRAMS")) {
            //create new message, otherwise the old one seems to be returned
            //returns a message with the task + array of sps (with courses)
            response = new Message();
            response = Request.retrieveStudyPrograms();
            response.task = message.task;
            return response;
        }
        return null;
    }
}
