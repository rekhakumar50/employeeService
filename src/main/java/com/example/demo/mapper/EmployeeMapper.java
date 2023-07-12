package com.example.demo.mapper;

import java.util.Objects;

import com.example.demo.dao.Employee;
import com.example.demo.dto.EmployeeDto;

public interface EmployeeMapper {
	
	public static EmployeeDto convertToEmployeeDto(Employee employee) {
		EmployeeDto employeeDto = null;
		if(Objects.nonNull(employee)) {
			employeeDto = new EmployeeDto();
			employeeDto.setId(employee.getId());
			employeeDto.setFirstName(employee.getFirstName());
			employeeDto.setLastName(employee.getLastName());
			employeeDto.setEmail(employee.getEmail());
			employeeDto.setPhoneNumbers(employee.getPhoneNumbers());
			employeeDto.setTotalExperience(employee.getTotalExperience());
		}
		
		return employeeDto;
	}
	
	public static Employee convertToEmployee(EmployeeDto employeeDto) {
		Employee employee = null;
		if(Objects.nonNull(employeeDto)) {
			employee = new Employee();
			employee.setId(employeeDto.getId());
			employee.setFirstName(employeeDto.getFirstName());
			employee.setLastName(employeeDto.getLastName());
			employee.setEmail(employeeDto.getEmail());
			employee.setPhoneNumbers(employeeDto.getPhoneNumbers());
			employee.setDepartmentCode(employeeDto.getDepartmentCode());
			employee.setBranchCode(employeeDto.getBranchCode());
			employee.setTotalExperience(employeeDto.getTotalExperience());
		}
		
		return employee;
	}
	
}
