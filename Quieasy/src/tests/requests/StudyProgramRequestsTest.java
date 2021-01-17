package requests;

import application.ClientAgent;
import data.StudyProgramData;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StudyProgramRequestsTest {
    private static StudyProgramData existingSP;
    private static StudyProgramData notExistingSP;
    private static ClientAgent tempClient;

    @BeforeClass
    public static void setUp() throws Exception {
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
            assertFalse(tempSPArray.contains(notExistingSP)); // used to be true
            System.out.println("assertFalse passed");

            assertTrue(tempSPArray.contains(existingSP));
            System.out.println("assertTrue passed");

    }

    @AfterClass
    public static void tearDown() throws Exception {
        //add destruction related calls
        existingSP = null; notExistingSP = null;
        System.out.println("Values destroyed");
    }
}