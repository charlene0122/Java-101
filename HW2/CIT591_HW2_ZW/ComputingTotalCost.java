/**
 * CIT 5910 HW1 Program 4
 * Based on given prices, computes the total cost of a particular number of drinks and tacos.
 *  
 * @author zhifeiwu
 */
public class ComputingTotalCost {

	public static void main(String[] args) {

		//number of drinks
		int numDrinks = 2;
		
		//number of tacos
		int numTacos = 3;

		//total cost
		double totalCost = 0.0;

		
		//TODO my code as below
		double costDrink = 2.10;		//store drink cost as a variable for easy reference
		double costTaco = 3.43;			//store taco cost as a variable for easy reference
		totalCost = costDrink * numDrinks + costTaco * numTacos; 	//calculate total cost
		
		//print the result
		System.out.print("If a drink costs " + costDrink + " dollars and a taco costs " + costTaco +" dollars, ");
		System.out.print("the total cost of " + numDrinks + " drinks and " + numTacos + " tacos is ");
		System.out.print(totalCost + " dollars.");
		
	}
}