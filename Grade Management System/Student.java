import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String studentId;
    private Map<String, Double> grades; 

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    public void addGrade(String subject, double grade) {
        grades.put(subject, grade);
    }

    public double calculateAverage() {
        double total = 0;
        for (double grade : grades.values()) {
            total += grade;
        }
        return grades.size() > 0 ? total / grades.size() : 0;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + studentId + ", Average Grade: " + calculateAverage();
    }
}
