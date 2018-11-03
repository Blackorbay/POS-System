import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Item {
	//Stored in Inventory is ID, name, cost, NumberInStore, supplier, threshold

private String id = "not assigned";
  private String name = "default";
  private float cost = 0.00f;  
  private int count = 0;
  private String supplier = "default";
  public static final String  filename = "InventoryItems.csv";
  private boolean exists;
  private int NumberInStore;
  private int thershold;
  
  Item()
  {
	  this.exists = false;
  }

  Item(String id,String name, float cost, int quant, String supplier, int thersholdIn){
	  this.id = id;
	if(this.checkItemInFileandUpdate() == true)
	{
		this.name = null;
	    this.cost = 0;
	    this.count = 0;  
	    this.NumberInStore = 0;
	    this.supplier = null;
	    this.id = null;
	    this.exists = false;
	}
	else
	{
		this.name = name;
	    this.cost = cost;
	    this.count = 0;  
	    this.NumberInStore = quant;
	    this.supplier = supplier;
	    this.exists = true;
	    this.thershold = thersholdIn;
		this.writeItemtoFile();
		
	}
    
  }
  
  void update(String id,String name, float cost, int quant, String supplier, int thersholdIn)
  {
	  
	  this.removefromstore();
	  this.id = id;
	this.name = name;
    this.cost = cost;
    this.count = 0;  
    this.NumberInStore = quant;
    this.supplier = supplier;
    this.exists = true;
    this.thershold = thersholdIn;
	this.writeItemtoFile();
  }
  
  Item(String id)
  {
    
    this.id = id;
    this.count = 0;
	this.exists = checkItemInFileandUpdate();
	
	//if it does not exist populate everything to null
	if(this.exists == false)
	{
	    this.cost = 0;
	    this.count = 0;  
	    this.NumberInStore = 0;
	    this.supplier = null;
	    this.id = null;
	}
    
    
  }

  public float getCost() {
    return cost;
  }

  public void setNumberInStore(int NumberInStore) {
	this.removefromstore();
    this.NumberInStore = NumberInStore;
    this.writeItemtoFile();
  }
  
  
  public void setThershold(int thershold) {
	this.removefromstore();
    this.thershold = thershold;
    this.writeItemtoFile();
  }

  public int getCount() {
    return count;
  }
  
  public int getNumberInStore()
  {
	  return this.NumberInStore;
  }

  public String getSup()
  {
    return this.supplier;
  }


  public void get(int count) {
	    this.count = count;
	  }

  public String getID() {
    return id;
  }
  
  public Boolean addtoCount()
  {
	  if(this.NumberInStore > 0)
	  {
		  this.count++;
		  this.NumberInStore--;
		this.removefromstore();
		  this.writeItemtoFile();
		  return true;
	  }
	  else
	  {
		  return false;
	  }

  }

  public Boolean removeCount()
  {
	  if(this.count >= 1)
	  {
		  this.count--;
		  this.NumberInStore++;
		  this.removefromstore();
		  this.writeItemtoFile();
		  return true;
	  }
	  else
	  {
		  return false;
	  }

  }
  
  String getName()
  {
    return name;
  }

  
	private boolean checkItemInFileandUpdate()
	{
		
		boolean itemFound = false;
      String line = "";
      String cvsSplitBy = ",";
		BufferedReader br = null;
		try {
		 br = new BufferedReader(new FileReader(filename));
       while ((line = br.readLine()) != null) {

           // use comma as separator
           String[] tempItem = line.split(cvsSplitBy);

           if(tempItem[0].equals(this.id))
           {
          	 this.name = tempItem[1];
          	 this.cost = Float.parseFloat(tempItem[2]);
          	 this.NumberInStore = Integer.parseInt(tempItem[3]);
        	 this.supplier = tempItem[4];
           	this.thershold = Integer.parseInt(tempItem[5]);
          	 this.exists = true;
          	 br.close();
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
	
	private void writeItemtoFile()
	{
      try {
      	FileWriter  pw = new FileWriter (filename,true);
	        StringBuilder sb = new StringBuilder();
	        sb.append(this.id);
	        sb.append(',');
	        sb.append(this.name);
	        sb.append(',');

	        sb.append(this.cost);
	        sb.append(',');
	        sb.append(this.NumberInStore);
	        sb.append(',');
	        sb.append(this.supplier);
	        sb.append(',');
	        sb.append(this.thershold);	        
	        
	        sb.append('\n');

	        pw.append(sb.toString());
          pw.flush();
          pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      

      
	}
	
	//updates the Number of the Items in the store
	private void removefromstore()
	{
		File inputFile = new File("InventoryItems.csv");
		File tempFile = new File("temp.csv");
		String cvsSplitBy = ",";
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String currentLine;
			
			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
				String[] tempItem = currentLine.split(cvsSplitBy);
			    if(tempItem[0].equals(this.id)) 
			    	continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			
			//clear the file
			PrintWriter pwriter = new PrintWriter(inputFile);
			pwriter.print("");
			pwriter.close();
			
			//copy back the data
			reader = new BufferedReader(new FileReader(tempFile));
		    writer = new BufferedWriter(new FileWriter(inputFile));
			
			while((currentLine = reader.readLine()) != null) {
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		
	}
	
	public void delete()
	{
		this.removefromstore();
		this.name = null;
	    this.cost = 0;
	    this.count = 0;  
	    this.NumberInStore = 0;
	    this.supplier = null;
	    this.id = null;
	    this.exists = false;
	}
	
	public boolean getExist()
	{
		return this.exists;
	}

}
