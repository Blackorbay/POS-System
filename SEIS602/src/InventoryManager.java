
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class InventoryManager
{
 
	private static final Object[][] Object = null;


	InventoryManager()
	{
		
	}
  
  
  public Object[][] displayInventoryReport()
  {
	  
	Item dummy = new Item();
	int count = 0;  
    String line = "";
    String cvsSplitBy = ",";
    BufferedReader br = null;
    
    try {
        br = new BufferedReader(new FileReader(dummy.filename));
        while ((line = br.readLine()) != null) {
        	   count++;
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
    
	Object[][] toreturn = new Object[count][6];
    int count2 = 0;
    
       try {
         br = new BufferedReader(new FileReader(dummy.filename));
         while ((line = br.readLine()) != null) {

             // use comma as separator
             String[] tempInventory = line.split(cvsSplitBy);
            for (int i = 0; i < 6; i++)
            {
            	toreturn[count2][i] = tempInventory[i];
            }
            count2++;
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
	return toreturn;     
  }
  
  public Object[][] nightlyRun()
  {
	  
	Item dummy = new Item();
	int count = 0;  
    String line = "";
    String cvsSplitBy = ",";
    BufferedReader br = null;
    
    try {
        br = new BufferedReader(new FileReader(dummy.filename));
        while ((line = br.readLine()) != null) {
        	   count++;
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
    
	Object[][] nightRun = new Object[count][6];
    int count2 = 0;
    
       try {
         br = new BufferedReader(new FileReader(dummy.filename));
         while ((line = br.readLine()) != null) {

             // use comma as separator
             String[] tempInventory = line.split(cvsSplitBy);
            if(Integer.parseInt(tempInventory[5]) > Integer.parseInt(tempInventory[3]))
            {
             for (int i = 0; i < 6; i++)
            {
            	nightRun[count2][i] = tempInventory[i];
            }
            count2++;
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
	return nightRun;     
  }
  
}


