/**
 * CIT 5910 HW5: Pick Your Letters
 * Design a game that involves re-arranging a group of cards in order to make up a word.
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */


//import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import files.WordReader;
import files.WordProcessor;

/**
 * About the Game
 * This game needs two players and we will just play the user versus the computer. 
 * The user’s moves are decided by the user playing the game, by asking for input, and the computer’s 
 * moves are decided by the program.
 * 
 * About the class
 * This class controls the entire game. 
 * It has the main method which serves as the entry point to the entire program.
 */
public class PickYourLetters {
	
	public static void main(String[] args) {
		
		PickYourLetters pyl = new PickYourLetters();
		pyl.run();
	}
	
	/**
	 * Launches and controls the game.
	 */
	public void run() {
		
		//print the welcome message
		System.out.println("Welcome to the game!");
		
		//reads all words from file
		WordReader wr = new WordReader();
		String[] allWords = wr.readFromFile("words.txt");

		//create scanner for user input
		Scanner scanner = new Scanner(System.in);		

		//ask for a number as the length of the word
		int lengthofword = askForLength(scanner);

		//create WordProcessor
		WordProcessor wordPro = new WordProcessor();
		
		//filter allWords with a length equal to the given length
		String[] selectWords = wordPro.filterWordList(allWords, lengthofword);
		System.out.println("Filtering the words with given length " + lengthofword + ". Please standby ...");
		allWords = selectWords;
		
		//create Cards
		Cards card = new Cards();
		
		//set up mainPile and discardPile
		card.setUpMain(lengthofword);
		card.setUpDiscard();
	    
	    //shuffle main pile
		card.shuffleMainCards();
	    
	    //create computer player and human player
		Human humanPlayer = new Human(card);
		Computer computerPlayer = new Computer(card);
		
		//deal cards to players and initialize discard pile
		card.dealInitialCards(computerPlayer, humanPlayer, lengthofword);
	   
	    //set up a target word list for computer strategy
		String[] targetWords = computerPlayer.filterTarget(allWords, lengthofword);
	    System.out.println("-----------------------------------------------");
	    
	    
	    //create a variable to store status of the game, set the initial status to false to indicate game is not over
	    boolean isGameOver = false;
	    
	    //while game is not over, run the game
	    while (!isGameOver) {
	    	
	        //first check if mainPile is empty by calling checkCards() in Cards class
	    	card.checkCards();
	        
	        //computer's turn
	    	computerPlayer.play(targetWords);
	        		    
	    	//check card again in case the computer picked the last card in the main file
	    	card.checkCards();
	    	
	    	//human's turn
	    	humanPlayer.play(scanner);
	    	
		    //check if game is over, and print out results
	    	isGameOver = checkGameOver(humanPlayer.getHandCards(),computerPlayer.getHandCards(),allWords);
	    }
	    
	    //close scanner
	    scanner.close();
	}
	
	/**
	 * Asks the user for the number of hand cards.
	 * Prompts again if the user input is not a valid integer, 
	 * or if the number is not between 3 - 10, inclusive.
	 * @param scanner for user input
	 * @return an integer indicating the number of hand cards
	 */
	public int askForLength(Scanner scanner) {
	    
		//print out the instruction
		System.out.println("Enter a number between 3 - 10 to be length of the word you are going to guess:");
		
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
				if (output >= 3 && output <=10)
					break;
				//if not, print the message again and enter the next loop
				else
					System.out.println("Value out of bound. Please enter a number between 3 - 10.");
					
			}
			
			//if the input is not integer, print error message and clear scanner buffer, enter the next loop
			catch (InputMismatchException e)
			{
				System.out.println("Invalid input. Please enter a number between 3 - 10.");	
				scanner.nextLine();	
			}
		}
		
		return output;
	}
	
	/**
	 * Checks if the game is over.
	 * @param humanHandCards human player's current letters in hand
	 * @param computerHandCards computer player's current letters in hand
	 * @param wordsWithSpecificLength an array containing all the words with a specific length
	 * @return true if computer or human wins the game or if there is a tie, otherwise, false
	 */
	public boolean checkGameOver(String[] humanHandCards, String[] computerHandCards, String[] wordsWithSpecificLength) {
	   
		//transform the human hand card array and the computer hand card array into two whole string
		String humanWord = "", computerWord = "";
		
		//iterate through the letter array, append each letter to the word string
		for (int i = 0; i < humanHandCards.length; i++)
		{
			humanWord += humanHandCards[i];
			computerWord += computerHandCards[i];
		}
		
		//create variable to record whether human and computer wins or not
		boolean humanWin = false, computerWin = false;
		
		//iterate through the word list to see if there is a word equal to the human word or the computer word
		for (int i = 0; i < wordsWithSpecificLength.length; i++)
		{
			//if there is a word equal to the human word, human wins
			if (humanWord.equals(wordsWithSpecificLength[i]))
				humanWin = true;
			//if there is a word equal to the computer word, computer wins
			if (computerWord.equals(wordsWithSpecificLength[i]))
				computerWin = true;
		}
		
		//if both human and computer win, it's a tie, print a message, print the human's word and computer's word
		if(humanWin && computerWin)
		{
			System.out.println("Tie!");
			System.out.println("Your word is " + humanWord);
			System.out.println("Computer's word is " + computerWord);
			return true;
		}
		
		//if only human wins, print a message and print the human word
		else if (humanWin)
		{
			System.out.println("Your word is " + humanWord);
			System.out.println("You win!");
			return true;
		}
		
		//if only computer wins, print a message and print the computer word
		else if (computerWin)
		{
			System.out.println("Computer's word is " + computerWord);
			System.out.println("Computer wins!");
			return true;
		}
			
		return false;
	}
}
