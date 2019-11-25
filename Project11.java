/*
 * Project11.java
 * 
 *   A program that reads in a text file that uses a specific input format and uses it to
 *   produce a formatted report for output.
 * 
 * @author Alex Pickering
 * @version 20150416
 * 
 */
package osu.cse1223;
import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Project11 {
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter an input file name: ");
		String inFileName = keyboard.nextLine();
		File inFile = new File(inFileName);
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> scores = new ArrayList<Integer>();
		try {
			Scanner fileReader = new Scanner(inFile);
			names = getNames(fileReader);
			fileReader = new Scanner(inFile);
			scores = readNextSeries(fileReader);
			fileReader.close();
		}catch(IOException e){
			System.out.println("Error reading file " + inFileName);
		}
		
		System.out.print("Enter an output file name: ");
		String outFileName = keyboard.nextLine();
		while(inFileName.equals(outFileName)){
			System.out.println("Error! Output file and input file must have different names!");
			System.out.print("Enter an output file name: ");
			outFileName = keyboard.nextLine();
		}
		File outFile = new File(outFileName);
		ArrayList<Integer> means = new ArrayList<Integer>();
		int current = 0;
		try {
			PrintWriter fileWriter = new PrintWriter(outFile);
			fileWriter.println("Final Overall Scores Report");
			fileWriter.println();
			fileWriter.printf("%-20s %7s %7s %5s %5s", "Name", "Mean", "Median", "Max", "Min");
			fileWriter.println();
			fileWriter.println("------------------    ------  ------  ----  ----");
			for(int i = 0; i < names.size(); i++){
				ArrayList<Integer> setOfScores = getSpecificScores(scores);
				current = writeReportRow(names.get(i), setOfScores, fileWriter);
				means.add(current);
			}
			fileWriter.println();
			fileWriter.println("Total number of participants: " + names.size());
			int highMean = indexOfHighMean(means), lowMean = indexOfLowMean(means);
			fileWriter.println("Highest average score: " + names.get(highMean) +
					" (" + means.get(highMean) + ")");
			fileWriter.println("Lowest average score: " + names.get(lowMean) + 
					" (" + means.get(lowMean) + ")");
			fileWriter.close();
		}catch(IOException e) {
			System.out.println("Error writing to file " + outFileName);
		}
		
	}
	
	/*
	 * This method searches through the input file for text, and then collects
	 * these sections of text as names in an ArrayList<String> names.
	 */
	private static ArrayList<String> getNames(Scanner inScanner) throws IOException {
		ArrayList<String> names = new ArrayList<String>();
		names.add(inScanner.nextLine());
		while(inScanner.hasNextLine()){
			String str = inScanner.nextLine();
			if(str.equals("-1") && inScanner.hasNextLine()){
				names.add(inScanner.nextLine());
			}
		}
		
		return names;
	}
	
	
	// Given a Scanner as input read in a list of integers one at a time until a negative
	// value is read from the Scanner.  Store these integers in an ArrayList<Integer> and
	// return the ArrayList<Integer> to the calling program.
	private static ArrayList<Integer> readNextSeries(Scanner inScanner) throws IOException {
		ArrayList<Integer> series = new ArrayList<Integer>();
		int current = 0;
		inScanner.nextLine();
		series.add(inScanner.nextInt());
		while(inScanner.hasNextLine()) {
			current = inScanner.nextInt();
			if(current == -1){
				series.add(current);
				if(inScanner.hasNextLine()){
					inScanner.nextLine();
					inScanner.nextLine();
				}
			}else{
				series.add(current);
			}
			
		}
		
		return series;
	}
	
	/*
	 * This method takes the collection of all scores and separates one person's
	 * scores from the rest, deleting them from the total set in the process.
	 * 
	 * @param ArrayList<Integer> allScores holds all scores, used to find an 
	 * 			individual's scores.
	 */
	private static ArrayList<Integer> getSpecificScores(ArrayList<Integer> allScores){
		ArrayList<Integer> setOfScores = new ArrayList<Integer>();
		while(allScores.get(0) != -1){
			setOfScores.add(allScores.get(0));
			allScores.remove(0);
		}
		if(allScores.size() > 1) {allScores.remove(0);}
		return setOfScores;
	}
	
	/*
	 * This method, using four other methods which get mean, median, max, and min,
	 * collects them together into one string with the name of the person who got
	 * the scores, printing them in neat rows into the output file using PrintWriter.
	 * 
	 * @param String name
	 * @param ArrayList<Integer> raw is the set of scores from that individual, uncategorized.
	 * 		This is used to find the mean, median, max, and min.
	 * @param PrintWriter fileWriter writes to the output file previously designated.
	 */
	private static int writeReportRow(String name, ArrayList<Integer> raw, PrintWriter fileWriter) {
		int mean = getAverage(raw);
		int median = getMedian(raw);
		int max = getMax(raw);
		int min = getMin(raw);
		fileWriter.printf("%-22s %5d %7d %5d %5d", name, mean, median, max, min);
		fileWriter.println();
		return mean;
	}
	
	/*
	 * This method finds the index of the name with the highest mean out of 
	 * all of the names.
	 * 
	 * @param ArrayList<Integer> means
	 */
	private static int indexOfHighMean(ArrayList<Integer> means){
		int highMean = 0;
		for(int i = 0; i < means.size(); i++){
			if(means.get(highMean) < means.get(i)){
				highMean = i;
			}
		}
		return highMean;
	}
	
	/*
	 * This method finds the index of the name with the lowest mean out of 
	 * all of the names.
	 * 
	 * @param ArrayList<Integer> means
	 */
	private static int indexOfLowMean(ArrayList<Integer> means){
		int lowMean = 0;
		for(int i = 0; i < means.size(); i++){
			if(means.get(lowMean) > means.get(i)){
				lowMean = i;
			}
		}
		return lowMean;
	}
	
	
	// Given a List<Integer> of integers, compute the median of the list and return it to
	// the calling program.
	private static int getMedian(List<Integer> inList) {
		int median = 0;
		Collections.sort(inList);
		if(inList.size() % 2 == 1){
			median = inList.get(inList.size() / 2);
		}else{
			median = ( inList.get((inList.size()/2)-1) + inList.get(inList.size()/2) ) / 2;
		}
		return median;
	}
	
	// Given a List<Integer> of integers, compute the average of the list and return it to
	// the calling program.
	private static int getAverage(List<Integer> inList) {
		int current = 0, total = 0;
		for(int i = 0; i < inList.size(); i++) {
			current = inList.get(i);
			total += current;
		}
		int mean = total / inList.size();
		return mean;
	}
	
	// Given a List<Integer> of integers, compute the maximum of the list and return it to
	// the calling program.
	private static int getMax(List<Integer> inList) {
		int max = inList.get(0);
		for(int i = 0; i < inList.size(); i++) {
			if(max < inList.get(i)){
				max = inList.get(i);
			}
		}
		return max;
	}
	
	// Given a List<Integer> of integers, compute the minimum of the list and return it to
	// the calling program.
	private static int getMin(List<Integer> inList) {
		int min = inList.get(0);
		for(int i = 0; i < inList.size(); i++) {
			if(min > inList.get(i)){
				min = inList.get(i);
			}
		}
		return min;
	}
	
	
}
