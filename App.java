import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        List<Student> students = new ArrayList<>(); 
        List<Course> courses = new ArrayList<>();  
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Student Management System");
            System.out.println("1. Add a new course");
            System.out.println("2. Add a new student");
            System.out.println("3. Show all students");
            System.out.println("4. Show all courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Enter Course Name:");
                    String courseName = scanner.nextLine();
                    System.out.println("Enter Course ID:");
                    int courseID = scanner.nextInt();
                    System.out.println("Enter Course Credits:");
                    int Credits = scanner.nextInt();
                    scanner.nextLine(); 
                    courses.add(new Course(courseName, courseID,Credits));
                    System.out.println("Course added successfully!");
                    break;

                case 2:
                    Student student = new Student();
                    students.add(student); 
                    System.out.println("Student added successfully!");
                    break;

                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("List of all students:");
                        for (Student s : students) {
                            System.out.println(s); 
                        }
                    }
                    break;

                case 4:
                    if (courses.isEmpty()) {
                        System.out.println("No courses found.");
                    } else {
                        System.out.println("List of all courses:");
                        for (Course c : courses) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


