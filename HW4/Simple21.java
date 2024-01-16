/**
 * CIT 5910 HW4: Simple21
 * Design a simplified version of the common card game "Blackjack".
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */

import java.util.Random;
import java.util.Scanner;

public class Simple21 {

	/**
	 * Entry point to the program. This should not be modified.
	 * Creates instance of Simple21 class and calls run().
	 * @param args
	 */
	public static void main(String[] args) {

		Simple21 simple21 = new Simple21();
		simple21.run();
	}
	
	/**
	 * This method runs and controls the overall flow of the program.
	 */
	void run() {

		//print the game instructions
		printInstructions();

		//create scanner for user input
		Scanner scanner = new Scanner(System.in);

		//get and set user's name
		System.out.println("What's your name?");
		String username = scanner.next();

		//set computer's name
		String computerName = "Computer";

		//TODO my code as below
		//play the game
		play(username, computerName, scanner);
		
		//ask the user whether to play again
		boolean playAgain = askYesOrNo("Play again? (y/n) ", scanner);
		
		//if user says yes
		if(playAgain == true)
		{
			//ask the user whether to change name or not
			boolean newName = askYesOrNo("Modify username? (y/n) ", scanner);
			
			//if user say yes, modify the name
			if(newName == true)
			{
				System.out.println("Update your name?");
				username = scanner.next();
			}
			//play the game again
			play(username, computerName, scanner);
		}
		else
			//if user does not want to play again, close the scanner and exit the program
			scanner.close();
	}

	/**
	 * This method controls the game and logic for the given user and computer.
	 * @param username for the user
	 * @param computerName for the computer
	 * @param scanner for getting user input
	 */
	void play(String username, String computerName, Scanner scanner) {

		//TODO my code as below
		//declare the variables
		int userHiddenPoint, userVisiblePoint, userTotalPoint, computerHiddenPoint, computerVisiblePoint, computerTotalPoint;
		
		//initialize the variables, or reset the variables when playing again
		userHiddenPoint = userVisiblePoint = userTotalPoint = 0;
		computerVisiblePoint = computerHiddenPoint = computerTotalPoint= 0;
				
		userHiddenPoint += nextCard();		//generate a random card and add to hidden points
		userVisiblePoint += nextCard();		//generate a random card and add to visible points
		userTotalPoint = userHiddenPoint + userVisiblePoint;		//calculate total point
		printStatus(true, username, userHiddenPoint, userVisiblePoint, userTotalPoint);	//print the status
		
		computerVisiblePoint += nextCard();	//generate a random card and add to visible points
		computerHiddenPoint += nextCard();	//generate a random card and add to hidden points
		computerTotalPoint = computerHiddenPoint + computerVisiblePoint;	//calculate total point
		printStatus(false, computerName, computerHiddenPoint, computerVisiblePoint, computerTotalPoint); //print status
		
		//ask the user and the computer if they want to take another card
		//variables that store whether another card should be taken
		boolean userMove, computerMove;
		
		//variables that store whether the user and the computer have passed
		boolean userPassed = false, computerPassed = false;
		
		//while user hasn't passed
		while(userPassed == false)
		{
		//ask the user whether to take another card or not
		userMove = askYesOrNo("Take another card? (y/n)", scanner);
		//if input is yes
		if(userMove == true)
		{
			//computer should determine whether to take card at the same time with the user
			computerMove = takeAnotherCard(computerTotalPoint, userVisiblePoint);
			
			//generate a new card and add it to user's visible point and total point
			int newCard = nextCard();
			userVisiblePoint += newCard;
			userTotalPoint += newCard;
			
			//print the new card and the new status
			System.out.println(username + " gets " + newCard);
			printStatus(true, username, userHiddenPoint, userVisiblePoint, userTotalPoint);
			
			//if computer decides to take a card and the computer has not passed
			if(computerMove == true && computerPassed ==false)
			{
				//generate a new card and add it to computer's visible point and total point
				newCard = nextCard();
				computerVisiblePoint += newCard;
				computerTotalPoint += newCard;
				
				//print the new card and the new status
				System.out.println(computerName + " gets " + newCard);
				printStatus(false, computerName, computerHiddenPoint, computerVisiblePoint, computerTotalPoint);
			}
			//if the computer decides not to take card and the computer has not passed
			else if(computerMove == false && computerPassed == false)
			{
				//change computer status to passed and print a message
				computerPassed = true;
				System.out.println(computerName + " passed!");
			}
		}
		//if the user decide not to take a card
		else
		{
			//change user status to passed and print a message
			userPassed = true;
			System.out.println(username + " passed!");
		}
		}
		//if the user passed and computer hasn't, start this loop
		while(computerPassed == false) {
			//ask computer to determine whether it should take a new card
			computerMove = takeAnotherCard(computerTotalPoint, userVisiblePoint);
			
			//if the computer decides to take another card
			if(computerMove == true)
			{
				//generate a new card and add it to computer's visible point and total point
				int newCard = nextCard();
				computerVisiblePoint += newCard;
				computerTotalPoint += newCard;
				
				//print the new card and the new status
				System.out.println(computerName + " gets " + newCard);
				printStatus(false, computerName, computerHiddenPoint, computerVisiblePoint, computerTotalPoint);
			}
			
			//if the computer decides not to take a card
			else 
			{
				//change computer status to passed and print a message
				computerPassed = true;
				System.out.println(computerName + " passed!");
			}
		}
		//check if game is over, if it is, print the winner message
		if(isGameOver(userPassed, computerPassed))
		{
			printWinner(username, userTotalPoint, computerName, computerTotalPoint);
		}
	}

