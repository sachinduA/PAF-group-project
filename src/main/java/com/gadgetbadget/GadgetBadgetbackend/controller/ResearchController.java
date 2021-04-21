package com.gadgetbadget.GadgetBadgetbackend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
@Controller
public class ResearchController {
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

	// View Researches
	@GetMapping("/researches")
	public String getAll(Model model) {
		model.addAttribute("researches", researchService.getAll());
		return "researches";
	}

	@GetMapping("/researches/{id}")
	public String get(@PathVariable Integer id, Model model) {
		try {
			Research research = researchService.get(id);
			model.addAttribute("research", research);
			return "research";
		} catch (NoSuchElementException e) {
			model.addAttribute("error", "Research not available");
			return "research";
		}
	}

	// Add new Research
	@GetMapping("/newResearchForm")
	public String newResearchForm(Model model) {
		Research research = new Research();

		model.addAttribute("research", research);
		return "researchNew";
	}

	@PostMapping("/newResearch")
	public String newResearch(@ModelAttribute("research") Research research) {
		researchService.save(research);
		return "researches";
	}

	// Update Research
	@GetMapping("/updateResearchForm/{id}")
	public String updateResearchForm(@PathVariable int id, Model model) {
		Research research = researchService.get(id);
		
		model.addAttribute("research", research);
		model.addAttribute("supervisors", supervisorService.getAll());
		model.addAttribute("funders", funderService.getAll());
		model.addAttribute("researchers", researcherService.getAll());
		model.addAttribute("buyers", buyerService.getAll());
		
		return "researchUpdate";
	}

	@PostMapping("/updateResearchForm/updateResearch")
	public String updateResearch(@ModelAttribute("research") Research research) {
		try {
			Research existingResearch = researchService.get(research.getResearch_id());
			research.setResearch_id(existingResearch.getResearch_id());
			researchService.save(research);
			return "redirect:/researches";
		} catch (NoSuchElementException e) {
			return "redirect:/researches";
		}
	}

	@GetMapping("/deleteResearch/{id}")
	public String deleteResearch(@PathVariable Integer id) {
		researchService.delete(id);
		return "redirect:/researches";
	}

	// Assign Researcher
	@PostMapping("/updateResearchForm/addResearcherToResearch")
	public String addResearcherToResearch(@RequestParam("id") int id, @RequestParam("researcherId") int researcherId, Model model) {
		Research research = researchService.get(id);
		Researcher researcher = researcherService.get(researcherId);

		research.getResearchers().add(researcher);
		researchService.save(research);
		model.addAttribute("research", research);
		return "redirect:/researches";
	}

	// Remove Researcher
	@PostMapping("/updateResearchForm/deleteResearcherFromResearch")
	public String deleteResearcherFromResearch(@RequestParam("id") int id, @RequestParam("researcherId") int researcherId, Model model) {
		Research research = researchService.get(id);
		Researcher researcher = researcherService.get(researcherId);

		research.getResearchers().remove(researcher);
		researchService.save(research);
		model.addAttribute("research", research);
		return "redirect:/researches";
	}

	// Assign Buyer
	@PostMapping("/updateResearchForm/addBuyerToResearch")
	public String addBuyerToResearch(@RequestParam("id") int id, @RequestParam("buyerId") int buyerId, Model model) {
		Research research = researchService.get(id);
		Buyer buyer = buyerService.get(buyerId);

		research.setBuyer(buyer);
		researchService.save(research);
		model.addAttribute("research", research);
		return "redirect:/researches";
	}

	// Remove Buyer
	@PostMapping("/updateResearchForm/deleteBuyerFromResearch")
	public String deleteBuyerFromResearch(@RequestParam("id") int id, Model model) {
		Research research = researchService.get(id);

		research.setBuyer(null);
		researchService.save(research);
		model.addAttribute("research", research);
		return "redirect:/researches";
	}

	// Assign Funder
	@PostMapping("/updateResearchForm/addFunderToResearch")
	public String addFunderToResearch(@RequestParam("id") int id, @RequestParam("funderId") int funderId, Model model) {
		Research research = researchService.get(id);
		Funder funder = funderService.get(funderId);

		research.getFunders().add(funder);
		researchService.save(research);
		model.addAttribute("research", research);
		return "redirect:/researches";
	}

	// Remove Funder
	@PostMapping("/updateResearchForm/deleteFunderFromResearch")
	public String deleteFunderFromResearch(@RequestParam("id") int id, @RequestParam("funderId") int funderId, Model model) {
		Research research = researchService.get(id);
		Funder funder = funderService.get(funderId);

		research.getFunders().remove(funder);
		researchService.save(research);
		model.addAttribute("research", research);
		return "redirect:/researches";
	}

	// Assign Supervisor
	@PostMapping("/updateResearchForm/addSupervisorToResearch")
	public String addSupervisorToResearch(@RequestParam("id") int id, @RequestParam("supervisorId") int supervisorId, Model model) {
		Research research = researchService.get(id);
		Supervisor supervisor = supervisorService.get(supervisorId);

		research.setSupervisor(supervisor);
		researchService.save(research);
		model.addAttribute("research", research);
		return "redirect:/researches";
	}

	// Remove Supervisor
	@PostMapping("/updateResearchForm/removeSupervisorFromResearch")
	public String removeSupervisorFromResearch(@RequestParam("id") int id, Model model) {
		Research research = researchService.get(id);

		research.setSupervisor(null);
		researchService.save(research);
		model.addAttribute("research", research);
		return "redirect:/research";
	}
	
	
	/* ------------------------------------------
	 * REST API Endpoints
	 * ------------------------------------------
	 */
	
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
