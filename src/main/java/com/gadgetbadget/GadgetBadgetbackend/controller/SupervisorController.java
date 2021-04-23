package com.gadgetbadget.GadgetBadgetbackend.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gadgetbadget.GadgetBadgetbackend.model.Supervisor;
import com.gadgetbadget.GadgetBadgetbackend.service.SupervisorService;

@Controller
public class SupervisorController {
	@Autowired
	private SupervisorService service;

	// View Supervisors
	@GetMapping("/supervisors")
	public String getAll(Model model) {
		model.addAttribute("supervisors", service.getAll());
		return "supervisors";
	}

	@GetMapping("/supervisors/{id}")
	public String get(@PathVariable Integer id, Model model) {
		try {
			Supervisor supervisor = service.get(id);
			model.addAttribute("supervisor", supervisor);
			return "supervisor";
		} catch (NoSuchElementException e) {
			model.addAttribute("error", "Supervisor not available");
			return "supervisor";
		}
	}

	// Add new Supervisor
	@GetMapping("/newSupervisorForm")
	public String newSupervisorForm(Model model) {
		Supervisor supervisor = new Supervisor();
		model.addAttribute("supervisor", supervisor);
		return "supervisorNew";
	}

	@PostMapping("/newSupervisor")
	public String newSupervisor(@ModelAttribute("supervisor") Supervisor supervisor) {
		service.save(supervisor);
		return "redirect:/supervisors";
	}

	// Update Supervisor
	@GetMapping("/updateSupervisorForm/{id}")
	public String updateSupervisorForm(@PathVariable int id, Model model) {
		Supervisor supervisor = service.get(id);
		model.addAttribute("supervisor", supervisor);
		return "supervisorUpdate";
	}

	@PostMapping("/updateSupervisorForm/updateSupervisor")
	public String updateSupervisor(@ModelAttribute("supervisor") Supervisor supervisor) {
		try {
			Supervisor existingSupervisor = service.get(supervisor.getSupervisor_id());
			supervisor.setSupervisor_id(existingSupervisor.getSupervisor_id());
			service.save(supervisor);
			return "redirect:/supervisors";
		} catch (NoSuchElementException e) {
			return "redirect:/supervisors";
		}
	}

	@GetMapping("/deleteSupervisor/{id}")
	public String deleteSupervisor(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:/supervisors";
	}
}
