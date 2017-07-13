package asgn2Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Pizzas.PizzaFactory;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Mark Parsons
 *
 */
public class CustomerFactoryTests {
	
	private CustomerFactory CF;
	@Before
	public void createCustomerFactory(){
		CF = new CustomerFactory();
	}
	
	// test exception
	@Test (expected = CustomerException.class)
	public void testNullcode () throws CustomerException {
		String customerCode = null;
		String name = "name";
		String mobileNumber = "0411111111";
		int locationX = 0;
		int locationY = 0;
		Customer cus = CF.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void testNullname () throws CustomerException {
		String customerCode = "PUC";
		String name = null;
		String mobileNumber = "0411111111";
		int locationX = 0;
		int locationY = 0;
		Customer cus = CF.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void testNullmobile () throws CustomerException {
		String customerCode = "PUC";
		String name = "name";
		String mobileNumber = null;
		int locationX = 0;
		int locationY = 0;
		Customer cus = CF.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void testnocode () throws CustomerException {
		String customerCode = "";
		String name = "name";
		String mobileNumber = "0411111111";
		int locationX = 0;
		int locationY = 0;
		Customer cus = CF.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void testincorrectcode () throws CustomerException {
		String customerCode = "PUT";
		String name = "name";
		String mobileNumber = "0411111111";
		int locationX = 0;
		int locationY = 0;
		Customer cus = CF.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void testlowercasecode () throws CustomerException {
		String customerCode = "puc";
		String name = "name";
		String mobileNumber = "0411111111";
		int locationX = 0;
		int locationY = 0;
		Customer cus = CF.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void testbadcode () throws CustomerException {
		String customerCode = "P U C";
		String name = "name";
		String mobileNumber = "0411111111";
		int locationX = 0;
		int locationY = 0;
		Customer cus = CF.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
	}
	
	// test cases
	@Test
	public void testpickup () throws CustomerException {
		String customerCode = "PUC";
		String name = "name";
		String mobileNumber = "0411111111";
		int locationX = 0;
		int locationY = 0;
		Customer cus = CF.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
		assertEquals(true, cus.getCustomerType().equals("Pick Up"));
	}
	
	@Test
	public void testDroneDelivery () throws CustomerException {
		String customerCode = "DNC";
		String name = "name";
		String mobileNumber = "0411111111";
		int locationX = 5;
		int locationY = 0;
		Customer cus = CF.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
		assertEquals(true, cus.getCustomerType().equals("Drone Delivery"));
	}
	
	@Test
	public void testDriverDelivery () throws CustomerException {
		String customerCode = "DVC";
		String name = "name";
		String mobileNumber = "0411111111";
		int locationX = 5;
		int locationY = 0;
		Customer cus = CF.getCustomer(customerCode, name, mobileNumber, locationX, locationY);
		assertEquals(true, cus.getCustomerType().equals("Driver Delivery"));
	}
}
