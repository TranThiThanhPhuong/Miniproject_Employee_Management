package com.example.employeemanagement.dto;

import java.util.Set;

import com.example.employeemanagement.model.Role;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Set<Role> roles;
}