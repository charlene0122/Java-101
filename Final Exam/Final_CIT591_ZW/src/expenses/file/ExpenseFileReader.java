/**
 * CIT 5910 Final Exam: Expense Management System
 * This Java program involves implementing an expense management system	.
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than course materials on Canvas and approved online Java documentation. 
 */


package expenses.file;


import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * Manages the loading and parsing of expense files.
 * @author lbrandon
 *
 */
public class ExpenseFileReader {

	/**
	 * Loads the given filename and adds each line to a list.
	 * Ignores lines with only whitespace.
	 * @param fileName to load
	 * @return list of lines from the file
	 * @throws FileNotFoundException 
	 */
	public static List<String> loadExpenses(String fileName){
		
		// TODO Implement method
		// Hint: Load and read each line in the file
		// Strip each line of leading and trailing whitespace
		// If a line is made up entirely of whitespace, ignore it
		// Return a list of lines
		
		// create a File object for the specified file name
		File myFile = new File(fileName);
		
		// create an empty list to store the expenses
		List<String> output = new ArrayList<String>();
		
		// try to read the file
		try 
		{
			// create a FileReader and BufferedReader for the file
			FileReader fileReader = new FileReader(myFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// read the first line from the file
			String line = bufferedReader.readLine();
		
			// read until reaching EOF
			 while (line!= null) 
			{
				 // strip leading and trailing whitespace from the line
				 line = line.trim();
				 
				 // if the line is not empty, add it to the list of expenses
			     if (!line.isEmpty()) 
			        output.add(line);
			     
			     // read the next line from the file
			     line = bufferedReader.readLine();
			}
			
			// close the file readers
			fileReader.close();
			bufferedReader.close();
		}
		
		// handle any exceptions that may occur
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// return the list of expenses
		return output;
		 
	}
	
	///// DO NOT CHANGE CODE IN MAIN METHOD! /////
	public static void main(String[] args) {
		
		//load expenses_sample.txt file and get list of expenses
		List<String> expensesListSample = ExpenseFileReader.loadExpenses("expenses_sample.txt");
		
		//print sample expenses
		System.out.println("SAMPLE EXPENSES\n");
		for (String line : expensesListSample) {
			System.out.println(line);
		}
		
		//blank line
		System.out.println();
		
		//load expenses.txt file and get list of expenses
		List<String> expensesList = ExpenseFileReader.loadExpenses("expenses.txt");
		
		//print expenses 
		System.out.println("EXPENSES\n");
		for (String line : expensesList) {
			System.out.println(line);
		}
	}
}