package com.shop.coffeeshop;

import com.shop.coffeeshop.model.BaconRoll;
import com.shop.coffeeshop.model.Coffee;
import com.shop.coffeeshop.model.Extra;
import com.shop.coffeeshop.model.OrangeJuice;
import com.shop.coffeeshop.service.ReceiptServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CoffeeShopApplicationTests {

	@Test
	void testReceiptGeneration() {
		ReceiptServiceImpl receiptService = new ReceiptServiceImpl();
		receiptService.addProduct(new Coffee("large"));
		receiptService.addProduct(new Extra("Extra Milk", 0.30));
		receiptService.addProduct(new Coffee("small"));
		receiptService.addProduct(new Extra("Special Roast", 0.90));
		receiptService.addProduct(new BaconRoll());
		receiptService.addProduct(new OrangeJuice());

		receiptService.applyDiscounts();
		String expectedReceipt = "Receipt:\n" +
				"Coffee (large): 3.50 CHF\n" +
				"Coffee (small): 2.50 CHF\n" +
				"Special Roast: 0.90 CHF\n" +
				"Bacon Roll: 4.50 CHF\n" +
				"Orange Juice (0.25l): 3.95 CHF\n" +
				"Total: 15.35 CHF\n";
		assertEquals(expectedReceipt, receiptService.generateReceipt());
	}

}
