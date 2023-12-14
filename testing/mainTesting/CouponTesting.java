package shoppingHW.testing.mainTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import shoppingHW.Shopper;

class CouponTesting{
	Shopper shopper;
	@BeforeEach
	void initializeShopper() {
		shopper = new Shopper(5, 500, "Meira");
	}
	@Test
	void applyCouponTo500ChangesPurchaseValueTo425() {
		assertEquals(shopper.getTotalPurchase(), 500);
		shopper.giveCoupon();
		assertEquals(shopper.getTotalPurchase(), 425);
	}
	@Test
	void applyCouponTo0KeepsValueAt0() {
		shopper.setTotalPurchase(0);
		assertEquals(shopper.getTotalPurchase(), 0);
		shopper.giveCoupon();
		assertEquals(shopper.getTotalPurchase(), 0);
	}
}
