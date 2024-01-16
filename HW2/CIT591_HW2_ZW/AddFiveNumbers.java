/**
 * CIT 5910 HW1 Program 1
 * Given 5 numbers (ints or doubles), prints the sum (as a double) of the numbers, one by one.
 * 
 * @author zhifeiwu
 */
public class AddFiveNumbers {

	public static void main(String[] args) {
		
		//calculate the total sum of the following 5 numbers (doubles and ints), one by one
		double num1 = 3.2;
		int num2 = 1;
		double num3 = -1.0;
		int num4 = 3241;
		double num5 = -9.9;
		
		//store the sum as a double
		double sum = 0.0;
		
		//TODO my codes as below
		//add the first number to sum and print
		sum += num1;
		System.out.println(sum);
		
		//add the second number to sum and print
		sum += num2;
		System.out.println(sum);
		
		//add the third number to sum and print
		sum += num3;
		System.out.println(sum);
		
		//add the fourth number to sum and print
		sum += num4;
		System.out.println(sum);
		
		//add the 5th number to sum and print
		sum += num5;
		System.out.println(sum);
		
	}
}
