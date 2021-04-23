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

import com.gadgetbadget.GadgetBadgetbackend.model.Researcher;
import com.gadgetbadget.GadgetBadgetbackend.service.ResearcherService;

@RestController
public class ResearcherRESTController {
	@Autowired
	private ResearcherService service;

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
