package com.shop.coffeeshop.model;

public class Coffee extends Product {
    public Coffee(String size) {
        super("Coffee (" + size + ")", size.equals("small") ? 2.50 : size.equals("medium") ? 3.00 : 3.50);
    }
}
