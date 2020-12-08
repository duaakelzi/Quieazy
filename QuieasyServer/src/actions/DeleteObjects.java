package actions;

import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;

public class DeleteObjects {
    public static Session session = HibernateUtil.getSessionFactory().openSession();
    public static Message message = new Message();
    public static Message DeleteQuiz(Long id, String course)
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
}