	/**
	 * Prints out instructions for the game.
	 */
	void printInstructions() {
		
		//TODO my code as below
		//print the instruction message 
		System.out.println("Let's play Simple21!\n"
				+ "You'll play against the computer.\n"
				+ "Try to get as close to 21 as possible, without going over.");
	}
	
	/**
	 * Displays the given prompt and asks the user for input using the given scanner.  
	 * If the user's input starts with "y", returns true.
	 * If the user's input starts with "n", returns false.
	 * 
	 * For example, calling askYesOrNo("Do you want to play again? (y/n) ") 
	 * would display "Do you want to play again? (y/n) ", wait for user input that starts with "y" or "n",
	 * and return true or false accordingly.
	 * 
	 * @param prompt to display before getting user input
	 * @return true if user input starts with "y", false if user input starts with "n"
	 */
	boolean askYesOrNo(String prompt, Scanner scanner) {

		//TODO my code as below
		System.out.println(prompt);		//print the question to ask the user
		boolean output = true;			//declare and initialize the variable to return
		
		//take user input, convert it all to lower case and strip the blank space at the front
		String response = scanner.next().strip().toLowerCase();	
		
		//if user input does not start with y or n, ask again until valid input is get
		while (!response.startsWith("y") && !response.startsWith("n"))
		{
			System.out.println(prompt);
			response = scanner.next().strip().toLowerCase();
		}
		//if the input starts with y, return true
		if(response.startsWith("y"))
		{
			output = true;
		}
		
		//if the input starts with n, return false
		else if (response.startsWith("n"))
		{
			output = false;
		}
		
		return output;
		
	}

	/**
	 * Returns a random "card", represented by an int between 1 and 10, inclusive.
	 * The "cards" are the numbers 1 through 10 and they are randomly generated, not drawn 
	 * from a deck of limited size.  The odds of returning a 10 are four times as likely 
	 * as any other value (because in an actual deck of cards, 10, Jack, Queen, and King all count as 10).
	 * @return random int between 1 and 10, inclusive
	 */
	int nextCard() {

		//TODO my code as below
		Random random = new Random();			//create instance of Random
		int cardPoint = random.nextInt(13) + 1;	//generate a random number from 0 to 12 and plus 1 to change the range to 1-13
		
		//if the number is greater than 10, set it to 10
		while(cardPoint >10)
		{
			cardPoint--;
		}
		
		//return the number generated
		return cardPoint;
	
	}

	/**
	 * Strategy for computer to take another card or not.  According to the computer’s own given
	 * total points (sum of visible cards + hidden card) and the user's sum of visible cards, you
	 * need to design a game strategy for the computer to win the game.
	 * 
	 * Returns true if the strategy decides to take another card, false if the computer decides not
	 * to take another card.
	 * 
	 * @param computerTotalPoints for the computer, the sum of visible cards + hidden card.
	 * @param userVisibleCards for the user, the sum of visible cards.
	 * @return true if the computer will take another card
	 */
	boolean takeAnotherCard(int computerTotalPoints, int userVisibleCards) {

		//TODO my code as below
		//if computer's current point is smaller than 11, any number from 1-10 will make output < 21, so take a card
		if(computerTotalPoints <= 11)
				{
			return true;
				}
		//if total point is smaller than 16 and user visible card is more than computer total point, try taking another card
		else if(computerTotalPoints <=15 && computerTotalPoints < userVisibleCards)
		{
			return true;
		}
		//if computer has 16 point or more, pass
		else
			return false;
	}

