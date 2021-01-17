package requests;

import application.ClientAgent;
import data.Message;
import data.StudyProgramData;

import java.util.ArrayList;

public class StudyProgramRequests {
    private static Message request = new Message();

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
