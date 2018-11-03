import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderTest {

	@Test
	void test() {
		
		Order testOrder = new Order();
		assertEquals(testOrder.addToOrder("id1"), true);
		assertEquals(testOrder.addToOrder("id0"), false); // no id0 
		assertEquals(testOrder.addToOrder("id1"), true);
		
		assertEquals(testOrder.getNumberOfItems(), 2);
		
		assertEquals(testOrder.getCost(), 2f);
		
	}

}
