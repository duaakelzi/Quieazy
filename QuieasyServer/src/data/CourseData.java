package data;

import java.io.Serializable;
/**
 * this class CourseData holds all the information for the course to be sent using messages
 * with constructor and all getter and setter
 */
public class CourseData implements Serializable {
    private String course;

    public CourseData(){}
    public CourseData(String course) {
        this.course = course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public String getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        CourseData courseToCompare = (CourseData) o;
        //String course
        return (course.equals(courseToCompare.getCourse()));
    }
}
