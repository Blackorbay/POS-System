import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


public class Order {
	
	private ArrayList<Item> incart = null;
	private float totalCost = 0;
	private int totalItems = 0;
	private long orderNumber = 0;
	protected final static String orderfile = "orders.csv";

public long getOrderNumber() {
	return orderNumber;
}



Order(){
	orderNumber = generateOrderNumber();
	incart = new ArrayList<Item>(1);
}

Order(int OrderNumber)
{
	this.orderNumber = OrderNumber;
}

private long generateOrderNumber()
{
	int year = Calendar.getInstance().get(Calendar.YEAR);
	year = year - ((year / 1000) * 1000);  
	String month = Integer.toString(1 + Calendar.getInstance().get(Calendar.MONTH));
	String day = Integer.toString(Calendar.getInstance().get(Calendar.DATE));
	//System.out.println(year);
	String hour = Integer.toString(Calendar.getInstance().get(Calendar.HOUR));
	String min = Integer.toString(Calendar.getInstance().get(Calendar.MINUTE));
	String sec = Integer.toString(Calendar.getInstance().get(Calendar.SECOND));
		
	long number = Long.parseLong((Integer.toString(year) + month + day + hour + min + sec));
	
	return number;
}

//The Id should be valided before passing to the function
public boolean addToOrder(String id)
{
	
	int arraysize = incart.size();
	for (int i = 0; i < arraysize ; i++)
	{
		if(incart.get(i).getID().equals(id))
		{
			if(incart.get(i).addtoCount())
		    {
		    	//item didnt work
		    	totalItems++;
		    	totalCost += incart.get(i).getCost();
		    	return true;
		    }
		    else
		    {
		    	return false;
		    }
		}
	}
	
	Item tempItem = new Item(id);
	if(tempItem.addtoCount())
	{
		incart.add(tempItem);
    	totalItems++;
    	totalCost += tempItem.getCost();
		return true;
	}
	
	
	return false;
	
	
}
	

int getNumberOfItems()
{	
	return totalItems;
}

public boolean removefromorder(String id)
{
	
	int arraysize = incart.size();
	for (int i = 0; i < arraysize ; i++)
	{
		if(incart.get(i).getID().equals(id))
		{
			incart.get(i).removeCount();
	    	totalItems--;
	    	totalCost -= incart.get(i).getCost();
			return true;
		}
	}
	
	return false;
}

public float getCost()
{
	return this.totalCost;
}
public boolean completeSale()
{
	//write order to file to be looked up later
    try {
    	FileWriter  pw = new FileWriter (orderfile,true);
        StringBuilder sb = new StringBuilder();
        int arraysize = incart.size();
        sb.append(this.orderNumber);
        sb.append(',');
        sb.append(arraysize);
        
        
    	for (int i = 0; i < arraysize ; i++)
    	{
    		sb.append(',');
    		sb.append(incart.get(i).getID());
    		sb.append(',');
    		sb.append(incart.get(i).getCount());
    	}
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
 
	return true;
}


public void voidSale()
{
	int arraysize = incart.size();
	for (int i = 0; i < arraysize ; i++)
	{
		int count = incart.get(i).getCount();
		Item remove = incart.get(i);
		for (int j = 0; j < count; j++)
		{
			remove.removeCount();
		}
		
	}
}

public  Object[][] displayOrderData()
{
	
	int arraysize = incart.size();
	Object[][] toreturn = new Object[arraysize][4];
	for (int i = 0; i < arraysize ; i++)
	{
		Item tempItem = incart.get(i);
		toreturn[i][0] = tempItem.getID();
		toreturn[i][1] = tempItem.getName();
		toreturn[i][2] = tempItem.getCount();
		toreturn[i][3] = tempItem.getCount() * tempItem.getCost();
		
	}
	return toreturn;
	
}

public Boolean returnItem(String ItemID)
{
	boolean userFound = false;
    String line = "";
    String cvsSplitBy = ",";
	BufferedReader br = null;
	try {
	 br = new BufferedReader(new FileReader(orderfile));
     while ((line = br.readLine()) != null) {

         // use comma as separator
         String[] tempread = line.split(cvsSplitBy);

         if(tempread[0].equals(String.valueOf(this.orderNumber)))
         {
        	 int length = 2 + 2*Integer.parseInt(tempread[1]);
        	for(int i = 2; i < length; i = i+2 )
        	{
        		if( ItemID.equals(tempread[i]) )
        		{
        			return true;
        		}
        	}
        	 return false;
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
	
	return false;
}
}
