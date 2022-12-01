package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping
	public ResponseEntity<List<Activity>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Activity> activities = activityService.getAll(startPosition, limitPage);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Activity> getById(@PathVariable("id") String activityId){
		final Activity activities = activityService.getById(activityId);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("activity-code")
	public ResponseEntity<Activity> getByActivityCode(@RequestParam("activityCode") String activityCode){
		final Activity activity = activityService.getByActivityCode(activityCode);
		
		return new ResponseEntity<>(activity,HttpStatus.OK);
	}
	
	@GetMapping("total-activity")
	public ResponseEntity<Activity> getTotalActivity(){
		Activity activity = activityService.getTotalByActivity();
		
		return new ResponseEntity<>(activity,HttpStatus.OK);
	}
	
	@GetMapping("activity-type")
	public ResponseEntity<List<Activity>> getByActivityType(@RequestParam("activityTypeId") String activityTypeId, @RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Activity> activities = activityService.getByActivityType(activityTypeId,startPosition,limitPage);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("total-activity-type")
	public ResponseEntity<Activity> getTotalByActivityType(@RequestParam("activityTypeId") String activityTypeId){
		Activity activity = activityService.getTotalByActivityType(activityTypeId);
		
		return new ResponseEntity<>(activity,HttpStatus.OK);
	}
	
	@GetMapping("activity-type-code")
	public ResponseEntity<List<Activity>> getByActivityTypeCode(@RequestParam("activityTypeCode") String activityTypeCode, @RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Activity> activities = activityService.getByActivityTypeCode(activityTypeCode,startPosition,limitPage);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("activity-type-code-order")
	public ResponseEntity<List<Activity>> getByActivityTypeCode(@RequestParam("activityTypeCode") String activityTypeCode, @RequestParam("startPosition") int startPosition, @RequestParam("limit") int limitPage, @RequestParam("asc") boolean ascending){
		final List<Activity> activities = activityService.getByActivityTypeCode(activityTypeCode,startPosition,limitPage,ascending);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("total-activity-type-code")
	public ResponseEntity<Activity> getTotalByActivityCode(@RequestParam("activityTypeCode") String activityTypeCode){
		Activity activity = activityService.getTotalByActivityTypeCode(activityTypeCode);
		
		return new ResponseEntity<>(activity,HttpStatus.OK);
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<Activity>> getByIsActive(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Activity> activities = activityService.getByIsActive(startPosition, limitPage);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("is-active-all")
	public ResponseEntity<List<Activity>> getByIsActive(){
		final List<Activity> activities = activityService.getByIsActive();
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("is-active-order")
	public ResponseEntity<List<Activity>> getByIsActiveAndOrder(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage,@RequestParam("asc") boolean ascending){
		final List<Activity> activities = activityService.getByIsActiveAndOrder(startPosition, limitPage,ascending);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("is-active-order-greater-now")
	public ResponseEntity<List<Activity>> getByIsActiveAndGreaterDateTimeNowAndOrder(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage,@RequestParam("asc") boolean ascending){
		final List<Activity> activities = activityService.getByIsActiveAndGreaterDateTimeNowAndOrder(startPosition, limitPage,ascending);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("users")
	public ResponseEntity<List<Activity>> getByUser(@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage,@RequestParam("asc") boolean ascending){
		final List<Activity> activities = activityService.getByUser(userId,startPosition, limitPage,ascending);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@GetMapping("users-activity-type-code")
	public ResponseEntity<List<Activity>> getByUserAndActivityTypeCode(@RequestParam("userId") String userId,@RequestParam("activityTypeCode") String activityTypeCode,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage,@RequestParam("asc") boolean ascending){
		final List<Activity> activities = activityService.getByUserAndActivityTypeCode(userId,activityTypeCode,startPosition, limitPage,ascending);
		
		return new ResponseEntity<>(activities,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Activity data){
		final ResponseMessageDto responseMessageDto = activityService.insert(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
	}
}
