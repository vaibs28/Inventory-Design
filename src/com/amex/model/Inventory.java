package com.amex.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Inventory {

    private static Inventory instance = null;
    private static HashMap<Product, Integer> inventoryMap = new HashMap<>();
    

    private Inventory() {

    }

    public static Inventory getInstance() {
        if (instance == null) {
            return new Inventory();
        }
        return instance;
    }

    public void addItems(Map<Product, Integer> items) throws Exception {
        for (Product product : items.keySet()) {
            if (!(items.get(product) >= 0)) {
                throw new Exception("Quantity should be greater than 0");
            }

            inventoryMap.put(product, items.get(product));
        }
    }

    public void removeItems(Map<Product, Integer> item) throws Exception {
        for (Product product : item.keySet()) {
            if (item.get(product) > inventoryMap.get(product))
                throw new Exception("Quantity greater than in inventory");
        }
    }

    public int getAvailableAmout(Product product) {
        return (inventoryMap.get(product));
    }

    public double getPrice(String productName) throws Exception {
        
        Iterator<Product> iter = inventoryMap.keySet()
            .iterator();
        while (iter.hasNext()) {
            Product product = iter.next();
            if (productName.equals(product.getName()))
                return product.getPrice();
        }
        throw new Exception("product not in inventory");
    }

    public static HashMap<Product, Integer> getInventoryMap() {
        return inventoryMap;
    }

    public static void setInventoryMap(HashMap<Product, Integer> inventoryMap) {
        Inventory.inventoryMap = inventoryMap;
    }

    public void view() {
        int num = inventoryMap.size();
        Iterator<Product> iter = inventoryMap.keySet()
            .iterator();
        while (iter.hasNext()) {
            Product product = iter.next();
            System.out.println(product.getName() + " " + inventoryMap.get(product) + " " + String.format("%.2f",product.getPrice()));
        }

    }

}
