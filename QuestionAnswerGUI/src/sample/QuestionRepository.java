package sample;

import java.util.ArrayList;

public class QuestionRepository {
    private ArrayList<Question> questionlibrary;


    public QuestionRepository(){
        this.questionlibrary = addQuiz();

    }
    private ArrayList<Question> addQuiz() {
        ArrayList<Question> qs = new ArrayList<>();
        Question q1 = (new Question(" Select the option that suits the Manifesto for Agile Software Development",
                new Answer[]{ new Answer("Individuals and interactions", true),
                        new Answer("Working software", true),
                        new Answer("Customer collaboration", true),
                        new Answer("FalseAnswer", false)},5));
        qs.add(q1);

        Question q2 = (new Question(" Agile Software Development is based on",
                new Answer[]{new Answer("Incremental Development", true),
                        new Answer("Iterative Development", true),
                        new Answer("Linear Development", false),
                        new Answer("Both Incremental and  Linear Development", false)},4));
        qs.add(q2);
        Question q3 = (new Question(" Which on of the following is not an agile method?",
                new Answer[]{new Answer("XP", false),
                        new Answer("4GT", true),
                        new Answer("AUP", false),
                        new Answer("All of the mentioned", false)},3));
        qs.add(q3);
        Question q4 = (new Question(" How is plan driven development different from agile development ?",
                new Answer[]{new Answer("Outputs are decided through a process of negotiation during the software development process", false),
                        new Answer("Specification, design, implementation and testing are interleaved", false),
                        new Answer("Iteration occurs within activities", true),
                        new Answer("All of the mentioned", false)},2));
        qs.add(q4);
        Question q5 = (new Question("How many phases are there in Scrum ?",
                new Answer[]{new Answer("Two", false),
                        new Answer("Three",true),
                        new Answer("Four", false),
                        new Answer("Scrum is an agile method which means it does not have phases", false)},8));
        qs.add(q5);
        Question q6 = (new Question("Which of the following does not apply to agility to a software process?",
                new Answer[]{new Answer("Uses incremental product delivery strategy", false),
                        new Answer("Only essential work products are produced", false),
                        new Answer("Eliminate the use of project planning and testing", true),
                        new Answer("All of the mentioned", false)}, 6));
        qs.add(q6);

        return qs;
    }




    public ArrayList<Question> getQuestionlibrary() {
        return questionlibrary;
    }

    public int getsize(){
        return questionlibrary.size();
    }
}
