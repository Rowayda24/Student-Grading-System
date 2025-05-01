import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Student {
    private int studentID;
    private String studentName;
    private List<Course> enrolledCourses = new ArrayList<>();

    public Student() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student Name:");
        this.studentName = scanner.nextLine();
        System.out.println("Enter Student ID:");
        this.studentID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("How many courses is the student enrolling in?");
        int numberOfCourses = scanner.nextInt();
        scanner.nextLine();

        PrintWriter pw = new PrintWriter(new FileWriter("EnrolledCourses.txt", true));
        pw.println("Enrolled Courses for " + studentName);

        for (int i = 0; i < numberOfCourses; i++) {
            System.out.println("Enter the name of course " + (i + 1) + ":");
            String courseName = scanner.nextLine();
            System.out.println("Enter the ID of course " + (i + 1) + ":");
            int courseID = scanner.nextInt();
            scanner.nextLine();

            pw.println("Course Name: " + courseName + ", Course ID: " + courseID);
            Course course = new Course(courseName, courseID, 0); // Assuming 0 credits if not provided
            enrolledCourses.add(course);
        }

        pw.println();
        pw.close();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    @Override
    public String toString() {
        return "StudentID: " + studentID + "\nName: " + studentName + "\nEnrolled Courses: " + enrolledCourses;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return this.studentID == other.studentID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID);
    }
}
