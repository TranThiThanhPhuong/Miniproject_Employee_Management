package com.example.employeemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Không được để trống")
	@Size(min = 2, max = 50, message = "Tên phải từ 2 đến 50 kí tự")
	private String name;
	
	@Email(message = "Không đúng định dạng email")
	@NotBlank(message = "Không được để trống")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
//	public Employee (Long id, String name, String email) {
//		this.id = id;
//		this.name = name;
//		this.email = email;
//	}
//	
//	public Long getId() { 
//		return id; 
//	}
//	
//    public void setId(Long id) { 
//    	this.id = id; 
//    }
//
//    public String getName() { 
//    	return name; 
//    }
//    
//    public void setName(String name) { 
//    	this.name = name; 
//    }
//    
//    public String getEmail() { 
//    	return email; 
//    }
//    
//    public void setEmail(String email) { 
//    	this.email = email; 
//    }
//    
//    public Department getDepartment() { 
//    	return this.department; 
//    }
//    
//    public void setDepartment(Department department) { 
//    	this.department = department; 
//    }
}

//Spring Boot (cụ thể là Hibernate) cần một constructor mặc định (không tham số) 
//để khởi tạo đối tượng khi đọc/ghi dữ liệu từ database.
//Nếu Employee của bạn chỉ có constructor có tham số (do bạn dùng @AllArgsConstructor 
//mà thiếu @NoArgsConstructor) thì Hibernate không thể tạo instance → báo lỗi này.