package shoppingHW.testing.priorityQueueTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shoppingHW.PriorityQueue;
import shoppingHW.Shopper;

class SizeTesting {
	PriorityQueue<Shopper> shoppers;
	
	@BeforeEach
	void initializeShoppers()
	{
		shoppers = new PriorityQueue(5);
	}
	@Test
	void emptyArrayIsSize0() {
		assertEquals(shoppers.size(), 0);
	}
	@Test
	void arrayWith1EnqueuedIsSize1()
	{
		shoppers.enqueue(new Shopper(0, 0, "Meira", 0));
		assertEquals(shoppers.size(), 1);
	}
	
	@Test
	void arrayWith5EnqueuedIsSize5()
	{
		for(int i=0;i<5;i++)
		{
			shoppers.enqueue(new Shopper(0, 0, "Meira", 0));
		}
		assertEquals(shoppers.size(), 5);
	}
	
	@Test
	void arrayWith5EnqueuedAnd1DequeuedIsSize4()
	{
		for(int i=0;i<5;i++)
		{
			shoppers.enqueue(new Shopper(0, 0, "Meira", 0));
		}
		shoppers.dequeue();
		assertEquals(shoppers.size(), 4);
	}

}
