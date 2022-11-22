package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("is-active")
	public ResponseEntity<List<PaymentActivityDetail>> getAllByIsActive(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
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
		final ResponseMessageDto paymentSubscribeInsertResDto = paymentActivityDetailService.update(data);
		return new ResponseEntity<>(paymentSubscribeInsertResDto, HttpStatus.CREATED);
	}
}
