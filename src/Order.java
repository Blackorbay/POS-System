
public class Order {
private int orderNumber;
private Item[] incart;

public int getOrderNumber() {
	return orderNumber;
}
Order()
{}
int getNumberOfItems()
{
	return 0;
}
public boolean addItem(String name, int number)
{
	return false;
}

public boolean returnItem(String name, int number)
{
	return false;
}
public boolean completeSale()
{
	return true;
}

//this function calculates the hash that goes on the receipt
public int getOrderNumberhash()
{
	return 0;
}

//This function takes in a hash and checks to see if the input hash is correct based on the current order
public boolean checkOrderhas(int hash)
{
	return true;
}

public float getTotalCost()
{
	return 0.0f;
}

public void voidSale()
{
	
}
}
