package keerthana;
import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("enter the number of subjects ");
        int numberOfSubjects = scanner.nextInt();

        int totalMarks = 0;
        int maxMarksPerSubject = 100;

        for (int i = 1; i <= numberOfSubjects; i++) {
            int marks = -1;
            while (marks < 0 || marks > maxMarksPerSubject) {
                System.out.print("Please enter the marks you got in subject " + i + " (out of " + maxMarksPerSubject + "): ");
                marks = scanner.nextInt();
                if (marks < 0 || marks > maxMarksPerSubject) {
                    System.out.println(" That’s not valid. Please enter the marks between 0 and " + maxMarksPerSubject + ".");
                }
            }
            totalMarks += marks;
        }

        double averagePercentage = (double) totalMarks / numberOfSubjects;

        String grade;
        if (averagePercentage >= 89) {
            grade = "A";
        } else if (averagePercentage >= 79) {
            grade = "B";
        } else if (averagePercentage >= 69) {
            grade = "C";
        } else if (averagePercentage >= 59) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("\nHere are your results:");
        System.out.println("Total Marks: " + totalMarks + " out of " + (numberOfSubjects * maxMarksPerSubject));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Your Grade: " + grade);

        scanner.close();
    }
}
