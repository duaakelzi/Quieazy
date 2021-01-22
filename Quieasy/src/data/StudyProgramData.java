package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class StudyProgramData is serializable forms of the domain class StudyProgram to be sent using messages
 * with constructor and all setter and geter
 */
public class StudyProgramData implements Serializable {

    private String studyprogram;
    private ArrayList<CourseData> courses = new ArrayList<>();


    public StudyProgramData() {
    }

    /**
     * constructor
     *
     * @param studyprogram
     * @param courses
     */
    public StudyProgramData(String studyprogram, ArrayList<CourseData> courses) {
        this.studyprogram = studyprogram;
        this.courses = courses;
    }

    /**
     * setter and getter
     *
     * @return studyprogram ,courses
     */
    public String getStudyprogram() {
        return studyprogram;
    }

    public void setStudyprogram(String studyprogram) {
        this.studyprogram = studyprogram;
    }

    public ArrayList<CourseData> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CourseData> courses) {
        this.courses = courses;
    }

    /**
     * override to enable comparisons for testing
     *
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        StudyProgramData spToCompare = (StudyProgramData) o;
        //String studyprogram, ArrayList<CourseData> courses
        return (studyprogram.equals(spToCompare.getStudyprogram()));
    }
}
