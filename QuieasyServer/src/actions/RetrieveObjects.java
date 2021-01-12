package actions;

import data.*;
import domain.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;

import java.util.*;

public class RetrieveObjects {
    static Session session;
    public static Message message;

    public static Message retrieveQuizzes(String email) { //used to be List<Quiz>
        System.out.println("retrieving all Quizzes ");
        try {
            message = new Message();
            session = HibernateUtil.getSessionFactory().openSession();
            //retrieve user so that we get the id info
            User user = session.getSession().createQuery("FROM User WHERE email = :email", User.class).setParameter("email", email).getSingleResult();
            //available for the user only?
            List<Quiz> quizList = session.getSession().createQuery("FROM Quiz WHERE id_user = :id_user", Quiz.class).setParameter("id_user", user.getUserID()).list();

            if(quizList.size()>0) {
                System.out.println("Quizzes retrieved. ");
                //convert List of Quiz into QuizData and add to the message
                for(Quiz q : quizList) {
                    QuizData fetchedQuiz = new QuizData(q.getCourse().getCourseName(), q.getQuiz_Name(), q.getThreshold(), q.getTimer());
                    message.allQuizzes.add(fetchedQuiz);
                }
                message.status = true;
                System.out.println("Size of the Array in RetrieveQuizzes(): " + message.allQuizzes.size());
            }else{
                System.out.println("No quizzes for that user ");
                message.status = false;
            }
            session.close();
        }catch(Exception e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.out.println("Error fetching quizzes. ");
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

    public static Message retrieveQuestions(String courseName, String quizName) { //used to be List<Quiz>
        System.out.println("retrieving all Questions ");
        try {
            message = new Message();
            session = HibernateUtil.getSessionFactory().openSession();

            Quiz queryQuiz = session.getSession().createQuery("SELECT q FROM Quiz q JOIN q.course c WHERE q.quiz_Name = :name AND c.courseName = :courseName", Quiz.class)
                    .setParameter("name", quizName).setParameter("courseName", courseName).getSingleResult();

            if(queryQuiz == null) {
                System.out.println("quiz for question doesn't exist.");
                message.status = false;
                return message;
            }

            System.out.println("quiz for question retrieved");

            String hql = "select  qu from Question qu " +
                    "JOIN qu.quiz t " +
                    "where t.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", queryQuiz.getId());
            List<Question> questions = query.list();
            //for testing
            System.out.println(questions.get(0).getId());
            System.out.println(questions.get(0).getQuestionText());
            System.out.println(questions.get(0).getPoints());

            //List<QuestionChoice> questionChoices = new ArrayList<>(questions.get(0).getQuestionChoices());

            if(questions.size()>0) {
                System.out.println("Questions retrieved...");
                   QuestionData newQuestionData = Converter.convertQuestionToQuestionData(questions.get(0));
                   message.questionData.add(newQuestionData);
                message.status = true;
                }

                else {
                message.status = false;
            }
            session.close();
        }catch(Exception e){
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            System.out.println("Error in retrieval encountered");
            message.status = false;
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
            message = new Message();
            session = HibernateUtil.getSessionFactory().openSession();
            List<Result> results = session.getSession().createQuery("from Result", Result.class).list();
            if(results.size()>0) {
                System.out.println("Results retrieved. Done ");
                message.task = "RESULTS_FETCH_OK";
                for (int i = 0; i < results.size(); i++) {
                    ResultData newResult = new ResultData(results.get(i).getPoints(), results.get(i).getPassed());
                    //message.resultData.add(newResult);
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
            message = new Message();
            session = HibernateUtil.getSessionFactory().openSession();
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
                message.status = true;
            }else{
                message.status = false; //user might not have any results yet
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
        System.out.println("Message status " + message.status + " will be sent");
        return message;
    }

    public static Message retrieveExistingQuestions(String text,String quiz) { //used to be List<Quiz>
        System.out.println("retrieving existing filtered Questions ");
        try {
            message = new Message();
            session = HibernateUtil.getSessionFactory().openSession();
            Query queryQuiz = session.getSession().createQuery("FROM Quiz WHERE quiz_Name = :quiz ");
            queryQuiz.setParameter("quiz", quiz);
            Quiz quizToSearch=(Quiz)queryQuiz.list().get(0);
            Long QuizId =quizToSearch.getId();

            ArrayList<QuestionData>questionData=new ArrayList<QuestionData>();
            List<Question> questionList = session.getSession().createQuery("from Question where questionText= :text ").list();
            for (int i=0;i<questionList.size();i++)
            {
                questionData.get(i).setQuestion(questionList.get(i).getQuestionText());

            }
            if(questionList.size()>0) {
                System.out.println("Questions retrieved. Done ");
                message.task = "FETCH_OK";
                // message.questionData.setAllQuestions(questionList);
            }else{
                message.task = "FETCH_FAILED";
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
}

