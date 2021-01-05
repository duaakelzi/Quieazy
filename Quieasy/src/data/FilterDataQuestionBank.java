package data;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class FilterDataQuestionBank {

private QuestionData questions;

private String author;

private String studyProgram;





    public FilterDataQuestionBank(QuestionData questions, String author, String studyProgram) {
        this.questions = questions;
        this.author = author;
        this.studyProgram = studyProgram;

    }

    public QuestionData getQuestions() {
        return questions;
    }

    public String getAuthor() {
        return author;
    }

    public String getStudyProgram() {
        return studyProgram;
    }
}
