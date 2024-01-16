/*
 * DO NOT MODIFY ANYTHING IN THIS FILE!!!!
 */

/**
 * Represents the way that money and items are returned from the Vending Machine 
 * to the user. This class is provided for you in its entirety.
 *
 */
public class Transaction {

	/**
	 * Provided payment for purchase.
	 */
	Payment payment;
	
	/**
	 * Item to be purchased.
	 */
	Item item;
	
	/**
	 * Indicates whether transaction is successful or not.
	 */
	boolean isSuccessful;

	public Transaction(Payment p, Item i, boolean success) {
		this.payment = p;
		this.item = i;
		this.isSuccessful = success;
	}

	/**
	 * @return the value of the item field.
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * @return the value of the payment field.
	 */
	public Payment getPayment() {
		return this.payment;
	}

	/**
	 * @return whether or not this was a successful transaction.
	 */
	public boolean getIsSuccessful() {
		return this.isSuccessful;
	}

	/**
	 * Prints out information about the transaction, displaying different
	 * information based on whether or not the transaction was successful.
	 */
    public void printTransaction() {
        if (this.isSuccessful) {
            System.out.printf("--> Dispensing " + this.item.getName() + " (ID " + this.item.getID() + " for $%.2f) with change $%.2f\n", this.item.getPrice(), this.payment.calculateValue());
        } else {
            System.out.printf("--> $%.2f is insufficient to purchase " + this.item.getName() + " (ID " + this.item.getID() + " for $%.2f)\n", this.payment.calculateValue(), this.item.getPrice());
        }
    }
}
