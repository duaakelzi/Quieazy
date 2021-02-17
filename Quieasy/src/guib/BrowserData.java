package guib;

import data.QuizData;
import requests.QuizRequests;

import java.util.ArrayList;

/**
 * Retrieves quizzes from server, extracts and stores course and author names for use in the QuizBrowser.
 */
public class BrowserData {

    private static ArrayList<String> authorList = new ArrayList<String>(); // all quiz authors
    private static ArrayList<String> courseList = new ArrayList<String>(); // all courses
    private static ArrayList<QuizData> quizList = new ArrayList<QuizData>(); // all public quizzes

    /**
     * Retrieve quizzes from server, extract and store course and author names in ArrayList.
     */
    public static void load() {

        // clear data ArrayLists first to avoid duplication in case of reloading.
        authorList.clear();
        courseList.clear();
        quizList.clear();

        quizList = QuizRequests.fetchAllQuizzes(); // Retrieve quizzes from server

        // Extract and store course and author names in ArrayList.
        for (int i = 0; i < quizList.size(); i++) {

            String course = quizList.get(i).getCourse();

            String author = quizList.get(i).getUser().getLastName() + ", " + quizList.get(i).getUser().getFirstName();

            if(!courseExists(course)){ // To avoid duplication, add course only if not added already

                courseList.add(course);

            }

            if(!authorExists(author)){ // To avoid duplication, add author only if not added already

                authorList.add(author);

            }

        }

    }

    /**
     * Check if a given course already exists in the list of courses.
     * @param course Name of course to be checked.
     * @return True if the given course already exists in the list of courses; false otherwise.
     */
    private static boolean courseExists(String course){

        for (int i = 0; i < courseList.size(); i++) {

            if(course.equals(courseList.get(i))) return true;

        }

        return false;

    }

    /**
     * Check if a given author already exists in the list of authors.
     * @param author Name of author to be checked.
     * @return True if the given author already exists in the list of authors; false otherwise.
     */
    private static boolean authorExists(String author){

        for (int i = 0; i < authorList.size(); i++) {

            if(author.equals(authorList.get(i))) return true;

        }

        return false;

    }

    /**
     * Get a list of names of quiz authors.
     * @return ArrayList of names of quiz authors.
     */
    public static ArrayList<String> authorList(){ return authorList;}

    /**
     * Get a list of names of courses.
     * @return ArrayList of names of courses.
     */
    public static ArrayList<String> courseList() { return courseList;}

    /**
     * Get a list of all quizzes.
     * @return ArrayList of all quizzes.
     */
    public static ArrayList<QuizData> quizList(){ return quizList;}

}
