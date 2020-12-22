package actions;

import data.Message;
import requests.Question;
import requests.Quiz;
import org.hibernate.Session;
import persistence.HibernateUtil;

import java.util.List;

public class RetrieveObjects {
    static Session session = HibernateUtil.getSessionFactory().openSession();

    public static Message retrieveQuizzes(String email) { //used to be List<Quiz>
        System.out.println("retrieving all Quizzes ");
        Message message = new Message();
        try {
            List<Quiz> quizList = session.getSession().createQuery("from Quiz", Quiz.class).list();
            if(quizList.size()>0) {
                System.out.println("Quizzes retrieved. Done ");
                message.task = "FETCH_OK";
               // message.quizlist.setAllQuizzes(quizList);
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
    public static Message retrieveQuestions(Long quizID) { //used to be List<Quiz>
        System.out.println("retrieving all Questions ");
        Message message = new Message();
        try {
            List<Question> questionList = session.getSession().createQuery("from Question", Question.class).list();
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
