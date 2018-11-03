import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTest {

	@Test
	void existanceTest() {
		//make sure the item is created before testing that it exists
		Item item1 = new Item("id1","Product Name", 1.1f,10,"Supplier", 12);
		Item item2 = new Item("id1");
		assertEquals (item2.getCost() ,1.1f,"Created Item could not be recreated");
	}
	
	
	@Test
	void addTest()
	{
		Item item1 = new Item("id2","Product Name", 1.1f,10,"Supplier", 12);
		Item item3 = new Item("id2");
		item3.addtoCount();
		assertEquals (item3.getCount() ,1,"Count not added");
		assertEquals (item3.getNumberInStore() ,9,"Count not added");
		item3.removeCount();
		assertEquals (item3.getCount() ,0,"Count not remove");
		assertEquals (item3.getNumberInStore() ,10,"Count not remove");
	}
	


}
