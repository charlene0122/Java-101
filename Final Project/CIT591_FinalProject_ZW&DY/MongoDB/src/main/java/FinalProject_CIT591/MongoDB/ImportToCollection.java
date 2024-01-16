package FinalProject_CIT591.MongoDB;

import static com.mongodb.client.model.Filters.eq;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Decimal128;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.UpdateResult;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * This class is used to import stock data from the Yahoo Finance API into a MongoDB collection.
 */
public class ImportToCollection 
{
	/**
	 * The URI for the MongoDB database.
	 */
	private String uri;
	
	/**
	 * The MongoDB client used to connect to the database.
	 */
    private MongoClient client;
    
    /**
     * The MongoDB database containing the sector's data.
     */
    private MongoDatabase database;
    
    /**
     * The MongoDB collection containing the sector's companies.
     */
    private MongoCollection<Document> collection;
    
    /**
	The average price-to-earnings ratio and price-to-book ratio for the sector.
    */
    private double averagePE, averagePB;
    
    /**
     * Constructs an ImportToCollection object with the specified MongoDB URI, database name, and collection name.
     *
     * @param uri the MongoDB URI
     * @param database the database name
     * @param collection the collection name
     */
    public ImportToCollection(String uri, String database, String collection)
    {
    	this.uri = uri;
    	this.client = MongoClients.create(this.uri);
    	this.database = client.getDatabase(database);
    	this.collection = this.database.getCollection(collection);
    	this.averagePE = this.averagePB = 0;
    }
    

    /**
     * Gets the MongoDB URI.
     *
     * @return the MongoDB URI
     */
    public String getURI()
    {
    	return this.uri;
    }
    
    /**
     * Gets the MongoDB client.
     *
     * @return the MongoDB client
     * @throws Exception if there is an error getting the client
     */
    public MongoClient getClient() throws Exception
    {
    	return this.client;
    }
    

	/**
	 * Gets the MongoDB database with the specified name.
	 *
	 * @param database the database name
	 * @return the MongoDB database
	 * @throws Exception if there is an error getting the database
	 */
    public MongoDatabase getDatabase(String database) throws Exception
    {
    	return this.database;
    }
    
    /**
     * Gets the MongoDB collection with the specified name.
     *
     * @param collection the collection name
     * @return the MongoDB collection
     * @throws Exception if there is an error getting the collection
     */
    public MongoCollection<Document> getCollection(String collection) throws Exception
    {
    	return this.collection;
    }
    
    /**
     * Gets the stock info for the specified stock symbol.
     *
     * @param symbol the stock symbol
     * @return a Document containing the stock info
     * @throws IOException if there is an error getting the stock info from the Yahoo Finance API
     */
    public Document getStockInfo(String symbol) throws IOException
    {
    	// create a document to store all the stock's info
    	Document document = new Document();
        	
    	// get the stock by its symbol
    	try 
    	{
    			Stock stock = YahooFinance.get(symbol);
   
    			// symbol to look up the stock
    			document.append("Symbol", symbol)
    			
    			
    			
    			// price related statistics
                .append("Price",  stock.getQuote().getPrice())
                .append("PriceAvg50",  stock.getQuote().getPriceAvg50())
                .append("%ChangeFromAvg50",  stock.getQuote().getChangeFromAvg50InPercent())
                .append("PriceAvg200",  stock.getQuote().getPriceAvg200())
                .append("%ChangeFromAvg200",  stock.getQuote().getChangeFromAvg200InPercent())
                .append("YearHigh",  stock.getQuote().getYearHigh())
                .append("YearLow",  stock.getQuote().getYearLow())
                
                
                // financial statistics
                .append("MarketCap", stock.getStats().getMarketCap())
                .append("PE", stock.getStats().getPe())
                .append("PEG", stock.getStats().getPeg())
                .append("EPS", stock.getStats().getEps())
                .append("EpsEstimateCurrentYear", stock.getStats().getEpsEstimateCurrentYear())
                .append("EpsEstimateNextQuarter", stock.getStats().getEpsEstimateNextQuarter())
                .append("EpsEstimateNextYear", stock.getStats().getEpsEstimateNextYear())
                .append("PriceBook", stock.getStats().getPriceBook())
                .append("PriceSales", stock.getStats().getPriceSales() )
                .append("BookValuePerShare", stock.getStats().getBookValuePerShare() )
                .append("Revenue", stock.getStats().getRevenue())
                .append("ROE", stock.getStats().getROE())
                .append("EBITDA", stock.getStats().getEBITDA())
                .append("ShortRatio", stock.getStats().getShortRatio() )
                
                // dividend statistics
                .append("DividendAnnualYield", stock.getDividend().getAnnualYield())
                .append("DividendAnnualYield%", stock.getDividend().getAnnualYieldPercent());
    			
    	}
    	catch(Exception error)
    	{
    		System.out.println("Stock " + symbol + " does not exist in Yahoo Finance API. Failed to import.");
    		return document;
    	}
    	
    	return document;
        
    }
    
