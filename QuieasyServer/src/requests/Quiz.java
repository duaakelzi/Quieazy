package requests;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public Quiz(String quiz_Name, double threshold, boolean isPublic, boolean deleted,int timer) { //deleted??
        super();
        this.quiz_Name = quiz_Name;
        this.threshold = threshold;
        this.isPublic = isPublic;
        this.deleted = deleted;
        this.timer=timer;

    }
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

    @ManyToMany(
    )
    @JoinTable(name = "quiz_question", joinColumns = { @JoinColumn(name = "id_quiz") }, inverseJoinColumns = { @JoinColumn(name = "id_question") })

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

    @ManyToOne
    @JoinColumn(name = "id_user")

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
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

    public void setResults(Set<Result> results) {
        this.results = results;
    }
    //orphan removal for bidirectional 1-N
    //result
    public void addResult(Result res) {
        res.setQuiz(this); //add this quiz to the result
        results.add(res); //add res to this quiz
    }
    public void deleteResult(Result res) {
        results.remove(res); //remove res from results
    }


}
