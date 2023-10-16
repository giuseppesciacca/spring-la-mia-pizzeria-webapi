package com.example.demo.db.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.db.auth.pojo.User;
import com.example.demo.db.auth.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	public List<User> findAll() {

		return userRepo.findAll();
	}

	public User findById(int id) {

		return userRepo.findById(id).get();
	}

	public void save(User user) {

		userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username);
	}
}
