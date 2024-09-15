import java.util.Scanner;

public class calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        // Ask the user how many subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        
        int totalMarks = 0;
        int marks;
        String[] subjects = new String[numSubjects];
        
        // Input subject names and marks
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter the name of subject " + (i + 1) + ": ");
            subjects[i] = scanner.nextLine();
            
            System.out.print("Enter marks obtained in " + subjects[i] + " (out of 100): ");
            marks = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
            
            totalMarks += marks;
        }
        
        // Calculate total marks and average percentage
        double averagePercentage = totalMarks / (double) numSubjects;
        
        // Determine the grade
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B+";
        } else if (averagePercentage >= 60) {
            grade = "B";
        } else if (averagePercentage >= 50) {
            grade = "C";
        } else {
            grade = "F";
        }

        // Display results
        System.out.println("\nResults:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.println(subjects[i] + ": " + "Marks entered.");
        }
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
