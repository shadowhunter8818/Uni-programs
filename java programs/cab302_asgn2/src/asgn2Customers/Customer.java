package asgn2Customers;

import asgn2Exceptions.CustomerException;

/** An abstract class to represent a customer at the Pizza Palace restaurant.
 *  The Customer class is used as a base class of PickUpCustomer, 
 *  DriverDeliveryCustomer and DroneDeliverCustomer. Each of these subclasses overwrites
 *  the abstract method getDeliveryDistance. A description of the class's
 * fields and their constraints is provided in Section 5.2 of the Assignment Specification.  
 * 
 * @author Filippo Capurso
*/
public abstract class Customer {
	private String name;
	private String mobileNumber;
	private int locationX;
	private int locationY;
	private String type;
	

	/**
	 *  This class represents a customer of the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.2. 
	 *  A CustomerException is thrown if the any of the constraints listed in Section 5.2 of the Assignment Specification
	 *  are violated. 
	 *  
  	 * <P> PRE: True
  	 * <P> POST: All field values are set
  	 * 
	 * @param name - The Customer's name 
	 * @param mobileNumber - The customer mobile number
	 * @param locationX - The customer x location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @param locationY - The customer y location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @param type - A human understandable description of this Customer type
	 * @throws CustomerException if supplied parameters are invalid 
	 * 
	 */
	public Customer(String name, String mobileNumber, int locationX, int locationY, String type) throws CustomerException{
		// Name
		if (name == null) {
			throw new CustomerException("Customer Name cannot be null");
		}
		else if (name.length() < 1) {
			throw new CustomerException("Customer Name cannot be empty");
		}
		else if (name.length() > 20){
			throw new CustomerException("Customer Name has more than 21 characters");
		}
		else if (!name.matches("^.*\\S.*$")) {
			throw new CustomerException("Customer Name cannot be entirely whitespaces, tabs or linebreaks");
		}
		
		// Mobile Number
		if (mobileNumber == null) {
			throw new CustomerException("Customer Name cannot be null");
		}
		else if (!mobileNumber.matches("^[0]\\d{9}$")){
			throw new CustomerException("Phone number is in the wrong format");
		}
		
		// Type of customer & distance
		if (type == null) {
			throw new CustomerException("Customer type cannot be null");
		}
		else if (type == "Driver Delivery" || type == "Drone Delivery"){
			if (locationX == 0 && locationY == 0) {
				throw new CustomerException("Delivery location cannot be 0,0");
			}
			else if (Math.abs(locationX) > 10 || Math.abs(locationY) > 10){
				throw new CustomerException("Delivery location cannot be more than 10 blocks north, south, east or west");
			}
		}
		else if (type == "Pick Up"){
			if (!(locationX == 0 && locationY == 0)) {
				throw new CustomerException("Pick up location has to be: 0,0");
			}
		}
		else {
			throw new CustomerException("Customer type can only be: 'Driver Delivery', 'Drone Delivery' or 'Pick Up'");
		}
		
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.locationX = locationX;
		this.locationY = locationY;
		this.type = type;
	}
	
	/**
	 * Returns the Customer's name.
	 * @return The Customer's name.
	 */
	public final String getName(){
		return name;
	}
	
	/**
	 * Returns the Customer's mobile number.
	 * @return The Customer's mobile number.
	 */
	public final String getMobileNumber(){
		return mobileNumber;
	}

	/**
	 * Returns a human understandable description of the Customer's type. 
	 * The valid alternatives are listed in Section 5.2 of the Assignment Specification. 
	 * @return A human understandable description of the Customer's type.
	 */
	public final String getCustomerType(){
		return type;
	}
	
	/**
	 * Returns the Customer's X location which is the number of blocks East or West 
	 * that the Customer is located relative to the Pizza Palace restaurant. 
	 * @return The Customer's X location
	 */
	public final int getLocationX(){
		return locationX;
	}

	/**
	 * Returns the Customer's Y location which is the number of blocks North or South 
	 * that the Customer is located relative to the Pizza Palace restaurant. 
	 * @return The Customer's Y location
	 */
	public final int getLocationY(){
		return locationY;
	}

	/**
	 * An abstract method that returns the distance between the Customer and 
	 * the restaurant depending on the mode of delivery. 
	 * @return The distance between the restaurant and the Customer depending on the mode of delivery.
	 */
	public abstract double getDeliveryDistance();

	
	
	/**
	 * Compares *this* Customer object with an instance of an *other* Customer object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 *  You do not need to test this method.
	 * 
	 * @return true if *this* Customer object and the *other* Customer object have the same values returned for 	
	 * getName(),getMobileNumber(),getLocationX(),getLocationY(),getCustomerType().
	 */
	@Override
	public boolean equals(Object other){
		Customer otherCustomer = (Customer) other;

		return ( (this.getName().equals(otherCustomer.getName()))  &&
			(this.getMobileNumber().equals(otherCustomer.getMobileNumber())) && 
			(this.getLocationX() == otherCustomer.getLocationX()) && 
			(this.getLocationY() == otherCustomer.getLocationY()) && 
			(this.getCustomerType().equals(otherCustomer.getCustomerType())) );			
	}

}
