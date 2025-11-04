package com.example.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.employeemanagement.model.Employee;

import com.example.employeemanagement.service.EmployeeService;

@RestController
@RequestMapping("api/employees")
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
	
	@GetMapping("/search")
	public List<Employee> searchByName(@RequestParam String name) {
		 return employeeservice.searchByName(name);
	}
	
	@GetMapping("/department/{department_id}")
	public List<Employee> searchByDepartment(@PathVariable Long department_id) {
	     return employeeservice.searchByDepartment(department_id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
	    Employee updated = employeeservice.updateEmployee(id, updatedEmployee);
	    if (updated != null) {
	        return ResponseEntity.ok(updated);
	    }
	    return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
	    employeeservice.deleteEmployee(id);
	    return ResponseEntity.noContent().build();
	}
}