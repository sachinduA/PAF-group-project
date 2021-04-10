package com.gadgetbadget.GadgetBadgetbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gadgetbadget.GadgetBadgetbackend.model.Researcher;
import com.gadgetbadget.GadgetBadgetbackend.repository.ResearchersRepository;

@Service
@Transactional
public class ResearcherService {

	@Autowired
	private ResearchersRepository repo;

	public List<Researcher> getAll() {
		return repo.findAll();
	}

	public void save(Researcher researcher) {
		repo.save(researcher);
	}

	public Researcher get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
