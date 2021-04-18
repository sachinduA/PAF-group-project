package com.gadgetbadget.GadgetBadgetbackend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gadgetbadget.GadgetBadgetbackend.model.Buyer;
import com.gadgetbadget.GadgetBadgetbackend.model.Funder;
import com.gadgetbadget.GadgetBadgetbackend.model.Research;
import com.gadgetbadget.GadgetBadgetbackend.model.Researcher;
import com.gadgetbadget.GadgetBadgetbackend.model.Supervisor;
import com.gadgetbadget.GadgetBadgetbackend.service.BuyerService;
import com.gadgetbadget.GadgetBadgetbackend.service.FunderService;
import com.gadgetbadget.GadgetBadgetbackend.service.ResearchService;
import com.gadgetbadget.GadgetBadgetbackend.service.ResearcherService;
import com.gadgetbadget.GadgetBadgetbackend.service.SupervisorService;

@RestController
public class ResearchController {
	@Autowired
	private ResearchService service;

	@Autowired
	private ResearcherService researcherService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private FunderService funderService;

	@Autowired
	private SupervisorService supervisorService;

	@GetMapping("/researches")
	public List<Research> getAll() {
		return service.getAll();
	}

	@GetMapping("/researches/{id}")
	public ResponseEntity<Research> get(@PathVariable Integer id) {
		try {
			Research research = service.get(id);
			return new ResponseEntity<Research>(research, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Research>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/researches")
	public void add(@RequestBody Research research) {
		service.save(research);
	}

	@PutMapping("/researches/{id}")
	public ResponseEntity<?> update(@RequestBody Research research, @PathVariable Integer id) {
		try {
			@SuppressWarnings("unused")
			Research existResearcher = service.get(id);
			research.setResearch_id(id);
			service.save(research);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/researches/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

	@PutMapping("researches/{id}/researchers/{researcherId}")
	public void addResearcherToResearch(@PathVariable int id, @PathVariable int researcherId) {
		Research research = service.get(id);
		Researcher researcher = researcherService.get(researcherId);

		research.getResearchers().add(researcher);
		service.save(research);
	}

	@DeleteMapping("researches/{id}/researchers/{researcherId}")
	public void deleteResearcherFromResearch(@PathVariable int id, @PathVariable int researcherId) {
		Research research = service.get(id);
		Researcher researcher = researcherService.get(researcherId);

		research.getResearchers().remove(researcher);
		service.save(research);
	}

	@PutMapping("researches/{id}/buyers/{buyerId}")
	public void addBuyerToResearch(@PathVariable int id, @PathVariable int buyerId) {
		Research research = service.get(id);
		Buyer buyer = buyerService.get(buyerId);

		research.getBuyers().add(buyer);
		service.save(research);
	}

	@DeleteMapping("researches/{id}/buyers/{buyerId}")
	public void deleteBuyerFromResearch(@PathVariable int id, @PathVariable int buyerId) {
		Research research = service.get(id);
		Buyer buyer = buyerService.get(buyerId);

		research.getBuyers().remove(buyer);
		service.save(research);
	}

	@PutMapping("researches/{id}/funders/{funderId}")
	public void addFunderToResearch(@PathVariable int id, @PathVariable int funderId) {
		Research research = service.get(id);
		Funder funder = funderService.get(funderId);

		research.getFunders().add(funder);
		service.save(research);
	}

	@DeleteMapping("researches/{id}/funders/{funderId}")
	public void deleteFunderFromResearch(@PathVariable int id, @PathVariable int funderId) {
		Research research = service.get(id);
		Funder funder = funderService.get(funderId);

		research.getFunders().remove(funder);
		service.save(research);
	}

	@PutMapping("researches/{id}/supervisors/{supervisorId}")
	public void addSupervisorToResearch(@PathVariable int id, @PathVariable int supervisorId) {
		Research research = service.get(id);
		Supervisor supervisor = supervisorService.get(supervisorId);

		research.setSupervisor(supervisor);
		service.save(research);
	}

	@DeleteMapping("researches/{id}/supervisors")
	public void removeSupervisorFromResearch(@PathVariable int id) {
		Research research = service.get(id);

		research.setSupervisor(null);
		service.save(research);
	}
}
