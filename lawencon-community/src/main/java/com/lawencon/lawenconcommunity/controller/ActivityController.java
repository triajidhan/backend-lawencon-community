package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("id/{id}")
	public ResponseEntity<Activity> getById(@PathVariable("id") String activityId){
		final Activity activities = activityService.getById(activityId);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("activity-type-code-order")
	public ResponseEntity<List<Activity>> getByActivityTypeCode(@RequestParam("activityTypeCode") String activityTypeCode, @RequestParam("startPosition") int startPosition, @RequestParam("limit") int limitPage, @RequestParam("asc") boolean ascending){
		final List<Activity> activities = activityService.getByActivityTypeCode(activityTypeCode,startPosition,limitPage,ascending);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("is-active-order")
	public ResponseEntity<List<Activity>> getByIsActiveAndOrder(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage,@RequestParam("asc") boolean ascending){
		final List<Activity> activities = activityService.getByIsActiveAndOrder(startPosition, limitPage,ascending);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("users")
	public ResponseEntity<List<Activity>> getByUser(@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage,@RequestParam("asc") boolean ascending){
		final List<Activity> activities = activityService.getByUser(userId,startPosition, limitPage,ascending);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("users-activity-type-code")
	public ResponseEntity<List<Activity>> getByUserAndActivityTypeCode(@RequestParam("userId") String userId,@RequestParam("activityTypeCode") String activityTypeCode,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage,@RequestParam("asc") boolean ascending){
		final List<Activity> activities = activityService.getByUserAndActivityTypeCode(userId,activityTypeCode,startPosition, limitPage,ascending);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Activity data){
		final ResponseMessageDto responseMessageDto = activityService.insert(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
	}
}
