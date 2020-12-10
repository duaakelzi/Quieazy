package domainServer;

import dataServer.ChoicesData;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public Question(String questionText, int points) {
		super();
		this.questionText = questionText;
		this.points = points;
	}
	public Question(Long id, String questionText, int points, Set<Quiz> quiz) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.points = points;
		this.quiz = quiz;
	}
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
	    public Set<Quiz> getQuizzes() {
	        return this.quiz;
	    }

	    public void setQuizzes(Set<Quiz> quiz) {
	        this.quiz = quiz;
	    }
	
	    public void addQuiz(Quiz quiz) 
	     {
	    	 this.quiz.add(quiz);
	     }
	public void setQuestionChoices(Set<ChoicesData> questionChoices) {
			this.questionChoices = questionChoices;
		}

	//owning for Questions
	@OneToMany(mappedBy = "primaryKey.question", cascade = CascadeType.ALL)
	public Set<QuestionChoice> getQuestionChoices() {
		return questionChoices;
	}

	public void setQuestionsChoices(Set<QuestionChoice> questionChoices) {
		this.questionChoices = questionChoices;
	}

	public void addChoice(QuestionChoice questionChoice) {
		this.questionChoices.add(questionChoice);
	}
	
	@ManyToOne
    @JoinColumn(name = "id_user")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
