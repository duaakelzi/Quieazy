// Decodes the message received from the client and forwards it to the concerned class for execution.

package application;

import data.*;
import domain.Quiz;
import domain.User;
import persistence.Request;

import java.util.ArrayList;

/**
 * decodes the received request, triggers necessary classes, returns response to the request
 */
public class ServerDecoder {
    private static Message response;
    public static Message decode(Message message) {

        if (message.task.equals("LOG_IN")) {
            response = new Message();
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
            QuizData quiz = message.quizData;
            UserData userData = message.userData;
            response = new Message();
            response = Request.createQuiz(quiz.getName(), quiz.getThreshold(), false, userData.getEmail(), quiz.getCourse(),quiz.getTimer());
            response.task = message.task;
            return response;
        } else if (message.task.equals("UPDATE_QUIZ")) {
            data.QuizData quiz = message.quizData;
            return Request.updateQuiz( quiz.getName(), quiz.getThreshold(), false, quiz.getCourse());
        } else if (message.task.equals("DELETE_QUIZ")) {
            QuizData quiz = message.quizData;
            response = new Message();
            response = Request.deleteQuiz(quiz.getName(), quiz.getCourse());
            response.task = message.task;
            return response;
        } else if (message.task.equals("FETCH_ALL_USER_QUIZZES")) {
            UserData userData = message.userData;
            response = new Message();
            response = Request.retrieveUserQuizzes(userData.getEmail());
            response.task = message.task;
            return response;
        } else if (message.task.equals("FETCH_ALL_QUIZZES")) {

            response = new Message();
            response = Request.retrieveQuizzes();
            response.task = message.task;
            return response;
        } else if (message.task.equals("CREATE_QUESTIONS")) {
            System.out.println("server: create questions method entered >> ");
            response = new Message();
            QuizData quiz = message.quizData;
            ArrayList<QuestionData> questionData = message.questionData;
            UserData user = message.userData;

            //go through array of new questions and make each persistent
            int count = 0;
            for(QuestionData q : questionData) {
                Message tempMsg = Request.createQuestion(q.getQuestion(), q.getPoints(),q.getAnswers(),quiz.getName(),user.getEmail());
                response.status = tempMsg.status;
                response.questionData.add(tempMsg.questionData.get(0));
                count++;
            }

            if(count != questionData.size()) {
                response.status = false;
            }
            response.task = message.task;
            return response;

        } else if (message.task.equals("SAVE_QUESTION_EDITS")) {
            //to be added
        } else if (message.task.equals("ADD_OLD_QUESTIONS")) {
            //to be added
        } else if (message.task.equals("DELETE_QUESTIONS")) {
            QuizData quiz = message.quizData;
            //check if deletes from breakout tables
            ArrayList<QuestionData> questionData = message.questionData;
            response = new Message();
            int count = 0;
            for(QuestionData q : message.questionData) {
                response = Request.deleteQuestion(q.getId());
                if(response.status) {
                    count++;
                }
            }
            if(count != questionData.size()) {
                response.status = false;
            }
            response.task = message.task;
            return response;
        } else if (message.task.equals("FETCH_ALL_QUESTIONS")) {
            QuizData quiz = message.quizData;
            response = new Message();
            response = Request.retrieveQuestions(quiz.getCourse(), quiz.getName());
            response.task = message.task;
            return response;
        } else if (message.task.equals("FETCH_ALL_EXISTING_QUESTIONS")) {

            response = new Message();
            response = Request.retrieveAllQuestions();
            response.task = message.task;
            return response;
        }else if (message.task.equals("SAVE_RESULT")) {
            UserData userData = message.userData;
            QuizData quizData = message.quizData;
            ResultData resultData = message.resultData;

              response = new Message();
              response= Request.createResult(resultData.getPoints(), resultData.isPassed(), quizData.getName(), userData.getEmail());
              response.task = message.task;

            return response;
        }else if (message.task.equals("FETCH_RESULTS")) {
            UserData userData = message.userData;
            return Request.retrieveResults(userData.getEmail());
        }else if (message.task.equals("UPDATE_RESULT")) {
            //TODO: to be added once repeat quiz is ready
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
