/*
 * DO NOT MODIFY ANYTHING IN THIS FILE!!!!
 */

/** 
 * A simple class associating a price, an item ID, and an item name together to 
 * represent an Item. The only things here are fields, getters, and a constructor.
 * This class is provided for you in its entirety. 
 */
public class Item {

	/**
	 * ID of item.
	 */
	int id;
	
	/**
	 * Name of item.
	 */
	String name;
	
	/**
	 * Price of item.
	 */
	double price;

	/**
	 * Creates an item with given ID, name, and price.
	 * @param itemID of item
	 * @param itemName of item
	 * @param itemPrice of item
	 */
	public Item(int itemID, String itemName, double itemPrice) {
		this.id = itemID;
		this.name = itemName;
		this.price = itemPrice;
	}

	/**
	 * @return ID of item
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * @return name of item.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return price of item.
	 */
	public double getPrice() {
		return this.price;
	}

}
