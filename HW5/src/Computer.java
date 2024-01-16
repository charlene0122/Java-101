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
//import java.util.Scanner;

/**
 * Represents a computer player.
 *
 */
public class Computer {
	
	/**
	 * Computer player's hand.
	 */
	String[] handCards;
	
	/**
	 * Access to main pile and discard pile.
	 */
	Cards cards;
	
	/**
	 * Creates a computer player.
	 * Gives access to the main pile and discard pile via given instance of Cards class.
	 * @param cards for access to main pile and discard pile
	 */
	public Computer(Cards cards) {
		this.cards = cards;
	}
	
	/**
	 * Sets computer player's hand.
	 * @param handCards to set
	 */
	public void setHandCards(String[] handCards) {
		this.handCards = handCards.clone();
	}
	
	/**
	 * Gets computer player's hand.
	 * @return computer player's hand
	 */
	public String[] getHandCards() {
		return this.handCards;
	}

	/**
	 * Print hand cards for the computer
	 */
	public void printHandCards()
	{
		System.out.println("Computer's hand cards are: "+ Arrays.asList(this.handCards));
	}
	
	/**
	 * Controls the game play for the computer.
	 * The computer first decides if the letter from the discard pile is useful, if so, 
	 * the computer will take the letter; otherwise, the computer will take the letter from 
	 * main pile and then decide if that's useful.
	 * 
	 * To decide if a given new letter is useful, the computer can put all the potential 
	 * words, which are the most similar words to the current word, in the target array.
	 * If replacing one letter in the current word with the new letter will increase the 
	 * similarity, then the computer decides the given new letter is useful.
	 * 
	 * @param targetArray containing all the most similar words to the current hand cards
	 * @return the newly narrowed down array
	 */
	public String[] play(String[] targetArray) {
	    
		//print out a message to indicate the start of computer's turn
		System.out.println("Computer's turn.");
		
		//get the first card in discard pile
		String letter = cards.getDiscardPile()[0];
		
		//create a variable to track whether the computer should take the card from discard file
		boolean keepDiscard = false;
		
		//iterate through every word in the target list
		for (int i = 0; i < targetArray.length; i++)
		{
			//compare every letter of the word with the letter of corresponding position in computer's hand card
			for (int j = 0; j < handCards.length; j++)
			{
				//if the card is different from letter in the hand card but is the same as the target word
				if (handCards[j].charAt(0) != targetArray[i].charAt(j) && letter.charAt(0) == targetArray[i].charAt(j))
					{
					//set keep discard to true
					keepDiscard = true;
					//replace the card to current position
					String temp = handCards[j];
					handCards[j] = cards.getFirstFromDiscardPileAndRemove();
					cards.addToDiscardPile(temp);
					//break the loop since we already replace the card
					break;
					}
			}
			//if the card from discard pile is already replaced, break the loop
			if (keepDiscard)
				break;
		}
		
		//after the above iteration, if the card from discard pile is kept, print a message 
		//and print the hand card after rearrangement
		if (keepDiscard)
		{
			System.out.println("Computer took \"" + letter  + "\" from DISCARD PILE.");
			printHandCards();
		}
		
		//if computer decide not to take card from the discard pile,
		//it should then look into the main pile
		else
		{
			//the first card in the main pile is revealed
			letter = cards.getMainPile()[0];
			
			//create a variable to track whether the computer should take the card from main file
			boolean keepMain = false;
			
			//iterate through the target list
			for (int i = 0; i < targetArray.length; i++)
			{
				//compare every letter of the word with the letter of corresponding position in computer's hand card
				for (int j = 0; j < handCards.length; j++)
				{
					//if the card is different from letter in hand card but is the same as the target word
					if (handCards[j].charAt(0) != targetArray[i].charAt(j) && letter.charAt(0) == targetArray[i].charAt(j))
						{
						//set keep main to true
						keepMain = true;
						//replace the card to the current position
						String temp = handCards[j];
						handCards[j] = cards.getFirstFromMainPileAndRemove();
						cards.addToDiscardPile(temp);
						//break the loop since we already replace the card
						break;
						}	
				}
				//if the card from main pile is already replaced, break the loop
				if (keepMain)
					break;
			}
			
			//if the main card is taken and replaced into the hand card in the above iteration
			//print a message and print the computer's hand card after the rearrangement
			if (keepMain)
			{
				System.out.println("Computer took \"" + letter  + "\" from MAIN PILE.");
				printHandCards();
			}
			
			//if the card from main pile also does not match any target word in the list,
			else
			{
				//print a message
				System.out.println("Computer discarded \"" + letter  + "\" from MAIN PILE.");
				//print computer's current hand card
				printHandCards();
				//put card on top of the main pile into the discard pile
				cards.addToDiscardPile(cards.getFirstFromMainPileAndRemove());
			}
			
		}
		
		//filter the target array again based on the hand card after rearrangement
		String [] targetWords = filterTarget(targetArray, handCards.length);
		
		//print a separate line to indicate the end of a session
		System.out.println("-----------------------------------------------");
		
		//return the target list
		return targetWords;
	}
	
