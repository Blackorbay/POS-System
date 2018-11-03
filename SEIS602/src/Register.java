import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;


public class Register {
	private float cashAmount;
	private float cashmade = 0;
	Employee currentUser;
	int totalSales;
	public static final String  filename = "Register.csv";
	int yearLog;
	int monthLog;
	int DayLog;
	int HourLog;
	int MinuteLog;
	
	Register(float cashAmountIN, Employee currentUserIN)
	{
		//right now we only have 1 register
		this.cashAmount = cashAmountIN;
		this.currentUser = currentUserIN;
		this.totalSales = 0;

		 this.yearLog = Calendar.getInstance().get(Calendar.YEAR);
		 this.monthLog = Calendar.getInstance().get(Calendar.MONTH);
		 this.DayLog = Calendar.getInstance().get(Calendar.DATE);
		 this.HourLog = Calendar.getInstance().get(Calendar.HOUR);
		 this.MinuteLog= Calendar.getInstance().get(Calendar.MINUTE);
		
	}
	
	public void addCash(float amount)
	{
		this.cashAmount +=amount;
	}
	
	public void returnCash(float amount)
	{
		this.cashAmount-=amount;
	}
	
	public void finishSale(float amount)
	{
		this.cashAmount +=amount;
		this.cashmade += amount;
		this.totalSales ++;
	}
 
	public static Object[][] outputReport()
	{
		int count = 0;  
	    String line = "";
	    String cvsSplitBy = ",";
	    BufferedReader br = null;
	    
	    try {
	        br = new BufferedReader(new FileReader(filename));
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
	    
		Object[][] toreturn = new Object[count][5];
	    int count2 = 0;
	    
	       try {
	         br = new BufferedReader(new FileReader(filename));
	         while ((line = br.readLine()) != null) {

	             // use comma as separator
	             String[] tempInventory = line.split(cvsSplitBy);
	            for (int i = 0; i < 5; i++)
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
	
	public void logoff()
	{
		
        try {
        	FileWriter  pw = new FileWriter (filename,true);
	        StringBuilder sb = new StringBuilder();
	        sb.append(this.currentUser.getUserName());
	        sb.append(',');
	        sb.append(this.totalSales);
	        sb.append(',');

	        sb.append(this.HourLog + ":" + this.MinuteLog + " " + this.monthLog + "/" + this.DayLog + "/" + this.yearLog);
	        sb.append(',');
	        sb.append(Calendar.getInstance().get(Calendar.HOUR) + ":" + Calendar.getInstance().get(Calendar.MINUTE)+ " " + Calendar.getInstance().get(Calendar.MONTH) + "/" + Calendar.getInstance().get(Calendar.DATE) + "/" + Calendar.getInstance().get(Calendar.YEAR));
	        sb.append(',');
	        sb.append(cashmade);
	        
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
	
}
