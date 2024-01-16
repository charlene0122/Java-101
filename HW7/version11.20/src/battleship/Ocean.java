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

import java.util.Random;

/**
 * A class that contains a 10x10 array of Ships, representing an “ocean”
 */
public class Ocean {
	
	/**
	 *  Used to quickly determine which ship is in any given location
	 */
	private Ship[][] ships = new Ship[10][10];
			
	/**
	 * The total number of shots fired by the user.
	 */
	private int shotsFired;
	
	/**
	 * The number of times a shot hit a ship.
	 * If the user shoots the same part of a ship more than once, every hit is counted.
	 */
	private int hitCount;
	
	/**
	 * The number of ships sunk.
	 */
	private int shipsSunk;
	
	/**
	 * A matrix to hold string printed out to the user
	 */
	private String[][] oceanImage = new String[10][10];
	
	/**
	 * Creates an ”empty” ocean and fills the ships array with EmptySea objects.
	 * Initializes any game variables, such as how many shots have been fired.
	 */
	public Ocean()
	{
		// initializes game variables
		shotsFired = 0; 
		hitCount = 0; 
		shipsSunk = 0;
		
		// fills the ships array with EmptySea objects
		for (int i = 0; i < 10; i++) 
		{ 
			for (int j = 0; j < 10; j++) 
			{
				EmptySea emptySea = new EmptySea();
				emptySea.placeShipAt(i, j, true, this);
				// initialize the string print out to users
				oceanImage[i][j] = "."; 
			}
		}
	}
	
	/**
	 * Place all ten ships randomly on the (initially empty) ocean. 
	 */
	void placeAllShipsRandomly()
	{
		//use random to generate random coordinates
		Random random = new Random();
		Ship[] ships = new Ship[10];
		
		//use for loop to create ships
		for (int i = 0; i < 10; i++) 
		{ 
			//Place larger ships before smaller ones
			if (i == 0) {
				ships[i] = new Battleship();	
			} else if (i < 3) {
				ships[i] = new Cruiser();
			} else if (i < 6) {
				ships[i] = new Destroyer();
			} else if (i < 10) {
				ships[i] = new Submarine();
			}
		}
		
		//randomly place the ships
		for (Ship ship : ships) { 
			while (true) {
				int row = random.nextInt(10); 
				int column = random.nextInt(10); 
				boolean horizontal = random.nextBoolean(); 
				
				//if it's legal to place the ship, place it
				if (ship.okToPlaceShipAt(row, column, horizontal, this)) 
				{ 
					ship.placeShipAt(row, column, horizontal, this); 
					break;
				}
			}
		}
	}
	
	/**
	 * @param row
	 * @param column
	 * @return true if the given location contains a ship, false if it does not
	 */
	boolean isOccupied(int row, int column)
	{
		// return true if the given location contains a ship, false if it does not
		return !ships[row][column].getShipType().equals("empty"); 
	}
	
	/**
	 * updates the number of shots that have been fired and the number of hits
	 * @param row
	 * @param column
	 * @return true if the given location contains a ”real” ship that is still afloat
	 */
	boolean shootAt(int row, int column)
	{
		//updates the number of shots that have been fired and the number of hits
		this.shotsFired++; 
		
		//if the position contains a ship
		if (isOccupied(row, column)) 
		{ 
			// shoot at the ship
			if (ships[row][column].shootAt(row, column)) 
			{ 
				// if this shoot makes ship sunk, print a message and increase number of ships sunk
				if (ships[row][column].isSunk()) { 
					System.out.println("You just sunk a " + ships[row][column].getShipType());
					shipsSunk++;
				}
				// reveal string to the user once the position is shot
				oceanImage[row][column] = ships[row][column].toString(); 
				hitCount++;
				
				//true if the given location contains a ”real” ship that is still afloat
				return true;
			}
			// if shootAt(row, column) returns false, the ship is already sunk, return false
			return false;
		} 
		
		//if the position is empty sea, reveal string to the user
		else 
		{
			oceanImage[row][column] = ships[row][column].toString(); 
		} 
		return false;
	}
	
	/**
	 * @return the number of shots fired
	 */
	int getShotsFired()
	{
		return shotsFired;
	}
	
	/**
	 * @return the number of hits recorded 
	 */
	int getHitCount()
	{
		return hitCount;
	}
	
	/**
	 * @return the number of ships sunk 
	 */
	int getShipsSunk()
	{
		return shipsSunk;
	}
	
	/**
	 * @return true if all ships have been sunk
	 */
	boolean isGameOver()
	{
		return shipsSunk == 10;
	}
	
	/**
	 * @return the 10x10 array of Ships
	 */
	Ship[][] getShipArray()
	{
		return ships;
	}

	/**
	 * print the ocean
	 */
	void print()
	{
		// display column numbers along the top
		System.out.println("    0 1 2 3 4 5 6 7 8 9");
		System.out.println("   ---------------------");
		
		// iterate through each position in the ocean
		for (int i = 0; i < 10; i++) 
		{
		    System.out.print(i + " |");
		    for (int j = 0; j < 10; j++) 
		    {
		    	// for alignment purpose
		    	System.out.print(" ");
		    	
		    	// if a ship is sunk, print its string, which should be "s"
		    	if (ships[i][j].isSunk()) 
		    		System.out.print(ships[i][j].toString());
		    	// otherwise, print string stored in oceanImage
		    	else 
		    		System.out.print(oceanImage[i][j]);
		    }
		    System.out.println(" |");
		}
	}
	
	
	/**
	 * print the ocean and show the location of the ships
	 */
	void printWithShips()
	{
		// display column numbers along the top
		System.out.println("    0 1 2 3 4 5 6 7 8 9");
		System.out.println("   ---------------------");
		
		// iterate through each position in the ocean
		for (int i = 0; i < 10; i++) 
		{
		    System.out.print(i + " |");
		    for (int j = 0; j < 10; j++) 
		    {
		    	// if it is a ship, see what kind of ship it is, and print out
		    	if (isOccupied(i,j)) 
		    	{ 
		    		//‘b’: Use ‘b’ to indicate a Battleship.
		    		if (ships[i][j].getShipType().equals("battleship")) 
		    		{
		    			System.out.print(" b");
		    		}
		    		
		    		//‘c’: Use ‘c’ to indicate a Cruiser.
		    		else if(ships[i][j].getShipType().equals("cruiser")) 
		    		{
		    			System.out.print(" c");
		    		}
		    		
		    		//‘d’: Use ‘d’ to indicate a Destroyer.
		    		else if(ships[i][j].getShipType().equals("destroyer")) 
		    		{
		    			System.out.print(" d");
		    		}
		    		
		    		// ‘s’: Use ‘s’ to indicate a Submarine.
		    		else if(ships[i][j].getShipType().equals("submarine")) 
		    		{
		    			System.out.print(" s");
		    		}
		    	} 
		    	else {
			        	System.out.print("  ");
		    		}
		    }System.out.println(" |");
		}
	}
}
