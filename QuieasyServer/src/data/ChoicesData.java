package data;

import java.io.Serializable;

public class ChoicesData implements Serializable {
    private String choiceDescription;
    private boolean isCorrect;
    //c-tor

    public ChoicesData(){ }

    public ChoicesData(String choiceDescription, boolean isCorrect) {
        this.choiceDescription = choiceDescription;
        this.isCorrect = isCorrect;
    }

    //getter and setter

    public String getChoiceDescription() {
        return choiceDescription;
    }

    public void setChoiceDescription(String choiceDescription) {
        this.choiceDescription = choiceDescription;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        ChoicesData choiceToCompare = (ChoicesData) o;
        //(String choiceDescription, boolean isCorrect)
        return (choiceDescription.equals(choiceToCompare.getChoiceDescription()) &&
                isCorrect == choiceToCompare.isCorrect());
    }
}
