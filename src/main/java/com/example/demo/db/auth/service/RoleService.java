package com.example.demo.db.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.auth.pojo.Role;
import com.example.demo.db.auth.repo.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;

	public List<Role> findAll() {

		return roleRepo.findAll();
	}

	public Role findById(int id) {

		return roleRepo.findById(id).get();
	}

	public void save(Role role) {

		roleRepo.save(role);
	}

}
