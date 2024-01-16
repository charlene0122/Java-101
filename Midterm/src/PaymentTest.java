/**
 * CIT 5910 MidTerm Exam: Vending Machine
 * This program implements a Vending Machine program.
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *The test class for Payment Class
 */
class PaymentTest {
	
	//declare payment for testing
	Payment myMoney;
	
	//runs before each method
	@BeforeEach
	void setUp() throws Exception {
		//initialize payment here
		myMoney = new Payment(2, 1, 1);
	}
	
	@Test
	void testCalculateValue() {
		//create payment
		//Payment myMoney = new Payment(2, 1, 1);
		assertEquals(2.35, myMoney.calculateValue(), 0.000001);
		
		//create another payment
		myMoney = new Payment(5, 0, 0);
		assertEquals(5.00, myMoney.calculateValue(), 0.000001);
		
		
		//Hint:
		//- Consider a test case for a payment of 1 dime
		myMoney = new Payment(0, 0, 1);
		//check if calculated value is 0.1
		assertEquals(0.10, myMoney.calculateValue(), 0.000001);
		
		
		//- Consider a test case for a payment of 0 dollars, quarters, and dimes
		myMoney = new Payment(0, 0, 0);
		//check if calculated value is 0
		assertEquals(0.00, myMoney.calculateValue(), 0.000001);
	}
		
	
	@Test
	void testMakeChangeForPurchase() throws Exception {
		//create payment
		//Payment myMoney = new Payment(2, 1, 1);
		
		//try to get change
		try {
			myMoney = myMoney.makeChangeForPurchase(1.25);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//check value of change
		assertEquals(1.10, myMoney.calculateValue(), 0.000001);
		
		
		//Hint:
		//- Consider a test case for making change for a purchase of 1.10
		//try to get change
		
		//make sure that code does not throw exception
		assertDoesNotThrow(() -> {
			myMoney = myMoney.makeChangeForPurchase(1.10);
		});
		
		//check value of change
		assertEquals(0.00, myMoney.calculateValue(), 0.000001);
		
		//- Consider a test case for making change for a purchase of 0.01
		//test whether exception can be properly thrown and whether the return value is null
		assertThrows(Exception.class, () -> {
			myMoney = myMoney.makeChangeForPurchase(0.01);
		});
		
		//make sure payment value doesn't change after the attempt
		assertEquals(0.00, myMoney.calculateValue(), 0.000001);
						
	}

}
