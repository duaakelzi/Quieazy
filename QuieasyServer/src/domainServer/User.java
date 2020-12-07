package domainServer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="q_user")
public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Quiz> quiz=new HashSet<Quiz>(0);;
    private Set<Question> question=new HashSet<Question>(0);
    private Set<Course> courses=new HashSet<Course>(0);
    private Set <StudyProgram> sp =new HashSet<StudyProgram>(0);
    // bidir 1-N with Result
    // inverse side
    private Set<Result> results = new HashSet<>();

    // c'tors
    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    //getters, setters
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="id_user")
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    @Column (name="first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column (name="last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column (name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column (name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy="user")
    public Set<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(Set<Quiz> quiz) {
        this.quiz = quiz;
    }


    @OneToMany(mappedBy="user")
    public Set<Question> getQuestion() {
        return question;
    }

    public void setQuestion(Set<Question> question) {
        this.question = question;
    }


    @ManyToMany(   )
    @JoinTable(name = "user_course", joinColumns = { @JoinColumn(name = "id_user") }, inverseJoinColumns = { @JoinColumn(name = "id_course") })
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @ManyToMany( mappedBy = "users")
    public Set<StudyProgram> getSp() {
        return sp;
    }
    public void setSp(Set<StudyProgram> sp) {
        this.sp = sp;
    }
    @OneToMany
    @JoinColumn(name = "id_result")
    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }
    //methods

    //orphan removal for bidirectional 1-N
    //result
    public void addResult(Result res) {
        res.setUser(this); //add this quiz to the result
        results.add(res); //add res to this user
    }
    public void deleteResult(Result res) {
        results.remove(res); //remove res from user
    }
}
