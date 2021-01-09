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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuestionRequestsTest {
    private static ClientAgent tempClient;
    protected static QuestionData questionToPersist;
    protected static QuizData quizForTest;
    protected static ArrayList<QuestionData> tempArray;
    private static UserData existingUser;

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
    @Test
    public void testBeforeAllSetup() {
        quizForTest.setId(QuizRequests.createNewQuiz(quizForTest, existingUser));
        assertNotSame(quizForTest.getId(), 0);
        System.out.println("assertTrue for quiz creation passed");

        assertFalse(QuestionRequests.fetchQuizQuestions(quizForTest).contains(questionToPersist));
        System.out.println("assertFalse for fetch before persistence passed.");
    }

    @Test
    public void testFirstPersistNewQuestions() {
        assertTrue(QuestionRequests.persistNewQuestions(existingUser, quizForTest, tempArray));
        System.out.println("assertTrue for persisting the question passed.");
    }

    @Test
    public void testSecondFetchQuizQuestions() {
        //question should be persisted now
        assertTrue(QuestionRequests.fetchQuizQuestions(quizForTest).contains(questionToPersist));
        System.out.println("assertTrue for fetch after persistence passed.");
    }

    //test deletion of Question
    @Test
    public void testThirdQuestionsDeletion() {
        assertTrue(QuestionRequests.deleteQuestions(quizForTest, tempArray));
        System.out.println("assertTrue for deleting question passed.");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        QuizRequests.deleteQuiz(quizForTest);
        questionToPersist = null; tempArray = null;
        existingUser = null;
        System.out.println("Values destroyed");
    }
}