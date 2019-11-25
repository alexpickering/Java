/*
 * Project06.java
 * 	This program uses a procedure to check the validity of UPC numbers. This procedure
 * 	verifies that the UPC codes input are 12 digits (or prints an error message), and 
 * 	prints the validity status of the UPC code by comparing its ideal check digit to 
 * 	its actual check digit. Finally, the procedure ends its loop when the user enters 
 * 	an empty line.
 * 
 * @author Alex Pickering
 * @version 20150225
 */
import java.util.Scanner;

public class Project06 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a UPC (enter a blank line to quit): ");
		String UPC = in.nextLine();
		checkUPC(UPC);
	}

	/*
	 * Calculates ideal UPC check digit and checks it against actual UPC check
	 * digit. Also verifies that code is 12 digits, and prints validity status.
	 * 
	 * @param UPC a string of numbers
	 */
	public static void checkUPC(String UPC) {
		Scanner in = new Scanner(System.in);
		while (UPC.length() != 0) {                      //loop ends when empty line is entered, terminating the program
			int evenNumPos = 0, oddNumPos = 0, idealCheckDigit = 0;
			if (UPC.length() != 12) {
				System.out.println("ERROR! UPC MUST have exactly 12 digits");
			} else {
				int i = 0;
				int actualCheckDigit = Character.getNumericValue(UPC.charAt(UPC
						.length() - 1));
				while (i < 11) {
					if (i % 2 == 0) {
						evenNumPos += Character.getNumericValue(UPC.charAt(i));
					} else if (i % 2 == 1) {
						oddNumPos += Character.getNumericValue(UPC.charAt(i));
					}
					i++;
				}
				idealCheckDigit = (((evenNumPos * 3) + oddNumPos) % 10);   //calculation of ideal check digit
				if (idealCheckDigit == 0) {
				} else {
					idealCheckDigit = 10 - idealCheckDigit;
				}
				System.out.println("Check digit should be: " + idealCheckDigit);
				System.out.println("Check digit is: " + actualCheckDigit);
				if (idealCheckDigit == actualCheckDigit) {          // examining validity of UPC
					System.out.println("UPC is valid");
				} else {
					System.out.println("UPC is not valid");
				}
			}
			System.out.println();
			System.out.print("Enter a UPC (enter a blank line to quit): ");
			UPC = in.nextLine();
		}
		System.out.println("Goodbye!");
	}
}
