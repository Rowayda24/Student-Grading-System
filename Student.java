import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Student {
    int StudentID;
    String StudentName;
    List<Course> enrolledCourses = new ArrayList<>();

    public Student() throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student Name:");
        this.StudentName = scanner.nextLine();
        System.out.println("Enter Student ID:");
        this.StudentID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("How many courses is the student enrolling in?");
        int numberOfCourses = scanner.nextInt();
        scanner.nextLine(); 
        PrintWriter pw = new PrintWriter(new FileWriter("EnrolledCourses.txt", true));
        pw.println("Enrolled Courses for "+StudentName); 

        for (int i = 0; i < numberOfCourses; i++) {
            System.out.println("Enter the name of course " + (i + 1) + ":");
            String courseName = scanner.nextLine();
            System.out.println("Enter the ID of course " + (i + 1) + ":");
            int courseID = scanner.nextInt();
            pw.print("Course Name: "+courseName); 
            pw.println(" ,Course ID: "+courseID); 
            scanner.nextLine();

            Course course = new Course(courseName, courseID); 
            enrolledCourses.add(course); 
        }
        pw.println(" ");
        pw.close();

    }

    @Override
    public String toString() {
        return "StudentID: " + StudentID + "\nName: " + StudentName + "\nEnrolled Courses: " + enrolledCourses;
    }
}
