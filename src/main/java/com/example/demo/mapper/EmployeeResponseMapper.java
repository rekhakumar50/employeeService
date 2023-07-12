package com.example.demo.mapper;

import java.util.Objects;

import com.example.demo.dao.Employee;
import com.example.demo.dto.BranchDto;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeResponseDto;

public class EmployeeResponseMapper {
	
	public static EmployeeResponseDto convertToEmployeeResponseDto(
			Employee employee, DepartmentDto departmentDto, BranchDto branchdto) {
		EmployeeResponseDto employeeResponseDto = null;
		if(Objects.nonNull(employee)) {
			employeeResponseDto = new EmployeeResponseDto();
			employeeResponseDto.setId(employee.getId());
			employeeResponseDto.setFirstName(employee.getFirstName());
			employeeResponseDto.setLastName(employee.getLastName());
			employeeResponseDto.setEmail(employee.getEmail());
			employeeResponseDto.setPhoneNumbers(employee.getPhoneNumbers());
			employeeResponseDto.setTotalExperience(employee.getTotalExperience());
		}
		if(Objects.nonNull(departmentDto)) {
			employeeResponseDto.setDepartment(departmentDto);
		}
		if(Objects.nonNull(branchdto)) {
			employeeResponseDto.setBranch(branchdto);
		}
		return employeeResponseDto;
	}

}
