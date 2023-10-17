package com.example.demo.api.dto;

public class PizzaDTO {

	private int id;
	private String name;
	private String description;
	private String photo_url;
	private float price;

	public PizzaDTO() {
	}

	public PizzaDTO(String name, String description, String photo_url, float price) {
		this.name = name;
		this.description = description;
		this.photo_url = photo_url;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {

		return "id: " + getId() + "\nname: " + getName() + "\nphoto-url: " + getPhoto_url() + "\nrprice: " + getPrice()
				+ "\ndescription: " + getDescription();
	}
}