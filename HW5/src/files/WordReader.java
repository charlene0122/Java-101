/**
 * CIT 5910 HW5: Pick Your Letters
 * Design a game that involves re-arranging a group of cards in order to make up a word.
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is provided by the instructor. Nothing has been changed to this java file.
 */

package files;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads words from a file and loads them into an array of strings.
 *
 */
public class WordReader {

	/**
	 * Reads all words from the given file.
	 * @param fileName is the name of the file
	 * @return an array containing all the words
	 */
	public String[] readFromFile(String fileName) {

		String[] allWords = new String[0];

		try {

			int count = this.getCountOfWords(fileName);
			allWords = new String[count];

			this.populateWords(allWords, fileName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return allWords;
	}

	/**
	 * Gets the count of words (lines) in the given file.
	 * @param inputFile to read
	 * @return count of words (lines)
	 * @throws IOException if I/O error occurs
	 */
	public int getCountOfWords(String inputFile) throws IOException {

		//create buffered reader
		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		int count = 0;

		String line = "";
		while ((line = br.readLine()) != null) {
			line = line.strip();
			if (!line.isEmpty()) {
				count++;
			}
		}

		br.close();

		return count;
	}

	/**
	 * Gets the words (lines) from the given file and populates the given array.
	 * @param words array to populate
	 * @param inputFile to read
	 * @throws IOException if I/O error occurs
	 */
	public void populateWords(String[] words, String inputFile) throws IOException {

		//create buffered reader
		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		int index = 0;

		String line = "";
		while ((line = br.readLine()) != null) {
			line = line.strip();
			if (!line.isEmpty()) {
				words[index] = line;
				index++;
			}
		}

		br.close();
	}
}
