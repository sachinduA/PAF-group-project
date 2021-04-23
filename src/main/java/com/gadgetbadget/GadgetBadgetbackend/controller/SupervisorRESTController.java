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

import com.gadgetbadget.GadgetBadgetbackend.model.Supervisor;
import com.gadgetbadget.GadgetBadgetbackend.service.SupervisorService;

@RestController
public class SupervisorRESTController {
	@Autowired
	private SupervisorService service;

	@GetMapping("/get-supervisors")
	public List<Supervisor> getAll() {
		return service.getAll();
	}

	@GetMapping("/get-supervisor/{id}")
	public ResponseEntity<Supervisor> get(@PathVariable Integer id) {
		try {
			Supervisor supervisor = service.get(id);
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
