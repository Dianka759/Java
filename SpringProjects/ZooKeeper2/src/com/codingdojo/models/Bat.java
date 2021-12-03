package com.codingdojo.models;

public class Bat extends Mammal{
    public Bat(int energyLevel){
        super(energyLevel);
    }
    public int fly(){
        System.out.println("I will be baacckkk, screeechhhh!");
        return energyLevel -= 50;
    }
    public int eatHumans(){
        System.out.println("Screeeechhh screeeeeechh! *chomp*");
        return energyLevel += 25;
    }
    public int attackTown(){
        System.out.println("Move aside Gorilla, it's my turn to destroy humanity! ");
        return energyLevel -= 100;
    }
}
