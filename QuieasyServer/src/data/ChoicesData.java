package data;

import java.io.Serializable;
import java.lang.Comparable;

/**
 * this class ChoiceData is serializable forms of the domain class choice to be sent using messages
 * with constructor and all getter and setter
 */
public class ChoicesData implements Serializable, Comparable {
    private String choiceDescription;
    private boolean isCorrect;
    private int id;
    //c-tor


    public ChoicesData(){ }
    /**
     * constructor
     * @param isCorrect
     * @param choiceDescription
     */
    public ChoicesData(String choiceDescription, boolean isCorrect) {
        this.choiceDescription = choiceDescription;
        this.isCorrect = isCorrect;
    }

    /**
     * getter and setter
     * @return choiceDescription , isCorrect
     */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * override to enable comparisons during testing
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        ChoicesData choiceToCompare = (ChoicesData) o;
        //(String choiceDescription, boolean isCorrect)
        return (choiceDescription.equals(choiceToCompare.getChoiceDescription()) &&
                isCorrect == choiceToCompare.isCorrect());
    }

    /**
     * override to enable comparisons during testing
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Object o) {
        ChoicesData choiceToCompare = (ChoicesData) o;
        return Integer.compare(id, choiceToCompare.getId());
    }
}
