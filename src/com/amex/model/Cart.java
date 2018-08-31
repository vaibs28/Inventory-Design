package com.amex.model;

import java.util.HashMap;
import java.util.Iterator;

public class Cart {

    private HashMap<Product, Integer> productMap = new HashMap<>();
    double totalAmount = 0.0;
    
    public void addItem(Product product, Integer amount) throws Exception {
        if (amount <= 0)
            throw new Exception("quantity cannot be less than 1");

        productMap.put(product, amount);
    }

    public void removeItem(Product product) throws Exception {
        if (!productMap.containsKey(product))
            throw new Exception("Product not present in the cart");

        productMap.remove(product);
    }

    public void generateInvoice() {
        totalAmount = 0.0;
        for (Product product : productMap.keySet()) {
            String name = product.getName();
            int qty = productMap.get(product);
            double price = (product.getPrice()
                .doubleValue()) * qty;
            totalAmount = totalAmount + price;
            String result = name + " " + qty + " " + String.format("%.2f",price);
            System.out.println(result);

        }
        System.out.println("Total Price:" + String.format("%.2f",totalAmount));
    }

    public void checkout() throws Exception {
        Inventory.getInstance()
            .removeItems(productMap);
        emptyCart();
    }

    public void emptyCart() {
        for (Product product : productMap.keySet()) {
            productMap.remove(product);
        }
    }

    public void view() {
        Iterator<Product> iter = productMap.keySet()
            .iterator();
        while (iter.hasNext()) {
            Product product = iter.next();
            System.out.println(product.getName() + " " + String.format("%.2f",product.getPrice()));
        }
    }
}
