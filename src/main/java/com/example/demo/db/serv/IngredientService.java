package com.example.demo.db.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.pojo.Ingredient;
import com.example.demo.db.repo.IngredientRepo;
import com.example.demo.db.repo.PizzaRepo;

@Service
public class IngredientService {

	@Autowired
	public IngredientRepo ingredientRepo;
	@Autowired
	public PizzaRepo pizzaRepo;

	public List<Ingredient> findAll() {

		return ingredientRepo.findAll();
	}

	public void save(Ingredient ingredient) {

		ingredientRepo.save(ingredient);
	}

	public Ingredient findById(int id) {

		return ingredientRepo.findById(id).get();
	}

	public void deleteIngredient(Ingredient ingredient) {

		ingredientRepo.delete(ingredient);
	}
}
