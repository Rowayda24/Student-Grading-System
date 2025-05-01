import java.util.HashMap;
import java.util.Map;

public class GradeManager {
    private Map<Student, Map<Course, String>> grades = new HashMap<>();

    public void assignGrade(Student student, Course course, String grade) {
        if (!student.enrolledCourses.contains(course)) {
            System.out.println("Error: Student is not enrolled in this course.");
            return;
        } ////prevents errors like assigning grades to students not enrolled in a course.


        grades.putIfAbsent(student, new HashMap<>());
        grades.get(student).put(course, grade);
        System.out.println("Grade assigned successfully.");
    }

    public String getGrade(Student student, Course course) {
        if (grades.containsKey(student) && grades.get(student).containsKey(course)) {
            return grades.get(student).get(course);
        } else {
            return "No grade assigned.";
        }
    }

    public void printStudentGrades(Student student) {
        if (!grades.containsKey(student)) {
            System.out.println("No grades found for " + student.StudentName);
            return;
        }
        System.out.println("Grades for " + student.StudentName + ":");
        for (Map.Entry<Course, String> entry : grades.get(student).entrySet()) {
            System.out.println(entry.getKey().CourseName + ": " + entry.getValue());
        }
    }
}
