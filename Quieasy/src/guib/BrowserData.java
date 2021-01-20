package guib;

import data.QuizData;
import requests.QuizRequests;

import java.util.ArrayList;

public class BrowserData {

    private static ArrayList<String> authorList = new ArrayList<String>(); // all quiz authors
    private static ArrayList<String> courseList = new ArrayList<String>(); // all courses
    private static ArrayList<QuizData> quizList = new ArrayList<QuizData>(); // all public quizzes

    // fetch quiz data from server
    public static void load() {

        // clear content
        authorList.clear();
        courseList.clear();
        quizList.clear();

        quizList = QuizRequests.fetchAllQuizzes();

        for (int i = 0; i < quizList.size(); i++) {

            String course = quizList.get(i).getCourse();

            String author = quizList.get(i).getUser().getLastName() + ", " + quizList.get(i).getUser().getFirstName();

            if(!courseExists(course)){

                courseList.add(course);

            }

            if(!authorExists(author)){

                authorList.add(author);

            }

        }

        /*
        User user1 = new User("Prof.", "Oliver", "Hart", "user@mail.com", 1, 1);
        User user2 = new User("Prof.", "Douglas", "Melton", "user@mail.com", 1, 1);
        User user3 = new User("Prof.", "Michael", "Porter", "user@mail.com", 1, 1);
        User user4 = new User("Prof.", "Ann", "Blair", "user@mail.com", 1, 1);
        User user5 = new User("Prof.", "Paul", "Farmer", "user@mail.com", 1, 1);

        authorList.add(user1);authorList.add(user2);authorList.add(user3);authorList.add(user4);authorList.add(user5);

        Course course1 = new Course("CTS", "Linear Algebra", "");
        Course course2 = new Course("CTS", "Calculus 1", "");
        Course course3 = new Course("INF", "Echtzeitsystem", "");
        Course course4 = new Course("CTS", "Computer Networking", "");

        courseList.add(course1);courseList.add(course2);courseList.add(course3);courseList.add(course4);

        Quiz quiz1 = new Quiz("LINA_Q01", user1, course1, "SEP 03, 2019", 23);
        Quiz quiz2 = new Quiz("LINA_Q02", user2, course1, "AUG 07, 2019", 30);
        Quiz quiz3 = new Quiz("LINA_Q03", user3, course1, "DEC 13, 2019", 50);

        Quiz quiz4 = new Quiz("CALC_Q01", user4, course2, "JAN 08, 2020", 47);
        Quiz quiz5 = new Quiz("CALC_Q02", user5, course2, "FEB 17, 2020", 35);
        Quiz quiz6 = new Quiz("CALC_Q03", user1, course2, "MAR 01, 2020", 37);

        Quiz quiz7 = new Quiz("PROG_Q01", user2, course3, "APR 02, 2020", 24);
        Quiz quiz8 = new Quiz("PROG_Q02", user3, course3, "MAY 05, 2020", 27);
        Quiz quiz9 = new Quiz("PROG_Q03", user4, course3, "JUN 09, 2020", 33);

        Quiz quiz10 = new Quiz("CON_Q01", user5, course4, "JUL 08, 2020", 22);
        Quiz quiz11 = new Quiz("CON_Q02", user1, course4, "AUG 06, 2020", 36);
        Quiz quiz12 = new Quiz("CON_Q03", user2, course4, "SEP 07, 2020", 43);

        quizList.add(quiz1);quizList.add(quiz2);quizList.add(quiz3);quizList.add(quiz4);quizList.add(quiz5);quizList.add(quiz6);
        quizList.add(quiz7);quizList.add(quiz8);quizList.add(quiz9);quizList.add(quiz10);quizList.add(quiz11);quizList.add(quiz12);
        */

    }

    private static boolean courseExists(String course){

        for (int i = 0; i < courseList.size(); i++) {

            if(course.equals(courseList.get(i))) return true;

        }

        return false;

    }

    private static boolean authorExists(String author){

        for (int i = 0; i < authorList.size(); i++) {

            if(author.equals(authorList.get(i))) return true;

        }

        return false;

    }

    public static ArrayList<String> authorList(){ return authorList;}
    public static ArrayList<String> courseList() { return courseList;}
    public static ArrayList<QuizData> quizList(){ return quizList;}

}
