import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        List<Student> students = new ArrayList<>(); 
        List<Course> courses = new ArrayList<>(); 
        GradeManager gradeManager = new GradeManager();
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            System.out.println("\nWelcome to the Student Management System");
            System.out.println("1. Add a new course");
            System.out.println("2. Add a new student");
            System.out.println("3. Show all students");
            System.out.println("4. Show all courses");
            System.out.println("5. Assign grade to student");
            System.out.println("6. View grades for a student");
            System.out.println("7. Calculate GPA for a student");
            System.out.println("8. Exit");
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
                    int credits = scanner.nextInt();
                    scanner.nextLine();
                    courses.add(new Course(courseName, courseID, credits));
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
                    if (students.isEmpty() || courses.isEmpty()) {
                        System.out.println("Add students and courses before assigning grades.");
                        break;
                    }

                    System.out.println("Enter Student ID:");
                    int sid = scanner.nextInt();
                    scanner.nextLine();

                    Student selectedStudent = null;
                    for (Student s : students) {
                        if (s.getStudentID() == sid) {
                            selectedStudent = s;
                            break;
                        }
                    }

                    if (selectedStudent == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.println("Enter Course ID:");
                    int cid = scanner.nextInt();
                    scanner.nextLine();

                    Course selectedCourse = null;
                    for (Course c : courses) {
                        if (c.getCourseID() == cid) {
                            selectedCourse = c;
                            break;
                        }
                    }

                    if (selectedCourse == null) {
                        System.out.println("Course not found.");
                        break;
                    }

                    System.out.println("Enter Grade (e.g., A, B+, etc.):");
                    String grade = scanner.nextLine();

                    gradeManager.assignGrade(selectedStudent, selectedCourse, grade);
                    System.out.println("Grade assigned.");
                    break;

                case 6:
                    System.out.println("Enter Student ID to view grades:");
                    int viewId = scanner.nextInt();
                    scanner.nextLine();

                    Student found = null;
                    for (Student s : students) {
                        if (s.getStudentID() == viewId) {
                            found = s;
                            break;
                        }
                    }

                    if (found == null) {
                        System.out.println("Student not found.");
                    } else {
                        gradeManager.viewGrades(found);
                    }
                    break;

                case 7:
                    System.out.println("Enter Student ID to calculate GPA:");
                    int gpaId = scanner.nextInt();
                    scanner.nextLine();

                    Student gpaStudent = null;
                    for (Student s : students) {
                        if (s.getStudentID() == gpaId) {
                            gpaStudent = s;
                            break;
                        }
                    }

                    if (gpaStudent == null) {
                        System.out.println("Student not found.");
                    } else {
                        gradeManager.calculateGPA(scanner, gpaStudent);
                    }
                    break;    
                    
                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
