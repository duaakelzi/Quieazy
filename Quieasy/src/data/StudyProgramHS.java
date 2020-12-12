package data;

import java.util.ArrayList;

public class StudyProgramHS {

    private String studyprogram;
    private ArrayList<Course> courses;

    public StudyProgramHS(String studyprogram, ArrayList<Course> courses) {
        this.studyprogram = studyprogram;
        this.courses = courses;
    }

    public String getStudyprogram() {
        return studyprogram;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
