package com.gadgetbadget.GadgetBadgetbackend.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gadgetbadget.GadgetBadgetbackend.model.Researcher;
import com.gadgetbadget.GadgetBadgetbackend.service.ResearcherService;

@Controller
public class ResearcherController {
	@Autowired
	private ResearcherService service;

	@GetMapping("/researchers")
	public String getAll(Model model) {
		model.addAttribute("researchers", service.getAll());
		return "researchers";
	}

	@GetMapping("/researchers/{id}")
	public String get(@PathVariable Integer id, Model model) {
		try {
			Researcher researcher = service.get(id);
			model.addAttribute("researcher", researcher);
			return "researcher";
		} catch (NoSuchElementException e) {
			model.addAttribute("error", "Researcher is not available.");
			return "researcher";
		}
	}

	// Add new Researcher
	@GetMapping("/newResearcherForm")
	public String newResearcherForm(Model model) {
		Researcher researcher = new Researcher();
		model.addAttribute("researcher", researcher);
		return "researcherNew";
	}

	@PostMapping("/newResearcher")
	public String newResearcher(@ModelAttribute("researcher") Researcher researcher, Model model) {
		service.save(researcher);
		model.addAttribute("researcher", researcher);
		return "redirect:/researchers";
	}

	// Update Researcher
	@GetMapping("/updateResearcherForm/{id}")
	public String updateResearcherForm(@PathVariable int id, Model model) {
		Researcher researcher = service.get(id);
		model.addAttribute("researcher", researcher);
		return "researcherUpdate";
	}

	@PostMapping("/updateResearcherForm/updateResearcher")
	public String updateResearcher(@ModelAttribute("researcher") Researcher researcher) {
		try {
			Researcher existingResearcher = service.get(researcher.getResearcher_id());
			researcher.setResearcher_id(existingResearcher.getResearcher_id());
			service.save(researcher);
			return "redirect:/researchers";
		} catch (NoSuchElementException e) {
			return "redirect:/researchers";
		}
	}

	@GetMapping("/deleteResearcher/{id}")
	public String deleteResearcher(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:/researchers";
	}
}
