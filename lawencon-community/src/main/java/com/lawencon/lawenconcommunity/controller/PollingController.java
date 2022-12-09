package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Polling;
import com.lawencon.lawenconcommunity.service.PollingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("pollings")
public class PollingController {

	@Autowired
	private PollingService pollingService;
	
	@GetMapping("id/{id}")
	public ResponseEntity<Polling> getById(@PathVariable("id") String pollingId){
		final Polling polling = pollingService.getById(pollingId);
		
		return new ResponseEntity<>(polling,HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody Polling data){
		final ResponseMessageDto pollingUpdateResDto = pollingService.update(data);
		return new ResponseEntity<>(pollingUpdateResDto, HttpStatus.OK);
	}
}
