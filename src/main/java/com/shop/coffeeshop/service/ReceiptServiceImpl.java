package com.shop.coffeeshop.service;

import com.shop.coffeeshop.model.BaconRoll;
import com.shop.coffeeshop.model.Coffee;
import com.shop.coffeeshop.model.Extra;
import com.shop.coffeeshop.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptServiceImpl {

    private final List<Product> products = new ArrayList<>();
    private int beverageCount = 0;

    public void addProduct(Product product) {
        products.add(product);
        if (product instanceof Coffee) {
            beverageCount++;
        }
    }

    public void applyDiscounts() {
        // Every 5th beverage is free
        if (beverageCount >= 5) {
            products.remove(products.size() - 1); // Remove the last coffee as it's free
        }

        // If there is a snack, one extra is free
        boolean hasSnack = products.stream().anyMatch(p -> p instanceof BaconRoll);
        if (hasSnack) {
            for (Product product : products) {
                if (product instanceof Extra) {
                    products.remove(product);
                    break; // Only one extra is free
                }
            }
        }
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder("Receipt:\n");
        double total = 0.0;

        for (Product product : products) {
            receipt.append(product.getName()).append(": ").append(String.format("%.2f", product.getPrice())).append(" CHF\n");
            total += product.getPrice();
        }

        receipt.append("Total: ").append(String.format("%.2f", total)).append(" CHF\n");
        return receipt.toString();
    }
}
