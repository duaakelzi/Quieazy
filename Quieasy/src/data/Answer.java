package data;

public class Answer {
    private  String answers;
    private  boolean correctAnswer;


    public Answer(String answers, boolean correctAnswer) {
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getAnswers() {
        return answers;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;

    }
    public void setAnswer(String answer){
        this.answers = answer;
    }

    public void setCorrectAnswer(boolean correctAnswer){
        this.correctAnswer = correctAnswer;
    }


}
