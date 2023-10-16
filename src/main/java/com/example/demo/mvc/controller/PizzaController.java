package com.example.demo.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.db.pojo.Ingredient;
import com.example.demo.db.pojo.Pizza;
import com.example.demo.db.serv.IngredientService;
import com.example.demo.db.serv.PizzaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private IngredientService ingredientService;

	@GetMapping
	public String getIndex(Model model, @RequestParam(required = false) String name) {

		List<Pizza> pizze = name == null ? pizzaService.findAll() : pizzaService.findByNameContaining(name);

		model.addAttribute("pizze", pizze);
		model.addAttribute("name", name);

		return "index";
	}

	@GetMapping("/{id}")
	public String showSinglePizza(@PathVariable int id, Model model) {

		Pizza pizza = pizzaService.findById(id).get();

		model.addAttribute("pizza", pizza);

		return "show-pizza";
	}

	@GetMapping("/create")
	public String createPizza(Model model) {

		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("pizza", new Pizza());

		return "create-pizza";
	}

	@PostMapping("/create")
	public String storePizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
			Model model) {

		return savePizza(formPizza, bindingResult, "create-pizza", "redirect:/");
	}

	@GetMapping("/update/{id}")
	public String getUpdatePizza(@PathVariable int id, Model model) {

		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);

		Pizza pizza = pizzaService.findById(id).get();
		model.addAttribute("pizza", pizza);

		return "update-pizza";
	}

	@PostMapping("/update/{id}")
	public String updatePizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
			Model model) {

		return savePizza(formPizza, bindingResult, "update-pizza", "redirect:/");
	}

	@PostMapping("/delete/{pizzaId}")
	public String deletePizza(@PathVariable("pizzaId") int id) {

		pizzaService.deletePizzaById(id);

		return "redirect:/";
	}

	/**
	 * function for save entity on db.
	 * 
	 * @param formPizza
	 * @param bindingResult
	 * @param route
	 * @param redirect
	 * @return
	 */
	private String savePizza(Pizza formPizza, BindingResult bindingResult, String route, String redirect) {
		if (bindingResult.hasErrors()) {
			System.out.println("Error:");
			bindingResult.getAllErrors().forEach(System.out::println);
			return route;
		}

		try {
			pizzaService.save(formPizza);
		} catch (Exception e) {

			// CONSTRAIN VALIDATION (unique)
			System.out.println("Errore constrain: " + e.getClass().getSimpleName());

			return route;
		}
		return redirect;
	}
}
