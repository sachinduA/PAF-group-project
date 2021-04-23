package com.gadgetbadget.GadgetBadgetbackend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gadgetbadget.GadgetBadgetbackend.model.Supervisor;
import com.gadgetbadget.GadgetBadgetbackend.repository.SupervisorRepository;

@Service
@Transactional
public class SupervisorService {
	@Autowired
	SupervisorRepository repo;

	public List<Supervisor> getAll() {
		return repo.findAll();
	}

	public void save(Supervisor supervisor) {
		repo.save(supervisor);
	}

	public Supervisor get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
