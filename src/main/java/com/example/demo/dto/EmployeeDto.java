package com.example.demo.dto;

import java.util.List;

import com.example.demo.dao.PhoneNumber;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private List<PhoneNumber> phoneNumbers;
	private Integer totalExperience;
	private String departmentCode;
	private String branchCode;

}
