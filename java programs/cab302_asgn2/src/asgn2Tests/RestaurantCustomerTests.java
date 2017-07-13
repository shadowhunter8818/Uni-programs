package asgn2Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Mark Parsons
 */
public class RestaurantCustomerTests {
	private PizzaRestaurant CT;
	@Before
	public void createPizzaRestaurant() {
		CT = new PizzaRestaurant();
	}
	
	// Constructor
	@Test
	public void testConstructor() throws CustomerException{
		assertEquals(true, 0 == CT.getNumCustomerOrders());
	}
	
	@Test (expected = LogHandlerException.class)
	public void testProcessLogNullFilename() throws PizzaException, CustomerException, LogHandlerException{
		String filename = null;
		assertEquals(false,CT.processLog(filename));
	}

	@Test
	public void testProcessLogGovenLogsNoException() throws PizzaException, CustomerException, LogHandlerException{
		String filename1 = "logs/20170101.txt";
		String filename2 = "logs/20170102.txt";
		String filename3 = "logs/20170103.txt";
		assertEquals(true, CT.processLog(filename1) && CT.processLog(filename2) && CT.processLog(filename3));
	}
	
	@Test (expected = CustomerException.class)
	public void testgetCustomerByIndexNegativeIndex() throws PizzaException, CustomerException, LogHandlerException{
		String filename1 = "logs/20170101.txt";
		CT.processLog(filename1);
		CT.getCustomerByIndex(-1);
	}
	
	@Test
	public void testgetCustomerByIndexInvalidIndex() throws PizzaException, CustomerException, LogHandlerException{
		String filename1 = "logs/20170101.txt";
		CT.processLog(filename1);
		CT.getCustomerByIndex(0);
	}
	
	@Test (expected = CustomerException.class)
	public void testgetCustomerByIndexOutofboundsIndex() throws PizzaException, CustomerException, LogHandlerException{
		String filename1 = "logs/20170101.txt";
		CT.processLog(filename1);
		CT.getCustomerByIndex(7);
	}
	
	@Test
	public void testgetCustomerByIndex() throws PizzaException, CustomerException, LogHandlerException{
		String filename1 = "logs/20170101.txt";
		CT.processLog(filename1);
		Customer cus = CT.getCustomerByIndex(0);
		boolean correct = true;
		correct &= cus.getName().equals("Casey Jones");
		correct &= cus.getMobileNumber().equals("0123456789");
		correct &= cus.getCustomerType().equals("Driver Delivery");
		correct &= 5 == cus.getLocationX();
		correct &= 5 == cus.getLocationY();
		assertEquals(true, correct);
	}
	
	@Test
	public void testgetNumCustomerOrders() throws PizzaException, CustomerException, LogHandlerException{
		String filename1 = "logs/20170101.txt";
		CT.processLog(filename1);
		assertEquals(true, 3 == CT.getNumCustomerOrders());
	}
	
	
	@Test
	public void testgetDeliverydistance() throws PizzaException, CustomerException, LogHandlerException{
		String filename1 = "logs/20170101.txt";
		CT.processLog(filename1);
		assertEquals(true, 15 == CT.getTotalDeliveryDistance());
	}
	
	@Test
	public void testgetCustomerbyindex() throws PizzaException, CustomerException, LogHandlerException{
		String filename1 = "logs/20170101.txt";
		CT.processLog(filename1);
		String Casey = "Casey Jones";
		assertEquals(true, CT.getCustomerByIndex(0).getName().equals(Casey));
	}
}
