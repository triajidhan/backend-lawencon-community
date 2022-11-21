package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.model.Industry;
import com.lawencon.lawenconcommunity.service.IndustryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("industries")
public class IndustryController {

	@Autowired
	private IndustryService industryService; 
	
	@GetMapping
	public ResponseEntity<List<Industry>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<Industry> industries = industryService.getIsActive(startPosition, limit);
		
		return new ResponseEntity<>(industries,HttpStatus.OK);
		
	}
	
	@GetMapping("total-industry")
	public ResponseEntity<Integer> getTotalIndustry(){
		int totalIndustry = industryService.getTotalIndustry();
		
		return new ResponseEntity<>(totalIndustry,HttpStatus.OK);
		
	}
}
