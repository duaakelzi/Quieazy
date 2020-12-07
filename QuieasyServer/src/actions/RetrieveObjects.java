package actions;

import domainServer.Quiz;
import org.hibernate.Session;
import persistence.HibernateUtil;

import java.util.List;

public class RetrieveObjects {
    static Session session = HibernateUtil.getSessionFactory().openSession();

    public static List<Quiz> retrieveQuizzes(String email) {
        System.out.println("retrieving all Quizzes ");
        List<Quiz> quizList = session.getSession().createQuery("from Quiz", Quiz.class).list();
        System.out.println("Quizzes retrieved. Done ");
        return quizList;
    }
}
