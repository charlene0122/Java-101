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
 * The test class for vending machine
 */
class VendingMachineTest {
	
	//declare payment for testing
		VendingMachine v;
		
		//runs before each method
		@BeforeEach
		void setUp() throws Exception {
			
			//initialize vending machine here
			//create inventory
			Item pretzels = new Item(29, "Pretzels", 0.60);
			Item chips = new Item(381, "Chips", 1.25);
			Item candy = new Item(328, "Candy", 2.85);
			Item[] itemInventory = { pretzels, chips, candy };

			//create vending machine
			v = new VendingMachine(itemInventory);
		}
		
	@Test 
	void testAddItem() {
		
		//add new item that doesn't exist
		Item newItem = new Item(4, "Chocolate", 2.99);
		boolean added = v.addItem(newItem);
		
		//check that it was added
		assertTrue(added);
		
		//check new length of array
		assertEquals(4, v.getInventory().length);
		
		//check that item is in inventory
		assertNotEquals(null, v.selectItemByNumber(newItem.getID()));
		assertEquals(newItem.getID(), v.selectItemByNumber(newItem.getID()).getID());
		
		
		//add another new item that doesn't exist
		newItem = new Item(5, "Milky Way", 3.25);
		added = v.addItem(newItem);
				
		//check that it was added
		assertTrue(added);
				
		//check new length of array
		assertEquals(5, v.getInventory().length);
		
		//check that item is in inventory
		assertNotEquals(null, v.selectItemByNumber(newItem.getID()));
		assertEquals(newItem.getID(), v.selectItemByNumber(newItem.getID()).getID());
		
		
		//Hint:
		//- Consider a test case for adding a new item that already exists 
		//in the inventory
		//newItem "Milky Way" was just added so it's in the inventory
		added = v.addItem(newItem);				
		
		//check that it was not added
		assertFalse(added);
				
		//check if the length of the inventory remains the same
		assertEquals(5, v.getInventory().length);
		
		//check that item is in inventory
		assertNotEquals(null, v.selectItemByNumber(newItem.getID()));
		assertEquals(newItem.getID(), v.selectItemByNumber(newItem.getID()).getID());
		
	}
	
	@Test 
	void testRemoveItem() {
		
		//remove item that exists
		boolean removed = v.removeItem(381);
		
		//check that it was removed
		assertTrue(removed);
		
		//check new length of array
		assertEquals(2, v.getInventory().length);
		
		//check that item is no longer in inventory
		assertEquals(null, v.selectItemByNumber(381));
		
		
		//remove another item that exists
		removed = v.removeItem(29);
		
		//check that it was removed
		assertTrue(removed);
		
		//check new length of array
		assertEquals(1, v.getInventory().length);
		
		//check that item is no longer in inventory
		assertEquals(null, v.selectItemByNumber(29));
		
		//Hint:
		//- Consider a test case for removing an item that doesn't exist 
		//in the inventory
		removed = v.removeItem(500);
		
		//check that it wasn't removed
		assertFalse(removed);
		
		//check if the length of the array remains the same
		assertEquals(1, v.getInventory().length);
		
		//check that item is not in inventory
		assertEquals(null, v.selectItemByNumber(29));
		
	}
	
	@Test
	void testSelectItemByNumber() {
		
		//existing item 
		Item item = v.selectItemByNumber(381);
		assertEquals(381, item.getID());
		assertEquals("Chips", item.getName());
		
	
		//Hint:
		//- Consider a test case for selecting an item that exists
		item = v.selectItemByNumber(29);
		
		//check if inventory ID matches
		assertEquals(29, item.getID());
		
		//check if inventory name matches
		assertEquals("Pretzels", item.getName());
		
		//check if inventory price matched
		assertEquals(0.60, item.getPrice());
		
		
		//- Consider a test case for selecting an item that doesn't exist
		item = v.selectItemByNumber(500);
		
		//check if the item is null
		assertEquals(null, item);	
	}
	
	@Test
	void testSelectItemByName() {
		
		//existing item 
		Item item = v.selectItemByName("Chips");
		assertEquals(381, item.getID());
		assertEquals("Chips", item.getName());
		
		
		//TODO Add 2 more test cases here
		//Hint:
		//- Consider a test case for selecting an item that exists
		item = v.selectItemByName("Candy");
		
		//check if inventory ID matches
		assertEquals(328, item.getID());
		
		//check if inventory name matches
		assertEquals("Candy", item.getName());
		
		//check if inventory price matches
		assertEquals(2.85, item.getPrice());
		
		
		//- Consider a test case for selecting an item that doesn't exist
		item = v.selectItemByName("Coke");
		
		//check if inventory is null
		assertEquals(null, item);
		
	}
	
	
	@Test
	void testAttemptPurchase() throws Exception {
		
		//create payment
		Payment myMoney = new Payment(2, 1, 1);
		
		
		//unsuccessful transaction
		//create transaction
		Transaction failedTransaction = v.attemptPurchase(328, myMoney);
		
		//get returned payment
		myMoney = failedTransaction.getPayment();
		
		//check isSuccessful value
		assertFalse(failedTransaction.getIsSuccessful());
		
		//check that returned payment has same value
		assertEquals(2.35, myMoney.calculateValue(), 0.000001);
		
		//check that returned item has same id
		assertEquals(328, failedTransaction.getItem().getID());
		
		
		//successful transaction
		//create transaction
		Transaction forChips = v.attemptPurchase(381, myMoney);
		
		//get returned payment
		myMoney = forChips.getPayment();
		
		//should be successful
		//check isSuccessful value
		assertTrue(forChips.getIsSuccessful());
				
		//check that returned payment has correct value of change
		assertEquals(1.10, myMoney.calculateValue(), 0.000001);
				
		//check that returned item has correct id
		assertEquals(381, forChips.getItem().getID());

		
		//TODO Add 1 more test case here
		//Hint:
		//- Consider a test case for another successful transaction
		//create transaction
		Transaction forPretzels = v.attemptPurchase(29, myMoney);
				
		//get returned payment
		myMoney = forPretzels.getPayment();
				
		//should be successful
		//check isSuccessful value
		assertTrue(forPretzels.getIsSuccessful());
						
		//check that returned payment has correct value of change
		assertEquals(0.5, myMoney.calculateValue(), 0.000001);
						
		//check that returned item is the correct one
		assertEquals(29, forPretzels.getItem().getID());

	}

}
