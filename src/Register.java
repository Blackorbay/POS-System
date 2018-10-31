

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;

public class Register {
	private float cashAmount;
	Employee currentUser;
	Order currentOrder;
  InventoryManager inventory;
  String filename = "OrderArchive.txt";
  
	Register()
	{
	}
	Register(float cashAmount)
	{
		
	}
	
	Register(float cashAmount, Employee currentUser)
	{
		
	}
	public void addCash(float amount)
	{
		
	}
	public float returnCash(float cashPaid) // must be performed after close order. 
	{ 
    return cashPaid - currentOrder.getCost();
	}
  
	public boolean returnItem(String itemID, int quant, int orderNumber)
	{
    
    Item returnItem = new Item(itemID, quant);
    inventory.updateInventory(returnItem, quant);
		return true;
	}
	
	public void startOrder()
	{
    inventory = new InventoryManager();
		currentOrder = new Order();
	}
  
  public void addItem(String ID, int quant)
  {
    
    System.out.println(ID + " was added to order: " + currentOrder.addToOrder(ID, quant));
  }
  
    public void removeItem(String ID, int quant)
  {
    currentOrder.removeItem(ID, quant);
  }
	
	public void closeOrder()
	{
	
  currentOrder.completeSale();
  }
	
	public void cancelOrder()
	{
		currentOrder = null;
	}
  
  private void saveOrderToFile()
  {
   try {
	        	FileWriter  pw = new FileWriter (filename,true);
	        	
	        		pw.append(currentOrder.toString() + "\n");
		          pw.flush();
	            pw.close();
			  } 
	      catch (FileNotFoundException e) {
				e.printStackTrace();
			  } 
	      catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }	 

  }
  
//   private boolean checkOrderFromArchive(int number)
//   {
    
    
    
//   }
  
  public static void main(String [] args)
  {
    Register testRegister = new Register();
    testRegister.startOrder();

    testRegister.addItem("6711798701051184", 5);
    testRegister.addItem("8797108841191111", 5);
    testRegister.addItem("76117110831051205", 1);
    testRegister.closeOrder();
    System.out.println(testRegister.returnCash(1000f));
    testRegister.saveOrderToFile();
  }
}
