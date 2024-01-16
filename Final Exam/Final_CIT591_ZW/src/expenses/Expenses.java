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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import expenses.file.ExpenseFileParser;
import expenses.file.ExpenseFileReader;

/**
 * Creates and stores monthly expenses.
 * @author lbrandon
 */
public class Expenses {

	/**
	 * List of monthly expenses.
	 */	
	private List<Expense> monthlyExpenses;
	
	/**
	 * Initializes list of monthly expenses.
	 */
	public Expenses() {
		
		//initialize list of expenses
		this.monthlyExpenses = new ArrayList<Expense>();
	}
	
	/**
	 * Gets list of monthly expenses.
	 * @return list of expenses
	 */
	public List<Expense> getMonthlyExpenses() {
		
		// TODO Implement method
		return this.monthlyExpenses;
	}
	
	/**
	 * Converts each expense in the given list to an Expense object,
	 * and adds it to the internal list of monthly expenses.
	 * @param expenses to add
	 */
	public void addExpenses(List<Map<Integer, Double>> expenses) {
		
		// TODO Implement method
		
		// iterate over every map in the list
		for (Map<Integer, Double> current: expenses)
		{
			// get the key of the Map
			Set<Integer> keys = current.keySet();

			// get each key, create an expense object for it and add it to the internal list
			for (Integer key : keys) 
			{
			    Expense temp = new Expense(key, current.get(key));
			    this.monthlyExpenses.add(temp);
			}
		}
		return;	
	}
	
	/**
	 * Adds given Expense object to the internal list of monthly expense objects.
	 * @param expense to add
	 */
	public void addExpense(Expense expense) {
		
		// add the given expense object to the internal list
		this.monthlyExpenses.add(expense);
	}
	
	///// DO NOT CHANGE CODE IN MAIN METHOD! /////
	public static void main(String[] args) {
		
		//load expenses.txt file and get list of expenses
		List<String> expensesList = ExpenseFileReader.loadExpenses("expenses.txt");
		
		//parse list of expenses into a list of expense maps
		List<Map<Integer, Double>> monthlyExpenses = ExpenseFileParser.parseExpenses(expensesList);
				
		//print list of expense maps for debugging purposes
		System.out.println(monthlyExpenses);
		
		//create instance of expenses class
		Expenses expenses = new Expenses();
				
		//add list of maps to internal list of expense objects
		expenses.addExpenses(monthlyExpenses);	
		
	}
}
