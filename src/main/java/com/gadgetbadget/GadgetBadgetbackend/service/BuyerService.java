package com.gadgetbadget.GadgetBadgetbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gadgetbadget.GadgetBadgetbackend.model.Buyer;
import com.gadgetbadget.GadgetBadgetbackend.repository.BuyerRepository;

@Service
@Transactional
public class BuyerService {
	
	@Autowired
	BuyerRepository repo;

	public List<Buyer> getAll() {
		return repo.findAll();
	}

	public void save(Buyer buyer) {
		repo.save(buyer);
	}

	public Buyer get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
