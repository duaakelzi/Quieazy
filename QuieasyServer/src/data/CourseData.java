package data;

import java.io.Serializable;

public class CourseData implements Serializable {
    private String courses;

    public CourseData(){}
    public CourseData(String courses) {
        this.courses = courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }
    public String getCourses() {
        return courses;
    }
}
