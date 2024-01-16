/**
 * CIT 5910 Final Project: Portfolio Recommendation Program
 * Provide a recommendation for the user's stock portfolio based on the user's input preferences and risk level.
 * 
 * Data source: Yahoo Finance API
 * Database: MongoDB
 * 
 * @author Zhifei Wu & Di Yang
 * Penn ID: 30406975 74113922 
 * This java file is solely completed by the author mentioned above. 
 */

package FinalProject_CIT591.MongoDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class provides a recommendation for the user's stock portfolio based on the user's input preferences and risk level.
 * The user will be asked to input their preferred industry, market capital, dividend preference, growth or value preference,
 * and risk level. The program will filter the stocks in the selected industry and provide a list of stock recommendations
 * asked on the user's input and risk level.
*/
public class PortfolioRecommendation {

	/**
	 * This is the main method that runs the Portfolio Recommendation program. It first checks if the user wants to update
	 * the stock information, then it asks the user for their risk level, market capital preference, dividend preference,
	 * and growth or value preference. It then filters the stocks based on these preferences and the user's preferred
	 * industries and outputs a list of recommended stocks for each industry.
	 *
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		// initialize the csv file and mongodb uri for the stock data
		String csv = "nasdaq_screener.csv";
		String uri = "mongodb+srv://charlene0122:java591@stockinfo.orlsi4o.mongodb.net/?retryWrites=true&w=majority";
		
		// create a scanner to read user input
		Scanner scanner = new Scanner(System.in);
		
		// create a BatchProcess object to parse the csv file and connect to mongodb database and collections
		BatchProcess nasdaq = new BatchProcess(csv, uri);
		
		// get all the info of stocks in csv using yahoo finance API and import them to mongoDB
		// comment the following code if want to save time from data uploading
		nasdaq.BatchAdd();
		
		// print a message indicating when the data is lastly updated, and ask user whether to update the data now
		System.out.println("Stock is lastly updated on " + nasdaq.getInfoDate() + ", would you like to update stock info now?");
		String preference = scanner.nextLine().toLowerCase().strip();
		if (preference.startsWith("y"))
			nasdaq.BatchUpdate();
		else
			System.out.println("Stock not updated, latest update date is: " + nasdaq.getInfoDate());
		
		// create a new client, output questions to the console and get user input
		Client client = new Client();
		client.setClientInfo();
		client. askForRiskLevel();
		
		// get client's intended investment capital, dividend preference, growth/value preference and risk level
		int marCap = client.getMarketCapital();
		boolean div = client.getDividend();
		boolean growthOrValue = client.getGrowthorvalue();
		String riskLevel = client.getRiskLevel();
			
		// get mongoDB collections
		HashMap<String, ImportToCollection> sectors = nasdaq.getCollections();
		
		// get client's preferred industry
		ArrayList<String> clientSector = client.getPreferredIndustry();
		
		// create a data structure to hold selected stocks
		HashMap<String, HashMap<String, Double>> selectedStocks = new HashMap<String, HashMap<String, Double>>();
		
		// for each sector that client is interested in
		for (String sec : clientSector)
		{		
			// filter stocks based on user's preference and risk level
			ArrayList<HashMap<String, Double>> currentSector = sectors.get(sec).filterStock(marCap, div, growthOrValue);
			
			// if no stocks exist, print a message and move on to the next sector
			if (currentSector.size() == 0)
			{			
				System.out.println();
				System.out.println("For sector "+ sec +", there are no stocks that match your preference.");
				continue;
			}
			
			// print a list of stocks and ask user to choose
			int start, end;
			System.out.println();
			System.out.println("For sector "+ sec +", we've filtered this stocks for you based on "
					+ "your preference and risk capacity.");
			
			// if less than 5 stocks filtered, print them all
			if(currentSector.size() <= 5)
			{
				start = 0;
				end = currentSector.size() - 1;
			}
			
			// if more than 5 stocks, print the first 10 stocks corresponding to client's risk level
			else {
				if(riskLevel.equals("low"))
				{
					start = 0;
					end = currentSector.size()/3;
					end = Math.min(start + 9, end);
				}
				
				else if (riskLevel.equals("moderate"))
				{
					start = currentSector.size()/3 + 1 ;
					end = currentSector.size()/3 * 2;
					end = Math.min(start + 9, end);
				}
				
				// else indicating a high risk level
				else 
				{
					start = currentSector.size()/3 * 2 + 1;
					end = currentSector.size()-1;
					end = Math.min(start + 9, end);
				}
			}		
			
			// list the options and ask client to choose
			int count = 0;
			for (int i = start; i <= end; i++) {
				  System.out.println(++count + ": " + currentSector.get(i).keySet() + " Price: " + currentSector.get(i).values());
				}
			System.out.println();
			System.out.println("Please select the stocks you are interested in. ");

					
				while (true) {
					try {
						 System.out.println("Enter index before stock symbol to choose stock, or press 'done' to complete the selection.");

						while (true) {
							Scanner temp = new Scanner(System.in);
							
							// when user enters done, break the loop
							String input = temp.next();
							if (input.equals("done")) {
								break;
							}

							int index = Integer.parseInt(input) - 1;
							
							// if a correct number is input, add the corresponding stock to the list
							if (index >= 0 && index < count) {
							      // Add the stock to the selectedStocks map
							      HashMap<String, Double> tempMap = new HashMap<String, Double>();
							      if(selectedStocks.containsKey(sec))
							        tempMap = selectedStocks.get(sec);
							      for(String stock: currentSector.get(start + index).keySet())
							      {
							        tempMap.put(stock, currentSector.get(start + index).get(stock));
							      }

							      selectedStocks.put(sec, tempMap);
							      System.out.println(selectedStocks.get(sec));
							    } else {
								System.out.println("Invalid input. Please try again.");
							}
						}
						break;
					} catch (Exception e) {
						System.out.println("Invalid input, please try again");
					}
				}
			
		}
		
		// calculate total capital to invest
		double totalCapital = client.getTotalCapital();
		double ratioInStock = client.getPercentageOfStock();
		double stockCap = totalCapital * ratioInStock;
		
		// get number of sectors in the portfolio
		int numOfSec = selectedStocks.size();
		
		// if no stock in the list, print a message and end the program
		if (numOfSec == 0)
		{
			System.out.println();
			System.out.println("Sorry, there are no stocks in the database based on your preference.");
		}
		else 
		{
			System.out.println("*********************************************************************");
			System.out.println("Here is the portfolio we built for you!");
			System.out.println();
			
			// divide capital to invest evenly among all sectors the client wants to invest
			double secCap = stockCap/numOfSec;
			
		// for each sector, print out the stock's symbol, price and shares to invest
		for (String keys:  selectedStocks.keySet())
		{
			int numOfStock = selectedStocks.get(keys).size();
			
			// divide capital to invest in this capital evenly among the stocks the client choosed
			double capital = secCap/numOfStock;
			System.out.println("Sector: " + keys);
			
			// print out the info
			for(String symbol: selectedStocks.get(keys).keySet())
			{
				double price = selectedStocks.get(keys).get(symbol);
				
				System.out.println("	Stock: " + symbol +"; Price: " + price +
						 "; Shares to purchase: " + (int)(capital/price));
			}
		}
		System.out.println("*********************************************************************");
		}			
		
		scanner.close();
	}

	
}
