/**
 * CIT 5910 MidTerm Exam: Vending Machine
 * This program implements a Vending Machine program.
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a sum of money.  Its fields track how many dollars ($1.00), 
 * quarters ($0.25), and dimes ($0.10) the Payment consists of.
 */
public class Payment {

	/**
	 * Numbers of dollars.
	 */
	int dollars;
	
	/**
	 * Numbers of quarters.
	 */
	int quarters;
	
	/**
	 * Number of dimes.
	 */
	int dimes;

	/**
	 * Creates a payment with given number of dollars, quarters, and dimes.
	 * @param numDollars number of dollars
	 * @param numQuarters number of quarters
	 * @param numDimes number of dimes
	 */
	public Payment(int numDollars, int numQuarters, int numDimes) {
		this.dollars = numDollars;
		this.quarters = numQuarters;
		this.dimes = numDimes;
	}

	/**
	 * Returns the value of this payment based on the number of dollars, quarters,
	 * and dimes. The value of a dollar is 1.0, the value of a quarter is 0.25, 
	 * and the value of a dime is 0.1.
	 * 
	 * The returned value should be rounded to 2 decimal places.
	 * @return total value rounded to 2 decimal places
	 */
	public double calculateValue() {
		
		//calculate the value of payment based on number of dollars, quarters and dimes
		double totalValue = 1.0 * dollars + 0.25 * quarters + 0.1 * dimes;
		
		//round result into two decimal places and return the result
		BigDecimal bd = new BigDecimal(totalValue);		//create big decimal with double value
		bd = bd.setScale(2, RoundingMode.HALF_UP);		//set config for big decimal
		Double output = bd.doubleValue();				//get rounded value
		return output;									//return the result
	}

	/**
	 * Given a particular itemCost, calculates the difference between this Payment
	 * amount and that cost. This is known as the "change" from the purchase, such
	 * that change + itemCost == this.calculateValue().
	 * 
	 * The change should be returned as a Payment, i.e. an object containing an
	 * integer amount of dollars, quarters, and dimes.
	 * 
	 * You can assume that the change can always be expressed as a sum of dollars,
	 * quarters, and dimes, or a sum of dollars and quarters, or a sum of dollars.
	 * That is, you might have $1.50 in change (1 dollar, 2 quarters) or $1.60 (1
	 * dollar, 2 quarters, 1 dime). You don't have to worry about making change for
	 * $1.40, which could only be expressed as 1 dollar, 4 dimes.
	 * 
	 * Throws an Exception if the given itemCost is greater than this
	 * Payment amount, i.e. itemCost > this.calculateValue(). Exception should 
	 * contain a message in a similar format to: 
	 * $2.30 is too expensive.  You only have $2.20.
	 * 
	 * @param itemCost for particular item
	 * @return A Payment containing an integer amount of dollars, quarters, and dimes
	 * @throws Exception if the given itemCost is greater than this Payment amount
	 */
	public Payment makeChangeForPurchase(double itemCost) throws Exception {
		
		//convert amount of dollars, quarters and dimes into the value of the payment
		double value = calculateValue();
		
		//throws exception if the given itemCost is greater than this Payment amount
		if (value < itemCost)
			throw new Exception("$" + itemCost + " is too expensive. You only have $" + value + ".");
			
		//calculate the the difference between this payment amount and the item cost
		//convert amount from dollar base to cent base by multiplying 100
		int changeValue = (int) (value * 100) - (int) (itemCost * 100);
		
		//calculate amount of dollars
		int changeDollar = changeValue / 100;
		
		//calculate amount left after dollars are distributed
		changeValue = changeValue % 100;
		
		//calculate amount of quarters
		int changeQuarter = changeValue / 25;
		
		//calculate amount left after quarters are distributed
		changeValue = changeValue % 25;
		
		//calculate amount of dimes
		int changeDime = changeValue / 10;
		
		//create a new payment object and store amount just calculated into this object
		Payment change = new Payment(changeDollar, changeQuarter, changeDime);
		
		//return the new payment object
		return change;
	}	
}