package com.example.demo.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.db.pojo.Offer;
import com.example.demo.db.pojo.Pizza;
import com.example.demo.db.serv.OfferService;
import com.example.demo.db.serv.PizzaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class OfferController {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private OfferService offerService;

	@GetMapping("/create_offer/{id}")
	public String getCreateOffer(@PathVariable int id, Model model) {

		Pizza pizza = pizzaService.findById(id).get();
		Offer offer = new Offer();

		model.addAttribute("pizza", pizza);
		model.addAttribute("offer", offer);

		return "create_offer";
	}

	@PostMapping("/create_offer/{pizza_id}")
	public String storeOffer(@Valid @ModelAttribute Offer formOffer, BindingResult bindingResult,
			@PathVariable("pizza_id") int id, Model model) {

		Pizza pizza = pizzaService.findById(id).get();

		return saveOffer(formOffer, pizza, id);
	}

	@GetMapping("/edit_offer/{id}")
	public String getUpdateOffer(@PathVariable int id, Model model) {

		Offer offer = offerService.findById(id);

		model.addAttribute("offer", offer);

		return "edit_offer";
	}

	@PostMapping("/edit_offer/{offer_id}")
	public String updateOffer(@Valid @ModelAttribute Offer formOffer, BindingResult bindingResult, Model model,
			@PathVariable("offer_id") int id) {

		Pizza pizza = offerService.findById(id).getPizza();

		int pizzaId = pizza.getId();

		return saveOffer(formOffer, pizza, pizzaId);
	}

	/**
	 * 
	 * @param formOffer
	 * @param pizza
	 * @param pizzaId
	 * @param route
	 * @return
	 */
	public String saveOffer(Offer formOffer, Pizza pizza, int pizzaId) {
		formOffer.setPizza(pizza);

		offerService.saveOffer(formOffer);
		return "redirect:/" + pizzaId;
	}
}
