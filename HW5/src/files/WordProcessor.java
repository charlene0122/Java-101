/**
 * CIT 5910 HW5: Pick Your Letters
 * Design a game that involves re-arranging a group of cards in order to make up a word.
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */

package files;

import java.util.Arrays;

/**
 * Filters all the words (in an array) based on a given length.
 *
 */
public class WordProcessor {

	/**
	 * Filters the words with a length equal to the given length.
	 * @param allWords array containing all the words
	 * @param length to filter words
	 * @return an array containing all the words with specific length
	 */
	public String[] filterWordList(String[] allWords, int length) {

		//create a new array
		String[] output = new String[1];
		int index = 0;
		
		//iterate the word array
		for(int i = 0; i < allWords.length; i++)
		{
			//if a word is of the same length as specified, 
			if (allWords[i].length() == length)
			{
				//if the array is full, expand the array to twice the length
				if (index + 1 >= output.length)
					output = resize(output, output.length * 2).clone();
				//add it to the new list
				output[index++] = allWords[i];
			}
		}
		
		//remove the null elements from the array
		String[] removedNull = Arrays.stream(output).filter(value ->
        value != null && value.length() > 0).toArray(size -> new String[size]);
		output = removedNull;
		
		//return the filtered array
		return output;
	}
	
	/**
	 * Expand the array to a specified length, with elements remain unchanged
	 * @param array array to be resized
	 * @param size size of the array to be returned
	 * @return a new array with a specified length with the same content as before
	 */
	public String[] resize(String[] array, int size)
	{
		//create a new array with specified size
		String[] output = new String[size];
		//iterate through the array, copy the original element into the new array
		for (int i = 0; i< array.length; i++)
		{
			output[i] = array[i];
		}
		//return the new array
		return output;
	}
	
	//test code for my own reference
//	public static void main(String[] args) {
//		String[] alphabets = new String[] {"aaa", "bss", "css", "ddd", "e", "f", 
//				"g", "h", "I", "j", "k", "l", "m", "n", "o", "p", "sq", "rs", "ss",
//				"t", "u", "v", "w", "x", "y", "z"};
//		WordProcessor test = new WordProcessor();
//		String[] list = test.filterWordList(alphabets, 1);
//		System.out.println(Arrays.asList(list));
//	
//	}
}