	/**
	 * Filter the target array to a list of words that have the highest similarity score with current hand card.
	 * Similarity score is defined by the number of letters that are the same between the hand card and the target word.
	 * @param length length of the target word, specified by user input at the beginning of the whole program
	 * @return array with the same highest similarity score
	 */
	public String[] filterTarget(String[] target, int length) {
		//declare and initialize the variable for the highest score
		int maxScore = 0;
		
		//create an array to store the similarity score for each word in the target array
		int[] targetWordScore = new int[target.length];
		
		//iterate through the word array
		for (int i = 0; i < target.length; i++)
		{
			//declare and initialize the variable to calculate similarity score
			int score = 0;
			
			//iterate through the each element in the hand card and each letter in the word
			for (int j = 0; j < length; j++)
			{
				//when finding a same letter in the same position, increase similarity score by 1
				if (target[i].charAt(j) == handCards[j].charAt(0))
					score++;
			}
			
			//store the similarity score into the array
			targetWordScore[i] = score;
			
			//if the current score is higher than the old highest, make it the new highest
			if (score > maxScore)
				maxScore = score;
		}
		
		//create a new array to pick out all the words with highest similarity score
		String [] targetWords = new String[target.length];
		
		int index = 0;			//a variable to track the position of the new array
		for (int i = 0; i < target.length; i++)
		{
			//if the current word's similarity score is highest, put it into the new array
			if (targetWordScore[i] == maxScore)
				targetWords[index++] = target[i];
				
		}
		
		//remove null elements in the array and return it
		String[] removedNull = Arrays.stream(targetWords).filter(value ->
                        value != null && value.length() > 0).toArray(size -> new String[size]);
		targetWords = removedNull;
		
		return targetWords;
		
	}
	
	//test code for my own reference
//	public static void main(String[] args) {
//		Cards card = new Cards();
//		card.setUpMain(3);
//		card.setUpDiscard();
//		Computer test = new Computer(card);
//		String[] letters = {"a", "b","c"};
//		test.setHandCards(letters);
//		test.printHandCards();
//		card.addToDiscardPile(card.getFirstFromMainPileAndRemove());
//		String[] target = {"sij", "sii", "ais", "bll", "csl", "abo", "ibc", "asc"};
//		String[] newtarget = test.filterTarget(target, 3);
//		System.out.println(Arrays.asList(newtarget));
//		System.out.println(Arrays.asList(card.getMainPile()));
//		System.out.println(Arrays.asList(card.getDiscardPile()));
//		newtarget = test.play(newtarget);
//		newtarget = test.play(newtarget);
//		newtarget = test.play(newtarget);
//		newtarget = test.play(newtarget);
//		newtarget = test.play(newtarget);
//		newtarget = test.play(newtarget);
//		newtarget = test.play(newtarget);
//		newtarget = test.play(newtarget);
//		test.printHandCards();
//	}
	
}