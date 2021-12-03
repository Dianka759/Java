package com.codingdojo.models;

public class Gorilla extends Mammal {

	public Gorilla(int energyLevel) {
		super(energyLevel);	
	}
	
	public int throwSomething(){
        System.out.println("The gorilla threw something at ya, ouchie.");
        return energyLevel -= 5;	
	}
	public int eatBananas(){
	     System.out.println("The gorilla is eating bananas (my favorite!)");
	     return energyLevel += 10;

	}
	public int climb(){
	     System.out.println("The gorilla climbed a skyscrapper.");
	     return energyLevel -= 10;
	}
}
