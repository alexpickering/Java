/*
 * Project10.java
 * 
 *   A program that plays and scores a round of the game Poker Dice.  In this game,
 *   five dice are rolled.  The player is allowed to select a number of those five dice
 *   to re-roll.  The dice are re-rolled and then scored as if they were a poker hand.  
 *   The following hands MUST be scored in this assignment:
 *   	* High card
 *   	* One Pair
 *   	* Two Pair
 *   	* Three of a Kind
 *   	* Full House
 *   	* Four of a Kind
 *   	* Five of a Kind
 *   For extra credit, you may also implement:
 *   	* Straight
 *   This code scores all of the above, INCLUDING Straights for extra credit.
 * 
 * @author Alex Pickering
 * @version 20150407
 * 
 */
package osu.cse1223;
import java.util.Arrays;
import java.util.Scanner;


public class Project10 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int [] dice = new int [5];
		Boolean playAgain = true;
		while (playAgain) {
			resetDice(dice);
			rollDice(dice);
			System.out.println("Your current dice: " + diceToString(dice));
			promptForReroll(dice, in);
			System.out.println("Keeping remaining dice...");
			System.out.println("Rerolling...");
			rollDice(dice);
			System.out.println("Your final dice: " + diceToString(dice));
			String result = getResult(dice);
			System.out.println(result + "!");
			playAgain = promptForPlayAgain(in);
			System.out.println();
		}
		System.out.println("Goodbye!");
	}
	
	// Given an array of integers as input, sets every element of the array to zero.
	private static void resetDice(int[] dice) {
		for(int i = 0; i < dice.length; i++) {
			dice[i] = 0;
		}
	}
	
	// Given an array of integers as input, checks each element of the array.  If the value
	// of that element is zero, generate a number between 1 and 6 and replace the zero with
	// it.  Otherwise, leave it as is and move to the next element.
	private static void rollDice(int[] dice) {
		for(int i = 0; i < dice.length; i++) {
			if (dice[i] == 0) {
				dice[i] = (int) (Math.random() * 6) + 1;
			}
		}
	}
	
	// Given an array of integers as input, create a formatted String that contains the
	// values in the array in the order they appear in the array.  For example, if the 
	// array contains the values [0, 3, 6, 5, 2] then the String returned by this method
	// should be "0 3 6 5 2".
	private static String diceToString(int[] dice) {
		String str = "";
		for(int i = 0; i < dice.length; i++) {
			str += dice[i];
			if(i < dice.length - 1) {str += " ";}
		}
		return str;
	}
	
	
	// Given an array of integers and a Scanner as input, prompt the user with a message
	// to indicate which dice should be re-rolled.  If the user enters a valid index (between
	// 0 and the total number of dice -1) then set the die at that index to zero.  If the 
	// user enters a -1, end the loop and return to the calling program.  If the user enters
	// any other invalid index, provide an error message and ask again for a valid index.
	private static void promptForReroll(int[] dice, Scanner inScanner) {
		int input = 0;
		System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
		input = inScanner.nextInt();
		while(input != -1) {
			if(!(input < 5 && input > -1)) {
				System.out.println("Error: Index must be between 0 and 4 (-1 to quit)!");
				System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
				input = inScanner.nextInt();
			} else {
				dice[input] = 0;
				System.out.println("Your current dice: " + diceToString(dice));
				System.out.print("Select a die to re-roll (-1 to keep remaining dice): ");
				input = inScanner.nextInt();
			}
		}
		
	}
	
	// Given a Scanner as input, prompt the user to play again.  The only valid entries
	// from the user are 'Y' or 'N', in either upper or lower case.  If the user enters
	// a 'Y' the method should return a value of true to the calling program.  If the user
	// enters a 'N' the method should return a value of false.  If the user enters anything
	// other than Y or N (including an empty line), an error message should be displayed
	// and the user should be prompted again until a valid response is received.
	private static boolean promptForPlayAgain(Scanner inScanner) {
		String playAgainInput = "";
		Boolean playAgain = true;
		System.out.print("Would you like to play again [Y/N]?: ");
		inScanner.nextLine();  
		playAgainInput = inScanner.nextLine();
		while (!(playAgainInput.equals("Y") || playAgainInput.equals("y") || playAgainInput.equals("N") || playAgainInput.equals("n") )) {
			System.out.println("ERROR! Only 'Y' and 'N' allowed as input!");
			System.out.print("Would you like to play again [Y/N]?: ");
			playAgainInput = inScanner.nextLine();
		}
		if (playAgainInput.equals("N") || playAgainInput.equals("n")) {
			playAgain = false;
		}
		return playAgain;
	}
	
	// Given an array of integers, determines the result as a hand of Poker Dice.  The
	// result is determined as:
	//	* Five of a kind - all five integers in the array have the same value
	//	* Four of a kind - four of the five integers in the array have the same value
	//	* Full House - three integers in the array have the same value, and the remaining two
	//					integers have the same value as well (Three of a kind and a pair)
	//	* Three of a kind - three integers in the array have the same value
	//	* Two pair - two integers in the array have the same value, and two other integers
	//					in the array have the same value
	//	* One pair - two integers in the array have the same value
	//	* Highest value - if none of the above hold true, the Highest Value in the array
	//						is used to determine the result
	//
	//	The method should evaluate the array and return back to the calling program a String
	//  containing the score from the array of dice.
	//
	//  EXTRA CREDIT: Include in your scoring a Straight, which is 5 numbers in sequence
	//		[1,2,3,4,5] or [2,3,4,5,6]. 
	private static String getResult(int[] dice) {
		int[] count = getCounts(dice);
		String result = "";
		int handValue = 0;                        ////TEST FOR FOAK, ETC.
		Arrays.sort(dice);
		boolean straight = isItAStraight(dice);  //the "hand" of dice checked for a Straight
		if(straight) {
			result = "Straight";
			handValue = 4;
		}
		for(int i = 0; i < count.length; i++) {
			if(count[i] == 0) {
			}else if(count[i] == 5) {
				result = "Five of a Kind";
				handValue = 8;
			}else if(count[i] == 4 && handValue < 7) {
				result = "Four of a Kind";
				handValue = 7;
			}else if( (count[i] == 3 && result.equals("One Pair")) || 
					(count[i] == 2 && result.equals("Three of a Kind")) && handValue < 6 ) {
				result = "Full House";
				handValue = 6;
			}else if(count[i] == 3 && handValue < 5) {
				result = "Three of a Kind";
				handValue = 5;                               //the straight has a value which would slot in here, but is handled in another method.
			}else if(count[i] == 2 && result.equals("One Pair") && handValue < 3) {
				result = "Two Pair";
				handValue = 3;
			}else if(count[i] == 2 && handValue < 2) {
				result = "One Pair";
				handValue = 2;
			}else if(handValue <= 1) {
				result = "Highest Card " + (i+1);
				handValue = 1;
			}
		}
		return result;
	}
	
	// Given an array of integers as input, return back an array with the counts of the
	// individual values in it.  You may assume that all elements in the array will have 
	// a value between 1 and 6.  For example, if the array passed into the method were:
	//   [1, 2, 3, 3, 5]
	// Then the array of counts returned back by this method would be:
	// [1, 1, 2, 0, 1, 0]
	// (Where index 0 holds the counts of the value 1, index 1 holds the counts of the value
	//  2, index 2 holds the counts of the value 3, etc.)
	// HINT:  This method is very useful for determining the score of a particular hand
	//  of poker dice.  Use it as a helper method for the getResult() method above.
	private static int[] getCounts(int[] dice) {
		int[] count = new int[6];
		for(int i = 0; i < 5; i++) {
			if(dice[i] == 1) {count[0]++;}
			if(dice[i] == 2) {count[1]++;}
			if(dice[i] == 3) {count[2]++;}
			if(dice[i] == 4) {count[3]++;}
			if(dice[i] == 5) {count[4]++;}
			if(dice[i] == 6) {count[5]++;}
		}
		return count;
	}
	
	/*
	 * This method scans through the set of dice and determines whether the set is a straight.
	 * 
	 * @param dice is the array of 5 dice
	 */
	private static boolean isItAStraight(int[] dice) {
		boolean straight = false;
		if(dice[0] + 1 != dice[1]) {
		}else if(dice[1] + 1 != dice[2]) {
		}else if(dice[2] + 1 != dice[3]) {
		}else if(dice[3] + 1 != dice[4]) {
		}else{	
			straight = true;
		}
		return straight;
	}

	
}
