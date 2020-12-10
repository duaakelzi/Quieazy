package data;

public class Answer {
    private String answer;
    private boolean correctAnswer;


    public Answer(String answer, boolean correctAnswer) {
        this.answer = answer;
        this.correctAnswer = correctAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;

    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public void setCorrectAnswer(boolean correctAnswer){
        this.correctAnswer = correctAnswer;
    }


}
