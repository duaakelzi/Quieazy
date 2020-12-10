package actions;

import dataServer.ChoicesData;
import dataServer.Message;

import dataServer.QuestionData;
import domainServer.Course;
import domainServer.Question;
import domainServer.Quiz;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;

import java.util.List;
import java.util.Set;

public class UpdateObjects {
    public static Session session = HibernateUtil.getSessionFactory().openSession();
    public static Message message = new Message();

    public static Message updateQuiz(String name, double threshold, boolean isPublic, String course) {
        try {
            session.beginTransaction();
            Query queryCourse = session.getSession().createQuery("FROM Course WHERE courseName = :courseName ");
            queryCourse.setParameter("courseName", course);
            Course courseToAdd = (Course) queryCourse.list().get(0);
            Long CourseId = courseToAdd.getId();
            Query queryQuiz = session.getSession().createQuery("FROM Quiz WHERE quiz_Name = :name ");
            queryQuiz.setParameter("name", name);

            Quiz quizToUpdate = (Quiz) queryQuiz.list().get(0);
            quizToUpdate.setQuiz_Name(name);
            quizToUpdate.setThreshold(threshold);
            quizToUpdate.setPublic(isPublic);
            session.update(quizToUpdate);
            session.getTransaction().commit();

            message.task = "QUIZ_UPDATED";
            // message.quizlist = new QuizData(quizToUpdate.getQuiz_Name(), false, quizToUpdate.getThreshold(),courseToAdd.getCourseName());}

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


    //what do we do with isCorrect?? so far not in question, only choices
    // switch QuestionChoice to Choice

    public static Message updateQuestion(Long questionID, List<ChoicesData> choicesList, String questionText, int points, boolean isCorrect) {

        try {
            session.beginTransaction();
            //retrieve the question
            Question questionToUpdate = session.getSession().createQuery("from Question WHERE id = :id ", Question.class).setParameter("id", questionID).getSingleResult();

            //set vals to passed ones
            if (questionToUpdate == null) {
                message.task = "UPDATE_FAILED";
            }else {
                questionToUpdate.setQuestionsChoices(((Set)(choicesList)));
                questionToUpdate.setQuestionText(questionText);
                questionToUpdate.setPoints(points);
                session.update(questionToUpdate);
                session.getTransaction().commit();
                message.task = "UPDATE_OK";
                //return choiceslist too
                message.questionData = new QuestionData(questionToUpdate.getId(), questionToUpdate.getQuestionText(), (List) questionToUpdate.getQuestionChoices(), questionToUpdate.getPoints(), questionToUpdate.getUser());
            }
        } catch(Exception e){
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }finally{
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
    }}


