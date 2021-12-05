package com.codingdojo.models;

import java.util.ArrayList;
import java.util.Collections;

public class Museum {

	public static void main(String[] args) {
		ArrayList<Art> museum = new ArrayList<Art> ();
		
		museum.add(new Painting("Some Art", "Some Artist", "Some fancy description", "markers"));
		museum.add(new Painting("The Presence of Memory", "Salvador Dali", "time is but a concept", "Oil paint, Bronzer"));
		museum.add(new Painting("Girl with Death Mask (She Plays Alone)", "Frida Kahlo", "a small child holding a yellow flower, wearing a skeleton mask with a jaguar mask to her right.", "oil"));
		museum.add(new Sculpture("Forever Bicycles", "Ai Weiwei", "childhood and freedom", "bicycles"));
		museum.add(new Sculpture("Hachiko", "Takeshi Ando and Teru Ando", "Hachiko was a Japanese Akita dog remembered for his remarkable loyalty to his owner.", "Bronze"));
		
		//Randomly rearranges the specified list using a default source of randomness.
		Collections.shuffle(museum);

		for(Art art : museum) {
			art.viewArt();
		}

	}

}