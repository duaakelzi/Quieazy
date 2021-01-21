package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class StudyProgramData holds all the information about the study Program to be sent using messages
 * with constructor and all getter and setter
 */
public class StudyProgramData implements Serializable {

    private String studyprogram;
    private ArrayList<CourseData> courses = new ArrayList<>();

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
        //String studyprogram, ArrayList<CourseData> courses
        return (studyprogram.equals(spToCompare.getStudyprogram()));
    }
}
