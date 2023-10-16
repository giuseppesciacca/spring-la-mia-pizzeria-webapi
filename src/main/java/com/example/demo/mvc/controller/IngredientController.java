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
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/show_all_ingredients")
	public String getAllIngredients(Model model, @RequestParam(required = false) String name) {

		List<Ingredient> ingredients = ingredientService.findAll();

		model.addAttribute("ingredients", ingredients);

		return "show_all_ingredients";
	}

	@GetMapping("/create_ingredient")
	public String getCreateIngredient(Model model) {

		model.addAttribute("ingredient", new Ingredient());

		return "create_ingredient";
	}

	@PostMapping("/create_ingredient")
	public String storeIngredient(@Valid @ModelAttribute("ingredient") Ingredient formIngredient,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			System.out.println("Error:");
			bindingResult.getAllErrors().forEach(System.out::println);
			return "/create_ingredient";
		}

		ingredientService.save(formIngredient);

		return "redirect:/show_all_ingredients";
	}

	@PostMapping("/delete_ingredient/{ingredient_id}")
	public String deletePizza(@PathVariable("ingredient_id") int id) {

		Ingredient ingredient = ingredientService.findById(id);
		for (Pizza p : ingredient.getPizze()) {
			p.deleteIngredient(ingredient);
			pizzaService.save(p);
		}

		ingredientService.deleteIngredient(ingredient);

		return "redirect:/show_all_ingredients";
	}

}