    /**
     * Adds the specified stock to the collection, either by updating an existing stock or adding a new one.
     *
     * @param symbol the stock symbol
     * @throws IOException if there is an error getting the stock info from the Yahoo Finance API
     */
    public void addStock(String symbol) throws IOException
    {
        Document stock = getStockInfo(symbol);
    	// if the stock already exist in the collection, update it, if not, add it
        Bson query = eq("Symbol", symbol);
        ReplaceOptions opts = new ReplaceOptions().upsert(true);
        UpdateResult result = collection.replaceOne(query, stock, opts);
        int count = (int) result.getModifiedCount();
        
        // if stock does not exist, print out the new id
        if (count == 0)
        	 System.out.println(symbol + " is added to the collection with ID: " + result.getUpsertedId()); 
        // if stock exist and get updated, print a message
        else
        	System.out.println(symbol + " is updated.");	
    }
    
    /**
     * Calculate the average Price-Earnings (PE) ratio for a given sector and store it in averagePE.
     * @return the average PE for the sector, if it has been calculated; 0 otherwise
     */
    public double calculatePE()
    {
    	// create a list to store values
    	List<Double> peList = new ArrayList<>(); 
    	
    	// get each pe and store them in the list
    	FindIterable<Document> documents = collection.find();
    	for (Document doc : documents) {
    		if (doc.get("PE") instanceof Decimal128) 
    		{
    		    Decimal128 decimal = (Decimal128) doc.get("PE");
    		    double pe = decimal.doubleValue();
    		    peList.add(pe);
    		} 
    	}
    	
    	// calculate and return
    	double sum = peList.stream().mapToDouble(Double::doubleValue).sum();
    	double average = sum / peList.size();
    	
    	averagePE = average;
		
    	return average;
    }
    
    /**
     * Gets the average price-to-earnings ratio for the sector.
     * @return The average price-to-earnings ratio for the sector.
     */
    public double getPE()
    {
    	if (averagePE == 0)
    	{
    		System.out.println("PE for the sector has not been calculated, please calculate first.");
    	}
    	return averagePE;
    }
    
    /**
     * Calculates the average price-to-book ratio for the sector and sets the value in averagePB.
     * @return The average price-to-book ratio for the sector.
     */
    public double calculatePB()
    {
    	// create a list to store values
    	List<Double> pbList = new ArrayList<>(); 
    	
    	// get each pe and store them in the list
    	FindIterable<Document> documents = collection.find();
    	for (Document doc : documents) {
    		{
    			if(doc.getDouble("PB") != null)
    			{double pb = doc.getDouble("PB");
    		    pbList.add(pb);}
    		} 
    	}
    	
    	// calculate and return
    	double sum = pbList.stream().mapToDouble(Double::doubleValue).sum();
    	double average = sum / pbList.size();
    	
    	averagePB = average;
		
    	return average;
    }
    
    /**
     * Gets the average price-to-book ratio for the sector.
     * @return The average price-to-book ratio for the sector.
     */
    public double getPB()
    {
    	if (averagePB == 0)
    	{
    		System.out.println("PB for the sector has not been calculated, please calculate first.");
    	}
    	return averagePB;
    }
    
