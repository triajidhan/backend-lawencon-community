package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
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
	public ResponseEntity<List<Position>> getAll(){
		List<Position> positions = positionService.getAll();
		
		return new ResponseEntity<>(positions,HttpStatus.OK);
		
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<Position>> getAllByIsActive(){
		List<Position> positions = positionService.getByIsActive();
		
		return new ResponseEntity<>(positions,HttpStatus.OK);
		
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<Position>> getAllByIsActive(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<Position> positions = positionService.getByIsActive(startPosition, limit);
		
		return new ResponseEntity<>(positions,HttpStatus.OK);
		
	}
	
	@GetMapping("{id}/get")
	public ResponseEntity<Position> getById(@PathVariable String id){
		Position position = positionService.getById(id);
		
		return new ResponseEntity<>(position,HttpStatus.OK);
	}
	
	@GetMapping("total-position")
	public ResponseEntity<Integer> getTotalPosition(){
		int totalIndustry = positionService.getTotalPosition();
		
		return new ResponseEntity<>(totalIndustry,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Position data){
		final ResponseMessageDto positionInsertResDto = positionService.insert(data);
		return new ResponseEntity<>(positionInsertResDto, HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody Position data){
		final ResponseMessageDto positionUpdateResDto = positionService.update(data);
		return new ResponseEntity<>(positionUpdateResDto, HttpStatus.CREATED);
	}
}
