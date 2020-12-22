package data;

import java.io.Serializable;

public class Course implements Serializable {
    private String courses;

    public Course(String courses) {
        this.courses = courses;
    }

    public String getCourses() {
        return courses;
    }
}
