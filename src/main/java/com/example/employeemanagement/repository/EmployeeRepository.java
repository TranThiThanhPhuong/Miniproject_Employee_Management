package com.example.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.employeemanagement.dto.DepartmentStatsDTO;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByNameContainingIgnoreCase(String name);

	List<Employee> findByDepartment(Department department);

	@Query("SELECT new com.example.employeemanagement.dto.DepartmentStatsDTO(d.name, COUNT(e.id)) "
			+ "FROM Employee e JOIN e.department d GROUP BY d.name")
	List<DepartmentStatsDTO> countEmployeesByDepartment();

	@Query("SELECT COUNT(e.id) FROM Employee e")
	long countAllEmployees();
}