package asgn2Tests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalTime;

import asgn2Pizzas.Pizza;
//Classes being tested
import asgn2Pizzas.PizzaFactory;
//Exceptions expected
import asgn2Exceptions.PizzaException;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Filippo Capurso 
 * 
 */
public class PizzaFactoryTests {
	
	private PizzaFactory PF;
	
	@Before
	public void createPizzaFactory(){
		PF = new PizzaFactory();
	}
	
	// Test unexpected valued
	@Test (expected = PizzaException.class)
	public void testNullCode() throws PizzaException {
		String code = null;
		Pizza Pizza = PF.getPizza(code, 1, LocalTime.of(20,00,00), LocalTime.of(20,00,00));
	}

	@Test (expected = PizzaException.class)
	public void testNullOrderTime() throws PizzaException {
		LocalTime orderTime = null;
		Pizza Pizza = PF.getPizza("PZM", 1, orderTime, LocalTime.of(20,00,00));
	}
	
	@Test (expected = PizzaException.class)
	public void testNullDeliveryTime() throws PizzaException {
		LocalTime deliveryTime = null;
		Pizza Pizza = PF.getPizza("PZM", 1, LocalTime.of(20,00,00), deliveryTime);
	}
	
	// Test Codes
	@Test
	public void testMargheritaCode() throws PizzaException {
		Pizza M = PF.getPizza("PZM", 1, LocalTime.of(20,00,00), LocalTime.of(20,11,00));
		assertEquals(true, M.getPizzaType().equals("Margherita"));
	}
	
	@Test
	public void testVegetarianCode() throws PizzaException {
		Pizza M = PF.getPizza("PZV", 1, LocalTime.of(20,00,00), LocalTime.of(20,11,00));
		assertEquals(true, M.getPizzaType().equals("Vegetarian"));
	}
	
	@Test
	public void testMeatLoversCode() throws PizzaException {
		Pizza M = PF.getPizza("PZL", 1, LocalTime.of(20,00,00), LocalTime.of(20,11,00));
		assertEquals(true, M.getPizzaType().equals("Meat Lovers"));
	}

	@Test (expected = PizzaException.class)
	public void testEmptyCode() throws PizzaException {
		Pizza M = PF.getPizza("", 1, LocalTime.of(20,00,00), LocalTime.of(20,00,00));
	}

	@Test (expected = PizzaException.class)
	public void testNotRegisteredCode() throws PizzaException {
		Pizza M = PF.getPizza("PML", 1, LocalTime.of(20,00,00), LocalTime.of(20,00,00));
	}
	
	@Test (expected = PizzaException.class)
	public void testCodeWithSpace() throws PizzaException {
		Pizza M = PF.getPizza("PML ", 1, LocalTime.of(20,00,00), LocalTime.of(20,00,00));
	}
	
	// All other requirements are tested in asgn2Tests.PizzaTests
}
