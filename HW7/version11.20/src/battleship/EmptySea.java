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

public class EmptySea extends Ship {
	/**
	 * hard-coded length for each ship
	 */
	static final int length = 1;

	/**
	 * hard-coded name for each ship
	 */
	static final String name = "empty";
	
	/**
	 * call the constructor in the super class with the hard-coded length
	 */
	public EmptySea() {
		super(length);
	}

	/**
	 * return name of the ship
	 */
	@Override
	public String getShipType() {
		return name;
	}

	/**
	 * always returns false to indicate that nothing was hit
	 */
	@Override
	boolean shootAt(int row, int column)
	{
		return false;
	}
	
	/**
	 * always returns false to indicate that you didn’t sink anything
	 */
	@Override
	boolean isSunk()
	{
		return false;
	}
	
	/**
	 * returns the single-character “-“ String to use in the Ocean’s print method
	 */
	@Override
	public String toString()
	{
		return "-";
	}
	

}
