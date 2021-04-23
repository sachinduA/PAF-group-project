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
import org.springframework.web.bind.annotation.RestController;

import com.gadgetbadget.GadgetBadgetbackend.model.Researcher;
import com.gadgetbadget.GadgetBadgetbackend.service.ResearcherService;

@RestController
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
	
	/* ------------------------------------------
	 * REST API Endpoints
	 * ------------------------------------------
	 */
	
	@GetMapping("/get-researchers")
	public List<Researcher> getAll() {
		return service.getAll();
	}

	@GetMapping("/get-researcher/{id}")
	public ResponseEntity<Researcher> get(@PathVariable Integer id) {
		try {
			Researcher researcher = service.get(id);
			return new ResponseEntity<Researcher>(researcher, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Researcher>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/new-researcher")
	public void add(@RequestBody Researcher researcher) {
		service.save(researcher);
	}

	@PutMapping("/update-researcher/{id}")
	public ResponseEntity<?> update(@RequestBody Researcher researcher, @PathVariable Integer id) {
		try {
			@SuppressWarnings("unused")
			Researcher existResearcher = service.get(id);
			researcher.setResearcher_id(id);
			service.save(researcher);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete-researcher/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
