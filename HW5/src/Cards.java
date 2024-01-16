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
import java.util.Random;

/**
 * Creates and manages a main pile of cards and a discard pile of cards.
 *
 */
public class Cards {

	/**
	 * Main pile of cards. 
	 */
	String[] mainPile;
	
	/**
	 * Discard pile of cards.
	 */
	String[] discardPile;
	
	/**
	 * Creates instance of Cards class.
	 */
	public Cards() {
		
	}
	
	/**
	 * Sets up main pile, which is an array containing the letters of the alphabet * length.
	 * @param length of the words
	 * @return main pile
	 */
	public String[] setUpMain(int length) {
	   
		//create an array to store the 26 letters
		String[] alphabets = new String[] {"a", "b", "c", "d", "e", "f", 
				"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
				"t", "u", "v", "w", "x", "y", "z"};
		
		//initialize the main pile as a new array with length 26 * specified length
		mainPile = new String[26 * length];
		
		//iterate through the main pile and put the letters into the array in order
		for (int i = 0; i < mainPile.length; i++)
		{
			mainPile[i] = alphabets[i % 26];
		}
		return mainPile;
	}
	
	/**
	 * Sets up discard pile, which is an empty array.
	 * @return discard pile
	 */
	public String[] setUpDiscard() {
		   
		//initialize discard pile as an empty array
		discardPile = new String[0];
		return discardPile;
	}
	
	/**
	 * Gets the main pile.
	 * @return main pile
	 */
	public String[] getMainPile() {
		return mainPile;
	}
	
	/**
	 * Gets the discard pile.
	 * @return discard pile
	 */
	public String[] getDiscardPile() {
		return discardPile;
	}
	
	/**
	 * Shuffles cards in the main pile.
	 */
	public void shuffleMainCards() {
		
		//create an instance of random
		Random random = new Random();
		
		//iterate the pile from bottom up
		for (int i = mainPile.length - 1; i > 0; i--)
		{
			//randomly select a card above the current card
			int index = random.nextInt(i);
			
			//exchange it with the current card
			String temp = mainPile[i];
			mainPile[i] = mainPile[index];
			mainPile[index] = temp;
		}
	}
	
	/**
	 * Shuffles cards in the discard pile.
	 */
	public void shuffleDiscardCards() {
		
		//create an instance of random
		Random random = new Random();
		
		//iterate the pile from bottom up
		for (int i = discardPile.length - 1; i > 0; i--)
		{
			//randomly select a card above the current card
			int index = random.nextInt(i);
			
			//exchange it with the current card
			String temp = discardPile[i];
			discardPile[i] = discardPile[index];
			discardPile[index] = temp;
		}
	}
	
	/**
	 * Deals two hands of length cards each to the given computer player 
	 * and to the given human player. Deals cards from the main pile.
	 * The computer is always the first person that gets dealt.
	 * Removes the card on top of the main pile and put it on the discard pile.
	 * @param computer computer player
	 * @param human human player
	 * @param length of the hand cards
	 */
	public void dealInitialCards(Computer computer, Human human, int length) {

		//create new array of Strings for computer and human respectively
		String[] humanCards = new String[length];
		String[] computerCards = new String[length];
		
		//deal card to the computer and human from the main pile, one by one
		for (int i = 0; i < length; i++)
		{
			//the computer is the first person to get dealt into
			computerCards[i] = getFirstFromMainPileAndRemove();
			humanCards[i] = getFirstFromMainPileAndRemove();
		}
		
		//pass the array created to the computer object and human object respectively
		computer.setHandCards(computerCards);
		human.setHandCards(humanCards);
		
		//remove the first card from the main pile and put it into the discard file
		addToDiscardPile(getFirstFromMainPileAndRemove());
	}
	
	/**
	 * Checks whether the main pile is empty.
	 * If so, shuffles the discard pile and moves all the cards to the main pile.
	 * Then turns over the top card of the main pile to be the start of the new discard pile.
	 * Otherwise, does nothing.
	*/
	public void checkCards() {
	    
		//a length of 0 indicates that the pile is empty
		if (mainPile.length == 0)
		{
			//shuffle the discard pile
			shuffleDiscardCards();	
			
			//create a copy of discard pile and pass it into the main pile
			mainPile = discardPile.clone();		
			
			//reset discard pile to 0
			setUpDiscard();			
			
			//remove the first card from the main pile and put it into the discard file
			addToDiscardPile(getFirstFromMainPileAndRemove());	
		}
	}
	
	/**
	 * Returns and removes the first card of the main pile.
	 * @return the first card of the main pile
	 */
	public String getFirstFromMainPileAndRemove() {
		
		//create a new array with that is one element shorter
		String [] temp = new String[mainPile.length - 1];
		
		//copy everything from the second to the last element into the new array
		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = mainPile[i+1];
		}
		
		//store the first element into a variable to return it later
		String output = mainPile[0];
		
		//point to the new array created
		this.mainPile = temp;
		
		//return the first card
		return output;
	}
	
	/**
	 * Returns and removes the first card of the discard pile.
	 * @return the first card of the discard pile
	 */
	public String getFirstFromDiscardPileAndRemove() {
		
		//create a new array with that is one element shorter
		String [] temp = new String[discardPile.length - 1];
		
		//copy everything from the second to the last element into the new array
		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = discardPile[i + 1];
		}
		
		//store the first element into a variable to return it later
		String output = discardPile[0];
		
		//point to the new array created
		this.discardPile = temp;
		
		//return the first card
		return output;
	}
	
	/**
	 *  Adds given card to top of the discard pile.
	 * @param card to add to top of discard pile
	 */
	public void addToDiscardPile(String card) {
		
		//create a new array with that is one element longer
		String [] temp = new String[discardPile.length + 1];
		
		//store the card to the first element
		temp[0] = card;
		
		//copy everything from the the old array to the new array, staring from the second element
		for (int i = 1; i < temp.length; i++)
		{
			temp[i] = discardPile[i - 1];
		}
		
		//point to the new array created
		this.discardPile = temp;
	}
	
}
