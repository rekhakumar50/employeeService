package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Employee findByEmail(String email);

}
