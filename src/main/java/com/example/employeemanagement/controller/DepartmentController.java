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
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.service.DepartmentService;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;

	@GetMapping
	public ResponseEntity<List<Department>> getAllDepartments() {
		return ResponseEntity.ok(departmentService.getAllDepartments());
	}
	
	@PostMapping
	public ResponseEntity<Department> addDepartment(@RequestBody Department newDepartment) {
		Department saved = departmentService.addDepartment(newDepartment);
		return ResponseEntity.status(201).body(saved);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}