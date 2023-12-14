package shoppingHW;

import java.text.DecimalFormat;
import java.util.Comparator;

public class Shopper extends Person implements Comparable<Shopper>{
	private int itemsPurchased;		/* Amount of items shopper purchased */
	private double totalPurchase;	/* Total purchase value by the shopper */
	
	public Shopper(int ip, double tp, String name)
	{
		/*
		 * Call superconstructor for name
		 */
		super(name);
		itemsPurchased = ip;
		totalPurchase = tp;
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
	 * take of 15% of total purchase value
	 */
	public void giveCoupon() {
		totalPurchase *= .85;
	}
	/**
	 * @return user's name
	 */
	public String getName()
	{
		return this.name;
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
		return Double.compare(getTotalPurchase(), shopper.getTotalPurchase());
	}
	
}
