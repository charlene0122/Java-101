/**
 * CIT 5910 Final Exam: Expense Management System
 * This Java program involves implementing an expense management system	.
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than course materials on Canvas and approved online Java documentation. 
 */


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import expenses.Expense;
import expenses.Expenses;

class ExpenseManagementTest {

	//define instance of expenses class for testing
	Expenses expenses;
	
	@BeforeEach
	void setUp() throws Exception {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		this.expenses = new Expenses();
		
		//initialize individual expenses and add for testing
		Expense expense = new Expense(12, 2.34);
		this.expenses.addExpense(expense);
		
		expense = new Expense(10, 98.34);
		this.expenses.addExpense(expense);
		
		expense = new Expense(2, 44.00);
		this.expenses.addExpense(expense);
		
		expense = new Expense(12, 12.01);
		this.expenses.addExpense(expense);
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
	}

	@Test
	void testGetMonthlyExpensesIntListOfExpense() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create list of expected monthly expense amounts
		List<Double> monthlyExpensesExpected = new ArrayList<Double>();
		monthlyExpensesExpected.add(98.34);
		
		//get actual monthly expense amounts for oct (10)
		List<Double> monthlyExpenses = ExpenseManagement.getMonthlyExpenses(10, this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected, monthlyExpenses, "The expected list of monthly expenses for Oct. do not match the actual list of monthly expenses for Oct.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 2 additional test cases
        // Hint(s): Create additional lists of expected monthly expense amounts 
		// and compare to actual monthly expense amounts for particular months
		
		// Case 1:
		//create list of expected monthly expense amounts
		monthlyExpensesExpected = new ArrayList<Double>();
		monthlyExpensesExpected.add(44.00);
		
		//get actual monthly expense amounts for feb (2)
		monthlyExpenses = ExpenseManagement.getMonthlyExpenses(2, this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected, monthlyExpenses, "The expected list of monthly expenses for Feb. do not match the actual list of monthly expenses for Feb.");

		// Case 2:
		//create list of expected monthly expense amounts
		monthlyExpensesExpected = new ArrayList<Double>();
		monthlyExpensesExpected.add(2.34);
		monthlyExpensesExpected.add(12.01);
		
		//get actual monthly expense amounts for dec (12)
		monthlyExpenses = ExpenseManagement.getMonthlyExpenses(12, this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected, monthlyExpenses, "The expected list of monthly expenses for Dec. do not match the actual list of monthly expenses for Dec.");

		
	}

	@Test
	void testGetMonthlyExpensesStringListOfExpense() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create list of expected monthly expense amounts
		List<Double> monthlyExpensesExpected = new ArrayList<Double>();
		monthlyExpensesExpected.add(98.34);
		
		//get actual monthly expense amounts for oct
		List<Double> monthlyExpenses = ExpenseManagement.getMonthlyExpenses("oct", this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected, monthlyExpenses, "The expected list of monthly expenses for Oct. do not match the actual list of monthly expenses for Oct.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 2 additional test cases
        // Hint(s): Create additional lists of expected monthly expense amounts 
		// and compare to actual monthly expense amounts for particular months

		// Case 1:
		//create list of expected monthly expense amounts
		monthlyExpensesExpected = new ArrayList<Double>();
		monthlyExpensesExpected.add(44.00);
		
		//get actual monthly expense amounts for feb (2)
		monthlyExpenses = ExpenseManagement.getMonthlyExpenses("feb", this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected, monthlyExpenses, "The expected list of monthly expenses for Feb. do not match the actual list of monthly expenses for Feb.");

		// Case 2:
		//create list of expected monthly expense amounts
		monthlyExpensesExpected = new ArrayList<Double>();
		monthlyExpensesExpected.add(2.34);
		monthlyExpensesExpected.add(12.01);
		
		//get actual monthly expense amounts for dec (12)
		monthlyExpenses = ExpenseManagement.getMonthlyExpenses("dec", this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected, monthlyExpenses, "The expected list of monthly expenses for Dec. do not match the actual list of monthly expenses for Dec.");

		
	}

	@Test
	void testGetTotalMonthlyExpenses() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//get actual total monthly expense amount for oct
		double totalMonthlyExpenses = ExpenseManagement.getTotalMonthlyExpenses("oct", this.expenses.getMonthlyExpenses());
		
		//confirm it is 98.34
		assertEquals(98.34, totalMonthlyExpenses, "The expected total of monthly expenses for Oct. does not match the actual total of monthly expenses for Oct.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 2 additional test cases
        // Hint(s): Test other total monthly expense amounts for particular months
		
		// Case 1:
		//get actual total monthly expense amount for feb
		totalMonthlyExpenses = ExpenseManagement.getTotalMonthlyExpenses("feb", this.expenses.getMonthlyExpenses());
				
		//confirm it is 44.00
		assertEquals(44.00, totalMonthlyExpenses, "The expected total of monthly expenses for Feb. does not match the actual total of monthly expenses for Feb.");
		
		// Case 2:
		//get actual total monthly expense amount for dec
		totalMonthlyExpenses = ExpenseManagement.getTotalMonthlyExpenses("dec", this.expenses.getMonthlyExpenses());
				
		//confirm it is 14.35
		assertEquals(14.35, totalMonthlyExpenses, "The expected total of monthly expenses for Dec. does not match the actual total of monthly expenses for Dec.");

	}

	@Test
	void testGetMostExpensiveMonth() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create expense object for expected most expensive month 10 (oct)
		Expense mostExpensiveMonthCompare = new Expense(10, 98.34);
		
		//get expense object for actual most expensive month
		Expense mostExpensiveMonth = ExpenseManagement.getMostExpensiveMonth(this.expenses.getMonthlyExpenses());
		
		//compare expected expense object with actual expense object
		assertEquals(mostExpensiveMonthCompare, mostExpensiveMonth, "The expected most expensive month does not match the actual most expensive month.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 2 additional test cases
        // Hint(s): Create additional expense object for a particular month and add to monthly expenses
		// Test if it's the most expensive month
		
		// Case 1:
		// create additional expense object
		Expense expense = new Expense(11, 102.93);
		this.expenses.addExpense(expense);
		expense = new Expense(3, 15.01);
		this.expenses.addExpense(expense);

		//create expense object for expected most expensive month 11 (nov)
		mostExpensiveMonthCompare = new Expense(11, 102.93);
		
		//get expense object for actual most expensive month
		mostExpensiveMonth = ExpenseManagement.getMostExpensiveMonth(this.expenses.getMonthlyExpenses());
		
		//compare expected expense object with actual expense object
		assertEquals(mostExpensiveMonthCompare, mostExpensiveMonth, "The expected most expensive month does not match the actual most expensive month.");
		

		
		// Case 2:
		// create additional expense object
		expense = new Expense(2, 108.00);
		this.expenses.addExpense(expense);
		
		expense = new Expense(12, 12.01);
		this.expenses.addExpense(expense);
		

		//create expense object for expected most expensive month 2 (feb)
		mostExpensiveMonthCompare = new Expense(2, 152);
		
		//get expense object for actual most expensive month
		mostExpensiveMonth = ExpenseManagement.getMostExpensiveMonth(this.expenses.getMonthlyExpenses());
		
		//compare expected expense object with actual expense object
		assertEquals(mostExpensiveMonthCompare, mostExpensiveMonth, "The expected most expensive month does not match the actual most expensive month.");
	
		
	}

}
