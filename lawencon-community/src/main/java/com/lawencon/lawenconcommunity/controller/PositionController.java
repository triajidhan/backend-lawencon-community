package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.model.Position;
import com.lawencon.lawenconcommunity.service.PositionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("positions")
public class PositionController {

	@Autowired
	private PositionService positionService; 
	
	@GetMapping
	public ResponseEntity<List<Position>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<Position> positions = positionService.getIsActive(startPosition, limit);
		
		return new ResponseEntity<>(positions,HttpStatus.OK);
		
	}
	
	@GetMapping("total-industry")
	public ResponseEntity<Integer> getTotalPosition(){
		int totalIndustry = positionService.getTotalPosition();
		
		return new ResponseEntity<>(totalIndustry,HttpStatus.OK);
		
	}
}
