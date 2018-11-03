

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
 
public class Main implements ItemListener {
	
	//layout data
    JPanel cards; //a panel that uses CardLayout
    final static String logincScreen = "LOGIN";
    final static String mainMenu = "MAINNEMU";
    final static String NewSale = "NewSale";
    final static String Return = "rerturn";
    final static String CheckIntventory = "CheckIntventory";
    final static String admin = "admin";
    final static String CashierReporttxt = "CashierReport";
    final static String RegisterReporttxt = "RegisterReport";
    final static String AddNewEmployeetxt = "AddNewEmployee";
    
    //for tables 
    private Object[] inventoryTableName = {"ID", "Name", "Cost", "Number In Store", "Supplier", "Threshold"};
    private Object[][] inventoryData = {{ "", "" , "" , "" , "" , ""}};
    private Object[] OrderTableName = {"ID","Name", "Number", "Total Cost"};
    private Object[][] orderData = {{ "", "", "" , ""}};
  
    private Object[] RegisterName = {"Name","Number of Sales", "Login Time", "LogOut Time", "cash Taken in"};
    private Object[][] RegisterData = {{ "", "", "" , "" , ""}};
    
    //data used 
    private Employee activeEmployee;
    private Register currentRegister;
    private Order    currentOrder;
    
    //different fields
    private JTextField textField;
    private JPasswordField passwordField;
    private JTextField NewUserName;
    private JPasswordField NewPassword;
    private JPasswordField confrimNewPassword;
    private JTextField newTitle;
    private JTextField NewName;
    private JTextField ReturnOrderNumber;
    private JTextField ReturnItemNUmber;
    private JTextField saleItem;
    private JTable inventoryTable;
    private JTable orderTable;
    private JTable Registertable;
    private JTextField updateId;
    private JTextField UpdateThershold;
    private JTextField updateNumber;
    private JTextField updateSupplier;
    private JTextField updateName;
    private JTextField UpdateCost;
    

     
    public void addComponentToPane(Container pane) {
    	
    	
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = { logincScreen, mainMenu,NewSale,Return, CheckIntventory, admin };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setVisible(false);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
         
        //Create the "cards".
        JPanel LoginScreen = new JPanel();
        LoginScreen.setPreferredSize(new Dimension(100, 100));
         
        JPanel MainMenu = new JPanel();
         
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(LoginScreen, logincScreen);
        GridBagLayout gbl_LoginScreen = new GridBagLayout();
        gbl_LoginScreen.columnWidths = new int[]{88, 88, 0};
        gbl_LoginScreen.rowHeights = new int[]{30, 0, 0, 0};
        gbl_LoginScreen.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_LoginScreen.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        LoginScreen.setLayout(gbl_LoginScreen);
        
        JLabel lblUsername = new JLabel("UserName");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername.gridx = 0;
        gbc_lblUsername.gridy = 0;
        LoginScreen.add(lblUsername, gbc_lblUsername);
        
        textField = new JTextField();
        textField.setColumns(10);
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.BOTH;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        LoginScreen.add(textField, gbc_textField);
        
        JLabel lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 0;
        gbc_lblPassword.gridy = 1;
        LoginScreen.add(lblPassword, gbc_lblPassword);
        
        passwordField = new JPasswordField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 5, 0);
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.gridx = 1;
        gbc_passwordField.gridy = 1;
        LoginScreen.add(passwordField, gbc_passwordField);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {

				activeEmployee = new Employee(textField.getText());
				Boolean pwdcheck = activeEmployee.checkPassword(passwordField.getText());
				if(pwdcheck)
				{
					 CardLayout cl = (CardLayout)(cards.getLayout());
					 cl.show(cards,mainMenu);
					 textField.setText("");
					 passwordField.setText("");
					 currentRegister = new Register((float) 20.00,activeEmployee );
				}
				else
				{
					JOptionPane.showMessageDialog(LoginScreen, "Incorrect UserName/Password");
				}
        	}
        });
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
        gbc_btnLogin.gridx = 0;
        gbc_btnLogin.gridy = 2;
        LoginScreen.add(btnLogin, gbc_btnLogin);
        cards.add(MainMenu, mainMenu);
        GridBagLayout gbl_MainMenu = new GridBagLayout();
        gbl_MainMenu.columnWidths = new int[]{0, 0};
        gbl_MainMenu.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_MainMenu.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_MainMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        MainMenu.setLayout(gbl_MainMenu);
        
        JButton btnNewSale = new JButton("New Sale");
        btnNewSale.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		currentOrder = new Order();
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,NewSale);
        		
        	}
        });
        GridBagConstraints gbc_btnNewSale = new GridBagConstraints();
        gbc_btnNewSale.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewSale.gridx = 0;
        gbc_btnNewSale.gridy = 0;
        MainMenu.add(btnNewSale, gbc_btnNewSale);
        
        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,Return);
        	}
        });
        GridBagConstraints gbc_btnReturn = new GridBagConstraints();
        gbc_btnReturn.insets = new Insets(0, 0, 5, 0);
        gbc_btnReturn.gridx = 0;
        gbc_btnReturn.gridy = 1;
        MainMenu.add(btnReturn, gbc_btnReturn);
        
        JButton btnCheckInventory = new JButton("Check Inventory");
        btnCheckInventory.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {

    			
    			//create inventory into JTable 
    			
    			InventoryManager inma = new InventoryManager();
    			inventoryData = inma.displayInventoryReport();
    			//inventoryTable = new JTable( inventoryData, inventoryTableName);
    			DefaultTableModel  model=new DefaultTableModel(inventoryData, inventoryTableName);

    			inventoryTable.setModel(model);
    			//mop
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,CheckIntventory);
        	}
        });
        GridBagConstraints gbc_btnCheckInventory = new GridBagConstraints();
        gbc_btnCheckInventory.insets = new Insets(0, 0, 5, 0);
        gbc_btnCheckInventory.gridx = 0;
        gbc_btnCheckInventory.gridy = 2;
        MainMenu.add(btnCheckInventory, gbc_btnCheckInventory);
        
        JButton btnAdmin_1 = new JButton("Admin");
        btnAdmin_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		if(activeEmployee.getaccesslevel() > 2 )
				{
        			CardLayout cl = (CardLayout)(cards.getLayout());
					 cl.show(cards,admin);
				}
        		else
        		{
        			JOptionPane.showMessageDialog(LoginScreen, "No Access");
        		}
        	}
        });
        GridBagConstraints gbc_btnAdmin_1 = new GridBagConstraints();
        gbc_btnAdmin_1.insets = new Insets(0, 0, 5, 0);
        gbc_btnAdmin_1.gridx = 0;
        gbc_btnAdmin_1.gridy = 3;
        MainMenu.add(btnAdmin_1, gbc_btnAdmin_1);
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		 currentRegister.logoff();
				 CardLayout cl = (CardLayout)(cards.getLayout());
				 cl.show(cards,logincScreen);
        	}
        });
        GridBagConstraints gbc_btnLogout = new GridBagConstraints();
        gbc_btnLogout.gridx = 0;
        gbc_btnLogout.gridy = 4;
        MainMenu.add(btnLogout, gbc_btnLogout);
        
        JPanel NewSalepanel = new JPanel();
       
        cards.add(NewSalepanel, NewSale);
        GridBagLayout gbl_NewSalepanel = new GridBagLayout();
        gbl_NewSalepanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_NewSalepanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_NewSalepanel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_NewSalepanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        NewSalepanel.setLayout(gbl_NewSalepanel);
        
        JButton btnBack_4 = new JButton("Void");
        btnBack_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		currentOrder.voidSale();
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,mainMenu);
        	}
        });
        
        JLabel lblTotal = new JLabel("Total:");
        GridBagConstraints gbc_lblTotal = new GridBagConstraints();
        gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
        gbc_lblTotal.gridx = 4;
        gbc_lblTotal.gridy = 0;
        NewSalepanel.add(lblTotal, gbc_lblTotal);
        
        JLabel SaleTotal = new JLabel("0.00");
        GridBagConstraints gbc_SaleTotal = new GridBagConstraints();
        gbc_SaleTotal.insets = new Insets(0, 0, 5, 0);
        gbc_SaleTotal.gridx = 5;
        gbc_SaleTotal.gridy = 0;
        NewSalepanel.add(SaleTotal, gbc_SaleTotal);
        
        JLabel lblItemNumber_1 = new JLabel("Item Number");
        GridBagConstraints gbc_lblItemNumber_1 = new GridBagConstraints();
        gbc_lblItemNumber_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblItemNumber_1.gridx = 0;
        gbc_lblItemNumber_1.gridy = 1;
        NewSalepanel.add(lblItemNumber_1, gbc_lblItemNumber_1);
        
        saleItem = new JTextField();
        GridBagConstraints gbc_saleItem = new GridBagConstraints();
        gbc_saleItem.insets = new Insets(0, 0, 5, 5);
        gbc_saleItem.fill = GridBagConstraints.HORIZONTAL;
        gbc_saleItem.gridx = 2;
        gbc_saleItem.gridy = 1;
        NewSalepanel.add(saleItem, gbc_saleItem);
        saleItem.setColumns(10);
        
        JButton button = new JButton("+");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		Item tempItem = new Item(saleItem.getText());
        		if(tempItem.getExist())
        		{
            		Boolean addItemCheck = currentOrder.addToOrder(saleItem.getText());
            		if(addItemCheck == false)
            		{
            			JOptionPane.showMessageDialog(LoginScreen, "Not enoough in Store to add");
            		}
            		else
            		{
            			//update total cost and table
            			orderData = currentOrder.displayOrderData();
            			DefaultTableModel  model=new DefaultTableModel(orderData, OrderTableName);

            			orderTable.setModel(model);
            			SaleTotal.setText(Float.toString(currentOrder.getCost()));
            		}
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(LoginScreen, "Item does not exist");
        		}

        	}
        });
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.insets = new Insets(0, 0, 5, 5);
        gbc_button.gridx = 4;
        gbc_button.gridy = 1;
        NewSalepanel.add(button, gbc_button);
        
        JButton button_1 = new JButton("-");
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Item tempItem = new Item(saleItem.getText());
        		if(tempItem.getExist())
        		{
            		Boolean addItemCheck = currentOrder.removefromorder(saleItem.getText());
            		if(addItemCheck == false)
            		{
            			JOptionPane.showMessageDialog(LoginScreen, "Item was not already in Cart");
            		}
            		else
            		{
            			orderData = currentOrder.displayOrderData();
            			DefaultTableModel  model=new DefaultTableModel(orderData, OrderTableName);
            			orderTable.setModel(model);
            			
            			SaleTotal.setText(Float.toString(currentOrder.getCost()));
            		}
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(LoginScreen, "Item does not exist");
        		}
        	}
        });
        GridBagConstraints gbc_button_1 = new GridBagConstraints();
        gbc_button_1.insets = new Insets(0, 0, 5, 0);
        gbc_button_1.gridx = 5;
        gbc_button_1.gridy = 1;
        NewSalepanel.add(button_1, gbc_button_1);
        
        orderTable = new JTable();
        GridBagConstraints gbc_orderTable = new GridBagConstraints();
        gbc_orderTable.gridwidth = 6;
        gbc_orderTable.insets = new Insets(0, 0, 5, 5);
        gbc_orderTable.fill = GridBagConstraints.BOTH;
        gbc_orderTable.gridx = 0;
        gbc_orderTable.gridy = 2;
        
        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setViewportView(orderTable);

        NewSalepanel.add(scrollPane, gbc_orderTable);
        
        GridBagConstraints gbc_btnBack_4 = new GridBagConstraints();
        gbc_btnBack_4.insets = new Insets(0, 0, 0, 5);
        gbc_btnBack_4.gridx = 2;
        gbc_btnBack_4.gridy = 4;
        NewSalepanel.add(btnBack_4, gbc_btnBack_4);
        
        JButton btnCompleteSale = new JButton("Complete Sale");
        btnCompleteSale.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		currentRegister.finishSale(currentOrder.getCost());
        		JOptionPane.showMessageDialog(LoginScreen, "Please Return:  " + currentOrder.getCost() + " to the customer" );
        		currentOrder.completeSale();
        		
        		
        		//clean up everything else
    			orderData = new Object[][]{{ "", "", "" , ""}}; ;
    			DefaultTableModel  model=new DefaultTableModel(orderData, OrderTableName);

    			orderTable.setModel(model);
    			
    			SaleTotal.setText("0.0");
    			
    			saleItem.setText("");
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,mainMenu);
        	}
        });
        GridBagConstraints gbc_btnCompleteSale = new GridBagConstraints();
        gbc_btnCompleteSale.insets = new Insets(0, 0, 0, 5);
        gbc_btnCompleteSale.gridx = 4;
        gbc_btnCompleteSale.gridy = 4;
        NewSalepanel.add(btnCompleteSale, gbc_btnCompleteSale);
         
        
        JPanel Returnpanel = new JPanel();
       
        cards.add(Returnpanel, Return);
        GridBagLayout gbl_Returnpanel = new GridBagLayout();
        gbl_Returnpanel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_Returnpanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_Returnpanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_Returnpanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        Returnpanel.setLayout(gbl_Returnpanel);
        
        JButton btnBack_5 = new JButton("Back");
        btnBack_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,mainMenu);
        	}
        });
        
        JLabel lblOrderNumber = new JLabel("Order Number");
        GridBagConstraints gbc_lblOrderNumber = new GridBagConstraints();
        gbc_lblOrderNumber.insets = new Insets(0, 0, 5, 5);
        gbc_lblOrderNumber.anchor = GridBagConstraints.EAST;
        gbc_lblOrderNumber.gridx = 1;
        gbc_lblOrderNumber.gridy = 0;
        Returnpanel.add(lblOrderNumber, gbc_lblOrderNumber);
        
        ReturnOrderNumber = new JTextField();
        GridBagConstraints gbc_ReturnOrderNumber = new GridBagConstraints();
        gbc_ReturnOrderNumber.anchor = GridBagConstraints.NORTHEAST;
        gbc_ReturnOrderNumber.insets = new Insets(0, 0, 5, 0);
        gbc_ReturnOrderNumber.gridx = 2;
        gbc_ReturnOrderNumber.gridy = 0;
        Returnpanel.add(ReturnOrderNumber, gbc_ReturnOrderNumber);
        ReturnOrderNumber.setColumns(10);
        
        JLabel lblItemNumber = new JLabel("Item Number");
        GridBagConstraints gbc_lblItemNumber = new GridBagConstraints();
        gbc_lblItemNumber.insets = new Insets(0, 0, 5, 5);
        gbc_lblItemNumber.anchor = GridBagConstraints.EAST;
        gbc_lblItemNumber.gridx = 1;
        gbc_lblItemNumber.gridy = 1;
        Returnpanel.add(lblItemNumber, gbc_lblItemNumber);
        
        ReturnItemNUmber = new JTextField();
        GridBagConstraints gbc_ReturnItemNUmber = new GridBagConstraints();
        gbc_ReturnItemNUmber.anchor = GridBagConstraints.NORTHEAST;
        gbc_ReturnItemNUmber.insets = new Insets(0, 0, 5, 0);
        gbc_ReturnItemNUmber.gridx = 2;
        gbc_ReturnItemNUmber.gridy = 1;
        Returnpanel.add(ReturnItemNUmber, gbc_ReturnItemNUmber);
        ReturnItemNUmber.setColumns(10);
        
        JButton btnProcessReturn = new JButton("Process Return");
        btnProcessReturn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		//create Item,
        		Item itemToReturn = new Item(ReturnItemNUmber.getText());
        		try
        		{
        			int Ordernumber = Integer.parseInt(ReturnOrderNumber.getText());
        			
        			currentOrder = new Order(Ordernumber);
            		if(currentOrder.returnItem(itemToReturn.getID()) == true)
            		{
            			currentRegister.returnCash(itemToReturn.getCost());
            			CardLayout cl = (CardLayout)(cards.getLayout());
            			cl.show(cards,mainMenu);
            			JOptionPane.showMessageDialog(LoginScreen, "Please Return:  " + itemToReturn.getCost() + " to the customer" );
            		}
            		else
            		{
            			CardLayout cl = (CardLayout)(cards.getLayout());
            			cl.show(cards,mainMenu);
            			JOptionPane.showMessageDialog(LoginScreen, "ITEM DOES NOT EXIST IN ORDER DO NOT REFUND!!" );
            		}
        			
        		}
        		catch (NumberFormatException e)
        		{
        			JOptionPane.showMessageDialog(LoginScreen, "Order Number not valid Please enter an int" );
        		}
        		

        		
        	}
        });
        GridBagConstraints gbc_btnProcessReturn = new GridBagConstraints();
        gbc_btnProcessReturn.insets = new Insets(0, 0, 0, 5);
        gbc_btnProcessReturn.gridx = 1;
        gbc_btnProcessReturn.gridy = 4;
        Returnpanel.add(btnProcessReturn, gbc_btnProcessReturn);
        GridBagConstraints gbc_btnBack_5 = new GridBagConstraints();
        gbc_btnBack_5.gridx = 2;
        gbc_btnBack_5.gridy = 4;
        Returnpanel.add(btnBack_5, gbc_btnBack_5);
        
        
        JPanel CheckIntventorypaenl = new JPanel();
       
        cards.add(CheckIntventorypaenl, CheckIntventory);
        GridBagLayout gbl_CheckIntventorypaenl = new GridBagLayout();
        gbl_CheckIntventorypaenl.columnWidths = new int[]{0, 0, 0, 0};
        gbl_CheckIntventorypaenl.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_CheckIntventorypaenl.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_CheckIntventorypaenl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        CheckIntventorypaenl.setLayout(gbl_CheckIntventorypaenl);
        
        inventoryTable = new JTable(inventoryData, inventoryTableName);
        GridBagConstraints gbc_inventoryTable = new GridBagConstraints();
        gbc_inventoryTable.gridheight = 3;
        gbc_inventoryTable.gridwidth = 3;
        gbc_inventoryTable.insets = new Insets(0, 0, 5, 0);
        gbc_inventoryTable.fill = GridBagConstraints.BOTH;
        gbc_inventoryTable.gridx = 0;
        gbc_inventoryTable.gridy = 1;
        JScrollPane scrollPaneInve = new JScrollPane();

        scrollPaneInve.setViewportView(inventoryTable);

        CheckIntventorypaenl.add(scrollPaneInve, gbc_inventoryTable);
        
        JButton btnBack_6 = new JButton("Back");
        btnBack_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,mainMenu);
        	}
        });
        GridBagConstraints gbc_btnBack_6 = new GridBagConstraints();
        gbc_btnBack_6.insets = new Insets(0, 0, 0, 5);
        gbc_btnBack_6.gridx = 1;
        gbc_btnBack_6.gridy = 4;
        CheckIntventorypaenl.add(btnBack_6, gbc_btnBack_6);
        
        
        JPanel adminpanel = new JPanel();
       
        cards.add(adminpanel, admin);
        GridBagLayout gbl_adminpanel = new GridBagLayout();
        gbl_adminpanel.columnWidths = new int[]{0, 0};
        gbl_adminpanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_adminpanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_adminpanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        adminpanel.setLayout(gbl_adminpanel);
        
        JButton btnUpdateItems = new JButton("Update Items");
        btnUpdateItems.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {

        		
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,CashierReporttxt);
        	}
        });
        GridBagConstraints gbc_btnUpdateItems = new GridBagConstraints();
        gbc_btnUpdateItems.insets = new Insets(0, 0, 5, 0);
        gbc_btnUpdateItems.gridx = 0;
        gbc_btnUpdateItems.gridy = 0;
        adminpanel.add(btnUpdateItems, gbc_btnUpdateItems);
        
        JButton btnRegisterReport = new JButton("Register Report");
        btnRegisterReport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		RegisterData = currentRegister.outputReport();
        		DefaultTableModel  model=new DefaultTableModel(RegisterData, RegisterName);

        		Registertable.setModel(model);
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,RegisterReporttxt);
        	}
        });
        GridBagConstraints gbc_btnRegisterReport = new GridBagConstraints();
        gbc_btnRegisterReport.insets = new Insets(0, 0, 5, 0);
        gbc_btnRegisterReport.gridx = 0;
        gbc_btnRegisterReport.gridy = 1;
        adminpanel.add(btnRegisterReport, gbc_btnRegisterReport);
        
        JButton btnNightlyRun = new JButton("Nightly Run");
        btnNightlyRun.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {

    			
    			//create inventory into JTable 
    			
    			InventoryManager inma = new InventoryManager();
    			inventoryData = inma.nightlyRun();
    			//inventoryTable = new JTable( inventoryData, inventoryTableName);
    			DefaultTableModel  model=new DefaultTableModel(inventoryData, inventoryTableName);

    			inventoryTable.setModel(model);
    			//mop
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,CheckIntventory);
        	}
        });
        
        GridBagConstraints gbc_btnNightlyRun = new GridBagConstraints();
        gbc_btnNightlyRun.insets = new Insets(0, 0, 5, 0);
        gbc_btnNightlyRun.gridx = 0;
        gbc_btnNightlyRun.gridy = 2;
        adminpanel.add(btnNightlyRun, gbc_btnNightlyRun);
        
        JButton btnAddNewEmployee = new JButton("Add New Employee");
        btnAddNewEmployee.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,AddNewEmployeetxt);
        	}
        });
        GridBagConstraints gbc_btnAddNewEmployee = new GridBagConstraints();
        gbc_btnAddNewEmployee.insets = new Insets(0, 0, 5, 0);
        gbc_btnAddNewEmployee.gridx = 0;
        gbc_btnAddNewEmployee.gridy = 3;
        adminpanel.add(btnAddNewEmployee, gbc_btnAddNewEmployee);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,mainMenu);
        	}
        });
        GridBagConstraints gbc_btnBack = new GridBagConstraints();
        gbc_btnBack.gridx = 0;
        gbc_btnBack.gridy = 4;
        adminpanel.add(btnBack, gbc_btnBack);
        
        JPanel ItemsUpdate = new JPanel();
       
        cards.add(ItemsUpdate, CashierReporttxt);
        GridBagLayout gbl_ItemsUpdate = new GridBagLayout();
        gbl_ItemsUpdate.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_ItemsUpdate.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_ItemsUpdate.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_ItemsUpdate.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        ItemsUpdate.setLayout(gbl_ItemsUpdate);
        
        JLabel lblId = new JLabel("ID");
        GridBagConstraints gbc_lblId = new GridBagConstraints();
        gbc_lblId.anchor = GridBagConstraints.EAST;
        gbc_lblId.insets = new Insets(0, 0, 5, 5);
        gbc_lblId.gridx = 0;
        gbc_lblId.gridy = 0;
        ItemsUpdate.add(lblId, gbc_lblId);
        
        updateId = new JTextField();
        GridBagConstraints gbc_updateId = new GridBagConstraints();
        gbc_updateId.insets = new Insets(0, 0, 5, 5);
        gbc_updateId.fill = GridBagConstraints.HORIZONTAL;
        gbc_updateId.gridx = 1;
        gbc_updateId.gridy = 0;
        ItemsUpdate.add(updateId, gbc_updateId);
        updateId.setColumns(10);
        
        JButton btnRemove = new JButton("Remove ID");
        btnRemove.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		Item tempItem = new Item(updateId.getText());
        		if(tempItem.getExist())
        		{
        			tempItem.delete();
        			updateId.setText("");
        			JOptionPane.showMessageDialog(LoginScreen, "Item Deleted");
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(LoginScreen, "Item Does not exist can not delete");
        		}
        	}
        });
        GridBagConstraints gbc_btnRemove = new GridBagConstraints();
        gbc_btnRemove.insets = new Insets(0, 0, 5, 5);
        gbc_btnRemove.gridx = 3;
        gbc_btnRemove.gridy = 0;
        ItemsUpdate.add(btnRemove, gbc_btnRemove);
        
        JLabel lblNumber = new JLabel("Number");
        GridBagConstraints gbc_lblNumber = new GridBagConstraints();
        gbc_lblNumber.anchor = GridBagConstraints.EAST;
        gbc_lblNumber.insets = new Insets(0, 0, 5, 5);
        gbc_lblNumber.gridx = 0;
        gbc_lblNumber.gridy = 2;
        ItemsUpdate.add(lblNumber, gbc_lblNumber);
        
        updateNumber = new JTextField();
        GridBagConstraints gbc_updateNumber = new GridBagConstraints();
        gbc_updateNumber.insets = new Insets(0, 0, 5, 5);
        gbc_updateNumber.fill = GridBagConstraints.HORIZONTAL;
        gbc_updateNumber.gridx = 1;
        gbc_updateNumber.gridy = 2;
        ItemsUpdate.add(updateNumber, gbc_updateNumber);
        updateNumber.setColumns(10);
        
        JLabel lblThershold = new JLabel("Thershold");
        GridBagConstraints gbc_lblThershold = new GridBagConstraints();
        gbc_lblThershold.anchor = GridBagConstraints.EAST;
        gbc_lblThershold.insets = new Insets(0, 0, 5, 5);
        gbc_lblThershold.gridx = 0;
        gbc_lblThershold.gridy = 3;
        ItemsUpdate.add(lblThershold, gbc_lblThershold);
        
        UpdateThershold = new JTextField();
        GridBagConstraints gbc_UpdateThershold = new GridBagConstraints();
        gbc_UpdateThershold.anchor = GridBagConstraints.NORTH;
        gbc_UpdateThershold.insets = new Insets(0, 0, 5, 5);
        gbc_UpdateThershold.fill = GridBagConstraints.HORIZONTAL;
        gbc_UpdateThershold.gridx = 1;
        gbc_UpdateThershold.gridy = 3;
        ItemsUpdate.add(UpdateThershold, gbc_UpdateThershold);
        UpdateThershold.setColumns(10);
        
        JButton btnUpdate = new JButton("Update Thershold and Number");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		Item tempItem = new Item(updateId.getText());
        		if(tempItem.getExist())
        		{
        			
        			tempItem.setNumberInStore(Integer.parseInt(updateNumber.getText()));
        			tempItem.setThershold(Integer.parseInt(UpdateThershold.getText()));
        			UpdateThershold.setText("");
        			updateNumber.setText("");
        			updateId.setText("");
        			JOptionPane.showMessageDialog(LoginScreen, "Item Updated");
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(LoginScreen, "Item Does not exist");
        		}
        	}
        });
        GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
        gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
        gbc_btnUpdate.gridx = 1;
        gbc_btnUpdate.gridy = 4;
        ItemsUpdate.add(btnUpdate, gbc_btnUpdate);
        
        JButton btnBack_1 = new JButton("Back");
        btnBack_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,admin);
        	}
        });
        
        JLabel lblSupplier = new JLabel("Supplier");
        GridBagConstraints gbc_lblSupplier = new GridBagConstraints();
        gbc_lblSupplier.anchor = GridBagConstraints.EAST;
        gbc_lblSupplier.insets = new Insets(0, 0, 5, 5);
        gbc_lblSupplier.gridx = 0;
        gbc_lblSupplier.gridy = 5;
        ItemsUpdate.add(lblSupplier, gbc_lblSupplier);
        
        updateSupplier = new JTextField();
        GridBagConstraints gbc_updateSupplier = new GridBagConstraints();
        gbc_updateSupplier.anchor = GridBagConstraints.NORTH;
        gbc_updateSupplier.insets = new Insets(0, 0, 5, 5);
        gbc_updateSupplier.fill = GridBagConstraints.HORIZONTAL;
        gbc_updateSupplier.gridx = 1;
        gbc_updateSupplier.gridy = 5;
        ItemsUpdate.add(updateSupplier, gbc_updateSupplier);
        updateSupplier.setColumns(10);
        
        JLabel lblName_1 = new JLabel("Name");
        GridBagConstraints gbc_lblName_1 = new GridBagConstraints();
        gbc_lblName_1.anchor = GridBagConstraints.EAST;
        gbc_lblName_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblName_1.gridx = 0;
        gbc_lblName_1.gridy = 6;
        ItemsUpdate.add(lblName_1, gbc_lblName_1);
        
        
        updateName = new JTextField();
        GridBagConstraints gbc_updateName = new GridBagConstraints();
        gbc_updateName.insets = new Insets(0, 0, 5, 5);
        gbc_updateName.fill = GridBagConstraints.HORIZONTAL;
        gbc_updateName.gridx = 1;
        gbc_updateName.gridy = 6;
        ItemsUpdate.add(updateName, gbc_updateName);
        updateName.setColumns(10);
        
        JLabel lblCost = new JLabel("Cost");
        GridBagConstraints gbc_lblCost = new GridBagConstraints();
        gbc_lblCost.insets = new Insets(0, 0, 5, 5);
        gbc_lblCost.gridx = 0;
        gbc_lblCost.gridy = 7;
        ItemsUpdate.add(lblCost, gbc_lblCost);
        
        UpdateCost = new JTextField();
        GridBagConstraints gbc_UpdateCost = new GridBagConstraints();
        gbc_UpdateCost.insets = new Insets(0, 0, 5, 5);
        gbc_UpdateCost.fill = GridBagConstraints.HORIZONTAL;
        gbc_UpdateCost.gridx = 1;
        gbc_UpdateCost.gridy = 7;
        ItemsUpdate.add(UpdateCost, gbc_UpdateCost);
        UpdateCost.setColumns(10);
        
        JButton btnCreate_1 = new JButton("Create");
        btnCreate_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		//(String id,String name, float cost, int quant, String supplier, int thersholdIn)
        		Item tempItem = new Item(updateId.getText(),updateName.getText(),Float.parseFloat(UpdateCost.getText()),Integer.parseInt(updateNumber.getText()),updateSupplier.getText(),Integer.parseInt(UpdateThershold.getText()));
        		if(tempItem.getExist())
        		{

        			UpdateThershold.setText("");
        			updateNumber.setText("");
        			updateId.setText("");
        			UpdateCost.setText("");
        			updateNumber.setText("");
        			updateSupplier.setText("");
        			JOptionPane.showMessageDialog(LoginScreen, "Item Created");
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(LoginScreen, "Item Id is already in use");
        		}
        	}
        });
        GridBagConstraints gbc_btnCreate_1 = new GridBagConstraints();
        gbc_btnCreate_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnCreate_1.gridx = 1;
        gbc_btnCreate_1.gridy = 9;
        ItemsUpdate.add(btnCreate_1, gbc_btnCreate_1);
        GridBagConstraints gbc_btnBack_1 = new GridBagConstraints();
        gbc_btnBack_1.insets = new Insets(0, 0, 0, 5);
        gbc_btnBack_1.gridx = 1;
        gbc_btnBack_1.gridy = 10;
        ItemsUpdate.add(btnBack_1, gbc_btnBack_1);
        
        JPanel RegisterReport = new JPanel();
       
        cards.add(RegisterReport, RegisterReporttxt);
        GridBagLayout gbl_RegisterReport = new GridBagLayout();
        gbl_RegisterReport.columnWidths = new int[]{0, 0, 0};
        gbl_RegisterReport.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_RegisterReport.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gbl_RegisterReport.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        RegisterReport.setLayout(gbl_RegisterReport);
        
        JButton btnBack_2 = new JButton("Back");
        btnBack_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,admin);
        	}
        });
        
        Registertable = new JTable();
        GridBagConstraints gbc_Registertable = new GridBagConstraints();
        gbc_Registertable.gridwidth = 2;
        gbc_Registertable.insets = new Insets(0, 0, 5, 5);
        gbc_Registertable.fill = GridBagConstraints.BOTH;
        gbc_Registertable.gridx = 0;
        gbc_Registertable.gridy = 0;
        JScrollPane scrollPane3 = new JScrollPane();

        scrollPane3.setViewportView(Registertable);

        RegisterReport.add(scrollPane3, gbc_Registertable);
        
        GridBagConstraints gbc_btnBack_2 = new GridBagConstraints();
        gbc_btnBack_2.gridx = 1;
        gbc_btnBack_2.gridy = 4;
        RegisterReport.add(btnBack_2, gbc_btnBack_2);
        
        JPanel AddNewEmployee = new JPanel();
       
        cards.add(AddNewEmployee, AddNewEmployeetxt);
        GridBagLayout gbl_AddNewEmployee = new GridBagLayout();
        gbl_AddNewEmployee.columnWidths = new int[]{0, 0, 0};
        gbl_AddNewEmployee.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_AddNewEmployee.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_AddNewEmployee.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        AddNewEmployee.setLayout(gbl_AddNewEmployee);
        
        JLabel lblName = new JLabel("Name");
        GridBagConstraints gbc_lblName = new GridBagConstraints();
        gbc_lblName.insets = new Insets(0, 0, 5, 5);
        gbc_lblName.anchor = GridBagConstraints.EAST;
        gbc_lblName.gridx = 0;
        gbc_lblName.gridy = 0;
        AddNewEmployee.add(lblName, gbc_lblName);
        
        NewName = new JTextField();
        GridBagConstraints gbc_NewName = new GridBagConstraints();
        gbc_NewName.insets = new Insets(0, 0, 5, 0);
        gbc_NewName.fill = GridBagConstraints.HORIZONTAL;
        gbc_NewName.gridx = 1;
        gbc_NewName.gridy = 0;
        AddNewEmployee.add(NewName, gbc_NewName);
        NewName.setColumns(10);
        
        JLabel lblNewUsername = new JLabel("New UserName");
        GridBagConstraints gbc_lblNewUsername = new GridBagConstraints();
        gbc_lblNewUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewUsername.anchor = GridBagConstraints.EAST;
        gbc_lblNewUsername.gridx = 0;
        gbc_lblNewUsername.gridy = 1;
        AddNewEmployee.add(lblNewUsername, gbc_lblNewUsername);
        
        NewUserName = new JTextField();
        GridBagConstraints gbc_NewUserName = new GridBagConstraints();
        gbc_NewUserName.insets = new Insets(0, 0, 5, 0);
        gbc_NewUserName.fill = GridBagConstraints.HORIZONTAL;
        gbc_NewUserName.gridx = 1;
        gbc_NewUserName.gridy = 1;
        AddNewEmployee.add(NewUserName, gbc_NewUserName);
        NewUserName.setColumns(10);
        
        JLabel lblNewPassword = new JLabel("New Password");
        GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
        gbc_lblNewPassword.anchor = GridBagConstraints.EAST;
        gbc_lblNewPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewPassword.gridx = 0;
        gbc_lblNewPassword.gridy = 2;
        AddNewEmployee.add(lblNewPassword, gbc_lblNewPassword);
        
        NewPassword = new JPasswordField();
        GridBagConstraints gbc_NewPassword = new GridBagConstraints();
        gbc_NewPassword.insets = new Insets(0, 0, 5, 0);
        gbc_NewPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_NewPassword.gridx = 1;
        gbc_NewPassword.gridy = 2;
        AddNewEmployee.add(NewPassword, gbc_NewPassword);
        
        JLabel lblConfrimPassword = new JLabel("Confrim Password");
        GridBagConstraints gbc_lblConfrimPassword = new GridBagConstraints();
        gbc_lblConfrimPassword.anchor = GridBagConstraints.EAST;
        gbc_lblConfrimPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblConfrimPassword.gridx = 0;
        gbc_lblConfrimPassword.gridy = 3;
        AddNewEmployee.add(lblConfrimPassword, gbc_lblConfrimPassword);
        
        confrimNewPassword = new JPasswordField();
        GridBagConstraints gbc_confrimNewPassword = new GridBagConstraints();
        gbc_confrimNewPassword.insets = new Insets(0, 0, 5, 0);
        gbc_confrimNewPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_confrimNewPassword.gridx = 1;
        gbc_confrimNewPassword.gridy = 3;
        AddNewEmployee.add(confrimNewPassword, gbc_confrimNewPassword);
        
        JButton btnCreate = new JButton("Create");
        btnCreate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(NewUserName.getText().compareTo( "") != 0 && NewPassword.getText().compareTo( "") != 0 )
        		{
        			
        			String passText1 = new String(confrimNewPassword.getPassword());
        			String passText2 = new String(NewPassword.getPassword());
        			if(passText1.compareTo(passText2) == 0 )
        			{
        				Employee NewEmployee = new Employee(NewName.getText(), 1,NewUserName.getText(),newTitle.getText() ,passText1);
        				if(NewEmployee.getExists() == false)
        				{
        					//user name is already in user
        					JOptionPane.showMessageDialog(LoginScreen, "Please select a different UserName");
        				}
        				else
        				{
        					//user was created 
        					//clear the boxes and return to admin pain
        					NewName.setText("");
        					NewUserName.setText("");
        					newTitle.setText("");
        					NewPassword.setText("");
        					confrimNewPassword.setText("");
                			CardLayout cl = (CardLayout)(cards.getLayout());
                			cl.show(cards,admin);
                			JOptionPane.showMessageDialog(LoginScreen, "New Employee Created");
        				}
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(LoginScreen, "Passwords dont match");
        			}
        			
        		}
        		
        	}
        });
        
        JLabel lblTilte = new JLabel("Tilte");
        GridBagConstraints gbc_lblTilte = new GridBagConstraints();
        gbc_lblTilte.anchor = GridBagConstraints.EAST;
        gbc_lblTilte.insets = new Insets(0, 0, 5, 5);
        gbc_lblTilte.gridx = 0;
        gbc_lblTilte.gridy = 4;
        AddNewEmployee.add(lblTilte, gbc_lblTilte);
        
        newTitle = new JTextField();
        GridBagConstraints gbc_newTitle = new GridBagConstraints();
        gbc_newTitle.insets = new Insets(0, 0, 5, 0);
        gbc_newTitle.fill = GridBagConstraints.HORIZONTAL;
        gbc_newTitle.gridx = 1;
        gbc_newTitle.gridy = 4;
        AddNewEmployee.add(newTitle, gbc_newTitle);
        newTitle.setColumns(10);
        GridBagConstraints gbc_btnCreate = new GridBagConstraints();
        gbc_btnCreate.insets = new Insets(0, 0, 0, 5);
        gbc_btnCreate.gridx = 0;
        gbc_btnCreate.gridy = 5;
        AddNewEmployee.add(btnCreate, gbc_btnCreate);
        
        JButton btnBack_3 = new JButton("Back");
        btnBack_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
    			CardLayout cl = (CardLayout)(cards.getLayout());
    			cl.show(cards,admin);
        	}
        });
        GridBagConstraints gbc_btnBack_3 = new GridBagConstraints();
        gbc_btnBack_3.gridx = 1;
        gbc_btnBack_3.gridy = 5;
        AddNewEmployee.add(btnBack_3, gbc_btnBack_3);
        
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }
     
     
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */

        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("CardLayoutDemo");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 
                //Create and set up the content pane.
                Main demo = new Main();
                demo.addComponentToPane(frame.getContentPane());
                 
                //Display the window.
                frame.pack();
                frame.setVisible(true);
            }
        });
    }



	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}