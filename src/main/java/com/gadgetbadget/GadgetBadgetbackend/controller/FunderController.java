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
public class FunderController {
	@Autowired
	private FunderService service;

	@GetMapping("/funders")
	public List<Funder> getAll() {
		return service.getAll();
	}

	@GetMapping("/funders/{id}")
	public ResponseEntity<Funder> get(@PathVariable Integer id) {
		try {
			Funder funder = service.get(id);
			return new ResponseEntity<Funder>(funder, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Funder>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/funders")
	public void add(@RequestBody Funder funder) {
		service.save(funder);
	}

	@PutMapping("/funders/{id}")
	public ResponseEntity<?> update(@RequestBody Funder funder, @PathVariable Integer id) {
		try {
			@SuppressWarnings("unused")
			Funder existFunder = service.get(id);
			funder.setId(id);
			service.save(funder);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/funders/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
