package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.adapter.BranchAdapter;
import com.example.demo.adapter.DepartmentAdapter;
import com.example.demo.dao.Employee;
import com.example.demo.dto.BranchDto;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.EmployeeResponseMapper;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentAdapter departmentAdapter;
	
	@Autowired
	private BranchAdapter branchAdapter;
	
	public Employee saveEmployee(EmployeeDto employeeDto) {
		Employee employee = null;
		try {
			DepartmentDto departmentDto = departmentAdapter.invokeAdapter(employeeDto.getDepartmentCode());
			BranchDto branchDto = branchAdapter.invokeAdapter(employeeDto.getBranchCode());
			if(Objects.nonNull(departmentDto) && Objects.nonNull(branchDto)) {
				employee = employeeRepository.save(EmployeeMapper.convertToEmployee(employeeDto));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return employee;
	}
	
	public List<EmployeeResponseDto> getEmployees() {
		List<EmployeeResponseDto> employeeDtos = new ArrayList<>();
		List<Employee> employees = employeeRepository.findAll();
		
		if(CollectionUtils.isNotEmpty(employees)) {
			employeeDtos = employees.stream()
									.filter(Objects::nonNull)
									.map(employeeDepartment)
									.filter(Objects::nonNull)
									.collect(Collectors.toList());
		}
		return employeeDtos;
	}
	
	public EmployeeResponseDto getEmployeeByEmail(String email) {
		EmployeeResponseDto employeeResponseDto = null;
		if(StringUtils.isNotEmpty(email)) {
			Employee employee = employeeRepository.findByEmail(email);
			DepartmentDto departmentDto = departmentAdapter.invokeAdapter(employee.getDepartmentCode());
			BranchDto branchDto = branchAdapter.invokeAdapter(employee.getBranchCode());
			employeeResponseDto = EmployeeResponseMapper.convertToEmployeeResponseDto(employee, departmentDto, branchDto);
		}
		return employeeResponseDto;
	}
	
	Function<Employee, EmployeeResponseDto> employeeDepartment = employee -> {
		DepartmentDto departmentDto = departmentAdapter.invokeAdapter(employee.getDepartmentCode());
		BranchDto branchDto = branchAdapter.invokeAdapter(employee.getBranchCode());
		return EmployeeResponseMapper.convertToEmployeeResponseDto(employee, departmentDto, branchDto);
	};

}
