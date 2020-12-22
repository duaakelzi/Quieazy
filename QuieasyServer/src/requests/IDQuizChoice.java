package requests;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
public class IDQuizChoice implements Serializable {
    //composite id approach
    private Question question;
    private Choices choices;

    // getters and setters
    @ManyToOne(cascade = CascadeType.ALL)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Choices getChoices() {
        return choices;
    }

    public void setChoices(Choices choices) {
        this.choices = choices;
    }
}
