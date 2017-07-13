package asgn2Tests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalTime;
// Classes being tested
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.VegetarianPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
// Exceptions expected
import asgn2Exceptions.PizzaException;



/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Filippo Capurso
 *
 */
public class PizzaTests {
	
//	### Tests for Margherita ###
	
	// Quantity tests
	
	@Test (expected = PizzaException.class)
	public void testMargheritaZeroQuantityException() throws PizzaException {
		int quantity = 0;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMargheritaNegativeQuantityException() throws PizzaException {
		int quantity = -1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMargheritaElevenQuantityException() throws PizzaException {
		int quantity = 11;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMargheritaTwentyQuantityException() throws PizzaException {
		int quantity = 20;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMargheritaOneQuantityNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMargheritaTwoQuantityNoException() throws PizzaException {
		int quantity = 2;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void testMargheritaNineQuantityNoException() throws PizzaException {
		int quantity = 9;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	
	// Order Time tests

	@Test (expected = PizzaException.class)
	public void testMargheritaOneSecBeforeSevenException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(18,59,59);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testMargheritaSevenAMException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(7,00,00);
		LocalTime deliveryTime = LocalTime.of(7,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testMargheritaTenAMException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(10,00,00);
		LocalTime deliveryTime = LocalTime.of(10,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMargheritaExactlyElevenException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(23,00,00);
		LocalTime deliveryTime = LocalTime.of(23,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMargheritaMidnightException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(00,00,00);
		LocalTime deliveryTime = LocalTime.of(00,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMargheritaExactlySevenNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(19,00,00);
		LocalTime deliveryTime = LocalTime.of(19,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMargheritaOneSecBeforeElevenNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(22,59,59);
		LocalTime deliveryTime = LocalTime.of(23,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	
	// Delivery Time tests
	
	@Test (expected = PizzaException.class)
	public void testMargheritaSameOrderAndDeliveryTimeException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,00,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMargheritaDeliveryOneSecAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,00,01);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMargheritaDeliveryAlmostTenMinutesAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,9,59);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMargheritaDeliveryTenMinutesAfterOrderNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,10,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMargheritaDeliveryOneHourAfterOrderNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(21,00,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMargheritaDeliveryOneHourOneSecAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(21,00,01);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMargheritaDeliveryTwoHoursAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(22,00,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testMargheritaDeliveryOneSecBeforeOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(19,59,59);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMargheritaDeliveryOneHourBeforeOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,30,00);
		LocalTime deliveryTime = LocalTime.of(19,30,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMargheritaLatestDeliveryPossibleNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(22,59,59);
		LocalTime deliveryTime = LocalTime.of(23,59,59);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMargheritaEarliestDeliveryPossibleNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(19,00,00);
		LocalTime deliveryTime = LocalTime.of(19,10,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testMargheritaOneSecAfterLatestDeliveryException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(22,59,59);
		LocalTime deliveryTime = LocalTime.of(00,00,00);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testMargheritaOneSecBeforeEarliestDeliveryException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(19,00,00);
		LocalTime deliveryTime = LocalTime.of(19,9,59);
		MargheritaPizza MP = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	
	

//	### Tests for Vegetarian ###
	
	// Quantity tests
	
	@Test (expected = PizzaException.class)
	public void testVegetarianZeroQuantityException() throws PizzaException {
		int quantity = 0;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testVegetarianNegativeQuantityException() throws PizzaException {
		int quantity = -1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testVegetarianElevenQuantityException() throws PizzaException {
		int quantity = 11;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testVegetarianTwentyQuantityException() throws PizzaException {
		int quantity = 20;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testVegetarianOneQuantityNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testVegetarianTwoQuantityNoException() throws PizzaException {
		int quantity = 2;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void testVegetarianNineQuantityNoException() throws PizzaException {
		int quantity = 9;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	
	// Order Time tests

	@Test (expected = PizzaException.class)
	public void testVegetarianOneSecBeforeSevenException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(18,59,59);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testVegetarianSevenAMException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(7,00,00);
		LocalTime deliveryTime = LocalTime.of(7,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testVegetarianTenAMException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(10,00,00);
		LocalTime deliveryTime = LocalTime.of(10,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testVegetarianExactlyElevenException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(23,00,00);
		LocalTime deliveryTime = LocalTime.of(23,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testVegetarianMidnightException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(00,00,00);
		LocalTime deliveryTime = LocalTime.of(00,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testVegetarianExactlySevenNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(19,00,00);
		LocalTime deliveryTime = LocalTime.of(19,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testVegetarianOneSecBeforeElevenNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(22,59,59);
		LocalTime deliveryTime = LocalTime.of(23,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	
	// Delivery Time tests
	
	@Test (expected = PizzaException.class)
	public void testVegetarianSameOrderAndDeliveryTimeException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,00,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testVegetarianDeliveryOneSecAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,00,01);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testVegetarianDeliveryAlmostTenMinutesAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,9,59);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testVegetarianDeliveryTenMinutesAfterOrderNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,10,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testVegetarianDeliveryOneHourAfterOrderNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(21,00,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testVegetarianDeliveryOneHourOneSecAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(21,00,01);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testVegetarianDeliveryTwoHoursAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(22,00,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testVegetarianDeliveryOneSecBeforeOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(19,59,59);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testVegetarianDeliveryOneHourBeforeOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,30,00);
		LocalTime deliveryTime = LocalTime.of(19,30,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testVegetarianLatestDeliveryPossibleNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(22,59,59);
		LocalTime deliveryTime = LocalTime.of(23,59,59);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testVegetarianEarliestDeliveryPossibleNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(19,00,00);
		LocalTime deliveryTime = LocalTime.of(19,10,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testVegetarianOneSecAfterLatestDeliveryException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(22,59,59);
		LocalTime deliveryTime = LocalTime.of(00,00,00);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testVegetarianOneSecBeforeEarliestDeliveryException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(19,00,00);
		LocalTime deliveryTime = LocalTime.of(19,9,59);
		VegetarianPizza MP = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	

//	### Tests for MeatLovers ###
	
	// Quantity tests
	
	@Test (expected = PizzaException.class)
	public void testMeatLoversZeroQuantityException() throws PizzaException {
		int quantity = 0;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMeatLoversNegativeQuantityException() throws PizzaException {
		int quantity = -1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMeatLoversElevenQuantityException() throws PizzaException {
		int quantity = 11;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMeatLoversTwentyQuantityException() throws PizzaException {
		int quantity = 20;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMeatLoversOneQuantityNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMeatLoversTwoQuantityNoException() throws PizzaException {
		int quantity = 2;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void testMeatLoversNineQuantityNoException() throws PizzaException {
		int quantity = 9;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	
	// Order Time tests

	@Test (expected = PizzaException.class)
	public void testMeatLoversOneSecBeforeSevenException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(18,59,59);
		LocalTime deliveryTime = LocalTime.of(20,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testMeatLoversSevenAMException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(7,00,00);
		LocalTime deliveryTime = LocalTime.of(7,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testMeatLoversTenAMException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(10,00,00);
		LocalTime deliveryTime = LocalTime.of(10,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMeatLoversExactlyElevenException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(23,00,00);
		LocalTime deliveryTime = LocalTime.of(23,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMeatLoversMidnightException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(00,00,00);
		LocalTime deliveryTime = LocalTime.of(00,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMeatLoversExactlySevenNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(19,00,00);
		LocalTime deliveryTime = LocalTime.of(19,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMeatLoversOneSecBeforeElevenNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(22,59,59);
		LocalTime deliveryTime = LocalTime.of(23,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	
	// Delivery Time tests
	
	@Test (expected = PizzaException.class)
	public void testMeatLoversSameOrderAndDeliveryTimeException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,00,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMeatLoversDeliveryOneSecAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,00,01);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMeatLoversDeliveryAlmostTenMinutesAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,9,59);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMeatLoversDeliveryTenMinutesAfterOrderNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(20,10,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMeatLoversDeliveryOneHourAfterOrderNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(21,00,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMeatLoversDeliveryOneHourOneSecAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(21,00,01);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMeatLoversDeliveryTwoHoursAfterOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(22,00,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testMeatLoversDeliveryOneSecBeforeOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,00,00);
		LocalTime deliveryTime = LocalTime.of(19,59,59);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test (expected = PizzaException.class)
	public void testMeatLoversDeliveryOneHourBeforeOrderException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(20,30,00);
		LocalTime deliveryTime = LocalTime.of(19,30,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMeatLoversLatestDeliveryPossibleNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(22,59,59);
		LocalTime deliveryTime = LocalTime.of(23,59,59);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}

	@Test
	public void testMeatLoversEarliestDeliveryPossibleNoException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(19,00,00);
		LocalTime deliveryTime = LocalTime.of(19,10,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testMeatLoversOneSecAfterLatestDeliveryException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(22,59,59);
		LocalTime deliveryTime = LocalTime.of(00,00,00);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void testMeatLoversOneSecBeforeEarliestDeliveryException() throws PizzaException {
		int quantity = 1;
		LocalTime orderTime = LocalTime.of(19,00,00);
		LocalTime deliveryTime = LocalTime.of(19,9,59);
		MeatLoversPizza MP = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	

//	###### Tests for abstract Pizza CLass ######
	
// 	Would use @Before to create MeatLoversPizza instance,
//	but this would apply to all previous tests also.
// 	Therefore following is a non-testing method that will be called in each 
//  Though for the first tests the initial price and type values will be checked for
// 	all three pizzas.
	
	public Pizza createMLPizza() throws PizzaException{
		Pizza ML = new MeatLoversPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		return ML;
	}
	
	// Margherita - Price & Type
	@Test 
	public void testMargheritaIndividualPriceSinglePizza() throws PizzaException {
		Pizza M = new MargheritaPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, 8.0 == M.getPricePerPizza());
	}
	
	@Test 
	public void testMargheritaIndividualPriceMoreThanOnePizza() throws PizzaException {
		Pizza M = new MargheritaPizza(5, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, 8.0 == M.getPricePerPizza());
	}

	@Test 
	public void testMargheritaType() throws PizzaException {
		Pizza M = new MargheritaPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, M.getPizzaType().equals("Margherita"));
	}
	
	// Vegetarian - Price & Type
	@Test 
	public void testVegetarianIndividualPriceSinglePizza() throws PizzaException {
		Pizza V = new VegetarianPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, 10.0 == V.getPricePerPizza());
	}
	
	@Test 
	public void testVegetarianIndividualPriceMoreThanOnePizza() throws PizzaException {
		Pizza V = new VegetarianPizza(5, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, 10.0 == V.getPricePerPizza());
	}

	@Test 
	public void testVegetarianType() throws PizzaException {
		Pizza V = new VegetarianPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, V.getPizzaType().equals("Vegetarian"));
	}
	
	// MeatLovers - Price & Type
	@Test 
	public void testMeatLoversIndividualPriceSinglePizza() throws PizzaException {
		Pizza ML = new MeatLoversPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, 12.0 == ML.getPricePerPizza());
	}
	
	@Test 
	public void testMeatLoversIndividualPriceMoreThanOnePizza() throws PizzaException {
		Pizza ML = new MeatLoversPizza(5, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, 12.0 == ML.getPricePerPizza());
	}

	@Test 
	public void testMeatLoversType() throws PizzaException {
		Pizza ML = new MeatLoversPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, ML.getPizzaType().equals("Meat Lovers"));
	}
	
	// ### Pizza Class Tests (using MeatLoversPizza() instance) ###
	
	// Check quantity (unchanged)
	@Test 
	public void testPizzaQuantitySingle() throws PizzaException {
		Pizza ML = new MeatLoversPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, 1 == ML.getQuantity());
	}

	@Test 
	public void testPizzaQuantityThree() throws PizzaException {
		Pizza ML = createMLPizza();
		assertEquals(true, 3 == ML.getQuantity());
	}

	@Test 
	public void testPizzaQuantityTen() throws PizzaException {
		Pizza ML = new MeatLoversPizza(10, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, 10 == ML.getQuantity());
	}
	
	// Calculate & check Cost per Pizza
	@Test 
	public void testMLIndividualCostSinglePizza() throws PizzaException {
		Pizza ML = new MeatLoversPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		ML.calculateCostPerPizza();
		assertEquals(true, 5.0 == ML.getCostPerPizza());
	}

	@Test 
	public void testMLIndividualCostMultiplePizzas() throws PizzaException {
		Pizza ML = createMLPizza();
		ML.calculateCostPerPizza();
		assertEquals(true, 5.0 == ML.getCostPerPizza());
	}
	
	// Order cost
	@Test 
	public void testMLOrderCostSinglePizza() throws PizzaException {
		Pizza ML = new MeatLoversPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		ML.calculateCostPerPizza();
		assertEquals(true, 5.0 == ML.getOrderCost());
	}

	@Test 
	public void testMLOrderCostMultiplePizzas() throws PizzaException {
		Pizza ML = createMLPizza();
		ML.calculateCostPerPizza();
		assertEquals(true, 15.0 == ML.getOrderCost());
	}

	// Order price
	@Test 
	public void testMLOrderPriceSinglePizza() throws PizzaException {
		Pizza ML = new MeatLoversPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		assertEquals(true, 12.0 == ML.getOrderPrice());
	}

	@Test 
	public void testMLOrderPriceMultiplePizzas() throws PizzaException {
		Pizza ML = createMLPizza();
		assertEquals(true, 36.0 == ML.getOrderPrice());
	}

	// Order profit
	@Test 
	public void testMLOrderProfitSinglePizza() throws PizzaException {
		Pizza ML = new MeatLoversPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
		ML.calculateCostPerPizza();
		assertEquals(true, 7.0 == ML.getOrderProfit());
	}

	@Test 
	public void testMLOrderProfitMultiplePizzas() throws PizzaException {
		Pizza ML = createMLPizza();
		ML.calculateCostPerPizza();
		assertEquals(true, 21.0 == ML.getOrderProfit());
	}
	
	// Check pizza toppings
	@Test 
	public void testMLToppings() throws PizzaException {
		Pizza ML = createMLPizza();
		boolean correct = true;
		correct &= ML.containsTopping(PizzaTopping.CHEESE);
		correct &= ML.containsTopping(PizzaTopping.TOMATO);
		correct &= ML.containsTopping(PizzaTopping.BACON);
		correct &= ML.containsTopping(PizzaTopping.SALAMI);
		correct &= ML.containsTopping(PizzaTopping.PEPPERONI);
		correct &= !ML.containsTopping(PizzaTopping.CAPSICUM);
		correct &= !ML.containsTopping(PizzaTopping.MUSHROOM);
		correct &= !ML.containsTopping(PizzaTopping.EGGPLANT);
		assertEquals(true, correct);
	}
	
	
	// ### Checking for other pizza subclasses ###
	
	// MARGHERITA
	
	// Calculate & check Cost per Pizza
		@Test 
		public void testMIndividualCostSinglePizza() throws PizzaException {
			Pizza M = new MargheritaPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			M.calculateCostPerPizza();
			assertEquals(true, 1.5 == M.getCostPerPizza());
		}

		@Test 
		public void testMIndividualCostMultiplePizzas() throws PizzaException {
			Pizza M = new MargheritaPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			M.calculateCostPerPizza();
			assertEquals(true, 1.5 == M.getCostPerPizza());
		}
		
		// Order cost
		@Test 
		public void testMOrderCostSinglePizza() throws PizzaException {
			Pizza M = new MargheritaPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			M.calculateCostPerPizza();
			assertEquals(true, 1.5 == M.getOrderCost());
		}

		@Test 
		public void testMOrderCostMultiplePizzas() throws PizzaException {
			Pizza M = new MargheritaPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			M.calculateCostPerPizza();
			assertEquals(true, 4.5 == M.getOrderCost());
		}

		// Order price
		@Test 
		public void testMOrderPriceSinglePizza() throws PizzaException {
			Pizza M = new MargheritaPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			assertEquals(true, 8.0 == M.getOrderPrice());
		}

		@Test 
		public void testMOrderPriceMultiplePizzas() throws PizzaException {
			Pizza M = new MargheritaPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			assertEquals(true, 24.0 == M.getOrderPrice());
		}

		// Order profit
		@Test 
		public void testMOrderProfitSinglePizza() throws PizzaException {
			Pizza M = new MargheritaPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			M.calculateCostPerPizza();
			assertEquals(true, 6.5 == M.getOrderProfit());
		}

		@Test 
		public void testMOrderProfitMultiplePizzas() throws PizzaException {
			Pizza M = new MargheritaPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			M.calculateCostPerPizza();
			assertEquals(true, 19.5 == M.getOrderProfit());
		}
		
		// Check pizza toppings
		@Test 
		public void testMToppings() throws PizzaException {
			Pizza M = new MargheritaPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			boolean correct = true;
			correct &= M.containsTopping(PizzaTopping.CHEESE);
			correct &= M.containsTopping(PizzaTopping.TOMATO);
			correct &= !M.containsTopping(PizzaTopping.BACON);
			correct &= !M.containsTopping(PizzaTopping.SALAMI);
			correct &= !M.containsTopping(PizzaTopping.PEPPERONI);
			correct &= !M.containsTopping(PizzaTopping.CAPSICUM);
			correct &= !M.containsTopping(PizzaTopping.MUSHROOM);
			correct &= !M.containsTopping(PizzaTopping.EGGPLANT);
			assertEquals(true, correct);
		}
		
		
		// VEGETARIAN
		
		// Calculate & check Cost per Pizza
		@Test 
		public void testVIndividualCostSinglePizza() throws PizzaException {
			Pizza V = new VegetarianPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			V.calculateCostPerPizza();
			double cost = 5.5;
			assertEquals(true, cost == V.getCostPerPizza());
		}

		@Test 
		public void testVIndividualCostMultiplePizzas() throws PizzaException {
			Pizza V = new VegetarianPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			V.calculateCostPerPizza();
			assertEquals(true, 5.5 == V.getCostPerPizza());
		}
		
		// Order cost
		@Test 
		public void testVOrderCostSinglePizza() throws PizzaException {
			Pizza V = new VegetarianPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			V.calculateCostPerPizza();
			assertEquals(true, 5.5 == V.getOrderCost());
		}

		@Test 
		public void testVOrderCostMultiplePizzas() throws PizzaException {
			Pizza V = new VegetarianPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			V.calculateCostPerPizza();
			assertEquals(true, 16.5 == V.getOrderCost());
		}

		// Order price
		@Test 
		public void testVOrderPriceSinglePizza() throws PizzaException {
			Pizza V = new VegetarianPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			assertEquals(true, 10.0 == V.getOrderPrice());
		}

		@Test 
		public void testVOrderPriceMultiplePizzas() throws PizzaException {
			Pizza V = new VegetarianPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			assertEquals(true, 30.0 == V.getOrderPrice());
		}

		// Order profit
		@Test 
		public void testVOrderProfitSinglePizza() throws PizzaException {
			Pizza V = new VegetarianPizza(1, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			V.calculateCostPerPizza();
			assertEquals(true, 4.5 == V.getOrderProfit());
		}

		@Test 
		public void testVOrderProfitMultiplePizzas() throws PizzaException {
			Pizza V = new VegetarianPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			V.calculateCostPerPizza();
			assertEquals(true, 13.5 == V.getOrderProfit());
		}
		
		// Check pizza toppings
		@Test 
		public void testVToppings() throws PizzaException {
			Pizza V = new VegetarianPizza(3, LocalTime.of(20,00,00), LocalTime.of(20,30,00));
			boolean correct = true;
			correct &= V.containsTopping(PizzaTopping.CHEESE);
			correct &= V.containsTopping(PizzaTopping.TOMATO);
			correct &= !V.containsTopping(PizzaTopping.BACON);
			correct &= !V.containsTopping(PizzaTopping.SALAMI);
			correct &= !V.containsTopping(PizzaTopping.PEPPERONI);
			correct &= V.containsTopping(PizzaTopping.CAPSICUM);
			correct &= V.containsTopping(PizzaTopping.MUSHROOM);
			correct &= V.containsTopping(PizzaTopping.EGGPLANT);
			assertEquals(true, correct);
		}
	
	
	
}




