package com.codingdojo.models;

public class Painting extends Art {
	private String paintingType;
	
	public Painting(String title, String author, String description, String paintingType) {
		super(title, author, description);
		this.paintingType = paintingType;
	}

	public String getPaintingType() {
		return paintingType;
	}

	public void setPaintingType(String paintingType) {
		this.paintingType = paintingType;
	}
	
	@Override
	public void viewArt() {
		System.out.println("---------------Painting----------------");
		System.out.println("Title: " + getTitle());
		System.out.println("Author: " + getAuthor());
		System.out.println("Description: " + getDescription());
		System.out.println("paintType: " + paintingType);	
	}
}
