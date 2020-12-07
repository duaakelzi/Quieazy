package actions;

import dataServer.Message;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;

public class UpdateObjects {
    public static Session session = HibernateUtil.getSessionFactory().openSession();
    public static Message message = new Message();

    public static Message updateQuiz(Long id,String name, int threshold, boolean isPublic, String course) {
        try {
            session.beginTransaction();
            Query queryCourse = session.getSession().createQuery("FROM Course WHERE courseName = :courseName ");
            queryCourse.setParameter("courseName", course);
            Course courseToAdd = (Course) queryCourse.list().get(0);
            Long CourseId = courseToAdd.getId();
            Query queryQuiz = session.getSession().createQuery("FROM Quiz WHERE id_quiz = :id ");
            queryQuiz.setParameter("id", id);

            Quiz quizToUpdate = (Quiz) queryQuiz.list().get(0);
            quizToUpdate.setQuiz_Name(name);
            quizToUpdate.setThreshold(threshold);
            quizToUpdate.setPublic(isPublic);
            session.update(quizToUpdate);
            session.getTransaction().commit();

            message.task = "QUIZ_UPDATED";
            message.QuizData = new QuizData(quiz.getId(), quiz.getQuiz_Name(), quiz.getThreshold, quiz.is_public(), course);
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

