package com.example.demo.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.pojo.Pizza;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {
	// contains all methods for CRUD operation for Pizza

	public List<Pizza> findByNameContaining(String name);
}