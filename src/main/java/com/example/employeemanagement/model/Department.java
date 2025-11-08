package com.example.employeemanagement.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany
	private List<Employee> employees;
	
	public Long getId() { 
		return id; 
	}
	
    public void setId(Long id) { 
    	this.id = id; 
    }

    public String getName() { 
    	return name; 
    }
    
    public void setName(String name) { 
    	this.name = name; 
    }
    
    public List<Employee> getAllEmployees() { 
    	return employees;
    }
    
    public void setEmployees(List<Employee> employees) { 
    	this.employees = employees; 
    }
}
