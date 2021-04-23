package com.gadgetbadget.GadgetBadgetbackend.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String addResearcherToResearch(@RequestParam("id") int id, @RequestParam("researcherId") int researcherId,
			Model model) {
		Research research = researchService.get(id);
		Researcher researcher = researcherService.get(researcherId);

		research.getResearchers().add(researcher);
		researchService.save(research);
		model.addAttribute("research", research);
		return "redirect:/researches";
	}

	// Remove Researcher
	@PostMapping("/updateResearchForm/deleteResearcherFromResearch")
	public String deleteResearcherFromResearch(@RequestParam("id") int id,
			@RequestParam("researcherId") int researcherId, Model model) {
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
	public String deleteFunderFromResearch(@RequestParam("id") int id, @RequestParam("funderId") int funderId,
			Model model) {
		Research research = researchService.get(id);
		Funder funder = funderService.get(funderId);

		research.getFunders().remove(funder);
		researchService.save(research);
		model.addAttribute("research", research);
		return "redirect:/researches";
	}

	// Assign Supervisor
	@PostMapping("/updateResearchForm/addSupervisorToResearch")
	public String addSupervisorToResearch(@RequestParam("id") int id, @RequestParam("supervisorId") int supervisorId,
			Model model) {
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

}
