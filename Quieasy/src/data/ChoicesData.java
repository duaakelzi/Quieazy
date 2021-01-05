package data;

import java.io.Serializable;

public class ChoicesData implements Serializable {
    private String choiceDescription;
    private boolean isCorrect;

    public ChoicesData(){ }

    public ChoicesData(String choiceDescription, boolean isCorrect) {
        this.choiceDescription = choiceDescription;
        this.isCorrect = isCorrect;
    }

    public String getChoiceDescription() {
        return choiceDescription;
    }

    public boolean isCorrect() {
        return isCorrect;

    }

    public void setChoiceDescription(String choiceDescription){
        this.choiceDescription = choiceDescription;
    }

    public void setCorrect(boolean correct){
        this.isCorrect = correct;
    }




}
