package actions;

import dataServer.Message;
import domainServer.Course;
import domainServer.Quiz;
import domainServer.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;

import java.util.HashSet;
import java.util.Set;

public class CreateObjects {
    public static Session session = HibernateUtil.getSessionFactory().openSession();
    public static Message message = new Message();
    public static Message CreateQuiz(String name,int threshold,boolean isPublic, String email, String course)
    {
        try {
        System.out.println("create quiz ");
        session.beginTransaction();
        //create quiz

        Query queryUser = session.getSession().createQuery("FROM User WHERE email = :email ");
            queryUser.setParameter("email", email);
        System.out.println("adding author [method]");
        User userToAdd = (User)queryUser.list().get(0);
        Query queryCourse = session.getSession().createQuery("FROM Course WHERE courseName = :courseName ");
            queryCourse.setParameter("courseName", course);
        Course courseToAdd=(Course)queryCourse.list().get(0);
        int CourseId =courseToAdd.getId();
        Query queryQuiz = session.getSession().createQuery("FROM Quiz WHERE quiz_name = :name  and id_course= :id");
            queryQuiz.setParameter("name", name);
            queryQuiz.setParameter("id", CourseId);

        if(queryQuiz.list().size() > 0)
        { System.out.println("Quiz already exists [method]");
            message.task = "QUIZ_IS_EXIST";}
        //add author

        else{
        Quiz quiz = new Quiz(name,threshold,false,false);
        Set<Quiz> quizUserSet= new HashSet<Quiz>();
        quizUserSet.add(quiz);
        quiz.setCourse(courseToAdd);
        course.setQuiz(quizUserSet);
        userToAdd.setQuiz(quizUserSet);
        quiz.setUser(userToAdd);

        session.save(quiz);
        session.getTransaction().commit();
            message.task = "QUIZ_CREATED";
            message.QuizData = new QuizData(quiz.getId(),quiz.getQuiz_Name(), quiz.getThreshold, false);}

        System.out.println("Done");}
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

    public static data.Message CreateQuestion(String questionText, int points, String email)
    {
        try {
            System.out.println("create Question ");
            session.beginTransaction();
            //does not check for existence of the question, since the client is separating the two requests
            Question question = new Question(questionText,points);

            //retrieve user to assign it to the question later
            User userToAdd = session.getSession().createQuery("FROM User WHERE email = :email ", User.class).setParameter("email", email).getSingleResult();

            Set<Question> questionUserSet= new HashSet<Question>();
            questionUserSet.add(question);
            userToAdd.setQuestion(questionUserSet);
            question.setUser(userToAdd);

            session.save(question);
            session.getTransaction().commit();
            message.task = "QUESTION_CREATED";
            message.questionData = new QuestionData(question.getId(), question.getQuestionText(), question.getPoints(), question.getUser()); //the rest should already be on the client side??

            System.out.println("Done");
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
                if(session != null) {
                    session.close();
                }
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
