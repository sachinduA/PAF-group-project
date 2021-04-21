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

import com.gadgetbadget.GadgetBadgetbackend.model.Funder;
import com.gadgetbadget.GadgetBadgetbackend.service.FunderService;

@RestController
@Controller
public class FunderController {
	@Autowired
	private FunderService service;

	// View Funders
	@GetMapping("/funders")
	public String getAll(Model model) {
		model.addAttribute("funders", service.getAll());
		return "funders";
	}

	@GetMapping("/funders/{id}")
	public String get(@PathVariable Integer id, Model model) {
		try {
			Funder funder = service.get(id);
			model.addAttribute("funder", funder);
			return "funder";
		} catch (NoSuchElementException e) {
			model.addAttribute("error", "Funder not available.");
			return "funder";
		}
	}

	// Add new Funder
	@GetMapping("/newFunderForm")
	public String newFunderForm(Model model) {
		Funder funder = new Funder();

		model.addAttribute("funder", funder);
		return "funderNew";
	}

	@PostMapping("/newFunder")
	public String newFunder(@ModelAttribute("funder") Funder funder) {
		service.save(funder);
		return "redirect:/funders";
	}

	// Update Funder
	@GetMapping("/updateFunderForm/{id}")
	public String updateFunderForm(@PathVariable int id, Model model) {
		Funder funder = service.get(id);
		model.addAttribute("funder", funder);
		return "funderUpdate";
	}

	@PostMapping("/updateFunderForm/updateFunder")
	public String updateFunder(@ModelAttribute("funder") Funder funder) {
		try {
			Funder existingFunder = service.get(funder.getFunder_id());
			funder.setFunder_id(existingFunder.getFunder_id());
			service.save(funder);
			return "redirect:/funders";
		} catch (NoSuchElementException e) {
			return "redirect:/funder";
		}
	}

	@GetMapping("/deleteFunder/{id}")
	public String deleteFunder(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:/funders";
	}
	
	/* ------------------------------------------
	 * REST API Endpoints
	 * ------------------------------------------
	 */
	
	@GetMapping("/get-funders")
	public List<Funder> getAll() {
		return service.getAll();
	}

	@GetMapping("/get-funder/{id}")
	public ResponseEntity<Funder> get(@PathVariable Integer id) {
		try {
			Funder funder = service.get(id);
			return new ResponseEntity<Funder>(funder, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Funder>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/new-funder")
	public void add(@RequestBody Funder funder) {
		service.save(funder);
	}

	@PutMapping("/update-funder/{id}")
	public ResponseEntity<?> update(@RequestBody Funder funder, @PathVariable Integer id) {
		try {
			@SuppressWarnings("unused")
			Funder existFunder = service.get(id);
			funder.setFunder_id(id);
			service.save(funder);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete-funder/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
