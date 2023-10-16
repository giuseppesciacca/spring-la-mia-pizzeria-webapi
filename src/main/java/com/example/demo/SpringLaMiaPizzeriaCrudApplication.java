package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.db.auth.pojo.Role;
import com.example.demo.db.auth.pojo.User;
import com.example.demo.db.auth.service.RoleService;
import com.example.demo.db.auth.service.UserService;
import com.example.demo.db.pojo.Ingredient;
import com.example.demo.db.pojo.Offer;
import com.example.demo.db.pojo.Pizza;
import com.example.demo.db.serv.IngredientService;
import com.example.demo.db.serv.OfferService;
import com.example.demo.db.serv.PizzaService;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OfferService offerService;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Ingredient i1 = new Ingredient("pomodoro");
		Ingredient i2 = new Ingredient("mozzarella");
		Ingredient i3 = new Ingredient("salame piccante");
		Ingredient i4 = new Ingredient("pecorino");
		Ingredient i5 = new Ingredient("origano");
		Ingredient i6 = new Ingredient("prezzemolo");
		Ingredient i7 = new Ingredient("aglio");
		Ingredient i8 = new Ingredient("acciughe");

		ingredientService.save(i1);
		ingredientService.save(i2);
		ingredientService.save(i3);
		ingredientService.save(i4);
		ingredientService.save(i5);
		ingredientService.save(i6);
		ingredientService.save(i7);
		ingredientService.save(i8);

		Pizza p1 = new Pizza("Margherita", "Pizza pomodoro e mozzarella",
				"https://images.ctfassets.net/nw5k25xfqsik/64VwvKFqxMWQORE10Tn8pY/200c0538099dc4d1cf62fd07ce59c2af/20220211142754-margherita-9920.jpg?w=200",
				6.99f, i1, i2);
		Pizza p2 = new Pizza("Rianata", "Pizza pomodoro, pecorino, prezzemolo, aglio, acciughe",
				"https://www.ricettedisicilia.site/wp-content/uploads/2022/03/Rianata-trapanese.jpg", 7.99f, i1, i4, i6,
				i7, i8);
		Pizza p3 = new Pizza("Diavola", "Pizza pomodoro, mozzarella, salame piccante, origano",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzYuT6BjO1hk3rDqnH2V_Xy6xKhDNB07ns4NF5BgH0vW9LzHyr4s7DQLOPuxLihaMFKtI&usqp=CAU",
				8.99f, i1, i2, i3, i5);

		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);

		Offer o1 = new Offer(LocalDate.now(), LocalDate.parse("2023-01-15"), "Offerta 1", p1);
		Offer o2 = new Offer(LocalDate.now(), LocalDate.parse("2023-01-15"), "Offerta 1 bis", p1);
		Offer o3 = new Offer(LocalDate.now(), LocalDate.parse("2023-01-15"), "Offerta 2", p2);

		offerService.saveOffer(o1);
		offerService.saveOffer(o2);
		offerService.saveOffer(o3);

		Role admin = new Role("ADMIN");
		Role user = new Role("USER");

		roleService.save(admin);
		roleService.save(user);

		final String pwsAdmin = new BCryptPasswordEncoder().encode("password");
		final String pwsUser = new BCryptPasswordEncoder().encode("password");

		User peppeAdmin = new User("peppeAdmin", pwsAdmin, admin, user);
		User peppeUser = new User("peppeUser", pwsUser, user);

		userService.save(peppeAdmin);
		userService.save(peppeUser);

		System.out.println("Insert OK!");
	}
}
