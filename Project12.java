/*
 * Project12.java
 * 
 *   A program that plays simple word guessing game.  In this game the user provides a list of 
 *   words to the program.  The program randomly selects one of the words to be guessed from 
 *   this list.  The player then guesses letters in an attempt to figure out what the hidden 
 *   word might be.  The number of guesses that the user takes are tracked and reported at the 
 *   end of the game.
 * 
 * @author Alex Pickering
 * @version 20150424
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Project12 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean validFile = false;
		String fileName = "";
		ArrayList<String> words = new ArrayList<String>();
		while(!(validFile)){
			try{
				System.out.print("Enter a filename containing your wordlist: ");
				fileName = in.nextLine();
				File inFile = new File(fileName);
				Scanner fileReader = new Scanner(inFile);
				words = getList(fileReader);
				if(words.size() > 0){
					System.out.println("Read "+words.size()+" words from the file");
					validFile = true;
				}else{
					System.out.println("The file you selected didn't contain any words");
				}
			}catch(IOException e) {
				System.out.println("Error reading file " + fileName);
			}
		}
		System.out.println();
		boolean tryAgain = true;
		while(tryAgain){
			String solution = getRandomWord(words);
			String currentGuess = starWord(solution);
			int numGuesses = 0;
			ArrayList<Character> myChar = new ArrayList<Character>();
			while(!(solution.equals(currentGuess))){
				System.out.println("The word to guess is: "+currentGuess);
				System.out.println("Previous characters guessed: "+pastGuesses(myChar));
				char charGuess = getCharacterGuess(in);
				currentGuess = modifyGuess(charGuess, solution, currentGuess);
				if(checkInList(charGuess, myChar)){
					System.out.println("You've already guessed "+charGuess+" try another character");
				}else if(solution.equals(currentGuess)){	
				}else{
					myChar.add(charGuess);
					System.out.println("The character "+charGuess+" occurs in "+checkChar(charGuess, solution)+" position(s)");
					System.out.println();
					System.out.println("The word to guess is now: "+currentGuess);
					System.out.print("Enter your word guess: ");
					String wordGuess = in.nextLine();
					wordGuess = wordGuess.toUpperCase();
					numGuesses++;
					if(checkWord(wordGuess, solution)){
						currentGuess = solution;
					}else{
						System.out.println("That is not the correct word.");
						System.out.println();
						System.out.println("Please guess another letter and try again.");
					}
				}
			}
			System.out.println("Congratulations! "+solution+" is the correct word.");
			System.out.println();
			System.out.println("You achieved the correct answer in "+numGuesses+" guess(es)!");
			System.out.print("Would you like a rematch [y/n]? ");
			String tryAgainEntry = in.nextLine();
			char tryChar = tryAgainEntry.charAt(0);
			while(!(tryChar=='y'||tryChar=='Y'||tryChar=='n'||tryChar=='N')){
				System.out.println("Error! Input either 'y' or 'n' ");
				System.out.print("Would you like a rematch [y/n]? ");
				tryAgainEntry = in.nextLine();
				tryChar = tryAgainEntry.charAt(0);
			}
			if(tryChar=='n'||tryChar=='N'){
				tryAgain = false;
			}
			System.out.println();
		}
		System.out.println("Thanks for playing! Goodbye!");
	}
	
	
	/*
	 * This method puts all of the characters from the ArrayList<Character> myChar, which have
	 * been guessed by the user, and puts them into one string to be printed from the main method
	 * onto the console.
	 * 
	 * @param myChar is the list of characters previously guessed by users.
	 */
	private static String pastGuesses(ArrayList<Character> myChar){
		String guesses = "";
		if(myChar.size()<1){
			return "";
		}else{
			for(int i = 0; i < myChar.size()-1; i++){
				guesses += (myChar.get(i) + ", ");
			}
			guesses += myChar.get(myChar.size()-1);
			return guesses;
		}
	}
	
	
	// Given a Scanner as input, returns a List<String> of strings read from the Scanner.
	// The method should read words from the Scanner until there are no more words in the file
	// (i.e. inScanner.hasNext() is false).  The list of strings should be returned to the calling program.
	private static ArrayList<String> getList(Scanner inScanner) {
		ArrayList<String> list = new ArrayList<String>();
		while(inScanner.hasNext()){
			list.add(inScanner.nextLine());
		}
		return list;
	}

	// Given two strings as input, compares the first string (guess) to the second
	// (solution) character by character.  If the two strings are not exactly the same,
	// return false.  Otherwise return true.
	private static boolean checkWord(String guess, String solution) {
		boolean same = false;
		if(guess.length() != solution.length()){
			return same;
		}
		for(int i = 0; i < guess.length(); i++){
			if(guess.charAt(i) != solution.charAt(i)){
				return same;
			}
		}
		same = true;
		return same;
	}
	
	
	// Given a ArrayList<String> list of strings as input, randomly selects one of the strings
	// in the list and returns it to the calling program.
	private static String getRandomWord(ArrayList<String> inList) {
		String random = inList.get((int)(Math.random() * inList.size()));
		return random.toUpperCase();
	}
	

	// Given a Scanner as input, prompt the user to enter a character.  If the character
	// enters anything other than a single character provide an error message and ask
	// the user to input a single character.  Otherwise return the single character to
	// the calling program.
	private static char getCharacterGuess(Scanner inScanner) {
		System.out.print("Enter a character to guess: ");
		String guess = inScanner.nextLine();
		while(guess.length()>1 || guess.length()<1){
			System.out.println("Error! Enter a single character.");
			System.out.print("Enter a character to guess: ");
			guess = inScanner.nextLine();
		}
		guess = guess.toUpperCase();
		return guess.charAt(0);
	}
	
	// Given a character inChar and a ArrayList<Character> list of characters, check to see if the
	// character inChar is in the list.  If it is, return true.  Otherwise, return false. 
	private static boolean checkInList(char inChar, ArrayList<Character> inList) {
		boolean included = false;
		for(int i = 0; i < inList.size(); i++){
			if(inList.get(i) == inChar){
				included = true;
			}
		}
		return included;
	}
	
	// Given a String, return a String that is the exact same length but consists of 
	// nothing but '*' characters. 
	private static String starWord(String inWord) {
		String stars = "";
		for(int i = 0; i < inWord.length(); i++){
			stars += "*";
		}
		return stars;
	}
	
	// Given a character and a String, return the count of the number of times the
	// character occurs in that String.
	private static int checkChar(char guessChar, String guessWord) {
		int count = 0;
		for(int i = 0; i<guessWord.length(); i++){
			if(guessWord.charAt(i) == guessChar){
				count++;
			}
		}
		return count;
	}

	// Given a character, a String containing a word, and a String containing a 'guess'
	// for that word, return a new String that is a modified version of the 'guess' 
	// string where characters equal to the character inChar are uncovered.
	private static String modifyGuess(char inChar, String word, String currentGuess) {
		String newGuess = "";
		if(word.indexOf(inChar) != -1){
			for(int i=0; i<word.length(); i++){
				if(word.charAt(i) == inChar){
					newGuess += inChar;
				}else{
					newGuess += currentGuess.charAt(i);
				}
			}
			return newGuess;
		}else{
			return currentGuess;
		}
	}
}
