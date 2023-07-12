package com.example.demo.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Employee;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		try {
			Employee emp = employeeService.saveEmployee(employeeDto);
			if(Objects.nonNull(emp)) {
				return new ResponseEntity<String>("Employee Data Saved!!", HttpStatus.CREATED);
			}
			return new ResponseEntity<String>("Employee Data Not Saved!!", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(Exception e) {
			return new ResponseEntity<String>("Employee Data Not Saved!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDto>> getEmployees() {
		List<EmployeeResponseDto> employeeDtos = employeeService.getEmployees();
		return new ResponseEntity<List<EmployeeResponseDto>>(employeeDtos, HttpStatus.OK);
	}
	
	@GetMapping("/email")
	public ResponseEntity<EmployeeResponseDto> getDepartmentByCode(@RequestParam String email) {
		EmployeeResponseDto employeeDto = employeeService.getEmployeeByEmail(email);
		return new ResponseEntity<EmployeeResponseDto>(employeeDto, HttpStatus.OK);
	}

}
