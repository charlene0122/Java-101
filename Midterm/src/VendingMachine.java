/**
 * CIT 5910 MidTerm Exam: Vending Machine
 * This program implements a Vending Machine program.
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */

/**
 * Main class for controlling the functionality for a Vending Machine 
 * from which a user can enter a product number and some payment and have 
 * the machine dispense the requested product.
 */
public class VendingMachine {

	/**
	 * Inventory of items in the vending machine.
	 */
	Item[] inventory;

	/**
	 * Creates a vending machine with given items.
	 * @param items for vending machine
	 */
	public VendingMachine(Item[] items) {
		this.inventory = items;
	}
	
	/**
	 * Adds the given item to the vending machine inventory.
	 * 
	 * Looks for item in this.inventory.  If it doesn't exist, adds it 
	 * by creating a new inventory array, adding all of the items from this.inventory 
	 * and the new item, and resetting this.inventory = the new array.
	 * 
	 * Returns true if item is added, otherwise false.
	 * 
	 * @param item for vending machine
	 * @return boolean value indicating if the given item was added to the
	 * vending machine's inventory
	 */
	public boolean addItem(Item item) {
	
		//iterate through the inventory list
		for (int i = 0; i < inventory.length; i++)
		{
			//if the item exists in this inventory, return false
			if (item.getID() == inventory[i].getID())
				return false;
		}
		
		//if it doesn't exist, add it to inventory
		//create a new inventory with a size of length + 1
		Item newInventory[] = new Item[inventory.length + 1];
		
		//copy everything from the old inventory array to the new inventory array
		for (int i = 0; i < inventory.length; i++)
		{
			newInventory[i] = inventory[i];
		}
		
		//add the new item to the end of the new array
		newInventory[newInventory.length - 1] = item;
		
		//point to the address of the new array created
		this.inventory = newInventory;
		
		//after the new item is added, return true
		return true;
	}
	
	/**
	 * Removes the item with the given ID from the vending machine inventory.
	 * 
	 * Finds the item in this.inventory and removes it by creating a new 
	 * inventory array, adding all of the items from this.inventory except the 
	 * one to remove, and resetting this.inventory = the new array.
	 * 
	 * Returns true if item is removed, otherwise false.
	 * 
	 * @param itemID of item to remove
	 * @return boolean value indicating if the item with the given ID was removed 
	 * from the vending machine's inventory
	 */
	public boolean removeItem(int itemID) {
	
		//create an variable to mark if the specified item is in the inventory or not
		int removedIndex = -1;
		//iterate through the list to see if the item with given ID is in the machine inventory
		for (int i = 0; i < inventory.length; i++)
		{
			//if item is found in the inventory, change removedIndex to the current index
			if (inventory[i].getID() == itemID)
			{
				removedIndex = i;
				break;
			}
		}
		
		//if the item is not found in the inventory, return false
		if (removedIndex == -1)
			return false;
		
		//if the item is in the inventory, remove it
		//create a new inventory with the size of length - 1
		Item[] newInventory = new Item[inventory.length - 1];
		
		//copy everything before the item removed into the new inventory
		for (int i = 0; i < removedIndex; i++)
		{
			newInventory[i] = inventory[i];
		}
		
		//skip the inventory to be removed, copy everything after into the new inventory
		for (int i = removedIndex; i < newInventory.length; i++)
		{
			newInventory[i] = inventory[i + 1];
		}
		
		//point to the address of the new array created
		this.inventory = newInventory;
		
		//after the item is removed, return true
		return true;
		
	}

	/**
	 * Traverses through the inventory of the vending machine and returns 
	 * an Item whose ID matches the provided itemID.
	 * 
	 * You can assume that the ID matches an Item in the Inventory, and you can
	 * "return null;" in the case that the ID doesn't match an Item.
	 * 
	 * @param itemID to match with the id of an Item in the Inventory
	 * @return the matching Item or null
	 */
	public Item selectItemByNumber(int itemID) {
		
		//iterate through the inventory array
		for (int i = 0; i < inventory.length; i++)
		{
			//if found an item whose ID matches ID provided, return the item
			if (inventory[i].getID() == itemID)
				return inventory[i];
		}
		//if itemID doesn't match any of the item in the inventory, return null
		return null;
	}
	
	/**
	 * Traverses through the inventory of the vending machine and returns 
	 * an Item whose name matches the provided itemName.
	 * 
	 * You can assume that the name matches an Item in the Inventory, and you can
	 * "return null;" in the case that the name doesn't match an Item.
	 * 
	 * @param itemName to match with the name of an Item in the Inventory
	 * @return the matching Item or null
	 */
	public Item selectItemByName(String itemName) {
		
		//iterate through the inventory array
		for (int i = 0; i < inventory.length; i++)
		{
			//if found an item whose name matches name provided, return the item
			if (inventory[i].getName() == itemName)
				return inventory[i];
		}
		//if itemName doesn't match any of the item in the inventory, return null
		return null;
	}

