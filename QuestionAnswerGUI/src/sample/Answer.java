package sample;

public class Answer {
    private final String answers;
    private final boolean correctAnswer;


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
}
