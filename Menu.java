package ie.atu.sw;

import java.io.File;
import java.io.IOException;
import java.util.*;

//### Menu Class Comments ##//
// A minimum and maximum value for the nGram sizes is set here.
// It cannot be changed by the user but can be edited by the programmer if desired.

// Private variables are used to save the users input.
// This keeps track of user input while the program is running.
// These values are then used when the CSV file is being created.

public class Menu {

	private static final int gramMin = 1;
	private static final int gramMax = 5;

	private Scanner sc = new Scanner(System.in);
	private boolean keepRunning = true;
	private boolean nGramSizeRunner = true;

	private String inputDirectoryName;
	private String outputFileName;

	public int getnGramSize() {
		return nGramSize;
	}

	private int nGramSize;

	// ### start Comments ###//
	// Starts the menu and keeps running until the user stops the program.
	// A dedicated menu option is present to allow the user to terminate the program
	// at any time.
	// Menu items are in a separate MenuOptions class to reduce the amount of
	// clutter in the Menu class.

	// The flow of control is blocked by the users successful input of an integer.
	// The integer value must match one of the values that matches a menu item or an
	// error is encountered.

	// Using the new switch expression to save space and be more efficient.
	// This is supported in Java 12 and later.
	// See https://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8192963 for more
	// information
	public void start() throws Exception {
		while (keepRunning) {
			MenuOptions.show();

			int choice = sc.nextInt();

			switch (choice) {
			case 1 -> loadTextDirectory();
			case 2 -> getUserNgramSize();
			case 3 -> setOutputFileName();
			case 4 -> buildNGramTable(inputDirectoryName, nGramSize, outputFileName);
			case 5 -> {
				System.out.println("[INFO] Shutting down...please wait...");
				keepRunning = false;
			}
			default -> {
				System.out.println("Error invalid input");
			}
			}
		}
	}

	// ### User input checks ### //
	// These methods check if each user input is valid
	// Checks are made when building the nGram table
	public boolean userDirectorySelected() {
		if (inputDirectoryName != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean nGramSizeSet() {
		if (getnGramSize() >= gramMin && getnGramSize() <= gramMax) {
			return true;
		} else {
			return false;
		}
	}

	public boolean outputFileNameSet() {
		if (outputFileName != null) {
			return true;
		} else {
			return false;
		}
	}

	// ### loadTextDirectory Comments ###//
	// Takes user input of a directory path to process the files within
	// Absolute file paths are not accepted and will not be processed
	// The file name is converted to a string
	// This string value is stored in inputFileName to be used later
	public String loadTextDirectory() throws Exception {
		Scanner sc2 = new Scanner(System.in);

		System.out.println("[INFO] Enter path to load directory");
		String dir = sc2.nextLine();
		File file = new File(dir);
		inputDirectoryName = file.toString();
		System.out.println("[INFO] Directory " + inputDirectoryName + " loaded");

		return inputDirectoryName.toString();
	}

	// ### setOutputFileName Comments ###//
	// Allows the user to set an output file name.
	// This must be set by the user so the output file is created.
	// If left null no file will be created.
	// The value is stored in the outputFileName variable to be used later.
	public String setOutputFileName() throws IOException {

		System.out.println("[INFO] Please enter a file name for your table");
		Scanner opFile = new Scanner(System.in);
		outputFileName = opFile.nextLine();

		System.out.println("Your file name is " + outputFileName);
		return outputFileName;
	}

	// ### buildNGramTable Comments ###//

	// Makes sure user has entered required information
	// before building nGram table.
	// The text directory set by the user is used.
	// The files and text with is parsed.

	// Uses the nGram size specified by user.
	// The text is broken into nGrams of this size.

	// Uses Output file name set by the user.
	// A new CSV file is created with that name and
	// the contents of the array list from addToCSV method.
	public void buildNGramTable(String fileName, int gramSize, String outputFileName) throws Exception {

		if (userDirectorySelected() && outputFileNameSet() && nGramSizeSet()) {

			Parser parser = new Parser(gramSize);
			parser.parseDirectory(inputDirectoryName, gramSize);
			parser.writeToCSV(outputFileName);
			System.out.println("[INFO] File " + outputFileName + " has been created.");
		} else {
			System.out.println("[ERROR] Please ensure you have:\n" + "* Set an input directory\n" + "Directory "
					+ fileName + " selected\n" + "\n" + "* Set a valid nGram size\n" + "nGram size " + gramSize
					+ " selected\n" + "\n" + "* Set an output file name\n" + "Output file name " + outputFileName
					+ " selected\n");

		}
	}

	// ### getnGramSize Comments ###//
	// Specifies the size of the ngram to use.
	// Only allows an ngram size between the min/max values.
	// Instructions are given to the user if they enter an invalid input.
	public int getUserNgramSize() throws Exception {
		while (nGramSizeRunner) {
			System.out.println("Please enter the size of your n-gram:");

			try {
				nGramSize = Integer.parseInt(sc.next());
			} catch (NumberFormatException exInt) {

				System.out.println("[ERROR] Please enter a number between 1 & 5");
			}

			if (nGramSize >= gramMin && nGramSize <= gramMax) {
				System.out.println("You have picked an nGram size of " + nGramSize);
				return nGramSize;
			} else {
				System.out.println("Please pick a number between [" + gramMin + " - " + gramMax + "]\n");
			}
		}
		return nGramSize;
	}
}
