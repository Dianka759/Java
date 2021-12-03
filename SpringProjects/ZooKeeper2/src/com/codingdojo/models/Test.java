package com.codingdojo.models;

public class Test {

	public static void main(String[] args) {
		Gorilla gorilla = new Gorilla(100);
		
		System.out.println("\n -------------------Gorilla------------------");
		gorilla.throwSomething();
		gorilla.throwSomething();
		gorilla.throwSomething();
		gorilla.eatBananas();
		gorilla.eatBananas();
		gorilla.climb();
		gorilla.displayEnergy();
	
		System.out.println("\n -------------------Bat------------------");
		Bat bat = new Bat(300);
		bat.attackTown();
		bat.attackTown();
		bat.attackTown();
		bat.eatHumans();
		bat.eatHumans();
		bat.fly();
		bat.fly();
		bat.displayEnergy();
	}		
}
