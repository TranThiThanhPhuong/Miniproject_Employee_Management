package com.example.employeemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private DepartmentRepository departmentRepo;
	
	public DepartmentService (DepartmentRepository departmentRepo) {
		this.departmentRepo = departmentRepo;
	}
	
	public List<Department> getAllDepartments() {
		return departmentRepo.findAll();
	}
	
	public Department addDepartment (Department department) {
		return departmentRepo.save(department);
	}
	
	public Department getDepartmentById(Long id) {
        return departmentRepo.findById(id).orElse(null);
    }

    public Department updateDepartment(Long id, Department departmentDetails) {
        Department existing = departmentRepo.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(departmentDetails.getName());
        return departmentRepo.save(existing);
    }

    public void deleteDepartment(Long id) {
        departmentRepo.deleteById(id);
    }
}
