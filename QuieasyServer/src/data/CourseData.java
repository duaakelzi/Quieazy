package data;

import java.io.Serializable;

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
}
