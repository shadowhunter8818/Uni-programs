package asgn2Tests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import asgn2Pizzas.Pizza;
//Classes being tested
import asgn2Restaurant.PizzaRestaurant;
//Exceptions expected
import asgn2Exceptions.PizzaException;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Filippo Capurso
 *
 */
public class RestaurantPizzaTests {
	private PizzaRestaurant PR;
	
	@Before
	public void createPizzaRestaurant() {
		PR = new PizzaRestaurant();
	}
	
	// Constructor
	@Test
	public void testConstructor() throws PizzaException{
		assertEquals(true, 0 == PR.getNumPizzaOrders());
	}
	
	// Process Log
	// NB: Most of the exceptions are triggered by called classes
	//     Therefore will only check for some just for safety

	@Test (expected = LogHandlerException.class)
	public void testProcessLogNullFilename() throws PizzaException, CustomerException, LogHandlerException{
		String filename = null;
		assertEquals(false,PR.processLog(filename));
	}

	@Test
	public void testProcessLogGovenLogsNoException() throws PizzaException, CustomerException, LogHandlerException{
		String filename1 = "logs/20170101.txt";
		String filename2 = "logs/20170102.txt";
		String filename3 = "logs/20170103.txt";
		assertEquals(true, PR.processLog(filename1) && PR.processLog(filename2) && PR.processLog(filename3));
	}
	
	@Test (expected = PizzaException.class)
	public void testPizzaErrorsFile() throws PizzaException, CustomerException, LogHandlerException{
		String filename = "logs/OnlyPizzaErrors.txt";
		PR.processLog(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testCustomersErrorsFile() throws PizzaException, CustomerException, LogHandlerException{
		String filename = "logs/OnlyCustomersErrors.txt";
		PR.processLog(filename);
	}
	
	@Test (expected = Exception.class)
	public void testMixedErrorsFile() throws PizzaException, CustomerException, LogHandlerException{
		String filename = "logs/OnlyCustomersErrors.txt";
		PR.processLog(filename);
	}

	
	// Get Pizza by Index
	
	@Test (expected = PizzaException.class)
	public void testNegativeIndex() throws PizzaException, CustomerException, LogHandlerException{
		String filename = "logs/20170101.txt";
		PR.processLog(filename);
		PR.getPizzaByIndex(-1);
	}

	@Test (expected = PizzaException.class)
	public void testOutOfBoundIndex() throws PizzaException, CustomerException, LogHandlerException{
		String filename = "logs/20170101.txt";
		PR.processLog(filename);
		PR.getPizzaByIndex(3);
	}

	@Test
	public void testCheckPizzaRetrievedByIndex() throws PizzaException, CustomerException, LogHandlerException{
		String filename = "logs/20170101.txt";
		PR.processLog(filename);
		Pizza P = PR.getPizzaByIndex(1);
		boolean correct = true;
		correct &= 8.0 == P.getPricePerPizza();
		correct &= 1 == P.getQuantity();
		correct &= P.getPizzaType().equals("Margherita");
		assertEquals(true, correct);
	}
	
	
	// Get Num Pizza Orders
	@Test
	public void testNumOfPizzaOrders() throws PizzaException, CustomerException, LogHandlerException{
		String filename = "logs/20170101.txt";
		PR.processLog(filename);
		assertEquals(true, 3 == PR.getNumPizzaOrders());
	}
	
	// Get Total Profit
	@Test
	public void testTotalProfit() throws PizzaException, CustomerException, LogHandlerException{
		String filename = "logs/20170101.txt";
		PR.processLog(filename);
		assertEquals(true, 36.5 == PR.getTotalProfit());
	}

	// Reset Details
	@Test ()
	public void testResetDetails() throws PizzaException, CustomerException, LogHandlerException{
		String filename = "logs/20170101.txt";
		PR.processLog(filename);
		PR.resetDetails();
		assertEquals(true, 0 == PR.getNumPizzaOrders());
	}
}
