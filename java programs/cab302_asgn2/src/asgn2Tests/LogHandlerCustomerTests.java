package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Mark Parsons
 */
public class LogHandlerCustomerTests {
private LogHandler LH;
	
	@Before
	public void createLogHandler(){
		LH = new LogHandler();
	}
	
	//populate customer dataset tests - Exceptions
	@Test (expected = LogHandlerException.class)
	public void testNullFilename() throws LogHandlerException, CustomerException {
		String filename = null;
		LH.populateCustomerDataset(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testEmptyFilename() throws LogHandlerException, CustomerException {
		String filename = "";
		LH.populateCustomerDataset(filename);
	}

	@Test (expected = LogHandlerException.class)
	public void testNotPresentFilename() throws LogHandlerException, CustomerException {
		String filename = "logs/ABCDEFGH.txt";
		LH.populateCustomerDataset(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testCorruptedFile() throws LogHandlerException, CustomerException {
		String filename = "logs/201701XX.txt";
		LH.populateCustomerDataset(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testErrorsFile() throws LogHandlerException, CustomerException {
		String filename = "logs/TestErrors.txt";
		LH.populateCustomerDataset(filename);
	}
	
	//populate customer dataset tests - No-Exceptions
	@Test
	public void testNoExceptionsProvidedLogFiles() throws LogHandlerException, CustomerException {
		String filename1 = "logs/20170101.txt";
		String filename2 = "logs/20170102.txt";
		String filename3 = "logs/20170103.txt";
		LH.populateCustomerDataset(filename1);
		LH.populateCustomerDataset(filename2);
		LH.populateCustomerDataset(filename3);
	}

	@Test
	public void testCorrectNumberOfPizzas() throws LogHandlerException, CustomerException {
		String filename = "logs/20170101.txt";
		ArrayList<Customer> CustomerArray = LH.populateCustomerDataset(filename);
		assertEquals(true, 3 == CustomerArray.size());
	}
	
	@Test
	public void testCorrectOrder() throws LogHandlerException, CustomerException {
		String filename = "logs/20170101.txt";
		ArrayList<Customer> CustomerArray = LH.populateCustomerDataset(filename);
		Customer Customer1 = CustomerArray.get(0);
		Customer Customer2 = CustomerArray.get(1);
		Customer Customer3 = CustomerArray.get(2);
		boolean correct1 = true;
		boolean correct2 = true;
		boolean correct3 = true;
		correct1 &= Customer1.getName().equals("Casey Jones");;
		correct1 &= Customer1.getMobileNumber().equals("0123456789");
		correct1 &= Customer1.getCustomerType().equals("Driver Delivery");
		correct1 &= 5 == Customer1.getLocationX();
		correct1 &= 5 == Customer1.getLocationY();
		correct2 &= Customer2.getName().equals("April O'Neal");;
		correct2 &= Customer2.getMobileNumber().equals("0987654321");
		correct2 &= Customer2.getCustomerType().equals("Drone Delivery");
		correct2 &= 3 == Customer2.getLocationX();
		correct2 &= 4 == Customer2.getLocationY();
		correct3 &= Customer3.getName().equals("Oroku Saki");;
		correct3 &= Customer3.getMobileNumber().equals("0111222333");
		correct3 &= Customer3.getCustomerType().equals("Pick Up");
		correct3 &= 0 == Customer3.getLocationX();
		correct3 &= 0 == Customer3.getLocationY();
		assertEquals(true, correct1);
		assertEquals(true, correct2);
		assertEquals(true, correct3);
	}
	
	//Create customer - Log handle exception
	@Test (expected = LogHandlerException.class)
	public void testNullline() throws LogHandlerException, CustomerException {
		String filename = null;
		LH.createCustomer(filename);
	}
	
	//Create customer - customer exceptions 
	@Test (expected = LogHandlerException.class)
	public void testNoOrderTime() throws LogHandlerException, CustomerException {
		String filename = "20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testNoDeliveryTime() throws LogHandlerException, CustomerException {
		String filename = "20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testnoCustomerName() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,0987654321,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testnoCustomerNumber() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,		,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testincorrectCustomerNumber() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,098765432,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testnotCustomermobileNumber() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,87654321,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testnoDeliveryCode() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,	,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testIncorrectDeliveryCode() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DtC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testnoCustomerLocationX() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,	,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testIncorrectCustomerLocationX() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,b,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testExceededCustomerLocationX() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,13,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testDecimalCustomerLocationX() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3.5,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testnoCustomerLocationY() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,	,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testIncorrectCustomerLocationY() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,b,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testExceededCustomerLocationY() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,14,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testDecimalCustomerLocationY() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4.5,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testNoPizzaCode() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testnoquantity() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testExtraCommaSection_1() throws LogHandlerException, CustomerException {
		String filename = ",20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testExtraCommaSection_2() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,Extra,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testExtraCommaSection_3() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,Extra,April O'Neal,0987654321,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testExtraCommaSection_4() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,Extra,0987654321,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testExtraCommaSection_5() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,Extra,DNC,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testExtraCommaSection_6() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,Extra,3,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testExtraCommaSection_7() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,Extra,4,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testExtraCommaSection_8() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,Extra,PZM,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testExtraCommaSection_9() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,Extra,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testExtraCommaSection_End() throws LogHandlerException, CustomerException {
		String filename = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1,1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testnoCommas() throws LogHandlerException, CustomerException {
		String filename = "20:00:00 20:25:00 April O'Neal 0987654321 DNC 3 4 PZM 1";
		LH.createCustomer(filename);
	}
	
	@Test (expected = CustomerException.class)
	public void testonlyCommas() throws LogHandlerException, CustomerException {
		String filename = ",,,,,,,,";
		LH.createCustomer(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testEmpty() throws LogHandlerException, CustomerException {
		String filename = "";
		LH.createCustomer(filename);
	}
	
	//Create Customer - No exceptions
	
	@Test
	public void testTimesWithSeconds() throws LogHandlerException, CustomerException {
		String line = "19:00:13,19:53:04,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createCustomer(line);
	}
	
	@Test
	public void testWeirdName() throws LogHandlerException, CustomerException {
		String line = "19:00:00,19:20:00,�pril O'Ne@l'McKill,0123456789,DVC,5,5,PZV,2";
		LH.createCustomer(line);
	}

	@Test
	public void testAllZerosPhoneNumber() throws LogHandlerException, CustomerException {
		String line = "19:00:00,19:20:00,Casey Jones,0000000000,DVC,5,5,PZV,2";
		LH.createCustomer(line);
	}

	@Test
	public void testNegativeXLoc() throws LogHandlerException, CustomerException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,-5,5,PZV,2";
		LH.createCustomer(line);
	}

	@Test
	public void testNegativeYLoc() throws LogHandlerException, CustomerException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,-5,PZV,2";
		LH.createCustomer(line);
	}

	@Test
	public void testBothNegativeLocations() throws LogHandlerException, CustomerException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,-5,-5,PZV,2";
		LH.createCustomer(line);
	}

	@Test
	public void testMaxLoc() throws LogHandlerException, CustomerException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,10,10,PZV,2";
		LH.createCustomer(line);
	}
	
	@Test
	public void testZerosLoc() throws LogHandlerException, CustomerException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,PUC,0,0,PZV,2";
		LH.createCustomer(line);
	}

	@Test
	public void testMinLoc() throws LogHandlerException, CustomerException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,-10,-10,PZV,2";
		LH.createCustomer(line);
	}
	
	@Test
	public void testCreatedCustomerValues() throws LogHandlerException, CustomerException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		Customer Customer1 = LH.createCustomer(line);
		boolean correct = true;
		correct &= Customer1.getName().equals("Casey Jones");;
		correct &= Customer1.getMobileNumber().equals("0123456789");
		correct &= Customer1.getCustomerType().equals("Driver Delivery");
		correct &= 5 == Customer1.getLocationX();
		correct &= 5 == Customer1.getLocationY();
		assertEquals(true, correct);
	}
	
}
