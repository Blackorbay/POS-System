
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class InventoryManager
{
 
	private String itemName;
	private String itemSup;
	private float itemCost;
	private int itemQuant;
  private Item tempItem; 
	private boolean exists;
	private String filename = "C:\\Users\\Wingman\\eclipse-workspace\\POS System\\bin\\InventoryItems.txt";
	
	

	InventoryManager()
	{
		//empty constructor
	}
  
  //specify filename for different inventories.
  InventoryManager(String filename)
  {
    this.filename = filename;
  }
	
	private boolean checkItemInFile(Item testItem)
	{
		
		boolean itemFound = false;
    String line = "";
    String cvsSplitBy = ",";
    BufferedReader br = null;
    String testItemID = testItem.getID();

    try {
      
    
		 br = new BufferedReader(new FileReader(filename));
         while ((line = br.readLine()) != null) {

             // use comma as separator
             String[] tempInventory = line.split(cvsSplitBy);

             if(tempInventory[1].equals(testItemID))
             {
            	 return true;
             }

         }
		 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return itemFound;
	}
  
  private void displayInventoryReport()
  {
    boolean itemFound = false;
    String line = "";
    String cvsSplitBy = ",";
		
    BufferedReader br = null;
    
    
       try {
         br = new BufferedReader(new FileReader(filename));
         while ((line = br.readLine()) != null) {

             // use comma as separator
             String[] tempInventory = line.split(cvsSplitBy);
            for (int i = 0; i < 5; i++)
              System.out.print(" " + tempInventory[i]);
           System.out.println();

          }
         } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        }
    

	private void addNewToInventory(Item newItem)
	{
    
      if (!checkItemInFile(newItem))
      {
        try {
        	FileWriter  pw = new FileWriter (filename,true);
	        StringBuilder sb = new StringBuilder();
         
	        sb.append(newItem.getName());
	        sb.append(',');
	        sb.append(newItem.getID());
	        sb.append(',');
	        sb.append(newItem.getSup());
	        sb.append(',');
	        sb.append(Integer.toString(newItem.getCount()));
	        sb.append(',');
	        sb.append(Float.toString(newItem.getCost()));
	        sb.append('\n');

	        pw.append(sb.toString());
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
	}
  
  private void updateInventory(Item newItem, int changeQuant)
  {
    
	  BufferedReader br = null;
		String tempFile = "C:\\Users\\Wingman\\eclipse-workspace\\POS System\\bin\\tempfile.txt";
    if (checkItemInFile(newItem))
    {
      try {
      	
      	String line = "";
          String cvsSplitBy = ",";
          br = new BufferedReader(new FileReader(filename));
      	while ((line = br.readLine()) != null) {
      	String[] tempInventory = line.split(cvsSplitBy);
      	
      	FileWriter  pw = new FileWriter (tempFile,true);
      	
	        StringBuilder sb = new StringBuilder();
	        if(!newItem.getID().equals(tempInventory[1])) {
	        sb.append(tempInventory[0]);
	        sb.append(',');
	        sb.append(tempInventory[1]);
	        sb.append(',');
	        sb.append(tempInventory[2]);
	        sb.append(',');
	        sb.append(tempInventory[3]);
	        sb.append(',');
	        sb.append(tempInventory[4]);
	        sb.append('\n');

	        pw.append(sb.toString());
          pw.flush();
          pw.close();
	        }
      	}
		  } 
    catch (FileNotFoundException e) {
			e.printStackTrace();
		  } 
    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
  }         
    
    
  }
	
	//TODO: for testing and debugging purposes only remove once done with project
	public static void main(String[] args) 
	{
 	InventoryManager inventory = new InventoryManager();
    Item testItemOne = new Item("One",11.11f, 12, "CostCo");
    Item testItemTwo = new Item("Two",11.11f, 12, "CostCo");
    Item testItemThree = new Item("Three",11.11f, 12, "CostCo");
//    inventory.addNewToInventory(testItemOne);
//    inventory.addNewToInventory(testItemTwo);
//    inventory.addNewToInventory(testItemThree);
    inventory.displayInventoryReport();
 	inventory.updateInventory(testItemThree, 9999);
      inventory.displayInventoryReport();
  }
	
	


  
}


