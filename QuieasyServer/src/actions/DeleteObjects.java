package actions;

import data.ChoicesData;
import data.Message;
import data.QuestionData;
import domain.Choices;
import domain.Course;
import domain.Question;
import domain.Quiz;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class DeleteObjects {
    public static Session session;
    public static Message message;

    /**
     * this method deletes quiz from the database
     * @param quizName
     * @param courseName
     * @return
     */
    public static Message deleteQuiz(String quizName, String courseName)
    {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Course course = session.getSession().createQuery("FROM Course WHERE course_name = :course", Course.class).setParameter("course", courseName).getSingleResult();
            Quiz quizToDelete = session.getSession().createQuery("FROM Quiz WHERE quiz_name= :quiz AND id_course = :course", Quiz.class)
                    .setParameter("quiz", quizName).setParameter("course", course.getId()).getSingleResult();
            message = new Message();
            if(quizToDelete == null){
                message.status = false;
            } else {
                session.delete(quizToDelete);
                session.getTransaction().commit();
                message.status = true;
            }
    } catch(Exception e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            message.status = false;
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
        session.close();
        return message;
    }

    /**
     * this method deletes questions from the database
     * @param questionID
     * @return
     */
    public static Message deleteQuestion(Long questionID)
    {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            message = new Message();
            Question questionToDelete = session.getSession().createQuery("SELECT q FROM Question q WHERE q.id = :id ", Question.class).setParameter("id", questionID).getSingleResult();
            //tried a different approach: got in the loop
//            List<Question> potentialQuestions = session.getSession().createQuery("SELECT q FROM Question q WHERE q.questionText = :text AND" +
//                    "q.points = :points ").setParameter("text", questionText).setParameter("points", points).list();

            if (questionToDelete == null) {
                message.status = false;
            }else {
                session.delete(questionToDelete);
                session.getTransaction().commit();
                message.status = true;
            }
        }
        catch(Exception e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            message.status = false;
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
        session.close();
        return message;
    }
}
