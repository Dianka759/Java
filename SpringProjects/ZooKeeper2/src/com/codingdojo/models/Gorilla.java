package com.codingdojo.models;

public class Gorilla extends Mammal {

	public Gorilla(int energyLevel) {
		super(energyLevel);	
	}
	
	public int throwSomething(){
        System.out.println("Ooga booga I throw the popo.");
        return energyLevel -= 5;	
	}
	public int eatBananas(){
	     System.out.println("Bananas good, tummy happy.");
	     return energyLevel += 10;

	}
	public int climb(){
	     System.out.println("Wowie it's so high up here.");
	     return energyLevel -= 10;
	}
}
