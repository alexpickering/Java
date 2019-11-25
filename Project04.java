/*
 * Project04.java
 * 	This program takes user input to choose rock, paper, or scissors, and randomizes
 * 	a response by generating a number (either 1, 2, or 3) and assigning that number to
 * 	a value (either rock, paper, or scissors). Then it outputs the outcome using branching.
 * 
 *	@author Alex Pickering
 *	@version 20150211
 */
import java.util.Scanner;
public class Project04 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String userRock = "Rr";
		String userPaper = "Pp";
		String userScissors = "Ss";
		String userOptions = "RrPpSs";
		System.out.print("Please select one of [R/P/S]: ");    // User selection
		String userChoice = in.nextLine();
		if (userOptions.indexOf(userChoice) != -1) 
		{
			if (userRock.indexOf(userChoice) != -1) {              //User selection designates value R, P, or S
				userChoice = "Rock";
			} else if (userPaper.indexOf(userChoice) != -1) {
				userChoice = "Paper";
			} else if (userScissors.indexOf(userChoice) != -1) {
				userChoice = "Scissors";
			}
		System.out.println("You chose: " + userChoice);	
		} else {
			System.out.println("Invalid choice! Defaulting to Rock.");    //User value defaults to R if user enters invalid input
			userChoice = "Rock";
		}
		int x = (int) (3 * Math.random()) + 1;                 // Randomized number from 1-3
		String computerChoice = "";
		if (x == 1) {                                          // Computer value assigned from randomized number
			computerChoice = "Rock";
		} else if (x == 2) {
			computerChoice = "Paper";
		} else if (x == 3) {
			computerChoice = "Scissors";
		}
		System.out.println("I chose: " + computerChoice);
		int outcome = 0;                                        // create integer to stand for the three different outcomes
		if (userChoice.equals("Rock")) {                        // branching used to create all possible outcomes and assign outcome integer value
			if (computerChoice.equals("Rock")) {               
				outcome = 3;	                                // outcome 1 == user wins, outcome 2 == computer wins, outcome 3 == it's a tie
			} else if (computerChoice.equals("Paper")) {
				outcome = 2;
			} else if (computerChoice.equals("Scissors")) {
				outcome = 1;
			}
		} else if (userChoice.equals("Paper")) {
			if (computerChoice.equals("Rock")) {
				outcome = 1;
			} else if (computerChoice.equals("Paper")) {
				outcome = 3;
			} else if (computerChoice.equals("Scissors")) {
				outcome = 2;
			}
		} else if (userChoice.equals("Scissors")) {
			if (computerChoice.equals("Rock")) {
				outcome = 2;
			} else if (computerChoice.equals("Paper")) {
				outcome = 1;
			} else if (computerChoice.equals("Scissors")) {
				outcome = 3;
			}
		}
		if (outcome == 1) {                                      //branching is used to print outputs according to outcome value
			System.out.println(userChoice + " beats " + computerChoice + " - you win!");
		} else if (outcome == 2) {
			System.out.println(computerChoice + " beats " + userChoice + " - you lose!");
		} else if (outcome == 3) {
			System.out.println("A Tie!");
		}
	}
}