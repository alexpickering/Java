/*
 * Project07.java
 * 
 *   A program that plays the dice game high/low
 *   Used to practice breaking code up into methods. 
 * 
 * @author Alex Pickering
 * @version 20150304
 * 
 */
//package osu.cse1223;             //program wouldn't work with this package
import java.util.Scanner;

public class Project07 {


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int dollars = 100;
		int bet = 1;
		while (dollars > 0 && bet != 0) {
			System.out.println("You have " + dollars + " dollars.");
			bet = getBet(in, dollars);
			if (bet == 0) {
				System.out.println();
			} else {
				char highLow = getHighLow(in);
				int firstRoll = getRoll(); 
				int secondRoll = getRoll();
				int totalRoll = firstRoll + secondRoll;
				System.out.println("Die 1 rolls: " + firstRoll);
				System.out.println("Die 2 rolls: " + secondRoll);
				System.out.println("Total of two dice is: " + totalRoll);
				int winnings = determineWinnings(highLow, bet, totalRoll);
				dollars += winnings;
				if (winnings > 0) {
					System.out.println("You won " + winnings + " dollars!");
				} else if (winnings < 0) {
					System.out.println("You lost!");
				}
				System.out.println();
			}
		}
		if (dollars < 0) {
			dollars = 0;
		}
		System.out.println("You have " + dollars + " dollars left");
		System.out.println("Goodbye!");
	}
	
	
	// Given a Scanner and a current maximum amount of money, prompt the user for
	// an integer representing the number of dollars that they want to bet.  This
	// number must be between 0 and to maximum number of dollars.  If the user enters
	// a number that is out of bounds, display an error message and ask again.
	// Return the bet to the calling program.
	private static int getBet(Scanner inScanner, int currentPool) {
		int bet = -1;
		while (bet > currentPool || bet < 0) {
			System.out.print("Enter an amount to bet (0 to quit): ");
			bet = inScanner.nextInt();
			if (bet > currentPool || bet < 0) { 
				System.out.println("Your bet MUST be between 0 and " + currentPool + " dollars");
			} 
		}
		return bet;
	}
	
	// Given a Scanner, prompt the user for a single character indicating whether they
	// would like to bet High ('H'), Low ('L') or Sevens ('S').  Your code should accept
	// either capital or lowercase answers, but should display an error if the user attempts
	// to enter anything but one of these 3 values and prompt for a valid answer.
	// Return the character to the calling program.
	private static char getHighLow(Scanner inScanner) {
		String options = "HhLlSs";
		inScanner.nextLine();
		String choice = "  ";
		while (options.indexOf(choice) == -1 || choice.length() > 1) {
			System.out.print("High, low, or sevens (H/L/S): ");
			
			choice = inScanner.nextLine();
			if (options.indexOf(choice) == -1) {
				System.out.println("You MUST select one of the three options.");
			} else if (choice.length() > 1) {
				System.out.println("You can only select ONE of the options.");
			}
		}	
		return choice.charAt(0);
	}
	
	// Produce a random roll of a single six-sided die and return that value to the calling
	// program
	private static int getRoll() {
		int roll = (int) (6 * Math.random()) + 1;
		return roll;
	}
	
	// Given the choice of high, low or sevens, the player's bet and the total result of
	// the roll of the dice, determine how much the player has won.  If the player loses
	// the bet then winnings should be negative.  If the player wins, the winnings should
	// be equal to the bet if the choice is High or Low and 4 times the bet if the choice
	// was Sevens.  Return the winnings to the calling program.
	private static int determineWinnings(char highLow, int bet, int roll) {
		int winnings = 0;
		if (highLow == 'h' || highLow == 'H') {
			if (roll == 7) {
				winnings = bet * -1;
			} else if (roll > 7) {
				winnings = bet;
			} else if (roll < 7) {
				winnings = bet * -1;
			}
		} else if (highLow == 'l' || highLow == 'L') {
			if (roll == 7) {
				winnings = bet * -1;
			} else if (roll > 7) {
				winnings = bet * -1;
			} else if (roll < 7) {
				winnings = bet;
			}
		} else if (highLow == 's' || highLow == 'S') {
			if (roll == 7) {
				winnings = bet * 4;
			} else if (roll > 7) {
				winnings = bet * -1;
			} else if (roll < 7) {
				winnings = bet * -1;
			}
		}
		return winnings;
	}

}
