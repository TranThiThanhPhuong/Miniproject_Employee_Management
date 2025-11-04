package com.example.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByNameContainingIgnoreCase (String name);
	
	List<Employee> findByDepartment (Department department);
	
}