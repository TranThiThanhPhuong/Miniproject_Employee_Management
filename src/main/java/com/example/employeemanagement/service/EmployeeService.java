package com.example.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final List<Employee> employees = new ArrayList<>();
	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	private final EmployeeRepository employeeRepo;
	
	public EmployeeService (EmployeeRepository employeeRepo) {
		
		this.employeeRepo = employeeRepo;
		
		employees.add(new Employee(1L, "Phuong", "p@gmail.com"));
		employees.add(new Employee(2L, "Tram", "t@gmail.com"));
	}
	
	public List<Employee> getAllEmployees() {
		return employees;
	}
	
	public Employee addEmployee (Employee employee) {
		employees.add(employee);
		return employee;
	}
	
	public List<Employee> searchByName(String name) {
		return employeeRepo.findByNameContainIgoreCase(name);
	}
	
	public List<Employee> searchByDepartment(Long department_id) {
		Department department = departmentRepo.findById(department_id).orElse(null);
		return department != null ? employeeRepo.findByDepartment(department) : List.of();
	}
}

//Spring nhìn thấy EmployeeService có một constructor nhận EmployeeRepository
//→ tự động tìm EmployeeRepository trong IoC Container
//→ tạo đối tượng EmployeeService và truyền EmployeeRepository vào.
//Bạn không cần dùng @Autowired nữa, vì Spring Boot 3+ tự inject nếu chỉ có 1 constructor duy nhất.