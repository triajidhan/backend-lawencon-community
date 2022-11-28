package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.service.BalanceService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("balances")
public class BalanceController {

	@Autowired
	private BalanceService balanceService;
	
	@GetMapping()
	public ResponseEntity<List<Balance>> getAll(){
		List<Balance> balances = balanceService.getAll();
		
		return new ResponseEntity<>(balances,HttpStatus.OK);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<List<Balance>> getById(@PathVariable("id") String id){
		List<Balance> balances = balanceService.getAll();
		
		return new ResponseEntity<>(balances,HttpStatus.OK);
	}
}
