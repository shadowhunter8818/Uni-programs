package asgn2Pizzas;

import java.time.LocalTime;

import asgn2Exceptions.PizzaException;


/**
 * 
 *  A class that represents a vegetarian pizza made at the Pizza Palace restaurant. 
 *  The vegetarian pizza has certain toppings listed in Section 5.1 of the Assignment Specification Document.  
 *  A description of the class's fields and their constraints is provided in Section 5.1 of the Assignment Specification.
 * 
 * @author Mark Parsons
 *
 */
public class VegetarianPizza extends Pizza {

	/**
	 * 
	 *  This class represents a vegetarian pizza made at the  Pizza Palace restaurant. The vegetarian pizza has certain
	 *  toppings listed in Section 5.1 of the Assignment Specification Document.  A description of the class's
	 *  fields and their constraints is provided in Section 5.1 of the Assignment Specification.
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification are violated. 
	 * 
     * <P> PRE: TRUE
	 * <P> POST: All field values including the cost per pizza are set
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 *
	 */
	public VegetarianPizza(int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException {
		super(quantity, orderTime, deliveryTime, "Vegetarian", 10.0);
		// Exception checks
		//Quantity
		
		if (quantity < 0) {
			throw new PizzaException ("cant have negative number of pizzas");
		}
		if (quantity > 10) {
			throw new PizzaException ("Maximum number of pizzas ordered is 10");
		}
		//Order and delivery times
		LocalTime ovenopens = LocalTime.of(19,00,00);
		LocalTime ovencloses = LocalTime.of(22,59,59);
		LocalTime lastdelivery = LocalTime.of(23,59,59);
		if (orderTime.isBefore(ovenopens) || deliveryTime.isBefore(ovenopens)) {
			throw new PizzaException ("The pizza restaurant doesnt open till 7pm (19:00)");
		}
		if (orderTime.isAfter(ovencloses)) {
			throw new PizzaException ("The pizza restaurant closes at 11pm (23:00)");
		}
		if (deliveryTime.isAfter(lastdelivery)) {
			throw new PizzaException ("The pizza restaurant doesnt keep any pizzas 1h after been made");
		}
		if (deliveryTime.isBefore(orderTime.plusMinutes(10))) {
			throw new PizzaException ("You cant deliver a pizza before its made");
		}
		if (deliveryTime.isAfter(orderTime.plusHours(1))) {
			throw new PizzaException ("You cant deliver a pizza after it thrown out");
		}
	}
}
