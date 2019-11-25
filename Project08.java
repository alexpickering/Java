/*
 * Project08.java
 * 
 *   A program that converts decimal numbers to Roman numerals.
 *   Used to practice breaking code up into methods. 
 * 
 * @author Alex Pickering
 * @version 20150311
 * 
 */
//package osu.cse1223;
import java.util.Scanner;

public class Project08 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int number = -1;
		String numeral = "";
		while (number != 0) {
			number = promptUserForNumber(in);
			numeral = convertNumberToNumeral(number);
			if (number != 0) {
				System.out.println("The number " + number + " is the Roman numeral " + numeral);
				System.out.println();
			}
		}
		System.out.println("Goodbye!");
	}

	/*
	 * Inputs a Scanner, and using branching statements, prompts the user for a number
	 * between 1 and 3999, printing an error if the user picks an invalid number, and 
	 * looping the prompt until they select a valid number.
	 * 
	 * @param inScanner is just a Scanner
	 */
	private static int promptUserForNumber(Scanner inScanner) {
		System.out.print("Enter a number between 1 and 3999 (0 to quit): ");
		int num = inScanner.nextInt();
		while (num > 3999 || num < 0) {
			System.out.println("ERROR! Number must be between 1 and 3999.");
			System.out.println();
			System.out.print("Enter a number between 1 and 3999 (0 to quit): ");
			num = inScanner.nextInt();
		}
		return num;
	}
	
	/*
	 * Inputs a number, filters out each of the numbers individual digits, converts them into 
	 * individual sections of Roman numerals (using convertDigitToNumeral), and finally outputs
	 * the Roman numeral equivalent to the number input.
	 * 
	 * @param number is any number between 1 and 3999 which will be converted into a Roman numeral.
	 */
	private static String convertNumberToNumeral(int number) {
		int onesDigit = 0, tensDigit = 0, hundredsDigit = 0, thousandsDigit = 0;
		String onesNumeral = "", tensNumeral = "", hundredsNumeral = "", thousandsNumeral = "";
		if (number > 999) {thousandsDigit = ((number %10000) / 1000);
		thousandsNumeral = convertDigitToNumeral(thousandsDigit, 'M', 'E', 'E'); }
		if (number > 99) {hundredsDigit = ((number % 1000) / 100);
		hundredsNumeral = convertDigitToNumeral(hundredsDigit, 'C', 'D', 'M');}
		if (number > 9) {tensDigit = ((number % 100) / 10);
		tensNumeral = convertDigitToNumeral(tensDigit, 'X', 'L', 'C');}
		onesDigit = number % 10;
		onesNumeral = convertDigitToNumeral(onesDigit, 'I', 'V', 'X');
		return thousandsNumeral + hundredsNumeral + tensNumeral + onesNumeral;
	}
	
	/*
	 * Inputs a digit and characters for relevant 'one', 'five', and 'ten' Roman numerals for
	 * that digit, and outputs the correct Roman numeral.
	 * 
	 * @param digit is the digit input
	 * @param one is the 'one' Roman numeral which is correlated to the position of the digit. (i.e. I, X, C, M)
	 * @param five is the 'five' Roman numeral which is correlated to the position of the digit. (i.e. V, L, D,)
	 * @param ten is the 'ten' Roman numeral which is correlated to the position of the digit. (i.e. X, C, M)
	 */
	private static String convertDigitToNumeral(int digit, char one, char five, char ten) {
		String numeral = "";
		if (digit == 1) {numeral += one;}
		if (digit == 2) {numeral += one; numeral += one;}
		if (digit == 3) {numeral += one; numeral += one; numeral += one;}
		if (digit == 4) {numeral += one; numeral += five;}
		if (digit == 5) {numeral += five;}
		if (digit == 6) {numeral += five; numeral += one;}
		if (digit == 7) {numeral += five; numeral += one; numeral += one;}
		if (digit == 8) {numeral += five; numeral += one; numeral += one; numeral += one;}
		if (digit == 9) {numeral += one; numeral += ten;}
		return numeral;
	}
	
}