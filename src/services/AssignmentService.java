package services;

import models.*;

public class AssignmentService {

    public void uploadAssignment(Course course, Student student, String text) {
        course.getAssignments().put(student, text);
        System.out.println("Assignment uploaded for " + course.getName());
    }
}

