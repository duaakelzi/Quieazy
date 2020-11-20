package sample;

import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Controller {
    final private Stage stage;
    final private QuestionAnswerScene questionAnswerScene;
    private QuestionRepository qs;
    static private int index = 0;

    public Controller(Stage stage) throws FileNotFoundException {
        this.stage = stage;
        this.questionAnswerScene = new QuestionAnswerScene(stage, this);
        qs = new QuestionRepository();

    }
    public QuestionAnswerScene getQuestionAnswerScene(){
        createQuestionAnswer();
        return questionAnswerScene;
    }



    public void onClickSubmit(){
        try {
            createQuestionAnswer();

        } catch (IndexOutOfBoundsException e) {
            index = 0;
        }
    }


    public void createQuestionAnswer(){
        Question question = qs.getQuestionlibrary().get(index);
        questionAnswerScene.getQuestionlabel().setText(question.getQuestion());
        int temp = index +1;
        questionAnswerScene.getTextmark().setText("Question "+ temp + " Not yet answered Marked out of " + question.getPoints());
        createAnswers(index);
        index++;
    }
    public void createAnswers(int index){
        for(int i = 0; i <= 3; i++ ){
            String answer = qs.getQuestionlibrary().get(index).getAnswers(i);
            questionAnswerScene.getAnswersCheck()[i].setText(answer);
        }
    }
    public void checkIfSelected(){
        for(int i = 0; i <= 3; i++){

        }
    }





}





