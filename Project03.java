/*
 * Project03.java
 * 	This program prompts users for their name, welcomes them, asks them 4 math questions with randomized variables,
 * 	reports whether their answers are correct or wrong, and at the end outputs the user's score in a percentage.
 * 
 * @author Alex Pickering
 * @version 20150201
 */

import java.util.Scanner;

public class Project03 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your name: "); // Intro and Welcoming
		String name = in.nextLine();
		System.out.println("Welcome " + name + "!  Please answer the following questions: ");
		System.out.println();
		int x = (int) (20 * Math.random()) + 1; // randomization for variables
		int y = (int) (20 * Math.random()) + 1;
		int correctAnswers = 0;
		int sum = x + y; // Creation of the answer key
		int product = x * y;
		int quotient = x / y;
		int remainder = x % y;
		System.out.print(x + " + " + y + " = "); // First Question
		int userSum = in.nextInt();
		if (userSum == sum) {
			System.out.println("Correct!");
			correctAnswers = correctAnswers + 1;
		} else {
			System.out.println("Wrong!");
			System.out.println("The correct answer is " + sum);
		}
		System.out.println();
		System.out.print(x + " * " + y + " = "); // Second Question
		int userProduct = in.nextInt();
		if (userProduct == product) {
			System.out.println("Correct!");
			correctAnswers = correctAnswers + 1;
		} else {
			System.out.println("Wrong!");
			System.out.println("The correct answer is " + product);
		}
		System.out.println();
		System.out.print(x + " / " + y + " = "); // Third Question
		int userQuotient = in.nextInt();
		if (userQuotient == quotient) {
			System.out.println("Correct!");
			correctAnswers = correctAnswers + 1;
		} else {
			System.out.println("Wrong!");
			System.out.println("The correct answer is " + quotient);
		}
		System.out.println();
		System.out.print(x + " % " + y + " = "); // Fourth Question
		int userRemainder = in.nextInt();
		if (userRemainder == remainder) {
			System.out.println("Correct!");
			correctAnswers = correctAnswers + 1;
		} else {
			System.out.println("Wrong!");
			System.out.println("The correct answer is " + remainder);
		}
		System.out.println();
		System.out.println("You got " + correctAnswers + " correct answers");
		double totalQuestions = 4.0; // Percentages
		double percentageCorrect = (correctAnswers / totalQuestions) * 100;
		System.out.println("That's " + percentageCorrect + "%!");
	}
}