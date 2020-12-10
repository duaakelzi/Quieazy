package actions;

import dataServer.Message;
import domainServer.Course;
import domainServer.Quiz;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;

public class DeleteObjects {
    public static Session session = HibernateUtil.getSessionFactory().openSession();
    public static Message message = new Message();

    public static Message deleteQuiz(Long id, String course)
    {
        try {
            session.beginTransaction();
            Query queryCourse = session.getSession().createQuery("FROM Course WHERE courseName = :courseName ");
            queryCourse.setParameter("courseName", course);
            Course courseToAdd = (Course) queryCourse.list().get(0);
            Long CourseId = courseToAdd.getId();
            Query queryQuiz = session.getSession().createQuery("FROM Quiz WHERE id_quiz = :id ");
            queryQuiz.setParameter("id", id);

            Quiz quizToDelete = (Quiz) queryQuiz.list().get(0);
            session.delete(quizToDelete);
            session.getTransaction().commit();

            message.task = "QUIZ_DELETED";
    }
        catch(Exception e)
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
        session.close();
        return message;
    }

    public static Message deleteQuestion(Long questionID)
    {
        try {
            session.beginTransaction();
            Question questionToDelete = session.getSession().createQuery("FROM Question WHERE id = :id ", Question.class).setParameter("id", questionID).getSingleResult();
            if (questionToDelete == null) {
                message.task = "DELETE_FAILED";
            }else {
                session.delete(questionToDelete);
                session.getTransaction().commit();
                message.task = "DELETE_OK";
            }
        }
        catch(Exception e)
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
        session.close();
        return message;
    }
}
