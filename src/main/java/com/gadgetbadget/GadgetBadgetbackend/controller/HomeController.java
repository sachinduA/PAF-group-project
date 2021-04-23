package com.gadgetbadget.GadgetBadgetbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gadgetbadget.GadgetBadgetbackend.service.ResearchService;

@Controller
public class HomeController {
	@Autowired
	ResearchService researchService;

	Map<String, String> users = createUsers();

	@GetMapping("/loginForm")
	public String welcome(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		if (users.containsKey(email)) {
			if (users.get(email).equals(password)) {
				return "redirect:/researches";
			} else {
				model.addAttribute("error", "Password is invalid.");
				return "login";
			}
		} else {
			model.addAttribute("error", "Email is invalid.");
			return "login";
		}
	}

	private static Map<String, String> createUsers() {
		Map<String, String> map = new HashMap<>();

		map.put("johndoe1@gmail.com", "johndoe1");
		map.put("janedoe1@gmail.com", "janedoe1");
		map.put("johndoe2@outlook.com", "johndoe2");
		map.put("janedoe2@outlook.com", "janedoe2");
		map.put("johndoe3@gmail.com", "johndoe3");

		return map;
	}
}
