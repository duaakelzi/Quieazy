package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * this is a mapping class to a table course with all needed attributes and methods(getter and setter)  and relationships
 */
@Entity
@Table(name="course")
public class Course {
	private Long id;
	private String courseName;
	private String shortcut;
	
	private Set <StudyProgram> sp =new HashSet<StudyProgram>(0);
	private Set <User> users =new HashSet<User>(0);
	private Set<Quiz> quiz=new HashSet<Quiz>(0);
	
	public Course() {};
	public Course(String courseName, String shortcut) {
		super();
		this.courseName = courseName;
		this.shortcut = shortcut;
	}
	
	public Course(String courseName, String shortcut, Set<StudyProgram> sp) {
		super();
		this.courseName = courseName;
		this.shortcut = shortcut;
		this.sp = sp;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id_course")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column (name="course_name")
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Column (name="shortcut")
	public String getShortcut() {
		return shortcut;
	}
	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}


	/**
	 * relationship between Course and studyProgram
	 */
	@ManyToMany( mappedBy = "courses")
	public Set<StudyProgram> getSp() {
		return sp;
	}
	public void setSp(Set<StudyProgram> sp) {
		this.sp = sp;
	}

	/**
	 * relationship between Course and user
	 */
	@ManyToMany( mappedBy = "courses")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	/**
	 * relationship between Course and quiz
	 */
	@OneToMany(mappedBy="course")
	   
    public Set<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
	}
	

}
