package com.example.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.employeemanagement.model.Employee;

import com.example.employeemanagement.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeservice;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeservice.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee) {
		Employee saved = employeeservice.addEmployee(newEmployee);
		return ResponseEntity.status(200).body(saved);
	}
	
}
