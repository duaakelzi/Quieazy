package actions;

import data.*;
import domain.*;
import org.hibernate.Session;
import persistence.HibernateUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RetrieveObjects {
    static Session session = HibernateUtil.getSessionFactory().openSession();
    public static Message message = new Message();

    public static Message retrieveQuizzes(String email) { //used to be List<Quiz>
        System.out.println("retrieving all Quizzes ");
        try {
            List<Quiz> quizList = session.getSession().createQuery("from Quiz", Quiz.class).list();
            if(quizList.size()>0) {
                System.out.println("Quizzes retrieved. Done ");
                message.task = "QUIZ_FETCH_OK";
               // message.quizlist.setAllQuizzes(quizList);
            }else{
                message.task = "QUIZ_FETCH_FAILED";
            }
            session.close();
        }catch(Exception e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(session != null)
                    session.close();
            }
            catch(Exception e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return message;
    }

    public static Message retrieveQuestions(Long quizID) { //used to be List<Quiz>
        System.out.println("retrieving all Questions ");
        try {
            List<Question> questions = session.getSession().createQuery("from Question", Question.class).list();
            if(questions.size()>0) {
                System.out.println("Questions retrieved.");
                for (int i = 0; i < questions.size(); i++) {
                    //translate the question into QuestionData, by
                    // creating one and adding choices with all info to it
                    QuestionData newQuestion = new QuestionData();
                    newQuestion.setQuestion(questions.get(i).getQuestionText());
                    for (Iterator<QuestionChoice> it = questions.get(i).getQuestionChoices().iterator(); it.hasNext(); ) {
                        QuestionChoice newQC = it.next();
                        ChoicesData choice = new ChoicesData();
                        choice.setChoiceDescription(newQC.getChoices().getChoiceDescription());
                        //check if this works as expected ==> not sure about use of primaryKey
                        QuestionChoice qc = session.getSession().createQuery("from QuestionChoice where primaryKey = :primaryKey", QuestionChoice.class)
                                .setParameter("primaryKey", newQC.getPrimaryKey()).getSingleResult();
                        choice.setCorrect(qc.isCorrect());
                        newQuestion.getAnswers().add(choice);
                    }
                    message.questionData.add(newQuestion);
                }
                message.task = "QUESTIONS_FETCH_OK";
            }else{
                message.task = "QUESTIONS_FETCH_FAILED";
            }
            session.close();
        }catch(Exception e){
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }finally{
            try{
                if(session != null)
                    session.close();
            }catch(Exception e){
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return message;
    }

    //retrieveResults(userEmail);
    public static Message retrieveResults(String userEmail) {
        System.out.println("retrieving all Results ");
        try {
            List<Result> results = session.getSession().createQuery("from Result", Result.class).list();
            if(results.size()>0) {
                System.out.println("Results retrieved. Done ");
                message.task = "RESULTS_FETCH_OK";
                for (int i = 0; i < results.size(); i++) {
                    ResultData newResult = new ResultData(results.get(i).getPoints(), results.get(i).getPassed());
                    message.resultData.add(newResult);
                }
            }else{
                message.task = "RESULTS_FETCH_FAILED"; //user might not have any results yet
            }
            session.close();
        }catch(Exception e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }finally{
            try{
                if(session != null)
                    session.close();
            } catch(Exception e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return message;
    }

    public static Message retrieveSP() {
        System.out.println("Retrieving study programs.");
        try {
            List<StudyProgram> studyPrograms = session.getSession().createQuery("from StudyProgram", StudyProgram.class).list();
            if(studyPrograms.size()>0) {
                System.out.println("Results retrieved. ");
                for (int i = 0; i < studyPrograms.size(); i++) {
                    //studyprogram, ArrayList<CourseData> courses
                    StudyProgramData newSP = new StudyProgramData();
                    newSP.setStudyprogram(studyPrograms.get(i).getStudyProgramName());
                    if(studyPrograms.get(i).getCourses() != null) {
                        System.out.println("SP course fetch entered " + i + " time");
                        for(Iterator<Course> it = studyPrograms.get(i).getCourses().iterator(); it.hasNext(); ) {
                            Course newCourse = it.next();
                            CourseData studyProgramCourse = new CourseData(newCourse.getCourseName());
                            newSP.getCourses().add(studyProgramCourse);
                        }
                    }
                    message.studyProgramData.add(newSP);
                }
                message.task = "STUDY_PROGRAMS_FETCH_OK";
            }else{
                message.task = "STUDY_PROGRAMS_FETCH_FAILED"; //user might not have any results yet
            }
            session.close();
        }catch(Exception e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }finally{
            try{
                if(session != null)
                    session.close();
            } catch(Exception e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        System.out.println("Message " + message.task + " will be sent");
        return message;
    }
}
