package FinalProject_CIT591.MongoDB;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
import java.util.HashMap;  

/**
 * This class parses a CSV file containing stock data and creates a map of stock symbols and their corresponding sectors.
 */
public class ParseCSV {
	
	/**
	 * Parses a CSV file containing stock data and creates a map of stock symbols and their corresponding sectors.
	 * @param fileName The name of the CSV file to be parsed.
	 * @return A map of stock symbols and their corresponding sectors.
	 */
	public static HashMap<String, String> parseFile(String fileName)   
	{  
		// to store stock Symbol and Sector and return to output
		HashMap<String, String> output = new HashMap<>();
		
		//initialize variables
		String line = "";  
		String splitBy = ",";  
		
		try   
		{  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(fileName));  
			
			// while haven't reach EOF
			while ((line = br.readLine()) != null)     
			{  
				// split current line into array of Strings, use comma as separator  
				String[] stock = line.split(splitBy);   
				
				// store stock symbol and sector into the map
				output.put(stock[0], stock[9]);
				
				//print a message indicate the import is successful
				System.out.println("Symbol: " +stock[0] +" is imported? "+ output.containsKey(stock[0]) + "; Sector: " + output.get(stock[0]));
			} 
			
			// print a message indicating the parsing process is completed.
			System.out.println("File parsed successfully. " + output.size() + " stock added to the HashMap.");
			br.close();
		}
	
		catch (IOException error)   
		{  
		error.printStackTrace();  
		}  
		
	return output;
	}  
}
