/**
 * CIT 5910 HW5: Pick Your Letters
 * Design a game that involves re-arranging a group of cards in order to make up a word.
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents a human player.
 *
 */
public class Human {
	
	/**
	 * Human player's hand.
	 */
	String[] handCards;
	
	/**
	 * Access to main pile and discard pile.
	 */
	Cards cards;
	
	/**
	 * Creates a human player.
	 * Gives access to the main pile and discard pile via given instance of Cards class.
	 * @param cards for access to main pile and discard pile
	 */
	public Human(Cards cards) {
		this.cards = cards;
	}
	
	/**
	 * Sets human player's hand.
	 * @param handCards to set
	 */
	public void setHandCards(String[] handCards) {
		this.handCards = handCards.clone();
	}
	
	/**
	 * Gets human player's hand.
	 * @return human player's hand
	 */
	public String[] getHandCards() {
		return this.handCards;
	}
	
	/**
	 * Controls the game play for the human.
	 * @param scanner to use for user input
	 */
	public void play(Scanner scanner) {
		
		//print a message to indicate the start of human's turn
		System.out.print("Your turn. ");
		
		//print human's card
		printHandCards();
		
		//ask the user whether to pick the card from the discard pile or to reveal letter from main pile
		System.out.println("Pick \"" + cards.discardPile[0]+ "\" from DISCARD PILE or reveal the letter from MAIN PILE\n"
				+ "Reply \"D\" or \"M\" to respond:");
		
		//take user input, convert it all to lower case and strip the blank space at the front
		String response = scanner.next().strip().toLowerCase();	
		
		//if user input does not start with m or d, ask again until valid input is get
		while (!response.startsWith("m") && !response.startsWith("d"))
		{
			System.out.println("Please enter a valid response:");
			response = scanner.next().strip().toLowerCase();
		}
		
		//if user wants to reveal the card in main pile
		if (response.startsWith("m"))
			{
			//reveal the card
			System.out.println("The letter from MAIN PILE is \"" + cards.mainPile[0] + "\"");
			
			//ask user whether to accept it or not
			boolean whetherAccept = askYesOrNo("Do you want to accept this letter? Type \"y/yes\" to accept, "
					+ "\"n/no\" to discard.", scanner);
			
			//if user decides to accept
			if (whetherAccept)
			{
				//ask user which card he/she wants to replace 
				int index = askForTheLetterToBeReplaced(handCards.length, scanner);
				
				//get the first card from the main pile
				String letter = cards.getFirstFromMainPileAndRemove();
				
				//discard card at specified index into the discard pile
				cards.addToDiscardPile(handCards[index]);
				
				//replace it with the card from main pile
				handCards[index] = letter;
				
				//print out a message to show user action completed
				System.out.println("You replaced \"" + cards.discardPile[0] +"\" with \"" + letter + "\"");
			}
			
			//if user decides to discard
			else
			{
				//print a message to indicate action completed
				System.out.println("You discarded \""+ cards.mainPile[0] + "\" from MAIN PILE");
				//add the card to the discard file
				cards.addToDiscardPile(cards.getFirstFromMainPileAndRemove());
			}
			
			}
		//if user wants to take the card from the discard pile
		if (response.startsWith("d"))
			{
			//ask user which card to replace
			int index = askForTheLetterToBeReplaced(handCards.length, scanner);
			
			//take the first card from the discard pile
			String letter = cards.getFirstFromDiscardPileAndRemove();
			
			//discard card at specified index into the discard pile
			cards.addToDiscardPile(handCards[index]);
			
			//replace it with card from the discard pile
			handCards[index] = letter;
			
			//print out the action completed
			System.out.println("You replaced \"" + cards.discardPile[0] +"\" with \"" + letter + "\"");
			
			}
		//show user their hand card after the rearrangement
		printHandCards();
		
		//print a separate line to indicate the end of a session
		System.out.println("-----------------------------------------------");
	}
	/**
	 * Print hand cards for the human
	 */
	public void printHandCards()
	{
		System.out.println("Your hand cards are: "+ Arrays.asList(this.handCards));
	}
	
	/**
	 * Displays (prints) the given msg and uses the given scanner to get user input 
	 * of a response.
	 * The expected response is yes/y or no/n.
	 * Prompts again if the response is invalid.
	 * @param msg to be displayed
	 * @return true if user answers yes/y, or false if user answers no/n
	 */
	public boolean askYesOrNo(String msg, Scanner scanner) {
		
		//print the question to ask the user
		System.out.println(msg);
		
		//declare and initialize the variable to return
		boolean output = true;			
		
		//take user input, convert it all to lower case and strip the blank space at the front
		String response = scanner.next().strip().toLowerCase();	
		
		//if user input does not start with y or n, ask again until valid input is get
		while (!response.startsWith("y") && !response.startsWith("n"))
		{
			System.out.println(msg);
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
	 * Asks the index of letter the user wants to replace.
	 * Prompts again if the input index is invalid or out of index range.
	 * @param length the valid index range should be in 0 to given length
	 * @param scanner to be used for user input
	 * @return the index of the letter to be replaced
	 */
	public int askForTheLetterToBeReplaced(int length, Scanner scanner) {
	    
		//print out the instruction
		System.out.println("Input the index of the letter to be replaced, e.g. \"1\":");
		
		//create a variable to store use input
		int output = 0;
		
		//while the input is not as specified, run the loop
		while(true)
		{
			try
			{
				//store user input in the variable
				output = scanner.nextInt();
				
				//if the input is within the range we specified, break the loop
				if (output >= 0 && output < length)
					break;
				
				//if not, print the message again and enter the next loop
				else
					System.out.println("Value out of bound. Please enter again.");
					
			}
			//if the input is not integer, print error message and clear scanner buffer, enter the next loop
			catch (InputMismatchException e)
			{
				System.out.println("Invalid input. Please enter again.");	//print the error message
				scanner.nextLine();					//clear the scanner buffer
			}
		}
		return output;
	}
	
	//test code for my own reference
//	public static void main(String[] args) {
//		Cards card = new Cards();
//		card.setUpMain(3);
//		card.setUpDiscard();
//		Human test = new Human(card);
//		String[] letters = {"a", "b","c"};
//		test.setHandCards(letters);
//		test.printHandCards();
//		card.addToDiscardPile(card.getFirstFromMainPileAndRemove());
//		Scanner scanner = new Scanner(System.in);
//		test.play(scanner);
//	}

}
