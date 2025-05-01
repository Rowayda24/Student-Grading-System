import java.util.Objects;

public class Course {
    private String courseName;
    private int courseID;
    private int credits;

    public Course(String courseName, int courseID, int credits) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.credits = credits;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Name: " + courseName + ", Credits: " + credits;
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
