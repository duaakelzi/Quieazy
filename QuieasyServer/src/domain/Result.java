package domain;

import javax.persistence.*;
/**
 * this is a mapping class to a table result with all needed attributes and methods(getter and setter) and relationships
 */
@Entity
@Table(name="result")
public class Result {
    private int id;
    private boolean isPassed;
    private int points;

     //relations
    //bidir N-1 with quiz
    private Quiz quiz;
    //bidir N-1 with user
    private User user;

    // constructors
    public Result() {
        super();
    }

    public Result(int id, boolean isPassed, int points) {
        this.id = id;
        this.isPassed = isPassed;
        this.points = points;
    }

    public Result(boolean isPassed, int points) {
        this.isPassed = isPassed;
        this.points = points;
    }

    // getters and setters
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id_result")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column (name="is_pass")
    public boolean getPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }
    @Column (name="statistics")
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    /**
     * relationship between Result and Quiz
     */
    @ManyToOne
    @JoinColumn(name = "id_quiz")
    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    /**
     * relationship between Result and User
     */
    @ManyToOne
    @JoinColumn(name = "id_user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
