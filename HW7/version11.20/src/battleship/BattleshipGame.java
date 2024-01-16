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

import java.util.Scanner;

public class BattleshipGame {
	
	/**
	 * ocean of the game
	 */
	private Ocean ocean;
	
	/**
	 * the constructor of the class
	 */
	public BattleshipGame(){}
	
	/**
	 * print the current scores and current ocean image
	 */
	public void printMessage()
	{
		System.out.println("Current scores: ");
		System.out.println("  Shots: " + ocean.getShotsFired());
		System.out.println("  Hits: " + ocean.getHitCount());
		System.out.println("  Ship(s) sunk: " + ocean.getShipsSunk());
		System.out.println("Type quit if you want to leave \n");
		ocean.print();
	}
	
	/**
	 * implement the execution of the whole game
	 * @param scanner pass in scanner to read user input
	 */
	public void playGame(Scanner scanner)
	{
		// print a welcome message
		System.out.println("Welcome to the BattleShip Game! \n");
		
		// initialize the ocean of the game
		this.ocean = new Ocean();
		
		// set up the 10 ships
		this.ocean.placeAllShipsRandomly();
		
		// print initial status of the game
		printMessage();
		
		while (!ocean.isGameOver()) 
		{
			System.out.print("Type in the place to shoot at: row,coloumn \n");
			String input = scanner.nextLine();
			
			// if user wants to quit, end the game
			if (input.equals("quit")) 
			{
				break;
			}
			
			try
			{
				String[] point = input.split(",");
				
				// tell user whether he hit the ship or missed it
				if (ocean.shootAt(Integer.parseInt(point[0]), Integer.parseInt(point[1]))) 
				{
					System.out.println("Congratulations! You hit it, continue!");
				} 
				else 
				{
					System.out.println("You missed it, please try again \n");
				}
			} 
			// handle your exception
			catch(Exception ex)
			{ 
				System.out.println("Invalid input, please type again. ");
				System.out.println("Make sure to follow the format: row,column, with no space in between.");
				System.out.println("Row and column number must be between 0 - 9.");
				System.out.println("Example input: 1,1 \n");
			}
			
			// print the current score of the game
			printMessage();
		}
		
		System.out.println("Game over!");
		// if all ship are sunk, user wins, print the final score
		if (ocean.isGameOver())
			System.out.println("You win! Your final score is " + ocean.getShotsFired());
	}

	public static void main(String[] args) {
		
		// create the scanner
		Scanner scanner = new Scanner(System.in);
		
		// create an object to launch the game
		BattleshipGame game = new BattleshipGame();
		
		// set initial status to true
		String whetherPlay = "y";
		
		// while the user wants to play again, keep playing the game
		while(whetherPlay.startsWith("y")) 
		{
			game.playGame(scanner);
			System.out.println("Play again? y or n: \n");
			whetherPlay = scanner.nextLine().strip().toLowerCase();
		}
		
		// close the scanner
		scanner.close();
	}
}
