package actions;


import dataServer.Message;
import dataServer.UserData;
import domain.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;


public class LoginAction {
    public static Message login(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Message message = new Message();
        System.out.println("login method... ");
        try {
            session.beginTransaction();
            Query query = session.getSession().createQuery("FROM User WHERE email = :email");
            query.setParameter("email", email);

            User userToRetrieve = (User)query.list().get(0);

            if(password.equals(userToRetrieve.getPassword())) {
                System.out.println("login successful [method]");
                message.task = "LOGIN_OK";
                message.userData = new UserData(userToRetrieve.getFirstName(), userToRetrieve.getLastName(), email);
                return message;
            }else {
                message.task = "login failed";
            }
            session.getTransaction().commit();
            System.out.println("User retrieval for login done");
            session.close();
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
        return message;
    }
}
