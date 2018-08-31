package com.amex.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingCart {

    static Scanner sc = new Scanner(System.in);
    static String name;
    static int qty;
    static double price;
    static Inventory inventory = Inventory.getInstance();
    static Cart cart = new Cart();
    static Product product = new Product();

    public static void main(String[] args) {
        

        addToInventory();

        while (true) {
            System.out.println("Select choice:" + "\n" + "1.Add to cart" + "\n" + "2.Remove from cart" + "\n" + "3.Generate Invoice" + "\n" + "4.View Cart" + "\n" + "5.View Inventory");
            int choice = sc.nextInt();
            switch (choice) {
            case 1:
                addToCart();
                break;
            case 2:
                removeFromCart();
                break;
            case 3:
                generateInvoice();
                break;
            case 4:
                viewCart();
                break;
            case 5:
                viewInventory();
                break;
            default:
                System.exit(0);

            }
        }

    }

    private static void generateInvoice() {
        cart.generateInvoice();
    }

    private static void removeFromCart() {

        System.out.println("Enter the product name");
        name = sc.next();
        product.setName(name);
        try {
            product.setPrice(inventory.getPrice(name));
        } catch (Exception e1) {
            
            e1.printStackTrace();
        }
        try {
            cart.removeItem(product);
            System.out.println("product removed");
        } catch (Exception e) {
         
            e.printStackTrace();
        }
    }

    public static void addToInventory() {
        System.out.println("Enter the number of products to be added in inventory");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter product name,quantity and price");
            name = sc.next();
            qty = sc.nextInt();
            price = sc.nextDouble();

            Product product = new Product(name, price);
            Integer amount = new Integer(qty);
            Map<Product, Integer> hm = new HashMap<>();
            hm.put(product, amount);
            try {
                inventory.addItems(hm);
                System.out.println("added to inventory");
            } catch (Exception e) {

                e.printStackTrace();
            }

        }

    }

    public static void addToCart() {
        System.out.println("Enter the number of products to add to cart");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter product name and amount");
            name = sc.next();
            qty = sc.nextInt();
            Product product = new Product();
            product.setName(name);
            try {
                product.setPrice(inventory.getPrice(name));
            } catch (Exception e1) {
              
                e1.printStackTrace();
            }
            try {
                cart.addItem(product, qty);
                System.out.println("Added to the cart");
            } catch (Exception e) {
                
                e.printStackTrace();
            }

        }
    }

    private static void viewInventory() {
        System.out.println("inventory view");
        inventory.view();
    }

    private static void viewCart() {
        System.out.println("Cart view");
        cart.view();
    }
}
