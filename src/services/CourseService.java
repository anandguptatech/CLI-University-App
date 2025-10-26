package services;

import models.*;
import java.util.*;

public class CourseService {
    private List<Course> courses = new ArrayList<>();

    public Course createCourse(String name, Instructor instructor) {
        for (Course c : courses) {
            if (c.getName().equalsIgnoreCase(name)) {
                System.out.println("Course already exists!");
                return null;
            }
        }
        Course c = new Course(name, instructor);
        courses.add(c);
        System.out.println("Course created successfully!");
        return c;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void enrollStudent(Course course, Student student) {
        if (course.getStudents().contains(student)) {
            System.out.println("Already enrolled in this course!");
            return;
        }
        course.getStudents().add(student);
        student.getEnrolledCourses().add(course);
        System.out.println("Enrolled successfully in " + course.getName());
    }
}

