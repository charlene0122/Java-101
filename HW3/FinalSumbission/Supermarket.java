/**
 * CIT 5910 HW3: Supermarket
 * Implement a supermarket shopping “game” based on assignment instructions
 * 
 * @author zhifeiwu
 * Penn ID: 30406975
 * This java file is solely completed by myself, without reference to any materials 
 * other than lecture slides and assignment instructions. 
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Supermarket {

	public static void main(String[] args) {
		
		//unit price of a lottery ticket
		double LOTTERY_UNIT_PRICE = 2.0;

		//unit price of an apple
		double APPLE_UNIT_Price = .99;

		//unit price of a can of beans
		double CANNED_BEANS_UNIT_PRICE = 1.58;

		//unit price of a soda
		double SODA_UNIT_PRICE = 1.23;

		//the user has initial $5 for shopping
		double money = 5.0;

		//the user has spent $0 initially
		double moneySpent = 0;

		//the amounts of lottery tickets, apples, cans of beans, and sodas the user has purchased
		int lotteryAmount = 0;
		int appleAmount = 0;
		int cannedBeansAmount = 0;
		int sodaAmount = 0;
		
		//create a scanner for getting user input
		Scanner scanner = new Scanner(System.in);
		
		//TODO my code as below
		
		//print a welcome message to the user along with a list of products and their unit prices
		System.out.println("Welcome to the supermarket!  Here's what we have in stock:\n"
				+ "- Lottery tickets cost $" + LOTTERY_UNIT_PRICE + " each\n"
				+ "- Apples cost $" + APPLE_UNIT_Price + " each\n"
				+ "- Cans of beans cost $" + CANNED_BEANS_UNIT_PRICE + " each\n"
				+ "- Sodas cost $" + SODA_UNIT_PRICE + " each");
		System.out.println();
		
		//tell the user how much money they have available and ask if they want to purchase a lottery ticket
		System.out.println("You have $" + money + " available\n"
				+ "First, do you want to buy a $" + LOTTERY_UNIT_PRICE +" lottery ticket for a chance at winning $2-$10? (y/n) ");
		
		//convert user input to lower cases and store
		String buyLottery = scanner.next().toLowerCase();
		
		//initialize the lottery winnings variable
		int lotteryWinnings = 0;
		
		//if user inputs y, process a lottery ticket purchase
		if(buyLottery.equals("y"))
		{
			money -= LOTTERY_UNIT_PRICE;			//subtract the cost of ticket from user's pocket
			moneySpent += LOTTERY_UNIT_PRICE;		//add cost to money spent
			lotteryAmount++;						//increase amount of lottery tickets purchased
			Random random = new Random();			//create instance of Random
			int winOrLose = random.nextInt(3);		//generate a random integer from 0 – 2
			
			//set 0 as winning condition
			//if user win, generate a random integer from 2 to 10, print a message and any money won to user's pocket
			if(winOrLose == 0)
			{
				lotteryWinnings = random.nextInt(9) + 2;		
				System.out.println("Congrats! You won $" + lotteryWinnings);	
				money += lotteryWinnings;										
			}
			//if user lose, simply print a message
			else
			{
				System.out.println("Sorry! You did not win the lottery.");		
			}
		}
		
		//if user choose not to purchase a lottery ticket, print a message
		else
		{
			System.out.println("No lottery tickets were purchased.");			
		}
		
		//a blank line to indicate the start of a new section
		System.out.println();
		
		//print the money left after purchasing the lottery ticket
		System.out.println("You have $" + money + " available.");
		
		//ask the user whether they want to purchase apples
		System.out.println("Do you want to buy apple(s)? (y/n) ");
		
		//convert user input to lower cases and store
		String buyApple = scanner.next().toLowerCase();
		
		//if user say yes, ask them how many apple to purchase, examine if they have enough money, and print the message
		if(buyApple.equals("y"))
		{
			int numApple = 0;
			//use try and catch to catch any potential variable type exceptions
			try {
			System.out.println("How many apple(s) do you want to buy?");
			numApple = scanner.nextInt();
			
			//calculate the cost of purchase and round it to 2 decimal places
			double cost = Round(numApple * APPLE_UNIT_Price);
			
			//print the result
			System.out.println("The user wants to buy " + numApple + " apple(s). This will cost " + cost + ".");
			
			//if user has more money than the expected cost, purchase succeed, subtract money from user pocket, 
			//add money spent and number of items purchased
			if(money >= cost)
			{
				System.out.println("The user has enough money. " + numApple + " apple(s) purchased.");
				money -= cost;
				money = Round(money);				//round to 2 decimal
				moneySpent += cost;
				moneySpent = Round(moneySpent);		//round to 2 decimal
				appleAmount += numApple;
			}
			
			//if user does not have enough money, purchase failed and print a message
			else
			{
				System.out.println("Not enough money! No apples purchased.");
			}
			}
			catch (InputMismatchException e)
			{
				System.out.println("Numerical values only! Purchase failed.");	//print the error message
				scanner.nextLine();					//clear the scanner buffer
			}
		}
		
		//if user says no, print a message
		else
		{
			System.out.println("No apples purchased.");
		}
		
		//a blank line to indicate the start of a new section
		System.out.println();
		
		//print the money left available
		System.out.println("You have $" + money + " available.");
		
		//ask the user whether they want to purchase can(s) of beans
		System.out.println("Do you want to buy can(s) of beans? (y/n) ");
		
		//convert user input to lower cases
		String buyBeans = scanner.next().toLowerCase();
		
		//if user say yes, ask them how many item to purchase, examine if they have enough money, and print the message
		if(buyBeans.equals("y"))
		{
			int numBeans = 0;
			//use try and catch to catch any potential variable type exceptions
			try {
			System.out.println("How many can(s) of beans do you want to buy?");
			numBeans = scanner.nextInt();
			
			//calculate the cost of purchase
			double cost = Round(numBeans * CANNED_BEANS_UNIT_PRICE);
			
			//print the result
			System.out.println("The user wants to buy " + numBeans + " can(s) of beans. This will cost " + cost + ".");
			
			//if user has more money than the expected cost, purchase succeed, subtract money from user pocket, 
			//add money spent and number of items purchased
			if(money >= cost)
			{
				System.out.println("The user has enough money. " + numBeans + " can(s) of beans purchased.");
				money -= cost;
				money = Round(money);				//round to 2 decimal
				moneySpent += cost;
				moneySpent = Round(moneySpent);		//round to 2 decimal
				cannedBeansAmount += numBeans;
			}
			
			//if user does not have enough money, purchase failed and print a message
			else
			{
				System.out.println("Not enough money! No can(s) of beans purchased.");
			}
			}
			catch (InputMismatchException e)
			{
				System.out.println("Numerical values only! Purchase failed.");//print the error message
				scanner.nextLine();				//clear the scanner buffer
			}
		}
		//if user says no, print a message
		else
		{
			System.out.println("No can(s) of beans purchased.");
		}
		
		//a blank line to indicate the start of a new section
		System.out.println();
		
		//print the money left available
		System.out.println("You have $" + money + " available.");
		
		System.out.println("Do you want to buy soda(s)? (y/n) ");
		String buySoda = scanner.next().toLowerCase();
		//if user say yes, ask them how many item to purchase, examine if they have enough money, and print the message
		if(buySoda.equals("y"))
		{
			int numSoda = 0;
			//use try and catch to catch any potential variable type exceptions
			try 
			{
			System.out.println("How many soda(s) do you want to buy?");
			numSoda = scanner.nextInt();
		
			//calculate the cost of purchase
			double cost = Round(numSoda * SODA_UNIT_PRICE);
			
			//print the result
			System.out.println("The user wants to buy " + numSoda + " soda(s). This will cost " + cost + ".");
			
			//if user has more money than the expected cost, purchase succeed, subtract money from user pocket, 
			//add money spent and number of items purchased
			if(money >= cost)
			{
				System.out.println("The user has enough money. " + numSoda + " soda(s) purchased.");
				money -= cost;
				money = Round(money);				//round to 2 decimal
				moneySpent += cost;
				moneySpent = Round(moneySpent);		//round to 2 decimal
				sodaAmount += numSoda;
			}
			//if user does not have enough money, purchase failed and print a message
			else
			{
				System.out.println("Not enough money! No sodas purchased.");
			}
			}
			catch (InputMismatchException e)
			{
				System.out.println("Numerical values only! Purchase failed.");	//print the error message
				scanner.nextLine();					//clear the scanner buffer
			}
		}
		
		//if user says no, print a message
		else
		{
			System.out.println("No soda(s) purchased.");
		}
		
		//a blank line to indicate the start of a new section
		System.out.println();
		//print a summary of the purchase
		System.out.println("Money left: $" + money +"\n"
				+ "Lottery ticket(s) purchased: " + lotteryAmount +"\n"
				+ "Lottery winnings: $" + lotteryWinnings +"\n"
				+ "Apple(s) purchased: " + appleAmount + "\n"
				+ "Can(s) of beans purchased: " +  cannedBeansAmount +"\n"
				+ "Soda(s) purchased: " +sodaAmount+ "\n"
				+ "Good bye!");
		//close the scanner
		scanner.close();
	
	}
	
	//write a method of rounding to simplify the rounding process
	public static double Round(double input)
	{
		//the following piece of code is referenced from code in the assignment instructions
		//create big decimal with double value
		BigDecimal bd = new BigDecimal(input);
		//set config for big decimal
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		//get rounded value
		Double output = bd.doubleValue();
		//return the value
		return output;
	}

}
