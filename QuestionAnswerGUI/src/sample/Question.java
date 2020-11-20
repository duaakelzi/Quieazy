package sample;

public class Question {
    private final String question;
    private final Answer [] answers;
    private final int points;

    public Question(String question, Answer[] answers, int points) {
        this.question = question;
        this.answers = answers;
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswers(int index) {
        return answers[index].getAnswers();
    }

    public int getPoints() {
        return points;
    }
}
