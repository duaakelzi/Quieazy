package domainServer;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class IDQuizChoice implements Serializable {
    //composite id approach
    private QuieazyEntityDemo.Question question;
    private QuieazyEntityDemo.Choices choices;

    // getters and setters
    @ManyToOne(cascade = CascadeType.ALL)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(QuieazyEntityDemo.Question question) {
        this.question = question;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Choices getChoices() {
        return choices;
    }

    public void setChoices(QuieazyEntityDemo.Choices choices) {
        this.choices = choices;
    }
}
