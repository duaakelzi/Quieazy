package actions;

import data.Message;
import data.UserData;
import requests.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;

public class RegisterAction {
    public static Message register(String firstName, String lastName, String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Message message = new Message();
        System.out.println("register method... ");
        try {
            session.beginTransaction();
            //check if user exists
            Query query = session.getSession().createQuery("FROM User WHERE email = :email ");
            query.setParameter("email", email);
            // User userToRetrieve = (User)

            if(query.list().size() > 0) {
                System.out.println("user already exists [method]");
                message.task = "EMAIL_IN_USE";
            } else {
                //user doesn't exist -> create one and inform the client
                System.out.println("creating user [method]");
                User newUser = new User(firstName, lastName, email, password);
                session.save(newUser);
                session.getTransaction().commit();
                message.task = "LOGIN_OK";
                message.userData = new UserData(firstName, lastName, email);
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