    /**
     * Filters the sector's companies by market capitalization.
     * @param filter The filter for the sector's companies.
     * @param marketCap The desired market capitalization type (1-5).
     * @return The updated filter for the sector's companies.
     */
    public Document filterMarketCap(Document filter, int marketCap)
    {
    	if (marketCap == 1)
    	{
    		filter.append("MarketCap", new Document("$lt",  new Decimal128((long)250000000)));
    	}
    	
    	else if (marketCap == 2)
    	{
    		filter.append("MarketCap", new Document("$gte",new Decimal128((long) 250000000)))
                    .append("MarketCap", new Document("$lt", new Decimal128((long)2000000000)));
    	}
    	
    	else if (marketCap == 3)
    	{
    		filter.append("MarketCap", new Document("$gte", new Decimal128((long)2000000000)))
                    .append("MarketCap", new Document("$lt", new Decimal128((long)1000*0000000)));
    	}
    	
    	else if (marketCap == 4)
    	{
    		filter.append("MarketCap", new Document("$gte", new Decimal128((long)1000*0000000)))
                    .append("MarketCap", new Document("$lt", new Decimal128((long)2000*00000000)));
    	}
    	
    	else if (marketCap == 5)
    	{
    		filter.append("MarketCap", new Document("$gte", new Decimal128((long)2000*00000000)));         
    	}
    	
    	else 
    	{
    		System.out.println("Market Capital type out of bound, filter failed.");
    		return null;
    	}
    		
    	return filter;
    }
    
    /**
     * Filters the sector's companies by dividend yield.
     * @param filter The filter for the sector's companies.
     * @param dividend True if only companies with dividends should be included in the filter, false otherwise.
     * @return The updated filter for the sector's companies.
     */
    public Document filterDiv(Document filter, boolean dividend)
    {
    	if (dividend)
    	{
    		filter.append("DividendAnnualYield", new Document("$gt", 0));
    	}
    	else if(!dividend)
    	{
    		filter.append("DividendAnnualYield", new Document("$eq", 0));
    	}
    	else
    	{
    		System.out.println("Null input argument, filter failed.");
    		return null;
    	}
    	
    	return filter;
    }
    
    /**
     * Filters the sector's companies by growth or value.
     * @param filter The filter for the sector's companies.
     * @param growthOrValue True if only growth companies should be included in the filter, false for value companies.
     * @return The updated filter for the sector's companies.
     */
    public Document filterGrowthOrValue(Document filter, boolean growthOrValue)
    {
    	calculatePB();
    	calculatePE();
    	
    	if(averagePE == 0 || averagePB == 0)
    	{
    		System.out.println("Sector PE or PB is unknown, please calculate PE and PB first.");
    		return null;
    	}
   
    	// growth stock should contain those whose pe is greater than sector average
    	if(growthOrValue)
    	{
    		filter.append("PE", new Document("$gte", new Decimal128((long)averagePE)));
   		}
    	
    	// value stock should contain those whose pe is smaller than sector average
    	else if(!growthOrValue)
    	{
    		filter.append("PE", new Document("$lt", new Decimal128((long)averagePE)));
    	}
    	
    	else
    	{
    		System.out.println("Null input argument, filter failed.");
    		return null;
    	}   
    	return filter;
    }
    
    /**
     * Filters the sector's companies by market capitalization, dividend yield, and growth or value.
     * @param marketCap The desired market capitalization type (1-5).
     * @param div True if only companies with dividends should be included in the filter, false otherwise.
     * @param growth True if only growth companies should be included in the filter, false for value companies.
     * @return The list of filtered stocks.
    */
    public ArrayList<HashMap<String, Double>> filterStock(int marketCap, boolean div, boolean growth)
    {
    	// create a list to store output later
    	ArrayList<HashMap<String, Double>> filteredStock = new ArrayList<HashMap<String, Double>>();
    	
    	// put all the filter together
    	Document filter = new Document();
    	filter = filterMarketCap(filter, marketCap);
    	filter = filterDiv(filter, div);
    	filter = filterGrowthOrValue(filter, growth);
    	
    	// filter stock and sort output based on a variable which indicates volatility of stock, to measure the stock's risk level
    	FindIterable<Document> stocks = collection.find(filter).sort(new Document("YearChangeInPercentage", 1));
    	
    	// put the stock's symbol and price info into an array list of hashmap and return it
    	for (Document doc: stocks)
    	{
    		String symbol = doc.getString("Symbol");
    		double price = 0;
    		if (doc.get("Price") instanceof Decimal128) 
    		{
    			Decimal128 decimal = (Decimal128) doc.get("Price");
    			price = decimal.doubleValue();
    		}
    		HashMap<String, Double> temp = new HashMap<String, Double>();
    		temp.put(symbol, price);
    		filteredStock.add(temp);
    	}
    	
    	return filteredStock;
    }
}
    








