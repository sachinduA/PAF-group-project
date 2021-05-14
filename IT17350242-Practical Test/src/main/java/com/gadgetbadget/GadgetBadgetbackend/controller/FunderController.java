package com.gadgetbadget.GadgetBadgetbackend.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gadgetbadget.GadgetBadgetbackend.model.Funder;
import com.gadgetbadget.GadgetBadgetbackend.service.FunderService;

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

}
