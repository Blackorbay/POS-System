
public class Employee {
	private String name;
	private ACCESSLEVEL accessLevel;
	private String usernameHash;
	private String passwordHash;
	private String Title;
	
	Employee(String name, ACCESSLEVEL accessLevel, String username, String title)
	{
		//this function should write to a file to record the new employee
	}
	
	public boolean checkPassword(String password) 
	{
		return false;
	}
	
	public Boolean setTitle(String Title)
	{
		return true;
	}
	
	public Boolean changePassword(String newPassword) {
		return true;
	}
	
	public ACCESSLEVEL getaccesslevel() {
		return this.accessLevel;
	}
}
