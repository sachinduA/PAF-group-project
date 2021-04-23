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

import com.gadgetbadget.GadgetBadgetbackend.model.Buyer;
import com.gadgetbadget.GadgetBadgetbackend.service.BuyerService;

@RestController
@Controller
public class BuyerController {
	@Autowired
	private BuyerService service;

	// View Buyers
	@GetMapping("/buyers")
	public String getAll(Model model) {
		model.addAttribute("buyers", service.getAll());
		return "buyers";
	}

	@GetMapping("/buyers/{id}")
	public String get(@PathVariable Integer id, Model model) {
		try {
			Buyer buyer = service.get(id);
			model.addAttribute("buyer", buyer);
			return "buyer";
		} catch (NoSuchElementException e) {
			model.addAttribute("error", "Buyer not available.");
			return "buyer";
		}
	}

	// Add new Buyer
	@GetMapping("/newBuyerForm")
	public String newResearchForm(Model model) {
		Buyer buyer = new Buyer();

		model.addAttribute("buyer", buyer);
		return "buyerNew";
	}

	@PostMapping("/newBuyer")
	public String newBuyer(@ModelAttribute("buyer") Buyer buyer) {
		service.save(buyer);
		return "redirect:/buyers";
	}

	// Update Buyer
	@GetMapping("/updateBuyerForm/{id}")
	public String updateBuyerForm(@PathVariable int id, Model model) {
		Buyer buyer = service.get(id);

		model.addAttribute("buyer", buyer);
		return "buyerUpdate";
	}

	@PostMapping("/updateBuyerForm/updateBuyer")
	public String updateBuyer(@ModelAttribute("buyer") Buyer buyer) {
		Buyer existingBuyer = service.get(buyer.getBuyer_id());
		buyer.setBuyer_id(existingBuyer.getBuyer_id());
		service.save(buyer);
		return "redirect:/buyers";
	}

	@GetMapping("/deleteBuyer/{id}")
	public String deleteBuyer(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:/buyers";
	}
	
	/* ------------------------------------------
	 * REST API Endpoints
	 * ------------------------------------------
	 */
	
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
