/*
 * Project01.java
 * 	My first Java program made from scratch
 * 	Used to find the output below
 * 
 * @author Alex Pickering
 * @version 20150123
 * 
 */ 
import java.util.Scanner;
public class Project01 {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the first number: ");
		int firstNumber = keyboard.nextInt();
		System.out.print("Enter the second number: ");
		int secondNumber = keyboard.nextInt();
		int sum = firstNumber + secondNumber;
		System.out.println(firstNumber + " + " + secondNumber + " = " + sum);
		int difference = firstNumber - secondNumber;
		System.out.println(firstNumber + " - " + secondNumber + " = " + difference);
		int product = firstNumber * secondNumber;
		System.out.println(firstNumber + " * " + secondNumber + " = " + product);
		int quotient = firstNumber / secondNumber;
		System.out.println(firstNumber + " / " + secondNumber + " = " + quotient);
		int remainder = firstNumber % secondNumber;
		System.out.println(firstNumber + " % " + secondNumber + " = " + remainder);
		int total = firstNumber + secondNumber;
		int average = total / 2;
		System.out.println("The average of your two numbers is: " + average);
		
	}

}
