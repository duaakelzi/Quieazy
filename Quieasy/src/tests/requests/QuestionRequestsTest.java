package requests;

import application.ClientAgent;
import data.ChoicesData;
import data.QuestionData;
import data.QuizData;
import data.UserData;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * class to test question related methods
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuestionRequestsTest {
    private static ClientAgent tempClient;
    protected static QuestionData questionToPersist;
    protected static QuizData quizForTest;
    protected static ArrayList<QuestionData> tempArray;
    private static UserData existingUser;

    /**
     * method to set up initial variables and connection
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        tempClient = ClientAgent.getClientAgent();
        (new Thread(tempClient)).start();

        ArrayList<ChoicesData> choicesOfNewQuestion  = new ArrayList<ChoicesData>();
        ChoicesData ch1 = new ChoicesData("Monstera", true);
        ChoicesData ch2 = new ChoicesData("Pepperomia", false);
        ChoicesData ch3 = new ChoicesData("Aloe Vera", false);
        ChoicesData ch4 = new ChoicesData("Bonsai", false);
        choicesOfNewQuestion.add(ch1);
        choicesOfNewQuestion.add(ch2);
        choicesOfNewQuestion.add(ch3);
        choicesOfNewQuestion.add(ch4);
        questionToPersist  = new QuestionData("Which plant is bigger?", choicesOfNewQuestion, 15);

        //question must be in a temp array
        tempArray = new ArrayList<>();
        tempArray.add(questionToPersist);

        //this quiz will be used to save/retrieve question
        quizForTest = new QuizData("Programming 1", "TestQuiz", 60, 15);

        existingUser = new UserData("Duaa", "kelzi", "user@mail.com"); //id_user = 1
    }

    /**
     * method to test with non-existent question
     */
    @Test
    public void testBeforeAllSetup() {
        //first the quiz should be persisted, as it's not in the system
        assertTrue(QuizRequests.createNewQuiz(quizForTest, existingUser));
        System.out.println("assertTrue for quiz creation passed");

        //check that the system behaves correctly when unknown question is requested
        assertFalse(QuestionRequests.fetchQuizQuestions(quizForTest).contains(questionToPersist));
        System.out.println("assertFalse for fetch before persistence passed.");
    }

    /**
     * method to test question persistence
     */
    @Test
    public void testFirstPersistNewQuestions() {
        //persist the question
        assertTrue(QuestionRequests.persistNewQuestions(existingUser, quizForTest, tempArray));
        System.out.println("assertTrue for persisting the question passed.");
    //    System.out.println("Question id: " + tempArray.get(0).getId());
    }

    /**
     * method to test retrieval with existing quiz
     */
    @Test
    public void testSecondFetchQuizQuestions() {
        //make sure the persisted question is found as expected by user
        assertTrue(QuestionRequests.fetchQuizQuestions(quizForTest).contains(questionToPersist)); //check in what order the choices are sent back
        // public boolean equals(Object o) {
        System.out.println("assertTrue for fetch after persistence passed.");
    }

    //test deletion of Question: not used
  //  @Test
//    public void testThirdQuestionsDeletion() {
//        //make sure deletion of the question goes ok
//        assertTrue(QuestionRequests.deleteQuestions(quizForTest, tempArray));
//        System.out.println("assertTrue for deleting question passed.");
//    }

    /**
     * method to destroy created variables
     * @throws Exception
     */
    @AfterClass
    public static void tearDown() throws Exception {
        //quiz deletion tested separately
        QuizRequests.deleteQuiz(quizForTest);
        questionToPersist = null; tempArray = null;
        existingUser = null;
        System.out.println("Values destroyed");
    }
}