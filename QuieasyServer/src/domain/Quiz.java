package domain;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * this is a mapping class to a table quiz with all needed attributes and methods(getter and setter)  and relationships
 */
@Entity
@Table(name="quiz")
public class Quiz  {

    private Long id;
    private String quiz_Name;
    private double threshold;
    private boolean isPublic;
    private boolean deleted;
    private int timer;
    private User user;
    //relations
    //bidir 1-N with course
    //owning side
    private Course course;
    private Set <Question> question =new HashSet<Question>(0);
    // bidir 1-N with Result
    // inverse side
    private Set<Result> results = new HashSet<>();
    // c'tors
    public Quiz() {};

    /**
     * constructor 1 for Quiz
     * @param quiz_Name
     * @param threshold
     * @param isPublic
     * @param deleted
     * @param timer
     */
    public Quiz(String quiz_Name, double threshold, boolean isPublic, boolean deleted,int timer) { //deleted??
        super();
        this.quiz_Name = quiz_Name;
        this.threshold = threshold;
        this.isPublic = isPublic;
        this.deleted = deleted;
        this.timer=timer;

    }

    /**
     * constructor 2 for Quiz
     * @param quiz_Name
     * @param threshold
     * @param isPublic
     * @param deleted
     * @param question
     */
    public Quiz( String quiz_Name, double threshold, boolean isPublic, boolean deleted, Set<Question> question) {
        super();

        this.quiz_Name = quiz_Name;
        this.threshold = threshold;
        this.isPublic = isPublic;
        this.deleted = deleted;
        this.question = question;

    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="id_quiz")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column (name="quiz_name")
    public String getQuiz_Name() {
        return quiz_Name;
    }
    public void setQuiz_Name(String quiz_Name) {
        this.quiz_Name = quiz_Name;
    }
    @Column (name="threshold")
    public double getThreshold() {
        return threshold;
    }
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
    @Column (name="is_public")
    public boolean isPublic() {
        return isPublic;
    }
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    @Column (name="deleted")
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column (name="timer")
    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @ManyToMany( )
    @JoinTable(name = "quiz_question",
            joinColumns = { @JoinColumn(name = "id_quiz") },
            inverseJoinColumns = { @JoinColumn(name = "id_question") })
    public Set<Question> getQuestion() {
        return this.question;
    }
    public void setQuestion(Set<Question> questions) {
        this.question = questions;
    }
    public void addQuestion(Question question)
    {
        this.question.add(question);
    }
    public void deleteQuestion(Question question) { this.question.remove(question);}
    /**
     * relationship between quiz and user
     */
    @ManyToOne
    @JoinColumn(name = "id_user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    /**
     * relationship between Course and quiz
     */
    @ManyToOne
    @JoinColumn(name = "id_course")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    @OneToMany
    @JoinColumn(name = "id_result")
    public Set<Result> getResults() {
        return results;
    }

    /**
     * relationship between result and quiz
     * @param results
     */
    public void setResults(Set<Result> results) {
        this.results = results;
    }

    /**
     * orphan linkage for bidirectional 1-N
     * @param res
     */
    public void addResult(Result res) {
        res.setQuiz(this); //add this quiz to the result
        results.add(res); //add res to this quiz
    }

    /**
     * orphan removal for bidirectional 1-N
     * @param res
     */
    public void deleteResult(Result res) {
        results.remove(res); //remove res from results
    }


}
