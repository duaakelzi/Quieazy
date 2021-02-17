package data;

import java.io.Serializable;

/**
 * this class CourseData is serializable forms of the domain class course to be sent using messages
 * with constructor and all getter and setter
 */
public class CourseData implements Serializable {
    private String course;


    public CourseData() {
    }

    /**
     * constructor
     *
     * @param course
     */
    public CourseData(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    /**
     * getter and setter
     *
     * @param course
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * override to enable comparisons during testing
     *
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        CourseData courseToCompare = (CourseData) o;
        //String course
        return (course.equals(courseToCompare.getCourse()));
    }
}
