package data;

import java.io.Serializable;


/**
 * this class ResultData is serializable forms of the domain class Result to be sent using messages
 *  with constructor and all getter and setter
 */
public class ResultData implements Serializable {
    private int points;
    private boolean passed;
    private int statistics;

    private boolean []correctAnswers;


    public boolean[] getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(boolean[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    //c'tor
    public ResultData(){}

    /**
     * constructor for ResultData
     * @param points
     * @param passed
     */
    public ResultData(int points, boolean passed) {
        this.points = points;
        this.passed = passed;
    }
    public int getStatistics() { return statistics; }

    public void setStatistics(int statistics) { this.statistics = statistics; }

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
