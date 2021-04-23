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

import com.gadgetbadget.GadgetBadgetbackend.model.Funder;
import com.gadgetbadget.GadgetBadgetbackend.service.FunderService;

@RestController
public class FunderRESTController {
	@Autowired
	private FunderService service;

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
