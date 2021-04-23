package com.gadgetbadget.GadgetBadgetbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gadgetbadget.GadgetBadgetbackend.model.Research;
import com.gadgetbadget.GadgetBadgetbackend.repository.ResearchRepository;

@Service
@Transactional
public class ResearchService {
	@Autowired
	ResearchRepository repo;

	public List<Research> getAll() {
		return repo.findAll();
	}

	public void save(Research research) {
		repo.save(research);
	}

	public Research get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
