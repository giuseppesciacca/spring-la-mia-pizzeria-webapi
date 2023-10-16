package com.example.demo.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.pojo.Offer;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Integer> {

}