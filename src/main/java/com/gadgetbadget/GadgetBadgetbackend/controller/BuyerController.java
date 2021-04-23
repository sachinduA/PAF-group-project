package com.gadgetbadget.GadgetBadgetbackend.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gadgetbadget.GadgetBadgetbackend.model.Buyer;
import com.gadgetbadget.GadgetBadgetbackend.service.BuyerService;

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

}
