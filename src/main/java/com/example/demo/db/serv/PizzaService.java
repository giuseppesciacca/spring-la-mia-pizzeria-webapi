package com.example.demo.db.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.pojo.Pizza;
import com.example.demo.db.repo.PizzaRepo;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;

	public void save(Pizza pizza) {
		pizzaRepo.save(pizza);
	}

	public List<Pizza> findAll() {

		return pizzaRepo.findAll();
	}

	public Pizza findById(int id) {
		return pizzaRepo.findById(id).get();
	}

	public List<Pizza> findByNameContaining(String name) {

		return pizzaRepo.findByNameContaining(name);
	}

	public void deletePizzaById(int id) {

		pizzaRepo.deleteById(id);
	}
}
