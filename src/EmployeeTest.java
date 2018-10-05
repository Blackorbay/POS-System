import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeTest {

	@Test
	void passwordTest() {
		//This test creates an Employee and then checks the password
		
		Employee bobdummy = new Employee("bob", 7,"bobsusername","Title1","mypassword");
		Employee bob = new Employee("bobsusername");
		assertEquals (bob.checkPassword("mypassword") ,true,"Password was not Accepted when should have been");
		assertEquals (bob.checkPassword("notmypassword"),false,"Password was Accepted when shouldnt have been");
		assertEquals (bob.checkPassword("notpassword1") != false,"Password was Accepted when shouldnt have been");
		assertEquals (bob.checkPassword(""),false,"Password was Accepted when shouldnt have been");
		bob.changePassword("newpassword1");
		assertEquals (bob.checkPassword("mypassword"),false,"Password was not changed and should of been");
		assertEquals (bob.checkPassword("newpassword1"), true, "Changed Password did not work");
		bob.changePassword("mypassword");
	}
	
	void existanceTest()
	{
		
		Employee bobdummy = new Employee("bob", 7,"bobsusername","Title1","mypassword");
		//try and create another Employee with the same name
		Employee mark = new Employee("bob", 7,"bobsusername","Title1","mypassword");
		assertEquals (mark.getExists(), false, "Same Employee exists when it should not of been");
		//make sure bob exists already
		Employee bob = new Employee("bobsusername");
		assertEquals (bob.getExists(), true, "Existing employee was not checked correctly");
		assertEquals (bob.checkPassword("mypassword") ,true,  "Retrived Password did not match");
		assertEquals (bob.checkPassword("notmypassword"), false,  "Retrived Password did not match");
		assertEquals (bob.checkPassword("notpassword1") , false,  "Retrived Password did not match");
	}
	
	void checkGetSetFunctions()
	{
		Employee bobdummy = new Employee("bob", 7,"bobsusername","Title1","mypassword");
		Employee bob = new Employee("bobsusername");
		assertEquals (bob.getaccesslevel(), 7, "Get access level didnt work");
		assertEquals (bob.getTitle(),"Title1", "Get Title didnt work");
		bob.setTitle("Title2");
		assertEquals (bob.getTitle() , "Title2", "set Title didnt work");
		bob.setTitle("Title1");
	}

}
