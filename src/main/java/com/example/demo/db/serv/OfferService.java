package com.example.demo.db.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.pojo.Offer;
import com.example.demo.db.repo.OfferRepo;

@Service
public class OfferService {
	@Autowired
	OfferRepo offerRepo;

	public void saveOffer(Offer offer) {
		offerRepo.save(offer);
	}

	public Offer findById(int id) {
		return offerRepo.findById(id).get();
	}

	public void deleteOfferById(int id) {

		offerRepo.deleteById(id);
	}
}
