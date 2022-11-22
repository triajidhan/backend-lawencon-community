package com.lawencon.lawenconcommunity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.PaymentActivityDetail;
import com.lawencon.lawenconcommunity.model.PaymentSubscribe;
import com.lawencon.lawenconcommunity.service.PaymentActivityDetailService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("payment-activity-details")
public class PaymentActivityDetailController {

	private PaymentActivityDetailService paymentActivityDetailService;
	
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
