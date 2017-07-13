package asgn2Pizzas;

import java.lang.reflect.Method;
import java.time.LocalTime;

import javax.xml.stream.events.EndDocument;

import asgn2Exceptions.PizzaException;


/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Mark Parsons
 *
 */
public abstract class Pizza  {
	private int quantity = 1;
	private double price = 0.0;
	private double cost = 0;
	private String type = null;
	
	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 * 
	 */
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException{
		// Checks type to make sure valid pizza type selected then checks to make sure cost is correct before pushing values to each pizza
		
		//Order and delivery times
		LocalTime ovenopens = LocalTime.of(19,00,00);
		LocalTime ovencloses = LocalTime.of(22,59,59);
		LocalTime lastdelivery = LocalTime.of(23,59,59);
		
		//Quantity
		if (quantity <= 0) {
			throw new PizzaException ("Can't have a negative number of pizzas");
		}
		else if (quantity > 10) {
			throw new PizzaException ("Maximum number of pizzas ordered is over 10");
		}
		
		// Order Time
		if (orderTime.equals(null)) {
			throw new PizzaException("Order time is null");
		}
		if (orderTime.isBefore(ovenopens) || deliveryTime.isBefore(ovenopens)) {
			throw new PizzaException ("The order time is before the restaurant's opening time (19:00:00)");
		}
		else if (orderTime.isAfter(ovencloses)) {
			throw new PizzaException ("The order time is after the restaurant's closing time (22:59:59)");
		}
		// Delivery Time
		if (deliveryTime.equals(null)) {
			throw new PizzaException("Delivery time is null");
		}
		if (deliveryTime.isAfter(lastdelivery)) {
			throw new PizzaException ("The pizza restaurant doesnt keep any pizzas 1h after they have been made");
		}
		else if (deliveryTime.isBefore(orderTime)) {
			throw new PizzaException ("A pizza can't be delivered before its made");
		}
		// Type & Price
		if (type.equals("Margherita")){
			if (price != 8){
				throw new PizzaException("Contains a not valid pizza price");
			}
		}
		else if (type.equals("Vegetarian")){ 
			if (price != 10){
				throw new PizzaException("Contains a not valid pizza price");
			}
		}
		else if (type.equals("Meat Lovers")){
			if (price != 12){
				throw new PizzaException("Contains a not valid pizza price");
			}
		}
		else {
			throw new PizzaException("Contains a not valid pizza type");
		}

		this.quantity = quantity;
		this.type = type;
		this.price = price;
	}

	/**
	 * Calculates how much a pizza would could to make calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		this.cost = 0;
		//Checks to see if pizza contains topping if yes adds to the cost 
		if (containsTopping(PizzaTopping.BACON)) {
			this.cost += PizzaTopping.BACON.getCost();
		}
		if (containsTopping(PizzaTopping.CHEESE)) {
			this.cost += PizzaTopping.CHEESE.getCost();
		}
		if (containsTopping(PizzaTopping.CAPSICUM)) {
			this.cost += PizzaTopping.CAPSICUM.getCost();
		}
		if (containsTopping(PizzaTopping.EGGPLANT)) {
			this.cost += PizzaTopping.EGGPLANT.getCost();
		}
		if (containsTopping(PizzaTopping.MUSHROOM)) {
			this.cost += PizzaTopping.MUSHROOM.getCost();
		}
		if (containsTopping(PizzaTopping.PEPPERONI)) {
			this.cost += PizzaTopping.PEPPERONI.getCost();
		}
		if (containsTopping(PizzaTopping.SALAMI)) {
			this.cost += PizzaTopping.SALAMI.getCost();
		}
		if (containsTopping(PizzaTopping.TOMATO)) {
			this.cost += PizzaTopping.TOMATO.getCost();
		}
	}
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		return this.cost;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		if (this.cost == 1.5) {
			return 8.0;
		}
		if (this.cost == 5.5) {
			return 10.0;
		}
		if (this.cost == 5) {
			return 12.0;
		}
		return price;
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		calculateCostPerPizza();
		return quantity*cost;
	}
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		double orderprice = 0;
		orderprice = (getPricePerPizza() * quantity);
		return orderprice;
	}
	
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		return getOrderPrice() - getOrderCost();
	}
	

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		//Checks margherita pizza toppings
		if (this.type.equals("Margherita")){
			if (topping == PizzaTopping.CHEESE) {
				return true;
			}
			if (topping == PizzaTopping.TOMATO) {
				return true;
			} 
			return false;
		}
		//end
		//Checks vegetarian pizza toppings
		if (this.type.equals("Vegetarian")){
			if (topping == PizzaTopping.CHEESE) {
				return true;
			}
			if (topping == PizzaTopping.TOMATO) {
				return true;
			} 
			if (topping == PizzaTopping.EGGPLANT) {
				return true;
			} 
			if (topping == PizzaTopping.CAPSICUM) {
				return true;
			} 
			if (topping == PizzaTopping.MUSHROOM) {
				return true;
			} 
			return false;
		}
		//End
		//Check meatlovers pizza toppings 
		if (this.type.equals("Meat Lovers")){
			if (topping == PizzaTopping.CHEESE) {
				return true;
			}
			if (topping == PizzaTopping.TOMATO) {
				return true;
			} 
			if (topping == PizzaTopping.BACON) {
				return true;
			} 
			if (topping == PizzaTopping.SALAMI) {
				return true;
			} 
			if (topping == PizzaTopping.PEPPERONI) {
				return true;
			} 
			return false;
			}
		//end
		return false;
	}
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		return quantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		return type;
	}


	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}

	
}
