package asgn2Tests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import asgn2Pizzas.Pizza;
//Classes being tested
import asgn2Restaurant.LogHandler;
//Exceptions expected
import asgn2Exceptions.PizzaException;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHandler class.
* 
* @author Filippo Capurso
* 
*/
public class LogHandlerPizzaTests {
	
	private LogHandler LH;
	
	@Before
	public void createLogHandler(){
		LH = new LogHandler();
	}
	
	
	// Populate Pizza Dataset - Log Handler Exceptions
	
	@Test (expected = LogHandlerException.class)
	public void testNullFilename() throws LogHandlerException, PizzaException {
		String filename = null;
		LH.populatePizzaDataset(filename);
	}

	@Test (expected = LogHandlerException.class)
	public void testEmptyFilename() throws LogHandlerException, PizzaException {
		String filename = "";
		LH.populatePizzaDataset(filename);
	}

	@Test (expected = LogHandlerException.class)
	public void testNotPresentFilename() throws LogHandlerException, PizzaException {
		String filename = "ABCDEFGH.txt";
		LH.populatePizzaDataset(filename);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testCorruptedFile() throws LogHandlerException, CustomerException {
		String filename = "logs/201701XX.txt";
		LH.populateCustomerDataset(filename);
	}
	

	@Test (expected = Exception.class)
	public void testErrorsFile() throws LogHandlerException, CustomerException {
		String filename = "logs/TestErrors.txt";
		LH.populateCustomerDataset(filename);
	}
	
	// Populate Pizza Dataset - No exceptions and check values
	@Test
	public void testNoExceptionsProvidedLogFiles() throws LogHandlerException, PizzaException {
		String filename1 = "logs/20170101.txt";
		String filename2 = "logs/20170102.txt";
		String filename3 = "logs/20170103.txt";
		LH.populatePizzaDataset(filename1);
		LH.populatePizzaDataset(filename2);
		LH.populatePizzaDataset(filename3);
	}

	@Test
	public void testCorrectNumberOfPizzas() throws LogHandlerException, PizzaException {
		String filename = "logs/20170101.txt";
		ArrayList<Pizza> PizzaArray = LH.populatePizzaDataset(filename);
		assertEquals(true, 3 == PizzaArray.size());
	}
	
	@Test
	public void testCorrectOrder() throws LogHandlerException, PizzaException {
		String filename = "logs/20170101.txt";
		ArrayList<Pizza> PizzaArray = LH.populatePizzaDataset(filename);
		Pizza Pizza1 = PizzaArray.get(0);
		Pizza Pizza2 = PizzaArray.get(1);
		Pizza Pizza3 = PizzaArray.get(2);
		boolean correct1 = true;
		boolean correct2 = true;
		boolean correct3 = true;
		correct1 &= 10.0 == Pizza1.getPricePerPizza();
		correct1 &= 2 == Pizza1.getQuantity();
		correct1 &= Pizza1.getPizzaType().equals("Vegetarian");
		correct2 &= 8.0 == Pizza2.getPricePerPizza();
		correct2 &= 1 == Pizza2.getQuantity();
		correct2 &= Pizza2.getPizzaType().equals("Margherita");
		correct3 &= 12.0 == Pizza3.getPricePerPizza();
		correct3 &= 3 == Pizza3.getQuantity();
		correct3 &= Pizza3.getPizzaType().equals("Meat Lovers");
		assertEquals(true, correct1);
		assertEquals(true, correct2);
		assertEquals(true, correct3);
	}
	
	
	// Create Pizza - Log Handler Exceptions
	
	@Test (expected = LogHandlerException.class)
	public void testNullLine() throws LogHandlerException, PizzaException {
		String line = null;
		LH.createPizza(line);
	}
	
	
	// Create Pizza - Pizza Exceptions
	
	@Test (expected = PizzaException.class)
	public void testSpaceBeforeOrderTime() throws LogHandlerException, PizzaException {
		String line = " 19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testWrongOrderTime() throws LogHandlerException, PizzaException {
		String line = "19:000:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testNoOrderTime() throws LogHandlerException, PizzaException {
		String line = ",19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testOrderTimeOverflowHours() throws LogHandlerException, PizzaException {
		String line = "24:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testOrderTimeOverflowMinutes() throws LogHandlerException, PizzaException {
		String line = "00:60:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testOrderTimeOverflowSeconds() throws LogHandlerException, PizzaException {
		String line = "00:00:60,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testOrderTimeUnexpectedLetters() throws LogHandlerException, PizzaException {
		String line = "0b:a0:XX,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testOrderTimeUnexpectedCharacters() throws LogHandlerException, PizzaException {
		String line = "19-00;00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testSpaceBeforeDeliveryTime() throws LogHandlerException, PizzaException {
		String line = "19:00:00, 19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testWrongDeliveryTime() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:000,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testNoDeliveryTime() throws LogHandlerException, PizzaException {
		String line = "19:00:00,        ,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testDeliveryTimeOverflowHours() throws LogHandlerException, PizzaException {
		String line = "19:00:00,24:00:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testDeliveryTimeOverflowMinutes() throws LogHandlerException, PizzaException {
		String line = "19:00:00,00:60:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testDeliveryTimeOverflowSeconds() throws LogHandlerException, PizzaException {
		String line = "19:00:00,00:00:60,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testDeliveryTimeUnexpectedLetters() throws LogHandlerException, PizzaException {
		String line = "19:00:00,0b:a0:XX,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testDeliveryTimeUnexpectedCharacters() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19-00;00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = LogHandlerException.class)
	public void testMissingOneTime() throws LogHandlerException, PizzaException {
		String line = "19:00:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testOrderMissingDigitHour() throws LogHandlerException, PizzaException {
		String line = "1:10:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testOrderMissingDigitMinute() throws LogHandlerException, PizzaException {
		String line = "19:1:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testOrderMissingDigitSecond() throws LogHandlerException, PizzaException {
		String line = "19:00:1,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testDeliveryMissingDigitHour() throws LogHandlerException, PizzaException {
		String line = "19:00:00,1:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testDeliveryMissingDigitMinute() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:2:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}
	
	@Test (expected = PizzaException.class)
	public void testDeliveryMissingDigitSecond() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:1,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test (expected = LogHandlerException.class)
	public void testMissingPizzaCode() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,5,5,2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testNotRegisteredPizzaCode() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DRC,5,5,PML,2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testNoQuantity() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testQuantityNotAnInteger() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,X";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testQuantityOnlyNegativeSign() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,-";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testQuantityOnlyNegativeWithSpace() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,- 2";
		LH.createPizza(line);
	}

	@Test (expected = PizzaException.class)
	public void testQuantityDouble() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2.5";
		LH.createPizza(line);
	}

	@Test (expected = LogHandlerException.class)
	public void testOneExtraCommaEnd() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2,";
		LH.createPizza(line);
	}

