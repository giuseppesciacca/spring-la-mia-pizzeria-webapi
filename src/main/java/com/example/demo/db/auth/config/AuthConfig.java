package com.example.demo.db.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.db.auth.service.UserService;

@Configuration
public class AuthConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/api/**").permitAll()
				.requestMatchers("/create").hasAuthority("ADMIN")
				.requestMatchers("/update/**").hasAuthority("ADMIN")
				.requestMatchers("/delete/**").hasAuthority("ADMIN")
				.requestMatchers("/create_ingredient").hasAuthority("ADMIN")
				.requestMatchers("/delete_ingredient/**").hasAuthority("ADMIN")
				.requestMatchers("/create_offer/**").hasAuthority("ADMIN")
				.requestMatchers("/edit_offer/**").hasAuthority("ADMIN")
				.requestMatchers("/**").permitAll().and().formLogin().defaultSuccessUrl("/")
				.and().logout();

		return http.build();
	}

	@Bean
	UserService userDetailsService() {
		return new UserService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
}
