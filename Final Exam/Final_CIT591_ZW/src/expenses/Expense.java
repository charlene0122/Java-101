/**
 * CIT 5910 Final Exam: Expense Management System
 * This Java program involves implementing an expense management system	.
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than course materials on Canvas and approved online Java documentation. 
 */

package expenses;

/**
 * Represents a single expense for a particular month.
 * @author lbrandon
 */
public class Expense {

	/**
	 * Number of month for expense.
	 */
	private int month;
	
	/**
	 * Amount of expense.
	 */
	private double amount;
	
	/**
	 * Creates Expense with given month number and amount.
	 * @param month for expense
	 * @param amount for expense
	 */
	public Expense(int month, double amount) {
		this.month = month;
		this.amount = amount;
	}
	
	/**
	 * Get month of expense.
	 * @return month
	 */
	public int getMonth() {
		return this.month;
	}
	
	/**
	 * Get amount of expense.
	 * @return amount
	 */
	public double getAmount() {
		return this.amount;
	}
	
	/**
	 * Returns the month number and amount for expense.
	 */
	@Override 
	public String toString() {
		return this.month + " : " + this.amount;
	}
	
	/**
	 * Compares two Expense objects for equality, based on the months and amounts.
	 * If the month and amount of one Expense object is equal to 
	 * the month and amount of the other Expense object, 
	 * the two Expense objects are equal.
	 */
	@Override 
	public boolean equals(Object o) {
		
		// TODO Implement method
		// Hint: Cast given Object o to Expense object
		// Compare month and amount of this Expense to other casted Expense
		
		try 
		{
		    // cast the object to Expense
		    Expense expense = (Expense) o;

		    // compare month and amount of this Expense the casted Expense object
		    if (this.month == expense.month && this.amount == expense.amount)
		        return true;
		    
		    else 
		        return false;
		} 
		catch (ClassCastException e) 
		{
		    // object could not be cast to Expense, return false
		    return false;
		}

	}
}