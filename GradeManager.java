import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;

public class GradeManager {
    private Map<Student, Map<Course, String>> grades = new HashMap<>();

    public void assignGrade(Student student, Course course, String grade) {
        grades.computeIfAbsent(student, k -> new HashMap<>()).put(course, grade);
    }

    public void viewGrades(Student student) {
        Map<Course, String> studentGrades = grades.get(student);
        if (studentGrades == null || studentGrades.isEmpty()) {
            System.out.println("No grades found for student: " + student.getStudentName());
        } else {
            System.out.println("Grades for " + student.getStudentName() + ":");
            for (Map.Entry<Course, String> entry : studentGrades.entrySet()) {
                System.out.println("Course: " + entry.getKey().getCourseName() + " | Grade: " + entry.getValue());
            }
        }
    }

    public void calculateGPA(Scanner scanner, Student student) {
        Map<Course, String> studentGrades = grades.get(student);
        if (studentGrades == null || studentGrades.isEmpty()) {
            System.out.println("No grades found for student: " + student.getStudentName());
            return;
        }

        double totalPoints = 0;
        int totalCredits = 0;

        for (Map.Entry<Course, String> entry : studentGrades.entrySet()) {
            Course course = entry.getKey();
            String grade = entry.getValue();
            double points = convertGradeToPoints(grade);
            totalPoints += points * course.getCredits();
            totalCredits += course.getCredits();
        }

        if (totalCredits == 0) {
            System.out.println("Error: total credit hours is zero.");
            return;
        }

        double gpa = totalPoints / totalCredits;
        System.out.printf("GPA for %s (%s): %.2f%n", student.getStudentName(), student.getStudentID(), gpa);
    }
    private double convertGradeToPoints(String grade) {
        switch (grade.toUpperCase()) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                System.out.println("Invalid grade: " + grade);
                return 0.0;
        }
    }
}
