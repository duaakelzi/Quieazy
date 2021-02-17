package requests;

import application.ClientAgent;
import data.Message;
import data.StudyProgramData;

import java.util.ArrayList;

public class StudyProgramRequests {
    private static Message request = new Message();

    /**
     * this method to request all Study Programs from the server
     * then get the response from the server that is holding the requested Study programs
     * @return returnedSP list of all study programs in the DB
     */
    public static ArrayList<StudyProgramData> fetchAllStudyPrograms(){
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        request.task = "FETCH_STUDY_PROGRAMS";
        ArrayList<StudyProgramData> returnedSP = new ArrayList<>();
        Message response = clientAgent.sendAndWaitForResponse(request);
        if(response != null && response.status){
            System.out.println("SPs found");
            returnedSP = response.studyProgramData;
        }else if(response != null && (!response.status)){
            //informed user that no SPs returned
            System.out.println("no SPs found");
        }
        return returnedSP;
    }
}
