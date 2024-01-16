package FinalProject_CIT591.MongoDB;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * get and set client info, including name, age, marital status, total capital,
 * annual income, preferred industries, market capital, dividend preference,
 * percentage of stock, growth vs value preference, and risk level
 * 
 * The class also has two functions: setClientInfo() - used to set the client's
 * information askForRiskLevel() - used to ask and get the client's risk level
 *
 */
public class Client {

	private String name;
	private int age;
	private boolean maritalStatus;
	private double totalCapital;
	private double annualIncome;
	private ArrayList<String> preferredIndustry;
	private int marketCapital;
	private boolean dividend;
	private double percentageOfStock;
	private boolean growthorvalue;
	private String riskLevel;

	// constructor
	public Client() {
	}

	/**
	 * 
	 * Returns the name of the client.
	 * 
	 * @return the name of the client
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * Sets the name of the client. If the name is an empty string, it will be set
	 * to null.
	 * 
	 * @param name the name of the client
	 */
	public void setName(String name) {
		if (name == "") {
			this.name = null;
		} else {
			this.name = name;
		}
	}

	/**
	 * 
	 * Returns the age of the client.
	 * 
	 * @return the age of the client
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 
	 * Sets the age of the client. If the age is less than or equal to 0, it will be
	 * set to 0.
	 * 
	 * @param age the age of the client
	 */
	public void setAge(int age) {
		if (age <= 0) {
			this.age = 0;
		} else {
			this.age = age;
		}
	}

