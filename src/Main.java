import models.*;
import services.*;
import utils.InputHelper;

import java.util.List;

public class Main {
    private static final UserService userService = new UserService();
    private static final CourseService courseService = new CourseService();
    private static final AssignmentService assignmentService = new AssignmentService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== CLI University App ===");
            System.out.println("1. Student Registration");
            System.out.println("2. Instructor Login");
            System.out.println("3. Exit");

            int choice = InputHelper.nextInt("Enter your choice: ");

            switch (choice) {
                case 1 -> handleStudentRegistration();
                case 2 -> handleInstructorLogin();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void handleStudentRegistration() {
        String name = InputHelper.nextLine("Enter student name: ");
        String email = InputHelper.nextLine("Enter email: ");
        Student student = userService.registerStudent(name, email);
        if (student != null) studentMenu(student);
    }

    private static void handleInstructorLogin() {
        String username = InputHelper.nextLine("Username: ");
        String password = InputHelper.nextLine("Password: ");
        Instructor instructor = userService.loginInstructor(username, password);
        if (instructor != null) {
            System.out.println("Login successful!");
            instructorMenu(instructor);
        } else {
            System.out.println("Invalid login!");
        }
    }

    private static void instructorMenu(Instructor instructor) {
        while (true) {
            System.out.println("\n--- Instructor Menu ---");
            System.out.println("1. Create Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Logout");

            int choice = InputHelper.nextInt("Enter choice: ");

            switch (choice) {
                case 1 -> {
                    String cname = InputHelper.nextLine("Enter course name: ");
                    courseService.createCourse(cname, instructor);
                }
                case 2 -> showAllCourses();
                case 3 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void studentMenu(Student student) {
        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View All Courses");
            System.out.println("2. Enroll in Course");
            System.out.println("3. Upload Assignment");
            System.out.println("4. Logout");

            int choice = InputHelper.nextInt("Enter choice: ");

            switch (choice) {
                case 1 -> showAllCourses();
                case 2 -> handleEnrollment(student);
                case 3 -> handleAssignmentUpload(student);
                case 4 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void showAllCourses() {
        List<Course> allCourses = courseService.getCourses();
        if (allCourses.isEmpty()) System.out.println("No courses available!");
        else {
            System.out.println("Available Courses:");
            for (int i = 0; i < allCourses.size(); i++)
                System.out.println((i + 1) + ". " + allCourses.get(i).getName());
        }
    }

    private static void handleEnrollment(Student student) {
        List<Course> allCourses = courseService.getCourses();
        if (allCourses.isEmpty()) {
            System.out.println("No courses available!");
            return;
        }
        showAllCourses();
        int choice = InputHelper.nextInt("Enter course number to enroll: ");
        if (choice < 1 || choice > allCourses.size()) {
            System.out.println("Invalid choice!");
            return;
        }
        courseService.enrollStudent(allCourses.get(choice - 1), student);
    }

    private static void handleAssignmentUpload(Student student) {
        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("You are not enrolled in any course!");
            return;
        }

        List<Course> enrolled = student.getEnrolledCourses();
        for (int i = 0; i < enrolled.size(); i++)
            System.out.println((i + 1) + ". " + enrolled.get(i).getName());

        int choice = InputHelper.nextInt("Select a course: ");
        if (choice < 1 || choice > enrolled.size()) {
            System.out.println("1Invalid choice!");
            return;
        }

        String text = InputHelper.nextLine("Enter assignment text: ");
        assignmentService.uploadAssignment(enrolled.get(choice - 1), student, text);
    }
}
