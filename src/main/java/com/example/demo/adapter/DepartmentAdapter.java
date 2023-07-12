package com.example.demo.adapter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.DepartmentDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class DepartmentAdapter {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${department.api.url}")
	private String departmentEndpointUrl;
	
	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "departmentFallBackMethod")
	public DepartmentDto invokeAdapter(final String departmentCode) {
		DepartmentDto departmentDto = null;
		ResponseEntity<DepartmentDto> responseEntity = null;
		try {
			responseEntity = restTemplate.getForEntity(departmentEndpointUrl + departmentCode, DepartmentDto.class);
			if(Objects.nonNull(responseEntity) && responseEntity.getStatusCode() == HttpStatus.OK) {
				departmentDto = responseEntity.getBody();
			}
		} catch(Exception e) {
			System.out.println("Depaertment Service is not available");
		}
		return departmentDto;
	}
	
	
	public DepartmentDto departmentFallBackMethod(final String departmentCode, Exception e) {
		System.out.println("Department Service is not available");
		return null;
	}

}
