package com.vm.training.moviecatlog;

public class CatlogItem {


	private String name;
	private String description;
	private int rating;

	public CatlogItem(String name, String description, int rating) {
		super();
		this.name = name;
		this.description = description;
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getMovieId() {
		// TODO Auto-generated method stub
		return null;
	}

}