	/**
	 * Determines if the game is over or not.
	 * If the given isUserPassed is set to true, the user has passed.
	 * If the given isComputerPassed is set to true, the computer has passed.
	 * This method returns true if both the user and the computer have passed,
	 * and false if either of them has not yet passed.
	 * @param isUserPassed is true if the user has passed
	 * @param isComputerPassed is true if the computer has passed
	 * @return true if both the user and the computer have passed 
	 */
	boolean isGameOver(boolean isUserPassed, boolean isComputerPassed) {

		//TODO my code as below
		//if both user and the computer pass, print the message and return true
		if(isUserPassed == true && isComputerPassed == true)
		{
			System.out.println("-- Game Over --");
			return true;
		}
		//if either the user or the computer has not passed, return false.
		else
			return false;
		
	}

	/**
	 * In each turn, prints out the current status of the game.
	 * If isUser is set to true, the given player is the user.  In this case, print out
	 * the user's given name, his/her hidden card points, visible card points, and total points.
	 * If isUser is set to false, the given player is the computer.  In this case, print out
	 * the computer's given name, and his/her visible card points.
	 * 
	 * For example, calling printStatus(true, "Brandon", 4, 15, 19) would print:
	 * Brandon has 4 hidden point(s).
	 * Brandon has 15 visible point(s).
	 * Brandon has 19 total point(s).
	 * 
	 * As another example, calling printStatus(false, "Computer", 1, 19, 20) would print:
	 * Computer has 19 visible point(s).
	 * 
	 * @param isUser true if the given player is the user, false if the given player is the computer
	 * @param name of the given player
	 * @param hiddenCard the given player's hidden card points
	 * @param visibleCard the given player's visible card points
	 * @param totalPoints for the given player, the sum of visible cards + hidden card
	 */
	void printStatus(boolean isUser, String name, int hiddenCard, int visibleCard, int totalPoints) {

		//TODO my code as below
		//if a user is specified(by setting isUser to true), print all the info we have
		if(isUser)
		{
			System.out.println(name + " has " + hiddenCard + " hidden point(s).");
			System.out.println(name + " has " + visibleCard + " visible point(s).");
			System.out.println(name + " has " + totalPoints + " total point(s).");
		}
		//if a computer is specified(by setting isUser to false), print only the computer's visible point
		else
			System.out.println(name + " has " + visibleCard + " visible point(s).");
	}

	/**
	 *  Determines who won the game and prints the game results in the following format:
	 *  - User's given name and the given user's total points
	 *  - Computer's given name and the given computer's total points
	 *  - The player who won the game and the total number of points he/she won by, or if it's a tie, nobody won
	 *  
	 * @param username for the user
	 * @param userTotalPoints for the user, the sum of visible cards + hidden card
	 * @param computerName for the computer
	 * @param computerTotalPoints for the computer, the sum of visible cards + hidden card
	 */
	void printWinner(String username, int userTotalPoints, String computerName, int computerTotalPoints) {

		//TODO my code as below
		
		//print user's total point and computer's total point
		System.out.println(username + " has " + userTotalPoints + " in total.");
		System.out.println(computerName + " has " + computerTotalPoints + " in total.");
		
		//if the user's total point exceeds 21
		if(userTotalPoints > 21)
		{
			//if computer's total point also exceeds 21, nobody wins, print the message
			if(computerTotalPoints > 21)
				System.out.println("Nobody wins.");
			//if computer's total point doesn't exceed 21, computer wins, print the point computer wins by
			else 
				System.out.println(computerName + " wins by " + (userTotalPoints - computerTotalPoints) + " point(s).");
		}
		
		//if computer's total point exceeds 21
		else if(computerTotalPoints > 21)
		{
			//if user's total point also exceeds 21, nobody wins, print the message
			if(userTotalPoints > 21)
				System.out.println("Nobody wins.");
			//if user's total point doesn't exceed 21, user wins, print the point user wins by
			else
				System.out.println(username + " wins by " + (computerTotalPoints - userTotalPoints) + " point(s).");
		}
		
		//if the both computer and the user's point is below 21 and they got the same point, 
		//it's a tie, print a message
		else if(userTotalPoints == computerTotalPoints)
		{
			System.out.println("Nobody wins. It's a tie!");
		}
		
		//if the both computer and the user's point is below 21 and user point is greater than computer's, 
		//user wins, print the point user wins by
		else if(userTotalPoints > computerTotalPoints)
		{
			System.out.println(username + " wins by " + (userTotalPoints - computerTotalPoints) + " point(s).");
		}
		
		//if the both computer and the user's point is below 21 and user point is smaller than computer's, 
		//computer wins, print the point computer wins by
		else 
		{
			System.out.println(computerName + " wins by " + (computerTotalPoints - userTotalPoints) + " point(s).");
		}
		
	}
}
