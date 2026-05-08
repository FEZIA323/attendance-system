package com.example.attendance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@MapperScan("com.example.attendance.dao")
public class AttendanceSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(AttendanceSystemApplication.class, args);
	}
}