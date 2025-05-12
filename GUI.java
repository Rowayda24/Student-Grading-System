import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUI extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JButton addCourseButton, addStudentButton, showStudentsButton, showCoursesButton, assignGradeButton, viewGradesButton, exitButton;

    // Data structures to store courses, students, and grades
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private Map<Student, Map<Course, String>> grades = new HashMap<>();

    public GUI() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1));

        addCourseButton = new JButton("Add Course");
        addStudentButton = new JButton("Add Student");
        showStudentsButton = new JButton("Show Students");
        showCoursesButton = new JButton("Show Courses");
        assignGradeButton = new JButton("Assign Grade");
        viewGradesButton = new JButton("View Grades");
        exitButton = new JButton("Exit");

        buttonPanel.add(addCourseButton);
        buttonPanel.add(addStudentButton);
        buttonPanel.add(showStudentsButton);
        buttonPanel.add(showCoursesButton);
        buttonPanel.add(assignGradeButton);
        buttonPanel.add(viewGradesButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.WEST);

        addCourseButton.addActionListener(this);
        addStudentButton.addActionListener(this);
        showStudentsButton.addActionListener(this);
        showCoursesButton.addActionListener(this);
        assignGradeButton.addActionListener(this);
        viewGradesButton.addActionListener(this);
        exitButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            String courseName = JOptionPane.showInputDialog(this, "Enter course name:");
            int courseID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter course ID:"));
            int credits = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter course credits:"));
            if (courseName != null && !courseName.trim().isEmpty()) {
                Course course = new Course(courseName.trim(), courseID, credits);
                courses.add(course);
                textArea.append("Course added: " + course + "\n");
            } else {
                textArea.append("Invalid course details.\n");
            }
        } else if (e.getSource() == addStudentButton) {
            String studentName = JOptionPane.showInputDialog(this, "Enter student name:");
            int studentID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter student ID:"));
            if (studentName != null && !studentName.trim().isEmpty()) {
                Student student = new Student(studentName.trim(), studentID);
                students.add(student);
                grades.put(student, new HashMap<>()); // Initialize the grades map for the student

                // Prompt the user to select courses for enrollment
                if (!courses.isEmpty()) {
                    JList<Course> courseList = new JList<>(courses.toArray(new Course[0]));
                    courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    int result = JOptionPane.showConfirmDialog(this, new JScrollPane(courseList), "Select Courses to Enroll",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        for (Course selectedCourse : courseList.getSelectedValuesList()) {
                            grades.get(student).put(selectedCourse, null); // Enroll the student in the course with no grade initially
                        }
                        textArea.append("Student added and enrolled in selected courses: " + student + "\n");
                    } else {
                        textArea.append("Student added without enrolling in any courses: " + student + "\n");
                    }
                } else {
                    textArea.append("No courses available for enrollment. Student added: " + student + "\n");
                }
            } else {
                textArea.append("Invalid student details.\n");
            }
        } else if (e.getSource() == showStudentsButton) {
            textArea.append("Students:\n");
            for (Student student : students) {
                textArea.append("- " + student + "\n");
            }
        } else if (e.getSource() == showCoursesButton) {
            textArea.append("Courses:\n");
            for (Course course : courses) {
                textArea.append("- " + course + "\n");
            }
        } else if (e.getSource() == assignGradeButton) {
            if (students.isEmpty() || courses.isEmpty()) {
                textArea.append("Add students and courses before assigning grades.\n");
                return;
            }
            Student student = (Student) JOptionPane.showInputDialog(this, "Select student:", "Assign Grade",
                    JOptionPane.QUESTION_MESSAGE, null, students.toArray(), students.get(0));
            Course course = (Course) JOptionPane.showInputDialog(this, "Select course:", "Assign Grade",
                    JOptionPane.QUESTION_MESSAGE, null, courses.toArray(), courses.get(0));
            String grade = JOptionPane.showInputDialog(this, "Enter grade:");
            if (student != null && course != null && grade != null && !grade.trim().isEmpty()) {
                grades.get(student).put(course, grade.trim());
                textArea.append("Grade assigned: " + student + " - " + course + " - " + grade + "\n");
            } else {
                textArea.append("Invalid grade assignment.\n");
            }
        } else if (e.getSource() == viewGradesButton) {
            textArea.append("Grades:\n");
            for (Student student : grades.keySet()) {
                textArea.append(student + ":\n");
                for (Course course : grades.get(student).keySet()) {
                    textArea.append("  " + course + ": " + grades.get(student).get(course) + "\n");
                }
            }
        } else if (e.getSource() == exitButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