	/**
	 * 
	 * Returns the marital status of the client.
	 * 
	 * @return the marital status of the client
	 */
	public boolean getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * 
	 * Sets the marital status of the client.
	 * 
	 * @param maritalStatus the marital status of the client
	 */
	public void setMaritalStatus(boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * 
	 * Returns the total capital of the client.
	 * 
	 * @return the total capital of the client
	 */
	public double getTotalCapital() {
		return totalCapital;
	}

	/**
	 * 
	 * Sets the total capital of the client. If the total capital is less than or
	 * equal to 0, it will be set to 0.
	 * 
	 * @param totalCapital the total capital of the client
	 */
	public void setTotalCapital(double totalCapital) {
		if (totalCapital <= 0) {
			this.totalCapital = 0;
		} else {
			this.totalCapital = totalCapital;
		}
	}

	/**
	 * 
	 * Returns the annual income of the client.
	 * 
	 * @return the annual income of the client
	 */
	public double getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * 
	 * The setAnnualIncome method sets the annual income of the client. If the input
	 * value is less than or equal to zero, the annual income is set to zero.
	 * Otherwise, the input value is set as the annual income.
	 * 
	 * @param annualIncome the annual income of the client
	 */
	public void setAnnualIncome(double annualIncome) {
		if (annualIncome <= 0) {
			this.annualIncome = 0;
		} else {
			this.annualIncome = annualIncome;
		}
	}

	/**
	 * 
	 * The getPreferredIndustry method returns the client's preferred industry.
	 * 
	 * @return the client's preferred industry
	 */
	public ArrayList<String> getPreferredIndustry() {
		return preferredIndustry;
	}

	/**
	 * 
	 * The setPreferredIndustry method sets the client's preferred industry.
	 * 
	 * @param selectedIndustries the client's selected industries
	 */
	public void setPreferredIndustry(ArrayList<String> selectedIndustries) {
		this.preferredIndustry = selectedIndustries;
	}

	/**
	 * 
	 * The getMarketCapital method returns the client's market capital.
	 * 
	 * @return the client's market capital
	 */
	public int getMarketCapital() {
		return marketCapital;
	}

	/**
	 * 
	 * The setMarketCapital method sets the client's market capital. If the input
	 * value is less than or equal to zero, the market capital is set to zero.
	 * Otherwise, the input value is set as the market capital.
	 * 
	 * @param marketCapital the client's market capital
	 */
	public void setMarketCapital(int marketCapital) {
		if (marketCapital <= 0) {
			this.marketCapital = 0;
		} else {
			this.marketCapital = marketCapital;
		}
	}

	/**
	 * 
	 * The getDividend method returns the client's dividend preference.
	 * 
	 * @return the client's dividend preference
	 */
	public boolean getDividend() {
		return dividend;
	}

	/**
	 * 
	 * The setDividend method sets the client's dividend preference.
	 * 
	 * @param dividend the client's dividend preference
	 */
	public void setDividend(boolean dividend) {
		this.dividend = dividend;
	}

	/**
	 * 
	 * The getPercentageOfStock method returns the client's percentage of stock.
	 * 
	 * @return the client's percentage of stock
	 */
	public double getPercentageOfStock() {
		return percentageOfStock;
	}

	/**
	 * 
	 * The setPercentageOfStock method sets the client's percentage of stock.
	 * 
	 * @param percentageOfStock the client's percentage of stock
	 */
	public void setPercentageOfStock(double percentageOfStock) {
		this.percentageOfStock = percentageOfStock;
	}

	/**
	 * 
	 * The getGrowthorvalue method returns the client's preference for growth or
	 * value stocks.
	 * 
	 * @return the client's preference for growth or value stocks
	 */
	public boolean getGrowthorvalue() {
		return growthorvalue;
	}

	/**
	 * 
	 * The setGrowthorvalue method sets the client's preference for growth or value
	 * stocks.
	 * 
	 * @param growthorvalue the client's preference for growth or value stocks
	 */
	public void setGrowthorvalue(boolean growthorvalue) {
		this.growthorvalue = growthorvalue;
	}

	/**
	 * 
	 * Getter method for risk level.
	 * 
	 * @return the client's risk level
	 */
	public String getRiskLevel() {
		return riskLevel;
	}

	/**
	 * 
	 * Setter method for risk level.
	 * 
	 * @param riskLevel the new risk level for the client
	 */
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	/**
	 * 
	 * Method for setting the client's information. Prompts the user for input and
	 * sets the attributes
	 * 
	 * based on the input. Handles invalid input and prompts the user to try again.
	 */
	public void setClientInfo() {

		// create scanner for user input

		while (true) {
			try {
				System.out.println("What's your name");
				Scanner temp = new Scanner(System.in);
				String name = temp.next();
				setName(name);
				break;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again");
			}
		}

		while (true) {
			try {
				System.out.println("What's your age");
				Scanner temp = new Scanner(System.in);
				int age = temp.nextInt();
				while (age <= 0) {
					System.out.println("Please enter a positive integer");
					age = temp.nextInt(); // read the number from the client
				}
				setAge(age);
				break;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again");
			}
		}

		System.out.println("What's your marital status (single or not)");
		Scanner temp2 = new Scanner(System.in);

		// Declare a variable to hold the client's marital status
		boolean maritalStatus = false;
		String maritalStatusTemp = temp2.next();
		// Loop until the client enters a valid marital status
		while (!maritalStatusTemp.equalsIgnoreCase("single") && !maritalStatusTemp.equalsIgnoreCase("not")) {
			System.out.println("Invalid input, please try again");
			// Read the client's response
			maritalStatusTemp = temp2.next();
		}
		if (maritalStatusTemp.equalsIgnoreCase("single")) {
			maritalStatus = true;
		}
		setMaritalStatus(maritalStatus);

		/**
		 * 
		 * The while loop prompts the user to input their total capital. If the input is
		 * invalid, the user is asked to try again.
		 */
		while (true) {
			try {
				System.out.println("What's your total capital");
				Scanner temp = new Scanner(System.in);
				double totalCapital = temp.nextDouble();
				setTotalCapital(totalCapital);
				break;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again");
			}
		}

		/**
		 * 
		 * The while loop prompts the user to input their annual income. If the input is
		 * invalid, the user is asked to try again.
		 * 
		 */
		while (true) {
			try {
				System.out.println("What's your annual income");
				Scanner temp = new Scanner(System.in);
				double annualIncome = temp.nextDouble();
				setAnnualIncome(annualIncome);
				break;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again");
			}
		}

		/**
		 * 
		 * The list of industries is initialized and displayed to the user. The user is
		 * then asked to input the industry numbers they want to invest in, one by one,
		 * until they type "done" to finish. If the input is invalid, the user is asked
		 * to try again. The selected industries are then set as the user's preferred
		 * industries.
		 */
		ArrayList<String> industries = new ArrayList<String>();
		industries.add("Basic Materials");
		industries.add("Consumer Discretionary");
		industries.add("Consumer Staples");
		industries.add("Energy");
		industries.add("Finance");
		industries.add("Health Care");
		industries.add("Industrials");
		industries.add("Real Estate");
		industries.add("Sector");
		industries.add("Technology");
		industries.add("Telecommunications");
		industries.add("Utilities");

		System.out.println("Choose industries by number (1-12) one by one" + "or type 'done' to finish: ");
		for (int i = 0; i < industries.size(); i++) {
			System.out.println((i + 1) + ". " + industries.get(i));
		}

		while (true) {
			try {
				ArrayList<String> selectedIndustries = new ArrayList<String>();

				while (true) {
					Scanner temp = new Scanner(System.in);

					String input = temp.next();
					if (input.equals("done")) {
						break;
					}

					int index = Integer.parseInt(input) - 1;
					if (index >= 0 && index < industries.size()) {
						selectedIndustries.add(industries.get(index));
					} else {
						System.out.println("Invalid input. Please try again.");
					}
				}

				setPreferredIndustry(selectedIndustries);
				break;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again");
			}
		}

		// Prompt user to select a market capital
		System.out.println("Please choose a market capital: ");
		System.out.println("1. Micro Cap (Under $250 million)");
		System.out.println("2. Small Cap ($250 million to $2 billion)");
		System.out.println("3. Mid Cap ($2 billion to $10 billion)");
		System.out.println("4. Large Cap ($10 billion to $200 billion)");
		System.out.println("5. Mega Cap (Over $200 billion)");
		while (true) {

			// Check if the input is a valid number from 1 to 5

			try {
				Scanner temp = new Scanner(System.in);
				// Read user input
				String marketCapChoice = temp.nextLine();
				// Parse the input as an integer
				int num = Integer.parseInt(marketCapChoice);
				// start a while loop to keep prompting the client for a number until they enter
				// a valid number
				while (num < 1 || num > 5) {
					System.out.println("Please enter a number from 1 to 5: ");
					num = temp.nextInt(); // read the number from the client
				}
				setMarketCapital(num);
				break;
			}
			// If the input is not a valid number, print an error message and continue the
			// loop
			catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number from 1 to 5: ");
			}

		}
		System.out.println("Do you care about dividend?(yes or no)");
		Scanner temp3 = new Scanner(System.in);
		// Declare a variable to hold the client's attitude
		boolean dividend = false;
		String dividendTemp = temp3.next();
		// Loop until the client enters a valid answer
		while (!dividendTemp.equalsIgnoreCase("yes") && !dividendTemp.equalsIgnoreCase("no")) {
			System.out.println("Invalid input, please try again");
			// Read the client's response
			dividendTemp = temp3.next();
		}
		if (dividendTemp.equalsIgnoreCase("yes")) {
			dividend = true;
		}
		setDividend(dividend);
		/**
		 * 
		 * This while loop is used to prompt the user for the percentage of stock they
		 * want to invest in. The user's input is then checked to ensure it is within
		 * the allowed range. If the input is valid, the percentage of stock is set and
		 * the loop is exited. If the input is invalid, the user is prompted to try
		 * again.
		 */
		while (true) {
			try {
				// Set the minimum and maximum values for percentageOfStock
				double minPercentageOfStock = 0;
				double maxPercentageOfStock = 1;

				// Prompt the user for the percentage of stock they want to invest in
				System.out.println("What's the percentage of your total capital would go toward investing? (Enter between 0 - 100)");
				Scanner temp = new Scanner(System.in);
				double percentageOfStock = temp.nextDouble()/100;

				// Check if the input is within the allowed range and set the value accordingly
				if (percentageOfStock >= minPercentageOfStock && percentageOfStock <= maxPercentageOfStock) {
					setPercentageOfStock(percentageOfStock);
					break;
				} else {
					System.out.println("Invalid input, please try again");
				}

			} catch (Exception e) {
				System.out.println("Invalid input, please try again");
			}
		}
		while (true) {
			try {
				// Ask the user whether they prefer growth or value stocks
				System.out.println("Do you prefer growth or value stocks? (Enter 'growth' or 'value')");
				Scanner temp = new Scanner(System.in);
				String preference = temp.next();

				// Check the user's preference and return a boolean value
				if (preference.equalsIgnoreCase("growth")) {
					setGrowthorvalue(true);
					break;
				} else if (preference.equalsIgnoreCase("value")) {
					setGrowthorvalue(false);
					break;
				} else {
					System.out.println("Invalid input, please try again");
				}

			} catch (Exception e) {
				System.out.println("Invalid input, please try again");
			}
		}
		// close scanner
		// temp.close();
	}

