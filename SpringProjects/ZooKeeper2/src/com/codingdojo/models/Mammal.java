package com.codingdojo.models;

public class Mammal {
	public int energyLevel = 100;
	
	public Mammal(int energyLevel) {
		this.energyLevel = energyLevel;
	}
	
	public int displayEnergy() {
		System.out.println("Current energy level: " + energyLevel);
		return energyLevel;
	}
}
