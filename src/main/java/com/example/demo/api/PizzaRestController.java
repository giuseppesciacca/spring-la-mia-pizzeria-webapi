package com.example.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.dto.PizzaDTO;
import com.example.demo.db.pojo.Pizza;
import com.example.demo.db.serv.PizzaService;

@RestController
@CrossOrigin
@RequestMapping("/api/pizze")
public class PizzaRestController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public ResponseEntity<List<Pizza>> getAll() {
		List<Pizza> pizze = pizzaService.findAll();

		return new ResponseEntity<List<Pizza>>(pizze, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizza(@PathVariable int id) {
		Optional<Pizza> optPizza = pizzaService.findById(id);

		if (optPizza.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Integer> save(@RequestBody PizzaDTO pizzaDto) {

		Pizza pizza = new Pizza(pizzaDto);

		pizza = pizzaService.save(pizza);

		return new ResponseEntity<>(pizza.getId(), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<Pizza> updatePizza(@PathVariable int id, @RequestBody PizzaDTO pizzaDto) {

		Optional<Pizza> optPizza = pizzaService.findById(id);

		if (optPizza.isEmpty()) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		Pizza pizza = optPizza.get();
		pizza.fillFromPizzaDto(pizzaDto);

		try {

			pizza = pizzaService.save(pizza);

			return new ResponseEntity<>(pizza, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletePizza(@PathVariable int id) {

		Optional<Pizza> optPizza = pizzaService.findById(id);

		if (optPizza.isEmpty()) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		Pizza pizza = optPizza.get();
		pizzaService.deletePizza(pizza);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
