package com.example.demo.db.pojo;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private LocalDate startDate;
	@Column(nullable = false)
	private LocalDate endDate;
	@Column(nullable = false, unique = true)
	@Length(min = 1, max = 128)
	private String title;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "pizza_id", nullable = false)
	private Pizza pizza;

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Offer() {
	}

	public Offer(LocalDate startDate, LocalDate endDate, String title, Pizza pizza) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
		setPizza(pizza);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {

		return "[" + getId() + "] Offer:\n" + "title: " + getTitle() + "\n" + "start date: " + getStartDate() + "\n"
				+ "end date: " + getEndDate() + "\n";
	}
}
