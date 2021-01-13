package data;

import java.io.Serializable;
import java.lang.Comparable;

public class ChoicesData implements Serializable, Comparable  {
    private String choiceDescription;
    private boolean isCorrect;
    private int id;

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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        ChoicesData choiceToCompare = (ChoicesData) o; //choices appear to have changed order
        //(String choiceDescription, boolean isCorrect)
        return (choiceDescription.equals(choiceToCompare.getChoiceDescription()) &&
                isCorrect == choiceToCompare.isCorrect());
    }
    @Override
    public int compareTo(Object o) {
        ChoicesData choiceToCompare = (ChoicesData) o;
        return Integer.compare(id, choiceToCompare.getId());
    }
}
