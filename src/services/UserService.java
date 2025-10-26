package services;

import models.*;
import java.util.*;

public class UserService {
    private List<Student> students = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();

    public UserService() {
        // Default instructor login
        instructors.add(new Instructor("admin", "1234"));
    }

    public Student registerStudent(String name, String email) {
        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already registered!");
                return null;
            }
        }
        Student s = new Student(name, email);
        students.add(s);
        System.out.println("Student registered successfully!");
        return s;
    }

    public Instructor loginInstructor(String username, String password) {
        for (Instructor i : instructors) {
            if (i.getUsername().equals(username) && i.getPassword().equals(password)) {
                return i;
            }
        }
        return null;
    }

    public List<Student> getStudents() { return students; }
    public List<Instructor> getInstructors() { return instructors; }
}
