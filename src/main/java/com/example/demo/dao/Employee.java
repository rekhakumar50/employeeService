package com.example.demo.dao;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(name="phone_number", nullable = false)
	@ElementCollection
	@CollectionTable(name="emp_phone_numbers", joinColumns=@JoinColumn(name="emp_id"))
	private List<PhoneNumber> phoneNumbers;
	
	@Column(nullable = false)
	private Integer totalExperience;
	
	@Column(nullable = false)
	private String departmentCode;
	
	@Column(nullable = false)
	private String branchCode;

}
