package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.model.Polling;
import com.lawencon.lawenconcommunity.service.PollingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("pollings")
public class PollingController {

	@Autowired
	private PollingService pollingService;
	
	@GetMapping
	public ResponseEntity<List<Polling>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Polling> pollings = pollingService.getAll(startPosition, limitPage);
		
		return new ResponseEntity<>(pollings,HttpStatus.OK);
	}
	
	@GetMapping("post")
	public ResponseEntity<List<Polling>> getByPost(@RequestParam("postId") String postId){
		final List<Polling> pollings = pollingService.getByPost(postId);
		
		return new ResponseEntity<>(pollings,HttpStatus.OK);
	}
	
	@GetMapping("user")
	public ResponseEntity<List<Polling>> getByUser(@RequestParam("userId") String userId){
		final List<Polling> pollings = pollingService.getByUser(userId);
		
		return new ResponseEntity<>(pollings,HttpStatus.OK);
	}
	
	
}
