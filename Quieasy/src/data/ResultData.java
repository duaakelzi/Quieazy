package data;

import java.io.Serializable;

public class ResultData implements Serializable {
    private int points;
    private boolean passed;
    private int statistics;

    private boolean[] correctAnswers;


    //c'tor
    public ResultData() {
    }

    // getters/setters
    public ResultData(int points, boolean passed) {
        this.points = points;
        this.passed = passed;
    }

    public boolean[] getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(boolean[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getStatistics() {
        return statistics;
    }

    public void setStatistics(int statistics) {
        this.statistics = statistics;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
