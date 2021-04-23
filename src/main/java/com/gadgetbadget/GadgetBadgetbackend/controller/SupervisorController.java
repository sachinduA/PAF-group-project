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

import com.gadgetbadget.GadgetBadgetbackend.model.Supervisor;
import com.gadgetbadget.GadgetBadgetbackend.service.SupervisorService;

@RestController
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
	
	/* ------------------------------------------
	 * REST API Endpoints
	 * ------------------------------------------
	 */
	
	@GetMapping("/get-supervisors")
	public List<Supervisor> getAll() {
		return service.getAll();
	}

	@GetMapping("/get-supervisor/{id}")
	public ResponseEntity<Supervisor> get(@PathVariable Integer id) {
		try {
			Supervisor supervisor= service.get(id);
			return new ResponseEntity<Supervisor>(supervisor, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Supervisor>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/new-supervisor")
	public void add(@RequestBody Supervisor supervisor) {
		service.save(supervisor);
	}

	@PutMapping("/update-supervisor/{id}")
	public ResponseEntity<?> update(@RequestBody Supervisor supervisor, @PathVariable Integer id) {
		try {
			@SuppressWarnings("unused")
			Supervisor existSupervisor = service.get(id);
			supervisor.setSupervisor_id(id);
			service.save(supervisor);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete-supervisor/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
