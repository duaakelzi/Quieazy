package actions;

import data.ChoicesData;
import data.Message;
import requests.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateObjects {
    public static Session session = HibernateUtil.getSessionFactory().openSession();
    public static Message message = new Message();
    public static Message CreateQuiz(String name,double threshold,boolean isPublic,int timer,  String email,String course)
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
        Long CourseId =courseToAdd.getId();

        Query queryQuiz = session.getSession().createQuery("FROM Quiz WHERE quiz_Name = :name  and id= :id");


            queryQuiz.setParameter("name", name);
            queryQuiz.setParameter("id", CourseId);

        if(queryQuiz.list().size() > 0)
        { System.out.println("Quiz already exists [method]");
            message.task = "QUIZ_IS_EXIST";}
        //add author

        else{
        Quiz quiz =new Quiz(name,threshold,false,false,timer);
        Set<Quiz> quizUserSet= new HashSet<Quiz>();
        quizUserSet.add(quiz);
        quiz.setCourse(courseToAdd);
            courseToAdd.setQuiz(quizUserSet);
        userToAdd.setQuiz(quizUserSet);
        quiz.setUser(userToAdd);

        session.save(quiz);
        session.getTransaction().commit();
            message.task = "QUIZ_CREATED";
            //message.quizlist = new QuizData(quiz.getQuiz_Name(), quiz.isPublic(),quiz.getThreshold(),quiz.getCourse().getCourseName());}

        System.out.println("Done");}}
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


    public static Message CreateQuestion(String questionText, int points, List<ChoicesData> questionChoicesList, String quizName,String email)
    {
        try {
            System.out.println("create Choices ");
            session.beginTransaction();
            //does not check for existence of the question, since the client is separating the two requests
            Choices ch1 = new Choices();
            ch1.setChoiceDescription(questionChoicesList.get(0).getChoiceDescription());
            Choices ch2 = new Choices();
            ch2.setChoiceDescription(questionChoicesList.get(1).getChoiceDescription());
            Choices ch3 = new Choices();
            ch3.setChoiceDescription(questionChoicesList.get(2).getChoiceDescription());
            Choices ch4 = new Choices();
            ch4.setChoiceDescription(questionChoicesList.get(3).getChoiceDescription());

            session.save(ch1);
            session.save(ch2);
            session.save(ch3);
            session.save(ch4);

            System.out.println("create Question ");
            Question question = new Question (questionText,5);

            //retrieve user to assign it to the question later
            User userToAdd = session.getSession().createQuery("FROM User WHERE email = :email", User.class).setParameter("email", email).getSingleResult();

            Set<Question> questionUserSet= new HashSet<Question>();
            questionUserSet.add(question);
            userToAdd.setQuestion(questionUserSet);
            question.setUser(userToAdd);

            //choices question
            Set <QuestionChoice> qch =new HashSet<QuestionChoice>();
            QuestionChoice qc1=new QuestionChoice();
            QuestionChoice qc2=new QuestionChoice();
            QuestionChoice qc3=new QuestionChoice();
            QuestionChoice qc4=new QuestionChoice();

            qc1.setQuestion(question);
            qc1.setChoices(ch1);
            qc1.setCorrect(questionChoicesList.get(0).isCorrect());

            qc2.setQuestion(qc1.getQuestion());
            qc2.setChoices(ch2);
            qc2.setCorrect(questionChoicesList.get(1).isCorrect());

            qc3.setQuestion(qc1.getQuestion());
            qc3.setChoices(ch3);
            qc3.setCorrect(questionChoicesList.get(2).isCorrect());

            qc4.setQuestion(qc1.getQuestion());
            qc4.setChoices(ch4);
            qc4.setCorrect(questionChoicesList.get(3).isCorrect());
            qch.add(qc1);
            qch.add(qc2);
            qch.add(qc3);
            qch.add(qc4);
            question.setQuestionsChoices(qch);

            session.save(question);
            session.getTransaction().commit();
            message.task = "QUESTION_CREATED";
            //return choiceslist too

           // message.questionData = new QuestionData(question.getId(), question.getQuestionText(), (List) question.getQuestionChoices(), question.getPoints(),  question.getUser()); //the rest should already be on the client side??


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
