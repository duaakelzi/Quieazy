package actions;

import dataServer.Message;
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
    public void CreateQuiz(String name,int threshold,boolean isPublic, String email)
    {
        System.out.println("create quiz ");
        session.beginTransaction();
        //create quiz
        Quiz quiz = new Quiz(name,threshold,isPublic,false);
        //add author
        Query query = session.getSession().createQuery("FROM User WHERE email = :email ");
        query.setParameter("email", email);
        System.out.println("adding author [method]");
        User userToAdd = (User)query.list().get(0);
        Set<Quiz> quizUserSet= new HashSet<Quiz>();
        quizUserSet.add(quiz);
        userToAdd.setQuiz(quizUserSet);
        quiz.setUser(userToAdd);

        session.save(quiz);
        session.getTransaction().commit();

        System.out.println("Done");
        session.close();
    }
    public static Message createQuiz(String name,int threshold,boolean isPublic ,String email) {

        System.out.println("calling create quiz method... ");
        try {
                createQuiz(name, threshold, isPublic, email);
                message.task = "QUIZ_CREATED";
        }
        catch(Exception e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        return message;
    }

}
