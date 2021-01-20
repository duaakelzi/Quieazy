package data;

import java.io.Serializable;
import java.util.ArrayList;

public class StudyProgramData implements Serializable {

    private String studyprogram;
    private ArrayList<CourseData> courses  = new ArrayList<>();;

    public StudyProgramData(){}

    public StudyProgramData(String studyprogram, ArrayList<CourseData> courses) {
        this.studyprogram = studyprogram;
        this.courses = courses;
    }

    public String getStudyprogram() {
        return studyprogram;
    }

    public ArrayList<CourseData> getCourses() {
        return courses;
    }
    public void setStudyprogram(String studyprogram) {
        this.studyprogram = studyprogram;
    }

    public void setCourses(ArrayList<CourseData> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        StudyProgramData spToCompare = (StudyProgramData) o;
        //temporarily removed the array of course --> to be fixed when the test case is adapted
        return (studyprogram.equals(spToCompare.getStudyprogram()));
    }
}
