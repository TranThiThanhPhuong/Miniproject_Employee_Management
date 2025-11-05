package com.example.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	private final EmployeeRepository employeeRepo;
	
	public EmployeeService (EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}
	
	public Employee getEmployeeById(Long id) {
		return employeeRepo.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Khong tim thay Id:" + id));
	}
	
	public Employee addEmployee (Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public List<Employee> searchByName(String name) {
		return employeeRepo.findByNameContainingIgnoreCase(name);
	}
	
	public List<Employee> searchByDepartment(Long department_id) {
		Department department = departmentRepo.findById(department_id).orElse(null);
		return department != null ? employeeRepo.findByDepartment(department) : List.of();
	}
	
	public Employee updateEmployee(Long id, Employee updatedEmployee) {
	    Employee existing = employeeRepo.findById(id).orElse(null);
	    if (existing != null) {
	        existing.setName(updatedEmployee.getName());
	        existing.setEmail(updatedEmployee.getEmail());
	        existing.setDepartment(updatedEmployee.getDepartment());
	        return employeeRepo.save(existing);
	    }
	    return null;
	}

	public void deleteEmployee(Long id) {
	    employeeRepo.deleteById(id);
	}
}

//Spring nhìn thấy EmployeeService có một constructor nhận EmployeeRepository
//→ tự động tìm EmployeeRepository trong IoC Container
//→ tạo đối tượng EmployeeService và truyền EmployeeRepository vào.
//Bạn không cần dùng @Autowired nữa, vì Spring Boot 3+ tự inject nếu chỉ có 1 constructor duy nhất.