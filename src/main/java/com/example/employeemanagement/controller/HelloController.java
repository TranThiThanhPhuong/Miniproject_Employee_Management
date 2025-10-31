package com.example.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemanagement.service.UtilityService;

@RestController
@RequestMapping("/api")
public class HelloController {
	
	private final UtilityService utilityService;

	@Autowired
	public HelloController(UtilityService utilityService) {
		this.utilityService = utilityService;
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/great")
	public String great(@RequestParam String name) {
		return "hello " + name;
	}

	@GetMapping("/bye/{name}")
	public String bye(@PathVariable String name) {
		return "good bye " + name;
	}

	@GetMapping("/format")
	public String formatString(@RequestParam String name) {
		return utilityService.formatString(name);
	}

	@GetMapping("/code")
	public String createIdEmployee() {
		return utilityService.createIdEmployee();
	}
}