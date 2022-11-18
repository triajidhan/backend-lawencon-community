package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Activity;
import com.lawencon.lawenconcommunity.service.ActivityService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@GetMapping
	public ResponseEntity<List<Activity>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Activity> activities = activityService.getAll(startPosition, limitPage);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("activity-code")
	public ResponseEntity<Activity> getByActivityCode(@RequestParam("activityCode") String activityCode){
		final Activity activity = activityService.getByActivityCode(activityCode);
		
		return new ResponseEntity<>(activity,HttpStatus.OK);
	}
	
	@GetMapping("activity-type")
	public ResponseEntity<List<Activity>> getByActivity(@RequestParam("activityTypeId") String activityTypeId){
		final List<Activity> activities = activityService.getByActivityType(activityTypeId);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Activity data){
		final ResponseMessageDto responseMessageDto = activityService.insert(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
	}
	
}
