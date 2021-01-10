package actions;

import data.ChoicesData;
import data.QuestionData;
import data.QuizData;
import domain.Question;
import domain.QuestionChoice;
import domain.Quiz;

import java.util.ArrayList;

public class Converter {

    public static QuestionData convertQuestionToQuestionData(Question question) {
        QuestionData newQuestion = new QuestionData();
        newQuestion.setQuestion(question.getQuestionText());

        for(QuestionChoice qc : question.getQuestionChoices()) {
            System.out.println("convert question method loop entered.");
            ChoicesData choice = new ChoicesData(qc.getChoices().getChoiceDescription(), qc.isCorrect());
            newQuestion.getAnswers().add(choice);
        }
        return newQuestion;
    }

    // seems this method won't need to be used. keep for the timebeing
    public static QuizData convertQuizToQuizData(Quiz quiz) {
        ArrayList<QuestionData> quizDataQuestionArray = new ArrayList<>();
        //first convert the set of questions into array of questionData
        for(Question q : quiz.getQuestion()) {
            QuestionData newQuestion = Converter.convertQuestionToQuestionData(q);
            quizDataQuestionArray.add(newQuestion);
        }

        QuizData newQuiz = new QuizData(quiz.getCourse().getCourseName(), quiz.getQuiz_Name(), quiz.getThreshold(), quiz.getTimer(), quizDataQuestionArray);
        return newQuiz;
    }
}
