/*
 * Project09.java
 * 
 *   A program that converts Roman numerals to decimal numbers.
 *   Used to practice breaking code up into methods. 
 * 
 * @author Alex Pickering
 * @version 20150325
 * 
 */
//package osu.cse1223;
import java.util.Scanner;

public class Project09 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int number = 0;
		String numeral = promptUserForNumeral(in);
		while (!(numeral.equals("Q")) && !(numeral.equals("q"))) {
			number = convertNumeralToNumber(numeral);
			System.out.println("The numeral " + numeral + " is the decimal number " + number);
			System.out.println();
			numeral = promptUserForNumeral(in);
		}
		System.out.println("Goodbye!");
	}

	/* 
	 * Given a Scanner as input, prompts the user to input a Roman numeral.  Checks to make
	 *  sure that the user does not enter an empty String.  If the user does enter an
	 *  empty String, report an error and ask for a new String until a non-empty String is
	 *  provided.  Return the String input by the user to the calling program.
	 *  
	 *  @param inScanner is a scanner used to obtain numeral from the user
	 */
	private static String promptUserForNumeral(Scanner inScanner) {
		String numeral = "";
		System.out.print("Enter a roman numeral (Q to quit): ");
		numeral = inScanner.nextLine();
		while (numeral.length() == 0) {
			System.out.println("ERROR! You must enter a non-empty line!");
			System.out.println();
			System.out.print("Enter a roman numeral (Q to quit): ");
			numeral = inScanner.nextLine();
		}
		return numeral;
	}
	/*
	 * Given a String as input, converts the String to a number assuming that the String
	 * is a Roman numeral.  Returns the number to the calling program.
	 * 
	 * @param numeral is the string input by the user
	 */
	private static int convertNumeralToNumber(String numeral) {
		int number = 0;
		int firstNumeral = 0, secondNumeral = 0;
		while (numeral.length() != 0) {
			if (numeral.length() > 1) {
				firstNumeral = convertCharacterToNumber(numeral.charAt(0));
				secondNumeral = convertCharacterToNumber(numeral.charAt(1));
			} else {
				firstNumeral = convertCharacterToNumber(numeral.charAt(0));
				secondNumeral = 0;
			}
			if (secondNumeral == 0) {
				number = number + firstNumeral;
				numeral = "";
			} else if (firstNumeral < secondNumeral) {
				number = number + secondNumeral - firstNumeral;
				numeral = numeral.substring(2, numeral.length()); 
			} else if (firstNumeral > secondNumeral) {
				number = number + firstNumeral;
				numeral = numeral.substring(1, numeral.length());
			} else if (firstNumeral == secondNumeral) {
				number = number + firstNumeral + secondNumeral;
				numeral = numeral.substring(2, numeral.length());
			}
		}
		return number;
	}
	
	/*
	 * Given a character that contains a single numeral, returns the integer value for
	 * that character.
	 * 
	 * @param numeral here is an individual character from the users input
	 */
	private static int convertCharacterToNumber(char numeral) {
		int number = 0;
		if (numeral == 'I') {number = 1;}
		if (numeral == 'V') {number = 5;}
		if (numeral == 'X') {number = 10;}
		if (numeral == 'L') {number = 50;}
		if (numeral == 'C') {number = 100;}
		if (numeral == 'D') {number = 500;}
		if (numeral == 'M') {number = 1000;}
		return number;
	}
	
	

	
}