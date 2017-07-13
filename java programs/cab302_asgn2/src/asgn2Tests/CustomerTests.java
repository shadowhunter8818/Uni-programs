package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Mark Parsons
 * 
 *
 */
public class CustomerTests {
	
	// No exception tests
	@Test
	public void testcustomerNoException_delivery() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_pickup() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drive_range() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 6;
		int LocY = 5;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drive_range2() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 7;
		int LocY = 3;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drive_range_max() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 10;
		int LocY = 0;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drive_range_neg() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -2;
		int LocY = -3;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drive_range_neg2() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drone_range() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 6;
		int LocY = 5;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drone_range2() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 7;
		int LocY = 3;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drone_range_max() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 10;
		int LocY = 0;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drone_range_neg() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -2;
		int LocY = -3;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test
	public void testcustomerNoException_Drone_range_neg2() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	//Caller function tests
	@Test
	public void testcustomergetName_Driver() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
		String actual = cus.getName();
		String expected = name;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testcustomergetName_pickup() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
		String actual = cus.getName();
		String expected = name;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testcustomergetName_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
		String actual = cus.getName();
		String expected = name;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testcustomergetmobile_driver() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
		String actual = cus.getMobileNumber();
		String expected = mobile;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testcustomergetmobile_drone() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
		String actual = cus.getMobileNumber();
		String expected = mobile;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testcustomergetmobile_pickup() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
		String actual = cus.getMobileNumber();
		String expected = mobile;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testcustomergettype_Driver() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
		String actual = cus.getCustomerType();
		String expected = "Driver Delivery";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testcustomergettype_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
		String actual = cus.getCustomerType();
		String expected = "Drone Delivery";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testcustomergettype_Pickup() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
		String actual = cus.getCustomerType();
		String expected = "Pick Up";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testcustomergetdeliverydistance_Driver() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
		double actual = cus.getDeliveryDistance();
		double expected = 13;
		assertEquals(true, actual == expected);
	}
	
	@Test
	public void testcustomergetdeliverydistance_drone() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -8;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
		double actual = cus.getDeliveryDistance();
		double expected = Math.sqrt(128);
		assertEquals(true, actual == expected);
	}
	
	@Test
	public void testcustomergetdeliverydistance_pickup() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
		double actual = cus.getDeliveryDistance();
		double expected = 0;
		assertEquals(true, actual == expected);
	}
	
	@Test
	public void testcustomergetlocationX_Driver() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
		double actual = cus.getLocationX();
		double expected = LocX;
		assertEquals(true, actual == expected);
	}
	
	@Test
	public void testcustomergetlocationX_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
		double actual = cus.getLocationX();
		double expected = LocX;
		assertEquals(true, actual == expected);
	}
	
	@Test
	public void testcustomergetlocationX_pickup() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
		double actual = cus.getLocationX();
		double expected = LocX;
		assertEquals(true, actual == expected);
	}
	
	@Test
	public void testcustomergetlocationY_Driver() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
		double actual = cus.getLocationY();
		double expected = LocY;
		assertEquals(true, actual == expected);
	}
	
	@Test
	public void testcustomergetlocationY_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = -8;
		int LocY = -5;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
		double actual = cus.getLocationY();
		double expected = LocY;
		assertEquals(true, actual == expected);
	}
	
	@Test
	public void testcustomergetlocationY_pickup() throws CustomerException {
		String name = "Me";
		String mobile = "0411111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
		double actual = cus.getLocationY();
		double expected = LocY;
		assertEquals(true, actual == expected);
	}
	
	// Exception tests
	@Test (expected = CustomerException.class)
	public void testcustomernameException_Driver() throws CustomerException {
		String name = null;
		String mobile = "0411111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernameException_Drone() throws CustomerException {
		String name = null;
		String mobile = "0411111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernameException_pickup() throws CustomerException {
		String name = null;
		String mobile = "0411111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernumber_too_short_Exception_Driver() throws CustomerException {
		String name = "Me";
		String mobile = "041111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernumber_too_short_Exception_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "041111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernumber_too_short_Exception_Pickup() throws CustomerException {
		String name = "Me";
		String mobile = "041111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernumber_too_long_Exception_driver() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernumber_too_long_Exception_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernumber_too_long_Exception_Pickup() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernot_mobile_numberException_Driver() throws CustomerException {
		String name = "Me";
		String mobile = "41111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernot_mobile_numberException_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "41111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomernot_mobile_numberException_Pickup() throws CustomerException {
		String name = "Me";
		String mobile = "41111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomertype_wrong_pickup_Exception() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 1;
		int LocY = 1;
		Customer cus = new PickUpCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomertype_wrong_delivery_Exception_driver() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomertype_wrong_delivery_Exception_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 0;
		int LocY = 0;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomerException_too_far_driver() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 11;
		int LocY = 0;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomerException_too_far_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 11;
		int LocY = 0;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomerException_too_far2_Driver() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = -11;
		int LocY = 0;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomerException_too_far2_drone() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = -11;
		int LocY = 0;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomerException_too_far3_Driver() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 0;
		int LocY = 11;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomerException_too_far3_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 0;
		int LocY = 11;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomerException_too_far4_driver() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 0;
		int LocY = -11;
		Customer cus = new DriverDeliveryCustomer(name, mobile, LocX, LocY);
	}
	
	@Test (expected = CustomerException.class)
	public void testcustomerException_too_far4_Drone() throws CustomerException {
		String name = "Me";
		String mobile = "04111111111";
		int LocX = 0;
		int LocY = -11;
		Customer cus = new DroneDeliveryCustomer(name, mobile, LocX, LocY);
	}
}