	@Test (expected = LogHandlerException.class)
	public void testOneExtraCommaStart() throws LogHandlerException, PizzaException {
		String line = ",19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test (expected = LogHandlerException.class)
	public void testOneExtraNumberEnd() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2,2";
		LH.createPizza(line);
	}

	@Test (expected = LogHandlerException.class)
	public void testOneExtraTimeStart() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,19:40:00,Casey Jones,0123456789,DVC,5,5,PZV,2,2";
		LH.createPizza(line);
	}

	@Test (expected = LogHandlerException.class)
	public void testOneExtraStringMiddle() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey,Extra,Jones,0123456789,DVC,5,5,PZV,2,2";
		LH.createPizza(line);
	}

	@Test (expected = LogHandlerException.class)
	public void testNoCommas() throws LogHandlerException, PizzaException {
		String line = "19:00:0019:20:00Casey Jones0123456789DVC55PZV22";
		LH.createPizza(line);
	}

	@Test (expected = LogHandlerException.class)
	public void testOnlyCommas() throws LogHandlerException, PizzaException {
		String line = ",,,,,,,,,";
		LH.createPizza(line);
	}

	@Test (expected = LogHandlerException.class)
	public void testEmptyLine() throws LogHandlerException, PizzaException {
		String line = "";
		LH.createPizza(line);
	}
	
	
	// Create Pizza - No exceptions and checking values

	@Test
	public void testTimesWithSeconds() throws LogHandlerException, PizzaException {
		String line = "19:00:13,19:53:04,Casey Jones,0123456789,DVC,5,5,PZV,2";
		LH.createPizza(line);
	}

	@Test
	public void testMaxPizzaQuantity() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,10";
		LH.createPizza(line);
	}

	@Test
	public void testMinPizzaQuantity() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,1";
		LH.createPizza(line);
	}

	@Test
	public void testCreatedPizzaValues() throws LogHandlerException, PizzaException {
		String line = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
		Pizza P = LH.createPizza(line);
		boolean correct = true;
		correct &= 10 == P.getPricePerPizza();
		correct &= 2 == P.getQuantity();
		correct &= P.getPizzaType().equals("Vegetarian");
		assertEquals(true, correct);
	}
	
	
}
