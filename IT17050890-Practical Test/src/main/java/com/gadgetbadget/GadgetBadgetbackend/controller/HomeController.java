package com.gadgetbadget.GadgetBadgetbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gadgetbadget.GadgetBadgetbackend.model.LoginUser;

@Controller
public class HomeController {
	private final String TRUE = "TRUE";
	private final String FALSE = "FALSE";

	private Map<String, String> users = createUsers();

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginUser user) {
		HashMap<String, String> map = new HashMap<>();

		if (users.containsKey(user.getEmail())) {
			if (users.get(user.getEmail()).equals(user.getPassword())) {
				map.put("result", this.TRUE);
				return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
			} else {
				map.put("result", this.FALSE);
				map.put("error", "Email/Password is incorrect.");
				return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
			}
		} else {
			map.put("result", this.FALSE);
			map.put("error", "Email address is not assigned with any account.");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
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
