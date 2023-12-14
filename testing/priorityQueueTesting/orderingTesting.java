package shoppingHW.testing.priorityQueueTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shoppingHW.PriorityQueue;
import shoppingHW.Shopper;

class orderingTesting {
	PriorityQueue<Shopper> shoppers;
	
	@BeforeEach
	void initializeQueue()
	{
		shoppers = new PriorityQueue(6);
	}
	@Test
	void oneShopperEnqueuedIsTheSameAsTheOneDequeued() {
		Shopper newShopper = new Shopper(20, 5, "Meira");
		shoppers.enqueue(newShopper);
		assertEquals(newShopper, shoppers.dequeue());
	}
	@Test
	void twoShoppersOfSamePriorityEnqueuedMaintainsInsertionOrder() {
		Shopper newShopper1 = new Shopper(20, 5, "Meira");
		Shopper newShopper2 = new Shopper(20, 5, "Tova");
		shoppers.enqueue(newShopper1);
		shoppers.enqueue(newShopper2);
		assertEquals(newShopper1, shoppers.dequeue());
		assertEquals(newShopper2, shoppers.dequeue());
	}
	@Test
	void twoShoppersLowerPriorityInsertedFirstMaintainsPriorityOrder() {
		Shopper newShopper1 = new Shopper(20, 5, "Meira");
		Shopper newShopper2 = new Shopper(20, 8, "Tova");
		shoppers.enqueue(newShopper1);
		shoppers.enqueue(newShopper2);
		assertEquals(newShopper2, shoppers.dequeue());
		assertEquals(newShopper1, shoppers.dequeue());
	}

}
