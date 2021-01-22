package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * this is a mapping class to a table study_program with all needed attributes and methods and relationships
 */
@Entity
@Table(name="study_program")
public class StudyProgram {

	private Long id;
	private String StudyProgramName;
	private String shortcut;
	private Set <Course> courses =new HashSet<Course>(0);
	private Set <User> users =new HashSet<User>(0);

	/**
	 * constructor 1 for StudyProgram
	 * @param studyProgramName
	 * @param shortcut
	 * @param courses
	 */
	public StudyProgram(String studyProgramName, String shortcut, Set<Course> courses) {
		super(); //object
		StudyProgramName = studyProgramName;
		this.shortcut = shortcut;
		this.courses = courses;
	}

	public StudyProgram() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructor 2 for StudyProgram
	 * @param studyProgramName
	 * @param shortcut
	 */
	public StudyProgram(String studyProgramName, String shortcut) {
		super();
		StudyProgramName = studyProgramName;
		this.shortcut = shortcut;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id_sp")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column (name="sp_name")
	public String getStudyProgramName() {
		return StudyProgramName;
	}

	public void setStudyProgramName(String studyProgramName) {
		StudyProgramName = studyProgramName;
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
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "sp_course", joinColumns = { @JoinColumn(name = "id_sp") }, inverseJoinColumns = { @JoinColumn(name = "id_course") })
	public Set<Course> getCourses() { return courses; }

	public void setCourses(Set<Course> courses) {
			this.courses = courses;
				}
	/**
	 * relationship between User and studyProgram
	 */
	@ManyToMany(cascade = {CascadeType.ALL} )
	@JoinTable(name = "sp_user", joinColumns = { @JoinColumn(name = "id_sp") }, inverseJoinColumns = { @JoinColumn(name = "id_user") })
	public Set<User> getUsers() {
				return users;
			}

	public void setUsers(Set<User> users) {
				this.users = users;
			}
}
