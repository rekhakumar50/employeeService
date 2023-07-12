package com.example.demo.adapter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.BranchDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class BranchAdapter {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${branch.api.url}")
	private String branchEndpointUrl;
	
	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "branchFallBackMethod")
	public BranchDto invokeAdapter(final String branchCode) {
		BranchDto branchDto = null;
		ResponseEntity<BranchDto> responseEntity = null;
		try {
			responseEntity = restTemplate.getForEntity(branchEndpointUrl + branchCode, BranchDto.class);
			if(Objects.nonNull(responseEntity) && responseEntity.getStatusCode() == HttpStatus.OK) {
				branchDto = responseEntity.getBody();
			}
		} catch(Exception e) {
			System.out.println("Branch Service is not available");
		}
		return branchDto;
	}
	
	
	public BranchDto branchFallBackMethod(final String branchCode, Exception e) {
		System.out.println("Branch Service is not available");
		return null;
	}
	
}
