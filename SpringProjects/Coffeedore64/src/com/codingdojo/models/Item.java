//package com.codingdojo.models;

public class Item {
    
    // MEMBER VARIABLES
    private String name;
    private double price;
    private int index;
    
    // CONSTRUCTOR
    //   Takes a name and price as arguments 
    //   and sets them accordingly
    public Item(String name, double price){
        this.name = name;
        this.price = price;
    }
    
    // GETTERS & SETTERS  - for name and price
    
    // name
    public String getItemName() {
        return this.name;
    }
    
    public void setItemName(String name) {
        this.name = name;
    }
    
    // price
    public double getItemPrice() {
        return this.price;
    }

    public void setItemPrice(double price) {
        this.price = price;
    }
    
    // 
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