	/**
	 * 
	 * This method asks the user to answer a questionnaire to determine their risk
	 * level in the stock market.
	 * 
	 * The questions are multiple choice and the user inputs their answer as a
	 * number from 1 to 4.
	 * 
	 * The answers are scored and the total score is used to determine the user's
	 * risk level.
	 */

	public void askForRiskLevel() {

		int score = 0;

		System.out.println("Welcome to the Risk Tolerance Questionnaire for the stock market!");
		System.out.println("Please answer the following questions to determine your risk level.");

		// Question 1
		System.out.println("\nQuestion 1: How long do you plan to invest in the stock market?");
		System.out.println("1. Less than 1 year");
		System.out.println("2. 1-3 years");
		System.out.println("3. 3-5 years");
		System.out.println("4. More than 5 years");

		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				int answer = input.nextInt();
				while (answer < 1 || answer > 4) {
					System.out.println("Please enter a number from 1 to 4: ");
					answer = input.nextInt(); // read the number from the client
				}
				if (answer == 1) {
					score += 1;
				} else if (answer == 2) {
					score += 2;
				} else if (answer == 3) {
					score += 3;
				} else if (answer == 4) {
					score += 4;
				}
				break;

			} catch (Exception e) {
				System.out.println("Invalid input, enter a number from 1 to 4");
			}
		}
		// Question 2
		System.out.println("\nQuestion 2: How much of your total savings do you plan to invest in the stock market?");
		System.out.println("1. Less than 25%");
		System.out.println("2. 25-50%");
		System.out.println("3. 50-75%");
		System.out.println("4. More than 75%");

		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				int answer = input.nextInt();
				while (answer < 1 || answer > 4) {
					System.out.println("Please enter a number from 1 to 4: ");
					answer = input.nextInt(); // read the number from the client
				}
				if (answer == 1) {
					score += 1;
				} else if (answer == 2) {
					score += 2;
				} else if (answer == 3) {
					score += 3;
				} else if (answer == 4) {
					score += 4;
				}
				break;

			} catch (Exception e) {
				System.out.println("Invalid input, enter a number from 1 to 4");
			}
		}
		// Question 3
		System.out.println("\nQuestion 3: How much experience do you have investing in the stock market?");
		System.out.println("1. None");
		System.out.println("2. Limited experience");
		System.out.println("3. Some experience");
		System.out.println("4. Extensive experience");

		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				int answer = input.nextInt();
				while (answer < 1 || answer > 4) {
					System.out.println("Please enter a number from 1 to 4: ");
					answer = input.nextInt(); // read the number from the client
				}
				if (answer == 1) {
					score += 1;
				} else if (answer == 2) {
					score += 2;
				} else if (answer == 3) {
					score += 3;
				} else if (answer == 4) {
					score += 4;
				}
				break;

			} catch (Exception e) {
				System.out.println("Invalid input, enter a number from 1 to 4");
			}
		}
		// Question 4
		System.out.println("\nQuestion 4: How comfortable are you with the potential for losses in the stock market?");
		System.out.println("1. Very uncomfortable");
		System.out.println("2. Somewhat uncomfortable");
		System.out.println("3. Neutral");
		System.out.println("4. Comfortable");

		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				int answer = input.nextInt();
				while (answer < 1 || answer > 4) {
					System.out.println("Please enter a number from 1 to 4: ");
					answer = input.nextInt(); // read the number from the client
				}
				if (answer == 1) {
					score += 1;
				} else if (answer == 2) {
					score += 2;
				} else if (answer == 3) {
					score += 3;
				} else if (answer == 4) {
					score += 4;
				}
				break;

			} catch (Exception e) {
				System.out.println("Invalid input, enter a number from 1 to 4");
			}
		}
		// Question 5: Financial Stability
		System.out.println("\nQuestion 5: How financially stable are you?");
		System.out.println("1. Very unstable");
		System.out.println("2. Somewhat unstable");
		System.out.println("3. Neutral");
		System.out.println("4. Stable");

		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				int answer = input.nextInt();
				while (answer < 1 || answer > 4) {
					System.out.println("Please enter a number from 1 to 4: ");
					answer = input.nextInt(); // read the number from the client
				}
				if (answer == 1) {
					score += 1;
				} else if (answer == 2) {
					score += 2;
				} else if (answer == 3) {
					score += 3;
				} else if (answer == 4) {
					score += 4;
				}
				break;

			} catch (Exception e) {
				System.out.println("Invalid input, enter a number from 1 to 4");
			}
		}
		String riskLevel = null;

		// Output risk level
		System.out.println(
				"\nBased on your answers, your risk level for investing in the stock market is " + score + ".");
		if (score <= 7) {
			riskLevel = "low";
			System.out.println("This indicates a low-risk investment strategy is recommended for you.");
		} else if (score <= 14) {
			riskLevel = "moderate";
			System.out.println("This indicates a moderate-risk investment strategy is recommended for you.");
		} else {
			riskLevel = "high";
			System.out.println("This indicates a high-risk investment strategy is recommended for you.");
		}

		this.riskLevel = riskLevel;

	}

}
