import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("===== Student Grade Tracker =====");

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // Input student data
        for (int i = 1; i <= n; i++) {
            System.out.println("\nStudent " + i);

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter grade: ");
            double grade = sc.nextDouble();
            sc.nextLine();

            students.add(new Student(name, grade));
        }

        // Calculate statistics
        double total = 0;
        double highest = students.get(0).grade;
        double lowest = students.get(0).grade;

        for (Student s : students) {
            total += s.grade;

            if (s.grade > highest)
                highest = s.grade;

            if (s.grade < lowest)
                lowest = s.grade;
        }

        double average = total / students.size();

        // Display Report
        System.out.println("\n========== SUMMARY REPORT ==========");
        System.out.printf("%-20s %-10s\n", "Student Name", "Grade");
        System.out.println("--------------------------------------");

        for (Student s : students) {
            System.out.printf("%-20s %-10.2f\n", s.name, s.grade);
        }

        System.out.println("--------------------------------------");
        System.out.printf("Average Grade : %.2f\n", average);
        System.out.printf("Highest Grade : %.2f\n", highest);
        System.out.printf("Lowest Grade  : %.2f\n", lowest);

        sc.close();
    }
}