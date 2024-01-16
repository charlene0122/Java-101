package FinalProject_CIT591.MongoDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	//test for a valid client
	public void setClientInfo_validInput_success() {
	    Client client = new Client();
	    // set the client's name to "John"
	    client.setName("John");

	    // set the client's age to 30
	    client.setAge(30);

	    // set the client's marital status to true (not single)
	    client.setMaritalStatus(true);

	    // set the client's total capital to 10000
	    client.setTotalCapital(10000);

	    // set the client's annual income to 50000
	    client.setAnnualIncome(50000);

	    // set the client's preferred industries to "tech" and "finance"
	    ArrayList<String> selectedIndustries = new ArrayList<String>();
	    selectedIndustries.add("tech");
	    selectedIndustries.add("finance");
	    client.setPreferredIndustry(selectedIndustries);

	    // set the client's market capital to 100000
	    client.setMarketCapital(100000);

	    // set the client's dividend preference to true (wants dividends)
	    client.setDividend(true);

	    // set the client's percentage of stock preference to 0.3
	    client.setPercentageOfStock(0.3);

	    // set the client's growth or value preference to true (prefers growth)
	    client.setGrowthorvalue(true);

	    // set the client's risk level to "high"
	    client.setRiskLevel("high");

	    // assert that the client's name is "John"
	    assertEquals(client.getName(), "John");

	    // assert that the client's age is 30
	    assertEquals(client.getAge(), 30);

	    // assert that the client's marital status is true
	    assertTrue(client.getMaritalStatus());

	    // assert that the client's total capital is 10000
	    assertEquals(client.getTotalCapital(), 10000, 0.01);

	    // assert that the client's annual income is 50000
	    assertEquals(client.getAnnualIncome(), 50000, 0.01);

	    // assert that the client's preferred industries are "tech" and "finance"
	    assertEquals(client.getPreferredIndustry().get(0), "tech");
	    assertEquals(client.getPreferredIndustry().get(1), "finance");

	    // assert that the client's market capital is 100000
	    assertEquals(client.getMarketCapital(), 100000);

	    // assert that the client's dividend preference is true
	    assertTrue(client.getDividend());

	    // assert that the client's percentage of stock preference is 0.3
	    assertEquals(client.getPercentageOfStock(), 0.3, 0.01);

	    // assert that the client's growth or value preference is true
	    assertTrue(client.getGrowthorvalue());

	    // assert that the client's risk level is "high"
	    assertEquals(client.getRiskLevel(), "high");

	}

	// Test for invalid inputs
	@Test
	public void testInvalidInputs() {
	    // create a Client object
	    Client client = new Client();

	    // test invalid name
	    String name = "";
	    client.setName(name);
	    assertEquals(null, client.getName());

	    // test invalid age
	    int age = -1;
	    client.setAge(age);
	    assertEquals(0, client.getAge());

	    // test marital status
	    boolean maritalStatus = false;
	    client.setMaritalStatus(maritalStatus);
	    assertEquals(false, client.getMaritalStatus());
	
	 // test invalid total capital
		client.setTotalCapital(-1);
		assertNotEquals(-1, client.getTotalCapital());

		 // test invalid annual income
		client.setAnnualIncome(-1);
		assertNotEquals(-1, client.getAnnualIncome());

		 // test invalid market capital
		client.setMarketCapital(-1);
		assertNotEquals(-1, client.getMarketCapital());

		 // test invalid percentage of stock
		client.setPercentageOfStock(-1);
		assertNotEquals(-1, client.getPercentageOfStock());

		 // test growth or value preference
		client.setGrowthorvalue(false);
		assertNotEquals(true, client.getGrowthorvalue());
		
		 // test invalid risk level 
		client.setRiskLevel("high");
		assertNotEquals(-1, client.getRiskLevel());
		}
	
}
