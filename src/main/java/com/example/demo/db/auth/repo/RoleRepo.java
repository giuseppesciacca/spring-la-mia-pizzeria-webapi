package com.example.demo.db.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.auth.pojo.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}