	/**
	 * Attempts to purchase the item with ID matching the given itemID 
	 * using the given Payment.
	 * 
	 * If the given Payment is insufficient given the price of the Item, return an
	 * unsuccessful Transaction with the original Payment, the requested Item, and
	 * isSuccessful set to "false".
	 * 
	 * If the Payment is sufficient given the price of the Item, return a successful
	 * Transaction with its Payment totaling the difference between the original
	 * Payment and the price of the purchased Item. The Transaction should also
	 * contain the purchased Item, and have its isSuccessful flag set to "true".
	 * 
	 * @param itemID of the item to purchase
	 * @param currencyProvided for the purchase
	 * @return successful or unsuccessful transaction
	 * @throws Exception 
	 */
	public Transaction attemptPurchase(int itemID, Payment currencyProvided) throws Exception {
		
		//search item by itemID and store the item in a new variable
		Item itemPurchased = selectItemByNumber(itemID);
		
		//if the item doesn't exist, return null
		if (itemPurchased == null)
			return null;
		
		//calculated the amount of money available at hand
		double amountAtHand = currencyProvided.calculateValue();
		
		//declare a new transaction to be returned later
		Transaction trans;
		
		//if item's price is greater than money at hand, transaction failed
		if (itemPurchased.price > amountAtHand)
			//payment remain unchanged, set transaction status to false
			trans = new Transaction(currencyProvided, itemPurchased, false);
		//if item's price is equal to or smaller than money at hand
		else
			{
			//set payment to the difference between original payment and the price of the purchased item
			try {
			currencyProvided = currencyProvided.makeChangeForPurchase(itemPurchased.price);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
			//return the change as new payment, set transaction status to true
			trans = new Transaction(currencyProvided, itemPurchased, true);
			}
		//return the transaction
		return trans;
	}
	
	/**
	 * @return the inventory for the vending machine
	 */
	public Item[] getInventory() {
		return this.inventory;
	}

	/**
	 * Do not change main!
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		//create inventory
		Item pretzels = new Item(29, "Pretzels", 0.60);
		Item chips = new Item(381, "Chips", 1.25);
		Item[] itemInventory = { pretzels, chips };

		//create vending machine
		VendingMachine v = new VendingMachine(itemInventory);
		
		//add another item
		Item candy = new Item(328, "Candy", 2.85);
		v.addItem(candy);
				

		//give user money
		Payment myMoney = new Payment(2, 1, 1);
		System.out.printf("I have $%.2f\n", myMoney.calculateValue());

		//failed transaction - not enough money
		Transaction failed = v.attemptPurchase(328, myMoney);
		failed.printTransaction();
		myMoney = failed.getPayment(); //no change, no updates to money
		System.out.printf("I now have $%.2f\n", myMoney.calculateValue());
		
		//successful transaction
		Transaction forChips = v.attemptPurchase(381, myMoney);
		forChips.printTransaction();
		myMoney = forChips.getPayment(); //change, updates to money
		System.out.printf("I now have $%.2f\n", myMoney.calculateValue());
		
		//successful transaction
		Transaction forPretzels = v.attemptPurchase(29, myMoney);
		forPretzels.printTransaction();
		myMoney = forPretzels.getPayment(); //change, updates to money
		System.out.printf("I now have $%.2f\n", myMoney.calculateValue());
			
		//successful transaction
		Transaction morePretzels = v.attemptPurchase(29, myMoney);
		morePretzels.printTransaction();
		myMoney = morePretzels.getPayment(); //change, updates to money
		System.out.printf("I now have $%.2f\n", myMoney.calculateValue());
		
		//remove an item
		v.removeItem(328);
		
		//give user more money
		myMoney = new Payment(2, 1, 1);
		System.out.printf(" I have more money now! I have $%.2f\n", myMoney.calculateValue());

		//transaction is null - item no longer available
		int itemID = 328;
		Transaction itemNotAvailable = v.attemptPurchase(itemID, myMoney);
		if (itemNotAvailable == null) {
			System.out.println("--> Item with ID " + itemID + " is not available");
		}
		System.out.printf("I now have $%.2f\n", myMoney.calculateValue());
		
		// INTENDED OUTPUT:
		
		/*
		I have $2.35
		--> $2.35 is insufficient to purchase Candy (ID 328 for $2.85)
		I now have $2.35
		--> Dispensing Chips (ID 381 for $1.25) with change $1.10
		I now have $1.10
		--> Dispensing Pretzels (ID 29 for $0.60) with change $0.50
		I now have $0.50
		--> $0.50 is insufficient to purchase Pretzels (ID 29 for $0.60)
		I now have $0.50
		I have more money now! I have $2.35
		--> Item with ID 328 is not available
		I now have $2.35
		*/


	}

}
