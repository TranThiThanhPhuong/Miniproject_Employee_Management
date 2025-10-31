package com.example.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employeemanagement.model.Employee;

@Service
public class EmployeeService {
	
	private final List<Employee> employees = new ArrayList<>();
	
	public EmployeeService () {
		employees.add(new Employee(1, "Phuong"));
		employees.add(new Employee(2, "Tram"));
	}
	
	public List<Employee> getAllEmployees() {
		return employees;
	}
	
	public Employee addEmployee (Employee employee) {
		employees.add(employee);
		return employee;
	}
}
