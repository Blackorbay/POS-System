import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class Employee {
	
	private String username;
	private String passwordHash;
	private String Title;
	private int accessLevel;

	private boolean exists;
	static final String filename = "EmployeeRecords.csv";
	//in the file data is stored as: username, passwordHash, Title, accessLevel 
	
	
	Employee(String name, int accessLevel, String username, String title, String password)
	{
		this.username = username;
		//This function should only be used when a user logs into the Database
		//this function should write to a file to record the new employee
		if(this.checkUserInFileandUpdate() == true)
		{
			this.accessLevel = 0;
			this.passwordHash = null;
			this.Title = null;
			this.exists = false;
		}
		else
		{
			this.username = username;
			this.passwordHash = this.createPasswordHash(password);
			this.Title = title;
			this.writeUsertoFile();
		}
		
				
	}
	Employee()
	{
		//empty constructor
		
	}
	Employee(String usernameInput)
	{
		//this constructor is used when an employee is already a user in the system
		this.username = usernameInput;
		
		//read the employee from the database 
		this.checkUserInFileandUpdate();
		
		//if it does not exist populate everything to null
		if(this.exists == false)
		{
			this.accessLevel = 0;
			this.passwordHash = null;
			this.Title = null;
		}
	}
	
	public boolean checkPassword(String password) 
	{
		if(password.equals(this.passwordHash))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public Boolean setTitle(String newTitle)
	{
		this.Title = newTitle;
		return true;
	}
	
	public Boolean changePassword(String newPassword) {
		this.passwordHash = newPassword;
		return true;
	}
	
	public int getaccesslevel() {
		return this.accessLevel;
	}
	
	public String getTitle()
	{
		return this.Title;
	}
	
	public boolean getExists()
	{
		return this.exists;
	}
	
	private boolean checkUserInFileandUpdate()
	{
		
		boolean userFound = false;
        String line = "";
        String cvsSplitBy = ",";
		BufferedReader br = null;
		try {
		 br = new BufferedReader(new FileReader(filename));
         while ((line = br.readLine()) != null) {

             // use comma as separator
             String[] tempEmployee = line.split(cvsSplitBy);

             if(tempEmployee[0].equals(this.username))
             {
            	 this.passwordHash = tempEmployee[1];
            	 this.Title = tempEmployee[2];
            	 this.accessLevel = Integer.parseInt(tempEmployee[3]);
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
		return userFound;
	}
	
	private void writeUsertoFile()
	{
        try {
        	FileWriter  pw = new FileWriter (filename,true);
	        StringBuilder sb = new StringBuilder();
	        sb.append(this.username);
	        sb.append(',');
	        sb.append(this.passwordHash);
	        sb.append(',');

	        sb.append(this.Title);
	        sb.append(',');
	        sb.append(this.accessLevel);
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
	
	private String createPasswordHash(String password)
	{
		return password;
	}
	
	//TODO: for testing and debugging purposes only remove once done with project
	public static void main(String[] args) 
	{
		Employee bobdummy = new Employee("bob", 7 ,"bobsusername","Title1","mypassword");
		Employee bob2 = new Employee("bob2", 7 ,"bobsusername2","Title12","mypassword2");
	}
	
	
}
