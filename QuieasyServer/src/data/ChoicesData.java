package data;

public class ChoicesData {
    private String choiceDescription;
    private boolean isCorrect;
    //c-tor

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
}
