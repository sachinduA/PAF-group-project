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

import com.gadgetbadget.GadgetBadgetbackend.model.Buyer;
import com.gadgetbadget.GadgetBadgetbackend.service.BuyerService;

@RestController
public class BuyerRESTController {
	@Autowired
	private BuyerService service;
	
	@GetMapping("/get-buyers")
	public List<Buyer> getAll() {
		return service.getAll();
	}

	@GetMapping("/get-buyer/{id}")
	public ResponseEntity<Buyer> get(@PathVariable Integer id) {
		try {
			Buyer buyer = service.get(id);
			return new ResponseEntity<Buyer>(buyer, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Buyer>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/new-buyer")
	public void add(@RequestBody Buyer buyer) {
		service.save(buyer);
	}

	@PutMapping("/update-buyer/{id}")
	public ResponseEntity<?> update(@RequestBody Buyer buyer, @PathVariable Integer id) {
		try {
			@SuppressWarnings("unused")
			Buyer existBuyer = service.get(id);
			buyer.setBuyer_id(id);
			service.save(buyer);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete-buyer/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
