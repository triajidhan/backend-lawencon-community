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
	public ResponseEntity<List<Industry>> getAll(){
		List<Industry> industries = industryService.getAll();
		
		return new ResponseEntity<>(industries,HttpStatus.OK);
		
	}
	
	@GetMapping("is-active-all")
	public ResponseEntity<List<Industry>> getAllByIsActive(){
		List<Industry> industries = industryService.getByIsActive();
		
		return new ResponseEntity<>(industries,HttpStatus.OK);
		
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<Industry>> getByIsActive(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<Industry> industries = industryService.getByIsActive(startPosition, limit);
		
		return new ResponseEntity<>(industries,HttpStatus.OK);
	}
	
	@GetMapping("{id}/get")
	public ResponseEntity<Industry> getById(@PathVariable String id){
		Industry industry = industryService.getById(id);
		
		return new ResponseEntity<>(industry,HttpStatus.OK);
	}
	
	@GetMapping("total-industry")
	public ResponseEntity<Industry> getTotalIndustry(){
		Industry industry = industryService.getTotalIndustry();
		
		return new ResponseEntity<>(industry,HttpStatus.OK);
		
	}
	
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Industry data){
		final ResponseMessageDto industryInsertResDto = industryService.insert(data);
		return new ResponseEntity<>(industryInsertResDto, HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody Industry data){
		final ResponseMessageDto industryUpdateResDto = industryService.update(data);
		return new ResponseEntity<>(industryUpdateResDto, HttpStatus.CREATED);
	}
}
