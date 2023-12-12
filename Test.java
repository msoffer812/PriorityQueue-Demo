package shoppingHW;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
	public static void main(String[] arg)
	{
		String continueToPlay = "1";
		String continueToCreateShoppers = "1";
		Scanner in = new Scanner(System.in);
		PriorityQueue<Shopper> shoppers = createPriorityQueue(in);
		/*
		 * Loop the "game" until the user wants to quit
		 */
		while(continueToPlay.equals("1"))
		{
			/*
			 * Create and Enqueue shoppers until user decides to stop
			 * or queue is full
			 */
			while(continueToCreateShoppers.equals("1") && !shoppers.isFull())
			{
				createAndEnqueueShopper(shoppers, in);
				continueToCreateShoppers = getStringInput("Press 1 to create a new shopper, any other key to checkout", in);
				checkIfQueueIsFull(shoppers, continueToCreateShoppers);
			}
			checkoutCustomers(shoppers);
			continueToPlay = getStringInput("Press 1 to create new shoppers, any other key to exit", in);
			continueToCreateShoppers = "1";
		}
		System.out.println("Thank you for shopping, goodbye!");
		in.close();
		System.exit(1);
	}
	
	/**
	 * Tell user if the queue is full
	 * @param shoppers
	 * @param continueToCreateShoppers
	 */
	public static void checkIfQueueIsFull(PriorityQueue<Shopper> shoppers, String continueToCreateShoppers)
	{
		if(shoppers.isFull()) {
			System.out.println("Queue is full, going to checkout");
		}
	}
	/**
	 * initialize priorityQueue
	 * @param in
	 * @return
	 * @return new priorityQueue
	 * @see getIntInput()
	 */
	public static PriorityQueue<Shopper> createPriorityQueue(Scanner in)
	{
		int sizeOfQueue = getIntInput("Enter the max amount of shoppers you'd like the line to hold", in);
		PriorityQueue<Shopper> shoppers= new PriorityQueue<>(sizeOfQueue);
		return shoppers;
	}
	
	/**
	 * Call the methods that create the shopper
	 * and then enqueue it
	 * @param shoppers
	 * @param in
	 */
	public static void createAndEnqueueShopper(PriorityQueue<Shopper> shoppers, Scanner in)
	{
		Shopper shopper = createShopper(in);
		enqueueShopper(shopper, shoppers);
	}
	
	/**
	 * Take in a shopper, apply the coupon, give him his order
	 * of insertion for debugging purposes, enqueue to the priorityQueue
	 * @param shopper
	 * @param shoppers
	 * @see giveCoupon()
	 */
	public static void enqueueShopper(Shopper shopper, PriorityQueue<Shopper> shoppers)
	{
		if(shopper.getTotalPurchase() >= 100)
		{
			giveCoupon(shopper);
		}
		int lineOrder = shoppers.size()+1;
		
		shopper.setOrder(lineOrder);
		try
		{
			shoppers.enqueue(shopper);	
		}
		/*
		 * Placed validation here but the loop should handle this -
		 * stop before the queue is full
		 */
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Queue is full, you must checkout before adding more. ");
		}
	}
	
	/**
	 * @see getStringInput()
	 * @see shop()
	 * @param in
	 * @return newly created shopper that this creates
	 */
	public static Shopper createShopper(Scanner in)
	{
		String shopperName = getStringInput("Enter the shopper's name: ", in);
		Shopper newShopper = new Shopper(0, 0, shopperName, 0);
		shop(in, newShopper);
		return newShopper;
	}
	
	/**
	 * Display the shop info,
	 * Get user info on what they want to buy
	 * and how much of it
	 * @param in
	 * @param shopper
	 */
	public static void shop(Scanner in, Shopper shopper)
	{
		Shop shop = new Shop();
		String continueToShop = "1";
		
		while(continueToShop.equals("1"))
		{
			String message = "Select an item: \n" + shop.toString();
			/*
			 *Call the method where the user selects a valid item to buy
			 */
			ShopItem item = selectItemToBuy(null, in, shop, message);
			/*
			 * Get quantity, purchase value, and add to shopper's total
			 */
			addItemsBoughtToShopper(shopper, shop, in, item);
			
			/*
			 * Validate to continue shopper or go to checkout
			 */
			continueToShop = getStringInput("Press 1 to shop some more, any other key to go to the line:", in);
		}
	}
	
	/**
	 * Add the value and amount of items the user selected to the shopper's info
	 * @param shopper
	 * @param shop
	 * @param in
	 * @param item
	 */
	public static void addItemsBoughtToShopper(Shopper shopper, Shop shop, Scanner in, ShopItem item)
	{
		double totalPurchaseValue;
		int quantity = getIntInput("How many would you like?", in);
		totalPurchaseValue = item.getPriceByQuantity(quantity);
		shopper.addItems(quantity);
		shopper.addPurchase(totalPurchaseValue);
		System.out.println("Item: " + item.getItemName() + " Price: $" + 
				item.getItemPrice() + " Quantity: " + quantity + " Total: $" + item.getPriceByQuantity(quantity) +
					" Shopper's total purchase value: $" + shopper.getTotalPurchase());
	}
	
	/**
	 * @return selected item
	 * @param item
	 * @param in
	 * @param shop
	 * @param message
	 */
	public static ShopItem selectItemToBuy(ShopItem item, Scanner in, Shop shop, String message)
	{
		while(item == null)
		{
			int itemIndex = getIntInput(message, in) - 1;
			item = shop.getItemByIndex(itemIndex);
			message = "Invalid selection, please reenter";
		}
		return item;
	}
	
	/**
	 * Get user's input, validate and return;
	 * @param message
	 * @param in
	 * @return user's int input
	 */
	private static int getIntInput(String message, Scanner in)
	{
		int ans = 0;
		boolean cont = false;
		do
		{
			System.out.println(message);
			message = "Invalid input; Please reenter:";
			cont = false;
			try
			{
				ans = in.nextInt();
				in.nextLine();
			}catch(InputMismatchException e)
			{
				cont = true;
				in.nextLine();
			}
			if(ans <= 0)
			{
				cont = true;
			}
		}while(cont);
		return ans;
	}
	/**
	 * Get user's string input, validate and return
	 * @param message
	 * @param in
	 * @return user's string input
	 */
	private static String getStringInput(String message, Scanner in)
	{
		String ans = "";
		boolean cont = false;
		do
		{
			System.out.println(message);
			cont = false;
			ans = in.nextLine();
			if(ans.length() == 0)
			{
				message = "Invalid input; Please reenter:";
				cont = true;
			}
		}while(cont);
		return ans;
	}
	
	/**
	 * Go through the priorityQueue, dequeue all the shoppers and print their info
	 * @param shoppers
	 */
	public static void checkoutCustomers(PriorityQueue<Shopper> shoppers)
	{
		while(!shoppers.isEmpty())
		{
			Shopper shopper = shoppers.dequeue();
			System.out.println("Checking out " + shopper.getName() + 
					" with a total of $" + shopper.getTotalPurchase() + " who was number " + 
						shopper.getLineOrder() + " to enter the line.");
		}
	}
	
	/**
	 * apply coupon to shopper's total - 15 percent off
	 * @param shopper
	 */
	public static void giveCoupon(Shopper shopper)
	{
		double totalPurchase = shopper.getTotalPurchase();
		
		System.out.println(shopper.getName() + "'s total: $" + totalPurchase);
		double discountAmount = totalPurchase * .15;
		
		totalPurchase -= discountAmount;
		
		shopper.setTotalPurchase(totalPurchase);
		System.out.println("Coupon applied! " + shopper.getName() + "'s new total: $" + shopper.getTotalPurchase());
	}
}
