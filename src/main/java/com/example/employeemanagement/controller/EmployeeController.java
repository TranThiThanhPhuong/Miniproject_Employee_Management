package com.example.employeemanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}

	@PostMapping
	public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee newEmployee, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage()).toList();
			
			return ResponseEntity.badRequest().body(errors);
		}

		Employee saved = employeeService.addEmployee(newEmployee);
		return ResponseEntity.status(200).body(saved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	    Employee employee = employeeService.getEmployeeById(id);
	    return ResponseEntity.ok(employee);
	}

	@GetMapping("/search")
	public List<Employee> searchByName(@RequestParam String name) {
		return employeeService.searchByName(name);
	}

	@GetMapping("/search/department/{department_id}")
	public List<Employee> searchByDepartment(@PathVariable Long department_id) {
		return employeeService.searchByDepartment(department_id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee updatedEmployee, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> err.getField() + ": " + err.getDefaultMessage())
					.toList();
			return ResponseEntity.badRequest().body(errors);
		}
		Employee updated = employeeService.updateEmployee(id, updatedEmployee);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/statistics")
	public ResponseEntity<Map<String, Object>> getEmployeeStatistics() {
	    Map<String, Object> result = new HashMap<>();
	    result.put("byDepartment", employeeService.getEmployeeStatsByDepartment());
	    result.put("total", employeeService.getTotalEmployeeCount());
	    return ResponseEntity.ok(result);
	}
}