package com.example.employeemanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Appconfig {

    // Bean này dùng để mã hóa mật khẩu (sẽ cần trong module 9 - Security)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean này dùng để ánh xạ giữa Entity và DTO
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}