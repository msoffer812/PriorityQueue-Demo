package shoppingHW;

import java.text.DecimalFormat;
import java.util.Comparator;

public class Shopper extends Person implements Comparable<Shopper>{
	private int itemsPurchased;		/* Amount of items shopper purchased */
	private double totalPurchase;	/* Total purchase value by the shopper */
	private int lineOrder;
	
	public Shopper(int ip, double tp, String name, int lineOrder)
	{
		/*
		 * Call superconstructor for name
		 */
		super(name);
		itemsPurchased = ip;
		totalPurchase = tp;
		this.lineOrder = lineOrder;
	}
	
	/*
	 * Getters
	 */
	
	/**
	 * 
	 * @return itemsPurchased
	 */
	public int getItemsPurchased()
	{
		return this.itemsPurchased;
	}
	
	/**
	 * 
	 * @return totalPurchase
	 */
	public double getTotalPurchase()
	{
		double amount = roundToTwoDecimalPoints(this.totalPurchase);
		return amount;
	}
	/**
	 * @return user's name
	 */
	public String getName()
	{
		return this.name;
	}
	/**
	 * 
	 * @return order got into line
	 */
	public int getLineOrder()
	{
		return this.lineOrder;
	}
	/*
	 * Setters
	 */
	
	/**
	 * Set amount of purchased items
	 * @param i
	 */
	public void setItemsPurchased(int i)
	{
		this.itemsPurchased = i;
	}
	/**
	 * Set total cost
	 * @param t
	 */
	public void setTotalPurchase(double t)
	{
		this.totalPurchase = t;
	}
	/**
	 * Set the order got into line
	 * @param o
	 */
	public void setOrder(int o)
	{
		this.lineOrder = o;
	}
	/**
	 * Add amount to total purchase
	 * @param t
	 */
	public void addPurchase(double t)
	{
		totalPurchase += t;
	}
	
	public void addItems(int amount)
	{
		this.itemsPurchased += amount;
	}
	
	private double roundToTwoDecimalPoints(double value)
	{
		 DecimalFormat df = new DecimalFormat("#.##");
		 String formattedNum = df.format(value);
		 double result = Double.parseDouble(formattedNum);
		 return result;
	}
	
	@Override
	public int compareTo(Shopper shopper)
	{
		/*
		int priority = Double.compare(shopper.getTotalPurchase(), getTotalPurchase());
		if(priority == 0)
		{
			return Integer.compare(getLineOrder(), shopper.getLineOrder());
		}
		return priority;
		*/
		return Double.compare(shopper.getTotalPurchase(), getTotalPurchase());
	}
	
}
