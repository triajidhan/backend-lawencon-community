package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.model.PollingStatus;
import com.lawencon.lawenconcommunity.service.PollingStatusService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("polling-status")
public class PollingStatusController {

	@Autowired
	private PollingStatusService pollingStatusService; 
	
	@GetMapping("user-posting")
	public ResponseEntity<PollingStatus> getByUserAndPosting(@RequestParam("userId")String userId,@RequestParam("pollingId")String postingId){
		final PollingStatus pollingStatus = pollingStatusService.getByUserAndPosting(userId,postingId);
		
		return new ResponseEntity<>(pollingStatus,HttpStatus.OK);
	}

}
