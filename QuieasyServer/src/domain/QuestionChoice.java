package domain;

import javax.persistence.*;

@Entity
@Table(name = "question_choices")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.question",
                joinColumns = @JoinColumn(name = "id_question")),
        @AssociationOverride(name = "primaryKey.choices",
                joinColumns = @JoinColumn(name = "id_choice")) })
public class QuestionChoice {
    //composite-id key
    private IDQuizChoice primaryKey = new IDQuizChoice();

    // additional fields
    private boolean isCorrect = false;

    public QuestionChoice() {}
    
    @EmbeddedId
    public IDQuizChoice getPrimaryKey() {
        return primaryKey;
    }
	public void setPrimaryKey(IDQuizChoice primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Question getQuestion() {
        return getPrimaryKey().getQuestion();
    }

    public void setQuestion(Question question) {
        getPrimaryKey().setQuestion(question);
    }

    @Transient
    public Choices getChoices() {
        return getPrimaryKey().getChoices();
    }

    public void setChoices(Choices choices) {
        getPrimaryKey().setChoices(choices);
    }
    @Column(name = "is_correct")
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}


}
