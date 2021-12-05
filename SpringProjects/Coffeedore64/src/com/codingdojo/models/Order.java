//package com.codingdojo.models;

import java.util.ArrayList;

// Here we're creating a new data type called Order
public class Order {
    
    // MEMBER VARIABLES
    private String name; // default value of null
    private boolean ready; // default value false
    private ArrayList<Item> items; // defaults to null
    
    // CONSTRUCTOR
    // No arguments, sets name to "Guest", initializes items as an empty list.
    public Order() {
        this.name = "Guest";
        this.items = new ArrayList<>();
    }

    // OVERLOADED CONSTRUCTOR
    // Takes a name as an argument, sets name to this custom name.
    // Initializes items as an empty list.
    public Order(String name){
        this.name = name;
        this.items = new ArrayList<>();
    }

    // GETTERS & SETTERS
    
    // name
    public String getName() {
        return this.name;
    }

    public void setName(String setName) {
        this.name = setName;
    }
    
    // items
    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<Item> setItems) {
        this.items = setItems;
    }
    
    // ready
    public boolean getReady(){
        return this.ready;
    }
    public void setReady(boolean ready){
        this.ready = ready;
    }
    // METHODS

    //that takes an Item object as an argument and adds the item to the order's items array. 
    //No need to return anything.
    public void addItem(Item item) {
        items.add(item);
    }

    //Create a method called getStatusMessage that returns a String message. 
    //If the order is ready, return "Your order is ready.", 
    //if not, return "Thank you for waiting. Your order will be ready soon."
    public String getStatusMessage() {
        if (ready){
            // return "\nOrder status for" + this.getName + ":" + ready + " = Your order is ready";
            return String.format("\nOrder status for %s = %s: Your order is ready", this.getName(), ready);
        } else {
            // return "\nOrder status:" + ready + " = Thank you for waiting. Your order will be ready soon.";
            return String.format("\nOrder status for %s = %s: Thank you for waiting. Your order will be ready soon.", this.getName(), ready);
        }
    }

    //create a method called getOrderTotal that sums together each of the item's prices, and returns the total amount. 
    //Note: This time, you do not need to pass in a list of prices, 
    //because each item object in this order's items array has its own price, that you can access using a getter!
    public double getOrderTotal() {
        double sum = 0.0;
        for (Item i : this.items ) {
            sum += i.getItemPrice();
        }
        return sum;
    }

    //create a method called display that prints out the order information like so:
    //Customer Name: Cindhuri
    //drip coffee - $1.50
    //capuccino - $3.50
    //Total: $5.00
    public void display() {
        System.out.println("\nCustomer Name: " + this.getName());
        for (Item i : this.items) {
            System.out.println(i.getItemName() + " - $" + String.format("%.2f", i.getItemPrice()));
        }
        System.out.println("Total: $" + String.format("%.2f", this.getOrderTotal()) + "\n");
    }
}
