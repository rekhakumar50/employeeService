package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchDto {
	
	private Long id;
	private String branchName;
	private String branchDescription;
	private String branchCode;
	private AddressDto address;
	
}
