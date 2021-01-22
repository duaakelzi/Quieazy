package data;

import java.io.Serializable;

public class CourseData implements Serializable {
    private String course;

    public CourseData() {
    }

    public CourseData(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        CourseData courseToCompare = (CourseData) o;
        //String course
        return (course.equals(courseToCompare.getCourse()));
    }
}
