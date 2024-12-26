import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeApplication {

    static int numberOfStudents;
    static int numberOfSubjects;
    static String[] nameOfStudents;
    static double[][] scores;
    static double[] averageScores;
    static double[] totalScores;
    static double overallHighest;
    static double overallLowest;
    static int[] hardestSubject;
    static int[] easiestSubject;
    static double[] averageSubjectScore = new double[numberOfSubjects];

    public static void main(String[] args) {
        dataAndTableFunction();

    }

    public static void dataAndTableFunction() {
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;

        while (continueInput) {

            try {
                System.out.print("Enter the number of students: ");
                numberOfStudents = input.nextInt();

                System.out.print("Enter the number of subjects they offer: ");
                numberOfSubjects = input.nextInt();

                System.out.println("Saving >>>>>>>>>>>>>>>>>>>>>>");
                System.out.println("Saved Successfully.");
                System.out.println();

                nameOfStudents = new String[numberOfStudents];
                scores = new double[numberOfStudents][numberOfSubjects];
                totalScores = new double[numberOfStudents];
                averageScores = new double[numberOfStudents];
                int[] positions = new int[numberOfStudents];


                for (int count = 0; count < numberOfStudents; count++) {
                    System.out.print("Enter the name of student " + (count + 1) + ": ");
                    nameOfStudents[count] = input.next();
                    for (int counter = 0; counter < numberOfSubjects; counter++) {
                        System.out.println("Enter the scores for " + nameOfStudents[count] + " below");
                        System.out.print("Enter score for subject " + (counter + 1) + ": ");
                        scores[count][counter] = input.nextDouble();
                        System.out.println("Saving >>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println("Saved Successfully.");
                        System.out.println();

                        while (scores[count][counter] < 1 || scores[count][counter] > 100) {
                            System.out.println("Invalid score. Enter a score between 1 and 100: ");
                            scores[count][counter] = input.nextDouble();
                        }

                        totalScores[count] += scores[count][counter];
                    }

                    averageScores[count] = totalScores[count] / numberOfSubjects;
                }


                for (int count = 0; count < numberOfStudents; count++) {
                    positions[count] = 1;
                    for (int counter = 0; counter < numberOfStudents; counter++) {
                        if (averageScores[count] < averageScores[counter]) {
                            positions[count]++;
                        }
                    }
                }


                System.out.println("\n--- STUDENTS SCORES TABLE ---");
                System.out.println("=========================================================================");
                System.out.print("STUDENTS\t");


                for (int count = 0; count < numberOfSubjects; count++) {
                    System.out.print("Sub" + (count + 1) + "\t");
                }
                System.out.println("TOTAL\tAVE\t    POS");
                System.out.println("=========================================================================");


                for (int dataCount = 0; dataCount < numberOfStudents; dataCount++) {
                    System.out.printf("%-10s\t", nameOfStudents[dataCount]);

                    for (int subScoreCount = 0; subScoreCount < numberOfSubjects; subScoreCount++) {
                        System.out.printf("%.2f\t", scores[dataCount][subScoreCount]);
                    }

                    System.out.printf("%.2f\t%.2f\t%d\n", totalScores[dataCount], averageScores[dataCount], positions[dataCount]);
                }

                System.out.println("=========================================================================");

                continueInput = false;

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input type. Please enter integers for student and subject counts, and numbers for scores.");
                input.nextLine();
            }

        }
        classSummary(totalScores, averageScores, nameOfStudents);
        subjectSummary(scores);
    }

    public static void subjectSummary(double[][] studentSubjects) {

        int benchMarkForPass = 40;

        System.out.println("\nSUBJECT SUMMARY");

        for (int subjectIndex = 0; subjectIndex < numberOfSubjects; subjectIndex++) {
            System.out.println("\nSubject " + (subjectIndex + 1) + ":");

            double totalScore = 0;
            int numberOfPasses = 0;
            int numberOfFails = 0;

            int highestScoringStudent = 0;
            double highestStudentScore = Double.MIN_VALUE;
            int lowestScoringStudent = 0;
            double lowestStudentScore = Double.MAX_VALUE;
            for (int studentIndex = 0; studentIndex < numberOfStudents; studentIndex++) {
                double subjectScore = studentSubjects[studentIndex][subjectIndex];
                totalScore += subjectScore;

                if (subjectScore >= benchMarkForPass) {
                    numberOfPasses++;
                } else {
                    numberOfFails++;
                }

                if (subjectScore > highestStudentScore) {
                    highestStudentScore = subjectScore;
                    highestScoringStudent = studentIndex;
                }

                if (subjectScore < lowestStudentScore) {
                    lowestStudentScore = subjectScore;
                    lowestScoringStudent = studentIndex;
                }
            }

            double averageScore = totalScore / studentSubjects.length;

            System.out.println("Highest scoring student is student " + (highestScoringStudent + 1) + " scoring " + highestStudentScore);
            System.out.println("Lowest scoring student is student " + (lowestScoringStudent + 1) + " scoring " + lowestStudentScore);
            System.out.println("Total score is " + totalScore);
            System.out.println("Average score is " + averageScore);
            System.out.println("Number of passes: " + numberOfPasses);
            System.out.println("Number of fails: " + numberOfFails);

        }
    }


    public static void classSummary(double[] totalScores, double[] averages, String[] names) {

        System.out.println("\nCLASS SUMMARY");

        double highest = totalScores[0];
        String highestStudent = names[0];

        double lowest = totalScores[0];
        String lowestStudent = names[0];

        double classTotalScore = 0;

        for (int count = 0; count < totalScores.length; count++) {
            classTotalScore += totalScores[count];

            if (totalScores[count] > highest) {
                highest = totalScores[count];
                highestStudent = names[count];
            }

            if (totalScores[count] < lowest) {
                lowest = totalScores[count];
                lowestStudent = names[count];
            }
        }

        double classAverageScore = classTotalScore / totalScores.length;

        System.out.println("=================================================================");
        System.out.println("\nBest Graduating Student is: " + highestStudent + " scoring " + highest);
        System.out.println("=================================================================");

        System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("\nWorst Graduating Student is: " + lowestStudent + " scoring " + lowest);
        System.out.print("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        System.out.println("=================================================================");
        System.out.println("\nClass total score is: " + classTotalScore);
        System.out.println("\nClass Average score is: " + classAverageScore);
    }
}
