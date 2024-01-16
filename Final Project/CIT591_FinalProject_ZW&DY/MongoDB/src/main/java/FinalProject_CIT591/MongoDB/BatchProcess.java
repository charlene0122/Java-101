package FinalProject_CIT591.MongoDB;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A class that processes a CSV file containing stock information and adds or updates the information in a MongoDB database.
 * The CSV file must contain the stock symbol and sector for each stock.
 */
public class BatchProcess {
	
	  // The path to the CSV file containing the stock information
	  private String stockCSV;

	  // The URI of the MongoDB database
	  private String mongoURI;

	  // A map of stock symbols to sectors, parsed from the CSV file
	  private HashMap<String, String> stocks;

	  // A set of unique sectors from the CSV file
	  private HashSet<String> sectors;

	  // A map of sectors to ImportToCollection objects for inserting or updating stocks in the database
	  private HashMap<String, ImportToCollection> collections;
	  
	  // The date when the info is updated
	  private String infoDate;
	
	/**
	 * @return the stockCSV field
	 */
	public String getStockCSV()
	{
	    return stockCSV;
	}

	/**
	 * @return the mongoURI field
	 */
	public String getMongoRUI()
	{
	    return mongoURI;
	}

	/**
	 * @return the stocks field
	 */
	public HashMap<String,String> getStocks()
	{
	    return stocks;
	}

	/**
	 * @return the sectors field
	 */
	public HashSet<String> getSectors()
	{
	    return sectors;
	}

	/**
	 * @return the collections field
	 */
	public HashMap<String, ImportToCollection> getCollections()
	{
	    return collections;
	}

	/**
	 * @return the infoDate field
	 */
	public String getInfoDate()
	{
	    return infoDate;
	}
	
	/**
	 * Creates a new BatchProcess instance with the given stock CSV file and MongoDB URI.
	 *
	 * @param csv the path to the stock CSV file
	 * @param uri the MongoDB URI
	 */
	public BatchProcess(String csv, String uri)
	{
		this.stockCSV = csv;
		this.mongoURI = uri;
		this.stocks = ParseCSV.parseFile(stockCSV);
		
		// iterate through the stock list and store unique value of sectors in an HashSet
		this.sectors = new HashSet<String>();
		for (HashMap.Entry<String, String> set :
            this.stocks.entrySet()) {
			this.sectors.add(set.getValue());
		}
		
		// get all the collections from mongoDB
		collections = new HashMap<String, ImportToCollection>();
		for (String sec:sectors)
		{
			collections.put(sec, new ImportToCollection(mongoURI, "Stock", sec));
		}

	}
	
	
	/**
	 * Adds or updates the stocks in the database.
	 *
	 * @throws IOException if there is an error adding or updating the stocks
	 */
	private void AddOrUpdate() throws IOException
	{
		// for every stock in the HashMap
		for (HashMap.Entry<String, String> set :
            this.stocks.entrySet()) 
		{
			// get its symbol and sector
			String symbol = set.getKey();
        	String sector = set.getValue();
            
        	// add it to the collection Sector, which contains all the stock
        	collections.get("Sector").addStock(symbol);
        	
        	// add it to the corresponding collection for each sector on mongoDB
        	for (HashMap.Entry<String, ImportToCollection> col : 
        		this.collections.entrySet()) 
        	{
    			if (sector.equals(col.getKey()))
    				col.getValue().addStock(symbol);
    		}
		}
		
		// update infoDate to today's date
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Calendar cal = Calendar.getInstance();
	    Date date = cal.getTime();
	    infoDate = dateFormat.format(date);
	    
	}
	
	/**
	 * Adds new stocks to the database.
	 *
	 * @throws IOException if there is an error adding the stocks
	 */
	public void BatchAdd() throws IOException
	{
		AddOrUpdate();
	    System.out.println("Stocks added to database on "+ infoDate);
	    
	}
	
	/**
	 * Updates the existing stocks in the database.
	 *
	 * @throws IOException if there is an error updating the stocks
	 */
	public void BatchUpdate() throws IOException
	{
		AddOrUpdate();
	    System.out.println("Stocks in database are updated on "+ infoDate);
	}
	

}
