package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.DepartmentService;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeWebController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees/list";
    }
    
    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employees/employee-detail";
    }
    
    @GetMapping("/count")
    public long getEmployeeByCount() {
        return employeeService.getEmployeeCount();
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/form";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employees/form";
        }
        employeeService.addEmployee(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm nhân viên thành công");
        return "redirect:/employees/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee e = employeeService.getEmployeeById(id);
        model.addAttribute("employee", e);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/form";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "employees/form";
        }
        employeeService.updateEmployee(id, employee);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật nhân viên thành công");
        return "redirect:/employees/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        employeeService.deleteEmployee(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa nhân viên thành công");
        return "redirect:/employees/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String name,
                         @RequestParam(required = false) Long departmentId,
                         Model model) {
        List<Employee> results;
        if (name != null && !name.isBlank()) {
            results = employeeService.searchByName(name);
        } else if (departmentId != null) {
            results = employeeService.searchByDepartment(departmentId);
        } else {
            results = List.of();
        }
        model.addAttribute("results", results);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/search";
    }
    
    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        model.addAttribute("statsByDept", employeeService.getEmployeeStatsByDepartment());
        model.addAttribute("totalCount", employeeService.getTotalEmployeeCount());
        return "statistics";
    }
}