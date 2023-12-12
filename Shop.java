package shoppingHW;

import java.util.*;

public class Shop {
	List<ShopItem> items;				/* List of the items, good for printing them out in a predictable order
	 										so the user can select an item based on index */
	Map<String, ShopItem> itemsValues;	/* Map of the items, good for easy selection based on name of the items */
	
	/**
	 * No-arg constructor - constructs with pre-coded values
	 */
	public Shop()
	{
		String[] itemsNames = {
	            "Teddy Bear", "Apple", "Book", "Coffee Mug", "Headphones",
	            "Sunglasses", "Backpack", "Laptop", "Umbrella", "Chocolates"
	        };
		double[] prices = {35.55, 29.99, 15.00, 67.89, 33.99, 17.99, 85.70, 60.89, 28.35, 59.99};
		
		this.items = new ArrayList<>();
		this.itemsValues = new HashMap<>();
		fillUpItems(itemsNames, prices);
	}
	
	/**
	 * Constructor with custom values for store items
	 * @param itemsNames
	 * @param itemsPrices
	 */
	public Shop(String[] itemsNames, double[] itemsPrices)
	{
		this.items = new ArrayList<>();
		this.itemsValues = new HashMap<>();
		fillUpItems(itemsNames, itemsPrices);
	}
	
	/**
	 * Fills up the map and list with values of shopItems
	 * @param names
	 * @param prices
	 */
	private void fillUpItems(String[] names, double[] prices)
	{
		for(int i=0;i<names.length;i++)
		{
			ShopItem newItem = new ShopItem(names[i], prices[i]);
			items.add(newItem);
			itemsValues.put(names[i], newItem);
		}
	}
	
	/**
	 * 
	 * @param item
	 * @return price of an item given its name
	 */
	public ShopItem getItemByName(String item)
	{
		if(itemsValues.containsKey(item))
		{
			return itemsValues.get(item);
		}
		return null;
	}
	
	/**
	 * 
	 * @param index
	 * @return price of an item given its index
	 */
	public ShopItem getItemByIndex(int index)
	{
		if(index < items.size())
		{
			return items.get(index);
		}
		return null;
	}
	
	/**
	 * @return the string representation of the store items
	 */
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		int index = 1;
		for(ShopItem item: items)
		{
			str.append(index + ". " + item.getItemName() + "..........$" + item.getItemPrice() + "\n");
			index++;
		}
		return str.toString();
	}
}
