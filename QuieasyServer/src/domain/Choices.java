package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * this is a mapping class to a table choices with all needed attributes and methods(getter and setter) and relationships
 */
@Entity
@Table(name="choices")
public class Choices {

	private int id;
	private String choiceDescription;
	private Set<QuestionChoice> questionChoices = new HashSet<QuestionChoice>();
	//constructors
	public Choices() {}
	public Choices(int id, String choiceDescription, Set<QuestionChoice> questionChoices) {
		super();
		this.id = id;
		this.choiceDescription = choiceDescription;
		this.questionChoices = questionChoices;
	}
	
	
	public Choices(String choiceDescription) {
		super();
		this.choiceDescription = choiceDescription;
	}
	
	//getters and setters
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id_choice")
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column (name="description")
	public String getChoiceDescription() {
		return choiceDescription;
	}

	public void setChoiceDescription(String choiceDescription) {
		this.choiceDescription = choiceDescription;
	}
	
	public void setQuestionChoices(Set<QuestionChoice> questionChoices) {
		this.questionChoices = questionChoices;
	}
	//inverse side for Question
	@OneToMany(mappedBy = "primaryKey.choices", cascade = CascadeType.ALL)
	public Set<QuestionChoice> getQuestionChoices() {
		return this.questionChoices;
	}

	public void setQuestionsChoices(Set<QuestionChoice> questionChoices) {
		this.questionChoices = questionChoices;
	}

	// methods
	public void addQuestionsChoices(QuestionChoice questionChoice) {
		this.questionChoices.add(questionChoice);
	}
}

