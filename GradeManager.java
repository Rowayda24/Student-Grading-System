import java.util.HashMap;
import java.util.Map;

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
}
