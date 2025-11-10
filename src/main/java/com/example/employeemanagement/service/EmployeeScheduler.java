package com.example.employeemanagement.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.employeemanagement.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeScheduler {
	
	private final EmployeeRepository employeeRepo;
	
	@Scheduled(fixedRate = 60000)
    public void logEmployeeCount() {
        long count = employeeRepo.count();
        log.info("Tổng số nhân viên hiện tại: {}", count);
    }
    
    @Scheduled(fixedRate = 30000)
    public void logSystemRunning() {
        log.info("System running");
    }
}

//Tự động chạy nhiệm vụ theo thời gian
//Ví dụ: backup dữ liệu, gửi email, xóa log cũ…

//fixedRate = 10000: chạy lặp lại mỗi 10 giây
//Có thể dùng:
//fixedDelay (chạy sau khi xong hàm)
//cron (định dạng CRON giống Linux)