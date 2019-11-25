/*
 * Project05.java
 * 	This program uses creates a randomized target number, and using a loop, prompts users to guess that number.
 *  The program informs users when their guesses are too high, too low, or out of range, and keeps track of 
 *  the number of guesses with a counter variable. When the user successfully guesses the number, they are prompted
 *  with an individualized message depending on the number of tries it took them to correctly guess the number. This
 *  is accomplished through branching.
 * 
 * @author Alex Pickering
 * @version 20150218
 */
import java.util.Scanner;

public class Project05 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numGuesses = 0;                                               // declaration of necessary integers
		int guess = 0;
		int target = (int) (100 * Math.random()) + 1;
		while (guess != target) {
			System.out.print("Enter a guess between 1 and 100: ");
			guess = in.nextInt();
			if (guess > 100 || guess < 1) {                              // sorting out wrong guesses as too high, too low, or out of range
				System.out.println("Your guess is out of range. Pick a number between 1 and 100");
				System.out.println();
			} else if (guess > target) {
				System.out.println("Your guess was too high. Try again.");
				System.out.println();
			} else if (guess < target) {
				System.out.println("Your guess was too low. Try again.");
				System.out.println();
			}
			numGuesses++;                                                // tallying number of guesses
		}
		System.out.println("Congratulations! Your guess was correct!");
		System.out.println();
		System.out.println("I had chosen " + target + " as the target number.");
		System.out.println("You guessed it in " + numGuesses + " tries.");
		if (numGuesses == 1) {                                          // individualized ending message based on number
			System.out.println("That was lucky!");                      // of guesses is determined with branching
		} else if (numGuesses >= 2 && numGuesses <= 4) {
			System.out.println("That was amazing!");
		} else if (numGuesses >= 5 && numGuesses <= 6) {
			System.out.println("That was good.");
		} else if (numGuesses == 7) {
			System.out.println("That was OK.");
		} else if (numGuesses >= 8 && numGuesses <= 9) {
			System.out.println("That was not very good.");
		} else if (numGuesses >= 10) {
			System.out.println("This just isn't your game.");
		}
	}
}