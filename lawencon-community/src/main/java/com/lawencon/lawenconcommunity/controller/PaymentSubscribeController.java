package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lawencon.lawenconcommunity.model.PaymentSubscribe;
import com.lawencon.lawenconcommunity.service.PaymentSubscribeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("payment-subscribes")
public class PaymentSubscribeController {

	@Autowired
	private PaymentSubscribeService paymentSubscribeService;
	
	@GetMapping("is-active")
	public ResponseEntity<List<PaymentSubscribe>> getAllByIsActive(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<PaymentSubscribe> industries = paymentSubscribeService.getByIsActive(startPosition, limit);
		
		return new ResponseEntity<>(industries,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody PaymentSubscribe data){
		final ResponseMessageDto paymentSubscribeInsertResDto = paymentSubscribeService.insert(data);
		return new ResponseEntity<>(paymentSubscribeInsertResDto, HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody PaymentSubscribe data){
		final ResponseMessageDto paymentSubscribeInsertResDto = paymentSubscribeService.update(data);
		return new ResponseEntity<>(paymentSubscribeInsertResDto, HttpStatus.CREATED);
	}
}