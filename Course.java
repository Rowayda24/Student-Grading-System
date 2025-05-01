import java.io.*;


public class Course {
    String CourseName;
    int CourseID;
    int credits;

    public Course(String courseName, int courseID) {
        this.CourseName = courseName;
        this.CourseID = courseID;   
    }

    public Course(String courseName, int courseID, int credits) throws IOException {
        this.CourseName = courseName;
        this.CourseID = courseID;
        this.credits = credits;
        PrintWriter pw = new PrintWriter(new FileWriter("Courses.txt", true));
        pw.println("Course Name: " + courseName); 
        pw.println("Course ID: " + courseID); 
        pw.println("Credits: " + credits); 
        pw.println(" "); 
        pw.close();
    }

    

    @Override
    public String toString() {
        return "CourseID: " + CourseID + "\nCourseName: " + CourseName + "\nCredits: " + credits;
    }
     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course)) return false;
        Course other = (Course) obj;
        return this.courseID == other.courseID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID);
    }
}
