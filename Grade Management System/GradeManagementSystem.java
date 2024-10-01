import java.io.*;
import java.util.*;

public class GradeManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.ser";

    public GradeManagementSystem() {
        students = new ArrayList<>();
        loadStudents();
    }

    public void addStudent(String name, String studentId) {
        students.add(new Student(name, studentId));
        System.out.println("Student added successfully!");
    }

    public void addGrade(String studentId, String subject, double grade) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.addGrade(subject, grade);
                System.out.println("Grade added successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public void generateReport() {
        for (Student student : students) {
            System.out.println(student);
            for (Map.Entry<String, Double> entry : student.getGrades().entrySet()) {
                System.out.println("Subject: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
            System.out.println("---------------");
        }
    }

    public void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            System.out.println("Students saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                students = (List<Student>) obj;
            } else {
                System.out.println("Data format error: The file does not contain a List of Students.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    
    

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----- Student Grade Management System -----");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Generate Report");
            System.out.println("4. Save Data");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    addStudent(name, studentId);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter subject: ");
                    String subject = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    try {
                        double grade = Double.parseDouble(scanner.nextLine());
                        addGrade(id, subject, grade);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid grade input! Please enter a valid number.");
                    }
                    break;
                case 3:
                    generateReport();
                    break;
                case 4:
                    saveStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        GradeManagementSystem gms = new GradeManagementSystem();
        gms.startMenu();
    }
}
