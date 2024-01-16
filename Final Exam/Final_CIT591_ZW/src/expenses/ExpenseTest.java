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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpenseTest {

	@Test
	void testEquals() {

		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create individual expenses
		Expense expense1 = new Expense(12, 2.34);
		Expense expense2 = new Expense(12, 2.34);
		
		//compare for equality
		assertEquals(expense1, expense2, "The 2 expenses should be considered equal. They are for the same amount and the same month.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 1 additional test case
        // Hint(s): Create additional expense objects and compare
		//create individual expenses
		Expense expense3 = new Expense(10, 1.34);
		Expense expense4 = new Expense(12, 2.34);
		
		//compare for equality
		assertTrue(expense1.equals(expense2), "The 2 expenses should be considered equal. They are for the same amount and the same month.");
		assertFalse(expense3.equals(expense4), "The 2 expenses should not be considered equal. They are for different month and different amonut.");
		
	}

}
