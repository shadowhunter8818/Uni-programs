package asgn2GUIs;

import java.io.File;
import java.text.DecimalFormat;


import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a dummy class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Mark Parsons and Filippo Capurso
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;
	
	private static PizzaGUI gui;
	private PizzaRestaurant PR;
	
	private JPanel pnlOne;
	private JPanel pnlTwo;
	private JPanel pnlThree;
	private JPanel pnlFour;
	private JPanel orderpnl= new JPanel();
	private JPanel customerpnl = new JPanel();
    private JTextArea filepath;
    private JTextField totalProfitText;
    private JTextField totalDistanceText;
    private JButton selectButton;
    private JButton resetButton;
    private JButton Closebutton;
    private JButton showcustomers;
    private JButton showorders;
    private JButton totalProfitButton;
    private JButton totalDistanceButton;
    private JTable pJTable = new JTable();
    private JTable cJTable = new JTable();
    private DefaultTableModel Customertable = new DefaultTableModel(0,0);
    private DefaultTableModel PizzaTable = new DefaultTableModel(0,0);
    private JScrollPane Customers = new JScrollPane(cJTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JScrollPane pizzas = new JScrollPane(pJTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		super(title);
	    gui = this;
	}
	
	/**
	 * Creates all features of GUI and adds them to the specified container
	 * @param pane - Window which all GUI features will be added to
	 */
	private void initialiseComponents(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //Setting up top pane for loadfile and reset button
        c.fill = GridBagConstraints.HORIZONTAL;
        pnlOne = createPanel(Color.BLUE);
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(pnlOne, c);
        
        //Creating load file button
        selectButton = new JButton("Click here to Select & Load a File");
        selectButton.addActionListener(this);
        pnlOne.add(selectButton);
        
        //Creating reset button
        resetButton = new JButton("Reset ALL");
        resetButton.addActionListener(this);
        pnlOne.add(resetButton);
        
        //Creating logfile display path pane
        pnlTwo = createPanel(Color.LIGHT_GRAY);
        c.ipady = 20;      
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(pnlTwo, c);
        
        //Creating logfile path display area
        filepath = new JTextArea("  Area to display the selected LogFile Path", 2,70);
        filepath.setEnabled(false);
        pnlTwo.add(filepath);
       
        //creating table panes
        pnlThree = createPanel(Color.WHITE);
        c.ipady = 0;       
        c.weighty = 1.0;      
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(pnlThree, c);

        pnlFour = createPanel(Color.WHITE);
        c.ipady = 0;       
        c.weighty = 1.0;   
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(pnlFour, c);
        
        //Setting up tables
        pizzas.setPreferredSize(new Dimension(800, 200));
        pJTable.setPreferredScrollableViewportSize(pJTable.preferredSize());
        pJTable.setFillsViewportHeight(true);
        pJTable.setEnabled(false);
		String ordercolumn[]={"Pizza Type","Quantity","Order Price ($)","Order Cost ($)","Order Profit ($)"};
		PizzaTable.setColumnIdentifiers(ordercolumn);
		pJTable.setModel(PizzaTable);
		pJTable.getTableHeader().setReorderingAllowed(false);
	    pnlThree.add(pizzas);
        
        Customers.setPreferredSize(new Dimension(800, 200));
        cJTable.setPreferredScrollableViewportSize(cJTable.preferredSize());
        cJTable.setFillsViewportHeight(true);
        cJTable.setEnabled(false);
        String cuscolumn[]={"Customer Name","Mobile Number","Customer Type","X and Y Location","Distance from Restaurant"};
		Customertable.setColumnIdentifiers(cuscolumn);
		cJTable.setModel(Customertable);
		cJTable.getTableHeader().setReorderingAllowed(false);
	    pnlFour.add(Customers);
	    
	    //Creating show orders button
        showorders = new JButton("<html><center>Show<br/>Orders</center></html>");
	    showorders.setPreferredSize(new Dimension(110,80));
        showorders.addActionListener(this);
        showorders.setEnabled(false);
        pnlThree.add(showorders);
        
        //creating show customers button
        showcustomers = new JButton("<html><center>Show<br/>Customers</center></html>");
        showcustomers.setPreferredSize(new Dimension(110,80));
        showcustomers.addActionListener(this);
        showcustomers.setEnabled(false);
        pnlFour.add(showcustomers);
        
        //Creating total profit button and text box
        totalProfitText = new JTextField("Tot Profit",15);
        totalProfitText.setHorizontalAlignment(JTextField.CENTER);
        totalProfitText.setEnabled(false);
        orderpnl.add(totalProfitText);
        
        totalProfitButton = new JButton("Calculate Total Profit");
        totalProfitButton.setPreferredSize(new Dimension(190,25));
        totalProfitButton.addActionListener(this);
        totalProfitButton.setEnabled(false);
        orderpnl.add(totalProfitButton);
        
        c.ipady = 0;
        c.gridx = 1;
        c.gridwidth = 2;
        c.gridy = 3;
        pane.add(orderpnl, c);
        
        //Creating total distance button and text bow
        totalDistanceText = new JTextField("Tot Distance",15);
        totalDistanceText.setHorizontalAlignment(JTextField.CENTER);
        totalDistanceText.setEnabled(false);
        customerpnl.add(totalDistanceText);
        
        totalDistanceButton = new JButton("Calculate Total Distance");
        totalDistanceButton.setPreferredSize(new Dimension(190,25));
        totalDistanceButton.addActionListener(this);
        totalDistanceButton.setEnabled(false);
        customerpnl.add(totalDistanceButton);
        
        c.ipady = 0;
        c.gridx = 1;       
        c.gridwidth = 2;   
        c.gridy = 5;       
        pane.add(customerpnl, c);
        
        //Creating close button
        Closebutton = new JButton("Close");
        Closebutton.addActionListener(this);
        Closebutton.setEnabled(true);
        c.ipady = 0;       
        c.weighty = 1.0;   
        c.anchor = GridBagConstraints.PAGE_END; 
        c.insets = new Insets(10,0,0,0);  
        c.gridx = 1;      
        c.gridwidth = 2;  
        c.gridy = 6;      
        pane.add(Closebutton, c);
	}
	
	/**
	 * Creates GUI and makes visible to user
	 */
	private void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
	    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    gui.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    gui.setLocation(new Point(150, 100));
	    initialiseComponents(gui.getContentPane());
	    gui.pack();
	    gui.setVisible(true);
	}
	
	/**
	 * Allows choice of file on system
	 * @return file path of selected file
	 */
	private String openFileLoader() {
    	String filename = null;
    	
	    final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
	    if (returnVal==JFileChooser.APPROVE_OPTION) {
		    File file = fc.getSelectedFile();
		    filename = file.getAbsolutePath();
	    }
	    else if(returnVal==JFileChooser.CANCEL_OPTION) {
	    }
	    return filename;
	}
	
	/**
	 * Creates new JPanel with specified background colour
	 * @param c - Desired background colour
	 * @return newly created JPanel
	 */
	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}
	
	/**
	 * Attempts to load the selected logfile
	 * @throws messages to screen if successful or if there's problems with file
	 */
	private void loadFile() {
		try{
			PR = new PizzaRestaurant();
			boolean success = PR.processLog(filepath.getText());
			if (success) {
				showcustomers.setEnabled(true);
				showorders.setEnabled(true);
		        totalProfitButton.setEnabled(true);
		        totalDistanceButton.setEnabled(true);
				JOptionPane.showMessageDialog(null, "File has been loaded successfully!\nClick 'Show Orders' or 'Show Customers' to view data");
			}
		}
		catch (Exception exc){
			String intro;
			if (exc.getClass().getSimpleName().equals("LogHandlerException")){
				intro = "Error loading file!\n";
			}
			else {
				intro = "Data contains Errors!\n";
			}
			String message = intro + exc.getClass().getSimpleName() + ":\n" + exc.getMessage();
			JOptionPane.showMessageDialog(null, message);
			exc.printStackTrace();
		}
	}
	
	/**
	 * Resets the GUI back to its starting values and format
	 */
	private void resetFields(){
		selectButton.setText("Click here to Select & Load a File");
		filepath.setText("Area to display the selected LogFile Path");
		Customertable.setRowCount(0);
		PizzaTable.setRowCount(0);
		showcustomers.setEnabled(false);
		showorders.setEnabled(false);
        totalProfitButton.setEnabled(false);
        totalDistanceButton.setEnabled(false);
		totalProfitText.setText("Tot Profit");
		totalDistanceText.setText("Tot Distance");
		filepath.setDisabledTextColor(Color.GRAY);
		totalProfitText.setDisabledTextColor(Color.GRAY);
		totalDistanceText.setDisabledTextColor(Color.GRAY);
	}
	
	@Override
	public void run() {
		createAndShowGUI();
	}
	/**
	 * Manipulates the GUI based on the users interactions
	 * @param e - The latest action the user has done
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Loading Pizza data 
		if (e.getActionCommand().contains("Orders")) {
			try {
				PizzaTable.setRowCount(0);
				for (int i = 0; i < PR.getNumPizzaOrders(); i++) {
					Pizza Order = PR.getPizzaByIndex(i);
					PizzaTable.addRow(new Object[] {Order.getPizzaType(), Order.getQuantity(), Order.getOrderPrice(), Order.getOrderCost(), Order.getOrderProfit()});
				}
			    revalidate();
			} catch (PizzaException e1) {
				e1.printStackTrace();
			}
		}
		//Loading customer data 
		if (e.getActionCommand().contains("Customers")) {
			try {
				Customertable.setRowCount(0);
				for (int i = 0; i < PR.getNumCustomerOrders(); i++) {
					Customer customer = PR.getCustomerByIndex(i);
					String Loc = Integer.toString(customer.getLocationX()) + ", " + Integer.toString(customer.getLocationY());
					String dist = Double.toString((double) Math.round(customer.getDeliveryDistance()*100)/100);
					Customertable.addRow(new Object[] {customer.getName(),customer.getMobileNumber(),customer.getCustomerType(),Loc,dist});
				}
			    revalidate();
			} catch (CustomerException e1) {
				e1.printStackTrace();
			}
		}
		
		// Selecting & Loading file
		if (e.getActionCommand().contains("File")) {
			String filename = openFileLoader();
			if (filename != null && !filename.isEmpty()) {
				resetFields();
				filepath.setText(filename);
				filepath.setDisabledTextColor(Color.BLACK);
				selectButton.setText("Reselect File");
				loadFile();
			}
		}	
		
		// calc total profit
		if (e.getActionCommand().contains("Profit")) {
			try{
				String result;
				result = "$ " + Double.toString(PR.getTotalProfit());
				totalProfitText.setText(result);
				totalProfitText.setDisabledTextColor(Color.BLACK);
			}
			catch (Exception e1) {}
		}
		
		// calc total distance
		if (e.getActionCommand().contains("Distance")) {
			try{
				String result;
				result = Double.toString((double) Math.round(PR.getTotalDeliveryDistance()*100)/100);
				totalDistanceText.setText(result);
				totalDistanceText.setDisabledTextColor(Color.BLACK);
			}
			catch (Exception e1) {}
		}

		//set close button to close window
		if (e.getActionCommand().equals("Close")) {
			System.exit(0);
		}

		//set close button to close window
		if (e.getActionCommand().contains("Reset")) {
			resetFields();
		}
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new PizzaGUI("Log Loader"));
	}

}
