# Project Portfolio Recommendation

## Description

This project takes in a cvs file with a list of stock and corresponding sectors as input, perform query on stock info through Yahoo Finance API, and directly store the query result on mongoDB database. 

After data are uploaded, the program asks users a set of questions to evaluate their risk capacity and get their investment preference. Based on users' info, the program search through the database, take user's info as to filter a list of stocks that match their preference and capacity, and return them to the user.

## How to Use

This is a maven project. The following dependencies must be added so pom.xml so that the project can be connected to mongoDB and the YahooFinance API:

	<dependency>
        	<groupId>org.mongodb</groupId>
        	<artifactId>mongodb-driver-sync</artifactId>
        	<version>4.8.0</version>
   	</dependency>
    

	<dependency>
		<groupId>com.yahoofinance-api</groupId>
    		<artifactId>YahooFinanceAPI</artifactId>
   		<version>3.17.0</version>
	</dependency>

The mongoDB URI is included in the java file.

2727 stock's info have already been uploaded onto mongoDB, the uploading process can be done every time when you start the program, but it would take 5-8 minutes. If you want to skip the part of data uploading, you can comment the following code on line 50 of "PortfolioRecommendation.java" and type in "no" when the program ask you whether you want to update stock information.

	// comment the following code if want to save time from data uploading
	nasdaq.BatchAdd();

## Known Issues

When the program runs, red words will be printed on the console, these are not error messages but program logging information. Please wait until the red words stops popping up and instructions on how to continue the program appears.


