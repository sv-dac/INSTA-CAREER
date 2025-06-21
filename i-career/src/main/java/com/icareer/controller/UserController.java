package com.icareer.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.icareer.entity.User;
import com.icareer.repository.UserRepository;

@RestController
@RequestMapping("/icareer")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/getRequest")
	public String getRequest() {
		System.out.println("get called");
		return "test";
	}

	@GetMapping("/login/{id}")
	public Optional<User> login(@PathVariable String id) {
		System.out.println("login called : " + id);
		return userRepository.findById(id);
	}

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		System.out.println(user + "hello");
		return userRepository.saveAndFlush(user);
	}
}