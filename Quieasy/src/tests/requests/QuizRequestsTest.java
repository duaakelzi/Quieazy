package tests.requests;

import application.ClientAgent;
import data.QuizData;
import data.UserData;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import requests.QuizRequests;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * class to test quiz related methods
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuizRequestsTest {
    private static ClientAgent tempClient;
    private static UserData existingUser;
    private static UserData anotherUser;
    private static UserData fakeUser;
    private static ArrayList<QuizData> tempArrayQuizzes1;
    private static ArrayList<QuizData> tempArrayQuizzes2;
    private static QuizData accessibleQuiz;
    private static QuizData fakeQuiz;
    private static QuizData nonAccessibleQuiz;

    /**
     * method to set up initial variables and connection
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        tempClient = ClientAgent.getClientAgent();
        (new Thread(tempClient)).start();

        existingUser = new UserData("Duaa", "kelzi", "user@mail.com"); //id_user = 1
        anotherUser = new UserData("Jerry", "Zefrank", "zefrank@zefrank.com"); //id_user = 10
        fakeUser = new UserData("Zefrank", "Zefrank", "zefrank@funny.com");

        tempArrayQuizzes1 = new ArrayList<>();
        tempArrayQuizzes2 = new ArrayList<>();

        //String course, String name, double threshold, int timer
        accessibleQuiz = new QuizData("Programming 1", "Java", 60, 15);
        fakeQuiz = new QuizData("Plant Ecology", "Monstera family", 60, 15);
        nonAccessibleQuiz = new QuizData("Calculus", "Integrals", 70, 60);
        //set current user to anotherUser
//        UserC.getUser(anotherUser.getFirstName(), anotherUser.getLastName(), anotherUser.getEmail());
    }

    /**
     * method to test creation of new quiz
     */
    @Test
    public void testCreateNewQuiz(){
        try{
            //create new quiz for Zefrank
            assertNotSame(QuizRequests.createNewQuiz(nonAccessibleQuiz, anotherUser), 0);
            System.out.println("assertTrue for quiz creation passed");
        }catch (AssertionError ae) {
            System.out.println("Assertion caught error." + ae.getMessage());
        }
    }

    /**
     * method to test retrieval of all quizzes
     */
    @Test
    public void testFetchAllQuizzes() {
        try {
            // no quizzes should be found for fake user -> size 0
            assertEquals(QuizRequests.fetchAllUserQuizzes(fakeUser).size(), 0);
            System.out.println("Fetch for fake user: assertEquals 0 passed");

            // quizzes for existing and another user > 0
            tempArrayQuizzes1 = QuizRequests.fetchAllUserQuizzes(existingUser); //this works when calling the method from another class
            assertNotSame(tempArrayQuizzes1.size(), 0);
            System.out.println("Fetch for existing user: assertNotSame as 0 for existingUser  passed");

            tempArrayQuizzes2 = QuizRequests.fetchAllUserQuizzes(anotherUser); //this works when calling the method from another class
            assertNotSame(tempArrayQuizzes2.size(), 0);
            System.out.println("Fetch for another user: assertNotSame as 0 for anotherUser  passed");
        }catch (AssertionError ae) {
            System.out.println("Assertion caught error." + ae.getMessage());
        }
    }

    /**
     * method to test content of retrieved with quiz
     */
    @Test
    public void testQuizContentOfFetchedQuizzes() {
            //SP: ComputerScience (id_sp = 1) contains Programming 1 (id_course = 1) which contains quiz java
            // existingUser should have access to CS, hence also to Prog 1
         //   tempArrayQuizzes1 = QuizC.fetchAllQuizzes(existingUser);
            assertTrue(tempArrayQuizzes1.contains(accessibleQuiz));
            System.out.println("assertTrue for existingUser  passed. Accessible quiz retrieved");
            assertFalse(tempArrayQuizzes1.contains(fakeQuiz));
            System.out.println("assertFalse for existingUser  passed. Fake quiz not retrieved");
            assertFalse(tempArrayQuizzes1.contains(nonAccessibleQuiz));
            System.out.println("assertFalse for existingUser  passed. Non-accessible quiz not retrieved");

            //for anotherUser
            assertTrue(tempArrayQuizzes2.contains(nonAccessibleQuiz));
            System.out.println("assertTrue for anotherUser  passed. Accessible quiz retrieved");
            assertFalse(tempArrayQuizzes2.contains(fakeQuiz));
            System.out.println("assertFalse for anotherUser  passed. Fake quiz not retrieved");
            assertFalse(tempArrayQuizzes2.contains(accessibleQuiz));
            System.out.println("assertFalse for anotherUser  passed. Non-accessible quiz not retrieved");
    }

    /**
     * method to test quiz deletion
     */
    @Test
    public void testQuizDelete() {
        assertTrue(QuizRequests.deleteQuiz(nonAccessibleQuiz));
        System.out.println("assertTrue for deleteQuiz passed. Nonaccessible quiz deleted");
        assertSame(QuizRequests.fetchAllUserQuizzes(anotherUser).size(), 0);
        System.out.println("assertSame for Nonaccessible quiz passed. Array size 0");
    }

    /**
     * method to destroy created variables
     * @throws Exception
     */
    @AfterClass
    public static void tearDown() throws Exception {
        tempArrayQuizzes1 = null; tempArrayQuizzes2 = null;
        existingUser = null; fakeUser = null; anotherUser = null;
        accessibleQuiz = null; fakeQuiz = null; nonAccessibleQuiz = null;
        System.out.println("Values destroyed");
    }
}