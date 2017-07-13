package asgn2Restaurant;


import java.util.ArrayList;
// Used for regular expressions
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// Used to read log files
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Mark Parsons and Filippo Capurso
 *
 */
public class LogHandler {
	


	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{
		ArrayList<Customer> ArrayC = new ArrayList<Customer>();
		// Check that filename is not null or empty
		if (filename != null && !filename.isEmpty()){
			// Try to find and read file
			try {
				// Create Buffered Reader
				BufferedReader BR = new BufferedReader(new FileReader(filename));
				String line;
				// Get customer for each line
				while ((line = BR.readLine()) != null){
					ArrayC.add(createCustomer(line));
				}
			}
			catch (Exception e) {
				if (e.getClass().getSimpleName().equals("CustomerException")){
					throw new CustomerException(e.getMessage());
				}
				else if (e.getClass().getSimpleName().equals("LogHandlerException")){
					throw new LogHandlerException(e.getMessage());
				}
				else {
					throw new LogHandlerException("File was not found or could not be read. Details:\n    " + e);
				}
			}
		}
		else {
			throw new LogHandlerException("Filename is null or empty");
		}
		return ArrayC;
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		ArrayList<Pizza> ArrayPizza = new ArrayList<Pizza>();
		// Check that filename is not null or empty
		if (filename != null && !filename.isEmpty()){
			// Try to find and read file
			try {
				// Create Buffered Reader
				BufferedReader BR = new BufferedReader(new FileReader(filename));
				String line;
				// Get pizza from each line
				while ((line = BR.readLine()) != null){
					ArrayPizza.add(createPizza(line));
				}
			}
			catch (Exception e) {
				if (e.getClass().getSimpleName().equals("PizzaException")){
					throw new PizzaException(e.getMessage());
				}
				else if (e.getClass().getSimpleName().equals("LogHandlerException")){
					throw new LogHandlerException(e.getMessage());
				}
				else {
					throw new LogHandlerException("File was not found or could not be read. Details:\n    " + e);
				}
			}
		}
		else {
			throw new LogHandlerException("Filename is null or empty");
		}
		return ArrayPizza;
	}		

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException{
		Customer C;
		CustomerFactory CF = new CustomerFactory();
		
		if (line != null && !line.isEmpty()) {
			// Check that there are 9 fields
			if (line.matches("^(?:[^,]*,){8}[^,]*$")) {
				Pattern fields = Pattern.compile("^[^,]*,[^,]*,([^,]*),([^,]*),([^,]*),([^,]*),([^,]*),[^,]*,[^,]*$");
				Matcher matcher = fields.matcher(line);
				matcher.find();
				//
				String customerName = matcher.group(1);
				String mobileNumber = matcher.group(2);
				String customerCode = matcher.group(3);
				String locationXstr = matcher.group(4);
				String locationYstr = matcher.group(5);
				
				if (!customerName.matches("^[^,0-9]{1,20}$")){
					throw new CustomerException("Customer Name is missing, out of limits or contains numbers");
				}
				else if (!mobileNumber.matches("^0[0-9]{9}$")){
					throw new CustomerException("Mobile number is invalid. Please remove white spaces and use only digits");
				}
				else if (!customerCode.matches("^(DVC|DNC|PUC)$")){
					throw new CustomerException("Customer code is not valid. Make sure characters are all capitals");
				}
				else if (!locationXstr.matches("^-?(?:[0-9]|10)$")){
					throw new CustomerException("Location X has to be an integer between 0 and 10 (extremes included)");
				}
				else if (!locationYstr.matches("^-?(?:[0-9]|10)$")){
					throw new CustomerException("Location Y has to be an integer between 0 and 10 (extremes included)");
				}
				else{
					int locationX = Integer.parseInt(locationXstr);
					int locationY = Integer.parseInt(locationYstr);
					C = CF.getCustomer(customerCode, customerName, mobileNumber, locationX, locationY);
				}
				
			}
			else {
				throw new LogHandlerException("String is not in the right format and/or does not contain 9 fields");
			}
		}
		else {
			throw new LogHandlerException("Line is null or empty");
		}
		return C;
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
		Pizza P = null;
		PizzaFactory PF = new PizzaFactory();
		if (line != null && !line.isEmpty()) {
			if (line.matches("^(?:[^,]*,){8}[^,]*$")) {
				Pattern fields = Pattern.compile("^([^,]*),([^,]*),[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,([^,]*),([^,]*)$");
				Matcher matcher = fields.matcher(line);
				matcher.find();
				
				String orderTimestring = matcher.group(1);
				String deliveryTimestring = matcher.group(2);
				String pizzaCode = matcher.group(3);
				String quantitystring = matcher.group(4);
				
				if (!orderTimestring.matches("(?:(?:(?:0|1)[0-9])|(?:2[0-3])):[0-5][0-9]:[0-5][0-9]")){
					throw new PizzaException("Order time is missing, out of limits");
				}
				else if (!deliveryTimestring.matches("(?:(?:(?:0|1)[0-9])|(?:2[0-3])):[0-5][0-9]:[0-5][0-9]")){
					throw new PizzaException("Delivery time is missing, out of limits");
				}
				else if (!pizzaCode.matches("^(PZM|PZV|PZL)$")){
					throw new PizzaException("Pizza code is not valid. Make sure characters are all capitals");
				}
				else if (!quantitystring.matches("^-?(?:[0-9]|10)$")){
					throw new PizzaException("Pizza Quantity must be an integer with a max value of 10");
				}
				else{
					LocalTime orderTime = LocalTime.parse(orderTimestring);
					LocalTime deliveryTime = LocalTime.parse(deliveryTimestring);
					int quantity = Integer.parseInt(quantitystring);
					P = PF.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
				}
			}
			else {
				throw new LogHandlerException("String is not in the right format and/or does not contain 9 fields");
			}
			
		} else {
			throw new LogHandlerException("Line is empty or Null");
		}
		return P;		
	}

}
