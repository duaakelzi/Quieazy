package requests;

import application.ClientAgent;
import data.Message;
import data.StudyProgramData;

import java.util.ArrayList;

public class StudyProgramC {
    private static Message message = new Message();

    public static void fetchAllStudyPrograms(){
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        message.task = "FETCH_STUDY_PROGRAMS";
        clientAgent.send(message);
    }
}
