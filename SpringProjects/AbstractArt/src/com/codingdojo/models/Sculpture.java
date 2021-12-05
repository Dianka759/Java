package com.codingdojo.models;

public class Sculpture extends Art{
	private String material;

	public String getMaterial() {
		return material;
	}

	public Sculpture(String title, String author, String description, String material) {
		super(title, author, description);
		this.material = material;
	}
	
	@Override
	public void viewArt() {
		System.out.println("---------------Sculpture---------------");
		System.out.println("Title: " + getTitle());
		System.out.println("Author: " + getAuthor());
		System.out.println("Description: " + getDescription());
		System.out.println("paintType: " + material);	
	}

}
