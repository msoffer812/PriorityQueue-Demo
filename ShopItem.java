package shoppingHW;

public class ShopItem {
	private String name;
	private double price;
	
	public ShopItem(String name, double price)
	{
		this.name = name;
		this.price = price;
	}
	
	public String getItemName()
	{
		return name;
	}
	public double getItemPrice()
	{
		return price;
	}
	
	public double getPriceByQuantity(int quantity)
	{
		return quantity * price;
	}
}
