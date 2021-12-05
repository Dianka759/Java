//package com.codingdojo.models;

import java.util.*;

public class CoffeeKiosk {
    private ArrayList<Item> menu = new ArrayList<Item>();
    private ArrayList<Order> orders = new ArrayList<Order>();
	
	public CoffeeKiosk() {
		menu = new ArrayList<Item>();
		orders = new ArrayList<Order>();		
	}
	
	public void addMenuItem(String itemName, double itemPrice) {
		Item newItem = new Item(itemName, itemPrice);
		menu.add(newItem);
		newItem.setIndex(menu.size() - 1);	
	}
	
	public void displayMenu() {
	     for(int i = 0; i < menu.size(); i++) {
	            Item item = menu.get(i);
                System.out.println("----------------------------");
	        System.out.println(item.getIndex() + " " + item.getItemName() + " -- $" + item.getItemPrice());
                System.out.println("----------------------------");
	        }
	}
	
	 public void newOrder() {
	        
	    	// Shows the user a message prompt and then sets their input to a variable, name
	        System.out.println("\nPlease enter customer name for new order:");
	        String name = System.console().readLine();
	    
	        // Create a new order with the given input string
	        // Show the user the menu, so they can choose items to add.
	        Order newOrder = new Order(name);
	        displayMenu();
	        
	    	// Prompts the user to enter an item number
	        System.out.println("\nPlease enter a menu item index or q to quit:");
	        String itemNumber = System.console().readLine();
	        
	        // Write a while loop to collect all user's order items
            // Get the item object from the menu, and add the item to the order
            // Ask them to enter a new item index or q again, and take their input
	        while(!itemNumber.equals("q")) {
	            int index = Integer.parseInt(itemNumber);
	            // Get item object from the menu and then add the item to the order
	            // Catch if the index is out of range and prompt to try again
	            try {
	            	Item item = menu.get(index);
	            	System.out.println(item.getItemName());
		            newOrder.addItem(item);
	            } catch ( IndexOutOfBoundsException e ) {
	            	System.out.println("----------------------------------");
	                System.out.println("Item doesn't exist. Invalid Index.");
	                System.out.println("----------------------------------");
	            }            
	            System.out.println("\nPlease enter a menu item index or q to quit:");
	            itemNumber = System.console().readLine();
	        }
	        // After you have collected their order, print the order details 
	    	// as the example below. You may use the order's display method.
	        newOrder.display();
	        this.orders.add(newOrder);
	        newOrder.getOrderTotal();
	    }
	 
	 //Create a method that lets an admin add menu items manually, 
	 //using what you now know about getting user input.
	 public void addMenuItemByInput(){
	    String addNewOrder = "y";
		    //while user wants to input more orders, do the following;
		    while(addNewOrder.equals("y")) {
		      System.out.println("Enter the name of the new product:");
		      String itemName = System.console().readLine();
		      //if the input is empty, tell them to input valid name
		      while(itemName.isEmpty()){
			System.out.println("Please enter a valid name:");
			itemName = System.console().readLine();
		      } 
		      System.out.println("Enter the price of the new product:");
		      String itemPrice = System.console().readLine();
		      //if the input is empty, tell them to input a valid price
		      while(itemPrice.isEmpty()){
			System.out.println("Please enter a valid price:");
			itemPrice = System.console().readLine();
		      }
		      //The console input will be of type String, so have to convert to double
		      double price =  Double.parseDouble(itemPrice);

		      //adding the inputed name and price into the menu.
		      addMenuItem(itemName, price);
		      System.out.println("Do you want to add another product? y/n");
		      addNewOrder = System.console().readLine().toLowerCase(); 
		      //turn to lower case in case the user inputs "Y" or "N"
			}
	}

}
