package models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String email;
    private List<Course> enrolledCourses = new ArrayList<>();

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }
}
