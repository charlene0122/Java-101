/**
 * CIT 5910 HW7: Battleship
 * Implementing a simple version of the Battleship game.
 * 
 * @author Di Yang & Zhifei Wu
 * Penn ID: 74113922 30406975
 * This java file is solely completed by the author mentioned above, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */

package battleship;

public class Submarine extends Ship {
	/**
	 * hard-coded length for each ship
	 */
	static final int length = 1;

	/**
	 * hard-coded name for each ship
	 */
	static final String name = "submarine";
	
	/**
	 * call the constructor in the super class with the hard-coded length
	 */
	public Submarine() {
		super(length);
	}

	/**
	 * return name of the ship
	 */
	@Override
	public String getShipType() {
		return name;
	}

}
