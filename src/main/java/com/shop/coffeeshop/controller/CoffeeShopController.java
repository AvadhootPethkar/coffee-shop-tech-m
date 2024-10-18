package com.shop.coffeeshop.controller;

import com.shop.coffeeshop.model.BaconRoll;
import com.shop.coffeeshop.model.Coffee;
import com.shop.coffeeshop.model.Extra;
import com.shop.coffeeshop.model.OrangeJuice;
import com.shop.coffeeshop.service.ReceiptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffee-shop")
public class CoffeeShopController {

    @Autowired
    ReceiptServiceImpl receiptService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getProduct(@PathVariable("id") Long id) {
        receiptService.addProduct(new Coffee("large"));
        receiptService.addProduct(new Extra("Extra Milk", 0.30));
        receiptService.addProduct(new Coffee("small"));
        receiptService.addProduct(new Extra("Special Roast", 0.90));
        receiptService.addProduct(new BaconRoll());
        receiptService.addProduct(new OrangeJuice());

        receiptService.applyDiscounts();
        String receipt = receiptService.generateReceipt();
        System.out.println(receipt);
        return ResponseEntity.ok(receipt);
    }
}
