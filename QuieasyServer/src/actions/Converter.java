package actions;

import data.ChoicesData;
import data.QuestionData;
import data.QuizData;
import data.UserData;
import domain.Choices;
import domain.Question;
import domain.QuestionChoice;
import domain.Quiz;

import java.util.ArrayList;
import java.util.Collections;

public class Converter {
    /**
     * because we have a mapping class Question that hold the Questions from the DB
     * and have data class QuestionData that used to send the request, here we convert the question
     * after we retrieve it to QuestionData object
     * @param question the question that we want to convert it to QuestionData
     * @return newQuestion QuestionData object
     */
    public static QuestionData convertQuestionToQuestionData(Question question) {
        QuestionData newQuestion = new QuestionData();
        newQuestion.setQuestion(question.getQuestionText());

        for(QuestionChoice qc : question.getQuestionChoices()) {
            System.out.println("convert question method loop entered.");
            ChoicesData choice = new ChoicesData(qc.getChoices().getChoiceDescription(), qc.isCorrect());
            choice.setId(qc.getChoices().getId());
            newQuestion.getAnswers().add(choice);
        }
        Collections.sort(newQuestion.getAnswers());
        System.out.println("choices sorted according to IDs.");
        UserData user=new UserData(question.getUser().getFirstName(),question.getUser().getLastName(),question.getUser().getEmail());
        newQuestion.setPoints(question.getPoints());
        newQuestion.setUser(user);
        return newQuestion;
    }

    /**
     * this method converts ChoicesData to Choices
     * @param ch
     * @return
     */
    public static Choices convertChoicesDataToChoices(ChoicesData ch) {
            Choices choice = new Choices(ch.getChoiceDescription());
            return choice;
    }
    /**
     * because we have a mapping class Quiz that hold the Quizzes from the DB
     * and have data class QuizData that used to send the request here we convert the Quiz
     * after we retrieve it to QuizData object
     * @param quiz the quiz that we want to convert to QuizData
     * @return newQuiz QuizData object
     */
    public static QuizData convertQuizToQuizData(Quiz quiz) {
        ArrayList<QuestionData> quizDataQuestionArray = new ArrayList<>();
        //first convert the set of questions into array of questionData
        for(Question q : quiz.getQuestion()) {
            QuestionData newQuestion = Converter.convertQuestionToQuestionData(q);
            quizDataQuestionArray.add(newQuestion);
        }
        UserData user=new UserData(quiz.getUser().getFirstName(),quiz.getUser().getLastName(),quiz.getUser().getEmail());

        QuizData newQuiz = new QuizData(quiz.getCourse().getCourseName(), quiz.getQuiz_Name(), quiz.getThreshold(), quiz.getTimer(), quizDataQuestionArray, user);

        return newQuiz;
    }
}
