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

/**
 * An abstract class describes the characteristics common to all ships
 */
public abstract class Ship {
	
	/**
	 * Row that contains the bow.
	 */
	private int bowRow;
	
	/**
	 * Column that contains the bow.
	 */
	private int bowColumn;
	
	/**
	 * Length of the ship.
	 */
	private int length;
	
	/**
	 * Represents whether the ship is going to be placed horizontally or vertically.
	 */
	private boolean horizontal;
	
	/**
	 * An array of booleans that indicate whether that part of the ship has been hit or not.
	 */
	private boolean[] hit;
	
	
	/**
	 * Create a ship with given length and initializes the hit array based on that length.
	 * @param length of the ship
	 */
	public Ship(int length)
	{
		this.length = length;
		
		// initialize the hit array by ship length and initialize every element to false
		hit = new boolean[this.length];

	}
	
	/**
	 * @return length of the ship
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * @return the row corresponding to the position of the bow
	 */
	public int getBowRow()
	{
		return bowRow;
	}
	
	/**
	 * @return the bow column location
	 */
	public int getBowColumn()
	{
		return bowColumn;
	}
	
	/**
	 * @return the hit array
	 */
	public boolean[] getHit()
	{
		return hit;
	}
	
	/**
	 * @return whether the ship is horizontal or not
	 */
	public boolean isHorizontal()
	{
		return horizontal;
	}
	
	/**
	 * @param row the value of bowRow to set
	 */
	public void setBowRow(int row)
	{
		this.bowRow = row;
	}
	
	/**
	 * @param column the value of bowColumn to set
	 */
	public void setBowColumn(int column)
	{
		this.bowColumn = column;
	}
	
	/**
	 * @param horizontal the value of the instance variable horizontal to set
	 */
	public void setHorizontal(boolean horizontal)
	{
		this.horizontal = horizontal;
	}
	
	/**
	 * @return the type of ship as a String
	 */
	public abstract String getShipType();

	/**
	 * examine if a ship of given length and bow can be placed in this location
	 * @param row row of bow
	 * @param column column of bow
	 * @param horizontal whether the bow is horizontal or not
	 * @param ocean ocean the ship to be placed
	 * @return true if a ship of given length and bow can be placed in this location
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean)
	{
		// if row and column is out of bound, return false
		if (row > 9 || column > 9)
			return false;
		
		// examine the position surrounding the ship
		int startRow = row + 1, startColumn = column + 1;
		
		// if ship start on border of the sea, no need to check surrounding beyond border
		if (row == 9)
			startRow = 9;
		if (column == 9)
			startColumn = 9;
		
		int endRow, endColumn;
		
		// if horizontal, set end column
		if (horizontal)
		{
			endRow = row;
			endColumn = column - length + 1;
		}
		
		// if vertical, set end row
		else
		{
			endRow = row - length + 1;
			endColumn = column;
			
		}
		
		// if end of the ship surpass border of ocean, return false
		if (endRow < 0 || endColumn < 0)
			return false;
		
		// examine the surrounding of the ship
		endRow--;
		endColumn--;
		
		// if end of the ship is on the border, no need to check surrounding beyond border
		if (endRow <0)
			endRow = 0;
		if (endColumn < 0)
			endColumn = 0;
		
		// iterate through the area to check, if any spot is occupied, return false
		for (int i = startRow; i >= endRow; i--)
		{
			for (int j = startColumn; j >= endColumn; j--)
			{
				if (ocean.isOccupied(i, j))
					return false;
			}
		}
		
		// otherwise, ok to place boat
		return true;
	}
	
	/**
	 * puts the ship in the ocean 
	 * @param row row of bow
	 * @param column column of bow
	 * @param horizontal whether the bow is horizontal or not
	 * @param ocean ocean the ship to be placed
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean)
	{
		// assign the parameters to the current boat
		bowRow = row;
		bowColumn = column;
		this.horizontal = horizontal;
		
		for (int i = 0; i < length; i++)
		{
			// if horizontal, place it along the row
			if (horizontal)
			{
				ocean.getShipArray()[row][column - i] = this;
			}
			
			// if vertical, place it along the column
			else
			{
				ocean.getShipArray()[row - i][column] = this;
			}
		}
	}
	
	/**
	 * mark given part of the ship as “hit” if the ship is afloat
	 * @param row row to hit
	 * @param column column to hit
	 * @return true if a part of the ship occupies the given row and column
	 */
	boolean shootAt(int row, int column)
	{
		// if it is sunk, return false
		if (isSunk())
			return false;
		
		// use index to store position relative to the ship
		int index;
		if (horizontal)
		{
			// get the position relative to the ship
			index = bowColumn - column;
			
			// if the position is on the ship, set hit to true and return true
			if (bowRow == row && index >= 0 && index < length)
			{
				hit[index] = true;
				return true;
			}
		}
		
		if (!horizontal)
		{
			// get the position relative to the ship
			index = bowRow - row;
			
			// if the position is on the ship, set hit to true and return true
			if (bowColumn  == column && index >= 0 && index < length)
			{
				hit[index] = true;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return true if every part of the ship has been hit
	 */
	boolean isSunk()
	{
		// if any element in the hit array is false, the ship is not sunk
		for (boolean b : hit)
		{
			if (!b)
				return false;
		}
		
		// if all elements are true, the ship is sunk
		return true;
	}
	
	/**
	 * @return ”s” if the ship has been sunk and ”x” if it has not been sunk
	 */
	@Override
	public String toString()
	{
		if(isSunk())
			return "s";
		return "x";
	}
	
	
}
