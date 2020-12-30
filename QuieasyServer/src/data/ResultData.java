package data;

public class ResultData {
    private int points;
    private boolean passed;
    //c'tor
    public ResultData(){}
    // getters/setters
    public ResultData(int points, boolean passed) {
        this.points = points;
        this.passed = passed;
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
