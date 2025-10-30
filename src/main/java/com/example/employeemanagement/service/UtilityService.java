package com.example.employeemanagement.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {
	public String formatString(String input) {
		if (input == null || input.isEmpty()) {
			return "khong the format";
		}
		return input.trim().substring(0,1).toUpperCase() + input.trim().substring(1).toLowerCase();
	}
	
	public String createIdEmployee() {
		int number = new Random().nextInt(900) + 100;
		return "Employee code " + number;
	}
}
