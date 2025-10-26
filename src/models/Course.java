package models;

import java.util.*;

public class Course {
    private String name;
    private Instructor instructor;
    private List<Student> students = new ArrayList<>();
    private Map<Student, String> assignments = new HashMap<>();

    public Course(String name, Instructor instructor) {
        this.name = name;
        this.instructor = instructor;
    }

    public String getName() { return name; }
    public Instructor getInstructor() { return instructor; }
    public List<Student> getStudents() { return students; }
    public Map<Student, String> getAssignments() { return assignments; }
}
