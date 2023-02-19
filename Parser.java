package ie.atu.sw;
import java.io.*;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import com.opencsv.CSVWriter;

//### Parser Class Comments ###//
// Instantiate the parser to create a table for ngrams 
// Default nGram size of 4 is specified. This can be changed by the user
public class Parser {

	private static Object[][] table;

	public Parser(int n) {
		table = new Object[((int) Math.pow(26, n + 1))][2];

	}

	// ### writeToCSV Comments ###//
	// This method uses the information from the createCSVData method to
	// write the information to a CSV file
	// The name of the CSV file specified by the user is used.
	public void writeToCSV(String outputFileName) {

		List<String[]> csvData = createCSVData();

		try (CSVWriter writer = new CSVWriter(new FileWriter(outputFileName))) {
			System.out.println("[INFO] Writing data to CSV. Please wait...");
			writer.writeAll(csvData);
		} catch (Exception e1) {
			System.out.println("[ERROR] Please enter an output file name");
		}
	}

	// ### createCSVData Comments ###//
	// A string array list is created with headers.
	// Header names are hard coded, cannot be changed by user input
	// The ngram records are created and added to a CSV file in this method
	public static List<String[]> createCSVData() {

		List<String[]> list = new ArrayList<>();

		String[] header = { "Index", "nGram", "Frequency" };
		list.add(header);

		for (int i = 0; i < table.length; i++) {
			String[] record = { String.valueOf(i), (String) table[i][0], String.valueOf(table[i][1]) };
			list.add(record);
		}
		return list;
	}

	// ### addNgramToTable Comments ###//
	// Gets numeric value of each ngram and finds it spot on the table
	public void addNgramToTable(String ngram) {
		int index = ngram.hashCode() % table.length;
		long counter = 1;
		if (table[index][1] != null) {
			counter += (long) (table[index][1]);

		}
		table[index][0] = ngram;
		table[index][1] = counter;
	}

	// ### parse Comments ###//
	// Gets and breaks down contents of files specified
	// Whitespaces are removed and all characters are converted to lowercase
	// Empty strings are taken into account to remove risk of errors
	// The words are broken into ngrams and added to the table/CSV file
	public void parse(String file, int n) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] words = line.split("\\s+");
				for (String word : words) {
					word = word.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
					if (StringUtils.isNotBlank(word)) {
						String[] ngram = new NGramGenerator().generateNGrams(word, n);

						for (String t : ngram) {
							addNgramToTable(t);
						}
					}
				}

			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void parseProgressMeter(String file, int n) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] words = line.split("\\s+");
				for (String word : words) {
					word = word.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
					if (StringUtils.isNotBlank(word)) {
						String[] ngram = new NGramGenerator().generateNGrams(word, n);

						for (String t : ngram) {
							addNgramToTable(t);
						}
					}
				}

			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ### parseDirectory Comments ###//
	// Gets directory of files and uses parse method to process them
	public void parseDirectory(String dir, int n) {
		try {
			File f = new File(dir);
			String[] files = f.list();
			for (String file : files) {
				parse(dir + "/" + file, n);
			}
		} catch (Exception e) {
			System.out.println("[ERROR] Parsing failed. Invalid directory selected.");
		}
	}
}
