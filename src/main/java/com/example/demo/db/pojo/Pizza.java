package com.example.demo.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	@Length(min = 1, max = 128)
	private String name;

	@Column(nullable = false)
	@Length(min = 10, max = 255)
	private String description;

	@Column(nullable = false)
	@Length(min = 10, max = 255)
	private String photo_url;

	@Column(nullable = false)
	@DecimalMin("0.01")
	private float price;

	@OneToMany(mappedBy = "pizza")
	private List<Offer> offers;

	@ManyToMany()
	@JoinTable(name = "pizza_ingredients", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<Ingredient> ingredients;

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Pizza() {
	}

	public Pizza(String name, String description, String photo_url, float price, Ingredient... ingredients) {
		this.name = name;
		this.description = description;
		this.photo_url = photo_url;
		this.price = price;
		setIngredients(Arrays.asList(ingredients));
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

	public void deleteIngredient(Ingredient ingredient) {

		getIngredients().remove(ingredient);
	}

	@Override
	public String toString() {

		return "[" + getId() + "] Pizza:\n" + "name: " + getName() + "\n" + "url_photo: " + getPhoto_url() + "\n"
				+ "price: " + getPrice() + "\n";
	}
}
