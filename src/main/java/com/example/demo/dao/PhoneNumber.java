package com.example.demo.dao;

import com.example.demo.enums.PhoneType;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PhoneNumber {
	
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private PhoneType type;

}
