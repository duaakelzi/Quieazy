package domain;

import javax.persistence.*;
import java.util.*;
/**
 * this is a mapping class to a table question with all needed attributes and methods(getter and setter)  and relationships
 */
@Entity
@Table(name="question")
public class Question  {

	private Long id;
	private String questionText;
	private int points;
	private User user;
	private Set<Quiz> quiz = new HashSet<Quiz>(0);
	private Set<QuestionChoice> questionChoices = new HashSet<QuestionChoice>(0);
	
	public Question() {}

	/**
	 * constructor 1 for Question
	 * @param questionText
	 * @param points
	 */
	public Question(String questionText, int points) {
		super();
		this.questionText = questionText;
		this.points = points;
	}

	/**
	 * constructor 2 for Question
	 * @param id
	 * @param questionText
	 * @param points
	 * @param quiz
	 */
	public Question(Long id, String questionText, int points, Set<Quiz> quiz) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.points = points;
		this.quiz = quiz;
	}

	/**
	 * constructor 3 for Question
	 * @param id
	 * @param questionText
	 * @param points
	 * @param quiz
	 * @param questionChoices
	 */
	public Question(Long id, String questionText, int points, Set<Quiz> quiz, Set<QuestionChoice> questionChoices) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.points = points;
		this.quiz = quiz;
		this.questionChoices = questionChoices;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id_question")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column (name="q_text")
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	@Column (name="points")
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	 @ManyToMany( mappedBy = "question")
	 public Set<Quiz> getQuiz() {
		 return this.quiz;
	 }
	//    public Set<Quiz> getQuizzes() {      return this.quiz;    }
	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
	}
	//    public void setQuizzes(Set<Quiz> quiz) { 	        this.quiz = quiz; 	    }
	
	    public void addQuiz(Quiz quiz) {
	    	 this.quiz.add(quiz);
	     }
	    public void deleteQuiz(Quiz quiz) {this.quiz.remove(quiz);}


	//owning for Questions
	/**
	 * relationship between question and choices
	 */
	@OneToMany(mappedBy = "primaryKey.question", cascade = CascadeType.ALL)

	public Set<QuestionChoice> getQuestionChoices() {
		return questionChoices;
	}

	public void setQuestionChoices(Set<QuestionChoice> questionChoices) {
		this.questionChoices = questionChoices;
	}

	public void addChoice(QuestionChoice questionChoice) {
		this.questionChoices.add(questionChoice);
	}
	/**
	 * relationship between question and user
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
