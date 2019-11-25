 /*
 * Project02.java
 *	This program prompts the user for a string and substring, and then proceeds to analyze the string lengths,
 *	start and end of substring in the string, state the character at a certain position, and replace the old
 * 	substring with a new substring.
 * 
 * @author Alex Pickering
 * @version 20150129
 * 
 * EXTRA CREDIT
 * 	The first several lines of the program are executed fine, but when it attempts to find the substring within the
 *  string, it fails to do so, and prints out a -1 index, the sign of an index error in which the substring called
 *  for isn't in the string.
 * 	
 * 	The compiling error occurs on line 31, when the program tries to define a string as (0, -1) as a result of the 
 * 	inability to index the substring in the string on line 29. You could use a try-catch method to give the user an
 *	message if their substring input was not in the string. We haven't learned how to do the try-catch method yet.
 */ 
import java.util.Scanner;
public class Project02 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a long string: ");
		String longStr = in.nextLine();
		System.out.print("Enter a substring: ");
		String subStr = in.nextLine();
		System.out.println("Length of your string: " + longStr.length());
		System.out.println("Length of your substring: " + subStr.length());
		int subStrStart = longStr.indexOf(subStr);
		System.out.println("Starting position of your substring in string: " + subStrStart);
		String beforeSubStr = longStr.substring(0, subStrStart);
		System.out.println("String before your substring: " + beforeSubStr);
		String afterSubStr = longStr.substring((subStrStart + subStr.length()), longStr.length());
		System.out.println("String after your substring: " + afterSubStr);
		int topPosition = longStr.length() - 1;
		System.out.print("Enter a position between 0 and " + topPosition + ": ");
		int position = in.nextInt();
		System.out.println("The character at position " + position + " is " + longStr.charAt(position));
		System.out.print("Enter a replacement string: ");
		in.nextLine();
		String replacement = in.nextLine();
		System.out.println("Your new string is: " + beforeSubStr + replacement + afterSubStr);
		
	}
}