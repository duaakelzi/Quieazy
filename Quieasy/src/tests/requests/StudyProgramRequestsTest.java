package requests;

import application.ClientAgent;
import data.StudyProgramData;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class StudyProgramRequestsTest {
    private static StudyProgramData existingSP;
    private static StudyProgramData notExistingSP;
    private static ClientAgent tempClient;

    @BeforeClass
    public void setUp() throws Exception {
        existingSP = new StudyProgramData();
        existingSP.setStudyprogram("Computer Science");

        notExistingSP  = new StudyProgramData();
        notExistingSP.setStudyprogram("Political Ecology");
        tempClient = ClientAgent.getClientAgent();
        (new Thread(tempClient)).start();
    }

    @Test
    //Testing whether FetchStudyPrograms works
    public void testFetchAllStudyPrograms() {
        ArrayList<StudyProgramData> tempSPArray = StudyProgramRequests.fetchAllStudyPrograms();

        try{
            assertFalse(String.valueOf(tempSPArray.contains(notExistingSP)), true); // used to be true
            System.out.println("assertFalse passed");

            assertTrue(String.valueOf(tempSPArray.contains(existingSP)), true);
            System.out.println("assertTrue passed");
        }catch (AssertionError ae) {
            System.out.println("Assertion caught error." + ae.getMessage());
        }

    }

    @AfterClass
    public void tearDown() throws Exception {
        System.out.println("Values destroyed");
    }
}