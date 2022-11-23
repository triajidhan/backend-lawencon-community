package com.lawencon.lawenconcommunity.controller;

import java.util.List;

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
import com.lawencon.lawenconcommunity.model.PaymentActivityDetail;
import com.lawencon.lawenconcommunity.service.PaymentActivityDetailService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("payment-activity-details")
public class PaymentActivityDetailController {

	private PaymentActivityDetailService paymentActivityDetailService;
	
	@GetMapping()
	public ResponseEntity<List<PaymentActivityDetail>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<PaymentActivityDetail> paymentActivityDetails = paymentActivityDetailService.getAll(startPosition, limit);
		
		return new ResponseEntity<>(paymentActivityDetails,HttpStatus.OK);
	}
	
	@GetMapping("{id}/get")
	public ResponseEntity<PaymentActivityDetail> getById(@PathVariable("id") String paymentActivityId){
		final PaymentActivityDetail paymentActivity = paymentActivityDetailService.getById(paymentActivityId);
		
		return new ResponseEntity<>(paymentActivity,HttpStatus.OK);
	}
	
	@GetMapping("activity")
	public ResponseEntity<List<PaymentActivityDetail>> getByActivity(@RequestParam("activityId") String activityId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<PaymentActivityDetail> paymentActivityDetails = paymentActivityDetailService.getByActivity(activityId,startPosition, limit);
		
		return new ResponseEntity<>(paymentActivityDetails,HttpStatus.OK);
	}
	
	@GetMapping("total-activity")
	public ResponseEntity<PaymentActivityDetail> getTotalByActivity(@RequestParam("activityId") String activityId){
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailService.getTotalByActivity(activityId);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<PaymentActivityDetail>> getByIsActive(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getByIsActive(startPosition, limit);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody PaymentActivityDetail data){
		final ResponseMessageDto paymentSubscribeInsertResDto = paymentActivityDetailService.insert(data);
		return new ResponseEntity<>(paymentSubscribeInsertResDto, HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody PaymentActivityDetail data){
		final ResponseMessageDto paymentSubscribeUpdatetResDto = paymentActivityDetailService.update(data);
		return new ResponseEntity<>(paymentSubscribeUpdatetResDto, HttpStatus.CREATED);
	}
}
