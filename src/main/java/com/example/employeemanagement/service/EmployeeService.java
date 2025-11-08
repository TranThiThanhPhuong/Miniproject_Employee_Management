package com.example.employeemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeService {
	
	private final DepartmentRepository departmentRepo;
	
	private final EmployeeRepository employeeRepo;
	
	public EmployeeService (EmployeeRepository employeeRepo, DepartmentRepository departmentRepo) {
		this.employeeRepo = employeeRepo;
		this.departmentRepo = departmentRepo;
	}
	
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}
	
	public Employee getEmployeeById(Long id) {
		return employeeRepo.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Khong tim thay Id:" + id));
	}
	
	public Employee addEmployee (Employee employee) {
		try {
			resolveAndSetDepartment(employee);
			Employee saved = employeeRepo.save(employee);
			log.info("Tạo nhân viên mới: id={}, name={}, email={}", saved.getId(), saved.getName(), saved.getEmail());
            return saved;
		}
		catch(Exception ex) {
			log.error("Lỗi khi tạo nhân viên: name={}, error={}", employee.getName(), ex.getMessage(), ex);
            throw ex;
		}
	}
	
	public List<Employee> searchByName(String name) {
		return employeeRepo.findByNameContainingIgnoreCase(name);
	}
	
	public List<Employee> searchByDepartment(Long department_id) {
		Department department = departmentRepo.findById(department_id)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng ban ID: " + department_id));
		return department != null ? employeeRepo.findByDepartment(department) : List.of();
	}
	
	public Employee updateEmployee(Long id, Employee updatedEmployee) {
	    Employee existing = employeeRepo.findById(id)
	    		.orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy nhân viên: "+ id));
	    try {
	    	existing.setName(updatedEmployee.getName());
	        existing.setEmail(updatedEmployee.getEmail());
	        existing.setDepartment(updatedEmployee.getDepartment());
	        Employee saved = employeeRepo.save(existing);
	        log.info("Cập nhật nhân viên: id={}, name={}, email={}", saved.getId(), saved.getName(), saved.getEmail());
	        return saved;
	    }
	    catch (Exception ex) {
	    	log.error("Lỗi khi cập nhật nhân viên id={}: {}", id, ex.getMessage(), ex);
            throw ex;
	    }
	}

	public void deleteEmployee(Long id) {
		Employee existing = employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhân viên ID: " + id));
        employeeRepo.delete(existing);
        log.info("Xóa nhân viên: id={}, name={}", existing.getId(), existing.getName());
	}
	
	public void resolveAndSetDepartment(Employee employee) {
		if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department d = departmentRepo.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng ban ID: " + employee.getDepartment().getId()));
            employee.setDepartment(d);
        } else {
            employee.setDepartment(null);
        }
	}
}

//Spring nhìn thấy EmployeeService có một constructor nhận EmployeeRepository
//→ tự động tìm EmployeeRepository trong IoC Container
//→ tạo đối tượng EmployeeService và truyền EmployeeRepository vào.
//Bạn không cần dùng @Autowired nữa, vì Spring Boot 3+ tự inject nếu chỉ có 1 constructor duy nhất.

// module 7 : Trước khi save/update, ta resolveAndSetDepartment() để lấy Department thật từ DB (tránh detached entity issues).