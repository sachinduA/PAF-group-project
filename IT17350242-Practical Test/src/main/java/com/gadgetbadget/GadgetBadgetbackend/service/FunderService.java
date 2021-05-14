package com.gadgetbadget.GadgetBadgetbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gadgetbadget.GadgetBadgetbackend.model.Funder;
import com.gadgetbadget.GadgetBadgetbackend.repository.FundersRepository;

@Service
@Transactional
public class FunderService {

	@Autowired
	FundersRepository repo;

	public List<Funder> getAll() {
		return repo.findAll();
	}

	public void save(Funder funder) {
		repo.save(funder);
	}

	public Funder get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
