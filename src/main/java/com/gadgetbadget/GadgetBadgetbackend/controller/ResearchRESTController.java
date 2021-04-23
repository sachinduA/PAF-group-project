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
public class ResearchRESTController {
	@Autowired
	private ResearchService researchService;

	@Autowired
	private ResearcherService researcherService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private FunderService funderService;

	@Autowired
	private SupervisorService supervisorService;

	@GetMapping("/get-researches")
	public List<Research> getAll() {
		return researchService.getAll();
	}

	@GetMapping("/get-research/{id}")
	public ResponseEntity<Research> get(@PathVariable Integer id) {
		try {
			Research research = researchService.get(id);
			return new ResponseEntity<Research>(research, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Research>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/new-research")
	public void add(@RequestBody Research research) {
		researchService.save(research);
	}

	@PutMapping("/update-research/{id}")
	public ResponseEntity<?> update(@RequestBody Research research, @PathVariable Integer id) {
		try {
			@SuppressWarnings("unused")
			Research existResearcher = researchService.get(id);
			research.setResearch_id(id);
			researchService.save(research);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete-research/{id}")
	public void delete(@PathVariable Integer id) {
		researchService.delete(id);
	}

	@PutMapping("researches/{id}/researchers/{researcherId}")
	public void addResearcherToResearch(@PathVariable int id, @PathVariable int researcherId) {
		Research research = researchService.get(id);
		Researcher researcher = researcherService.get(researcherId);

		research.getResearchers().add(researcher);
		researchService.save(research);
	}

	@DeleteMapping("researches/{id}/researchers/{researcherId}")
	public void deleteResearcherFromResearch(@PathVariable int id, @PathVariable int researcherId) {
		Research research = researchService.get(id);
		Researcher researcher = researcherService.get(researcherId);

		research.getResearchers().remove(researcher);
		researchService.save(research);
	}

	@PutMapping("researches/{id}/buyers/{buyerId}")
	public void addBuyerToResearch(@PathVariable int id, @PathVariable int buyerId) {
		Research research = researchService.get(id);
		Buyer buyer = buyerService.get(buyerId);

		research.setBuyer(buyer);
		researchService.save(research);
	}

	@DeleteMapping("researches/{id}/buyers")
	public void deleteBuyerFromResearch(@PathVariable int id) {
		Research research = researchService.get(id);

		research.setBuyer(null);
		researchService.save(research);
	}

	@PutMapping("researches/{id}/funders/{funderId}")
	public void addFunderToResearch(@PathVariable int id, @PathVariable int funderId) {
		Research research = researchService.get(id);
		Funder funder = funderService.get(funderId);

		research.getFunders().add(funder);
		researchService.save(research);
	}

	@DeleteMapping("researches/{id}/funders/{funderId}")
	public void deleteFunderFromResearch(@PathVariable int id, @PathVariable int funderId) {
		Research research = researchService.get(id);
		Funder funder = funderService.get(funderId);

		research.getFunders().remove(funder);
		researchService.save(research);
	}

	@PutMapping("researches/{id}/supervisors/{supervisorId}")
	public void addSupervisorToResearch(@PathVariable int id, @PathVariable int supervisorId) {
		Research research = researchService.get(id);
		Supervisor supervisor = supervisorService.get(supervisorId);

		research.setSupervisor(supervisor);
		researchService.save(research);
	}

	@DeleteMapping("researches/{id}/supervisors")
	public void removeSupervisorFromResearch(@PathVariable int id) {
		Research research = researchService.get(id);

		research.setSupervisor(null);
		researchService.save(research);
	}
}
