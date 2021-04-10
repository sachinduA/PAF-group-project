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

import com.gadgetbadget.GadgetBadgetbackend.model.Research;
import com.gadgetbadget.GadgetBadgetbackend.service.ResearchService;

@RestController
public class ResearchController {
	@Autowired
	private ResearchService service;

	@GetMapping("/researches")
	public List<Research> getAll() {
		return service.getAll();
	}

	@GetMapping("/researches/{id}")
	public ResponseEntity<Research> get(@PathVariable Integer id) {
		try {
			Research research = service.get(id);
			return new ResponseEntity<Research>(research, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Research>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/researches")
	public ResponseEntity<Research> add(@RequestBody Research research) {
		System.out.println(research);
		service.save(research);
		return new ResponseEntity<Research>(research, HttpStatus.OK);
	}

	@PutMapping("/researches/{id}")
	public ResponseEntity<?> update(@RequestBody Research research, @PathVariable Integer id) {
		try {
			@SuppressWarnings("unused")
			Research existResearcher = service.get(id);
			research.setId(id);
			service.save(research);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/researches/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
