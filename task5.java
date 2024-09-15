import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Class to represent an individual student
class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    
    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }
    
    public String getName() {
        return name;
    }
    
    public int getRollNumber() {
        return rollNumber;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

// Class to manage the collection of students
class StudentManagementSystem {
    private ArrayList<Student> students;
    private final String FILE_PATH = "students.dat";  // File to store student data

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        loadStudentsFromFile();  // Load existing students from file at startup
    }
    
    // Method to add a new student
    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile();
        System.out.println("Student added successfully!");
    }

    // Method to remove a student by roll number
    public void removeStudent(int rollNumber) {
        boolean found = false;
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                students.remove(student);
                saveStudentsToFile();
                System.out.println("Student removed successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
        }
    }
    
    // Method to search for a student by roll number
    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;  // Return null if student not found
    }
    
    // Method to display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("All Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Method to save student data to file
    private void saveStudentsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving student data to file.");
        }
    }

    // Method to load student data from file
    private void loadStudentsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing student data found.");
        }
    }
}

// Main class with user interface
public class StudentManagementApp {
    private static StudentManagementSystem sms = new StudentManagementSystem();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    // Method to add a student
    private static void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();
        
        if (!name.isEmpty() && rollNumber > 0 && !grade.isEmpty()) {
            Student student = new Student(name, rollNumber, grade);
            sms.addStudent(student);
        } else {
            System.out.println("All fields are required and Roll Number must be greater than 0.");
        }
    }
    
    // Method to remove a student
    private static void removeStudent() {
        System.out.print("Enter Roll Number of student to remove: ");
        int rollNumber = scanner.nextInt();
        sms.removeStudent(rollNumber);
    }

    // Method to search for a student
    private static void searchStudent() {
        System.out.print("Enter Roll Number of student to search: ");
        int rollNumber = scanner.nextInt();
        
        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student Found: " + student);
        } else {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
        }
    }
}
