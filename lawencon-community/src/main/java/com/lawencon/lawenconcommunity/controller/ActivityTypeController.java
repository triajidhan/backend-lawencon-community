package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.model.ActivityType;
import com.lawencon.lawenconcommunity.service.ActivityTypeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("activity-types")
public class ActivityTypeController {

	@Autowired
	private ActivityTypeService activityTypeService;
	
	@GetMapping
	public ResponseEntity<List<ActivityType>> getAll(){
		final List<ActivityType> activityTypes = activityTypeService.getAll();
		
		return new ResponseEntity<>(activityTypes,HttpStatus.OK);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<ActivityType> getById(@PathVariable("id") String activityTypeId){
		final ActivityType activities = activityTypeService.getById(activityTypeId);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<ActivityType>> getByIsActive(){
		final List<ActivityType> activityTypes = activityTypeService.getByIsActive();
		
		return new ResponseEntity<>(activityTypes,HttpStatus.OK);
	}
}
