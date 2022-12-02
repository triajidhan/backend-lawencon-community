package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lawencon.lawenconcommunity.model.PaymentSubscribe;
import com.lawencon.lawenconcommunity.service.PaymentSubscribeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("payment-subscribes")
public class PaymentSubscribeController {

	@Autowired
	private PaymentSubscribeService paymentSubscribeService;
	
	@GetMapping()
	public ResponseEntity<List<PaymentSubscribe>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<PaymentSubscribe> paymentSubscribe = paymentSubscribeService.getAll(startPosition, limit);
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<PaymentSubscribe> getById(@PathVariable("id") String paymentSubscribeId){
		final PaymentSubscribe paymentSubscribe = paymentSubscribeService.getById(paymentSubscribeId);
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<PaymentSubscribe>> getAllByIsActive(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<PaymentSubscribe> paymentSubscribe = paymentSubscribeService.getByIsActive(startPosition, limit);
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("total")
	public ResponseEntity<PaymentSubscribe> getTotalPayment(){
		PaymentSubscribe paymentSubscribe = paymentSubscribeService.getTotalPaymentSubscribe();
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("is-active-false")
	public ResponseEntity<List<PaymentSubscribe>> getByIsActiveFalse(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentSubscribe> paymentSubscribe = paymentSubscribeService.getByIsActiveFalse(startPosition, limit, false);
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("is-active-approve-false")
	public ResponseEntity<List<PaymentSubscribe>> getByIsActiveTrueAndApprovedFalse(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentSubscribe> paymentSubscribe = paymentSubscribeService.getByIsActiveTrueAndApprovedFalse(startPosition, limit, false);
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("total-is-active-approve-false")
	public ResponseEntity<PaymentSubscribe> getTotalByIsActiveTrueAndApprovedFalse(){
		PaymentSubscribe paymentSubscribe = paymentSubscribeService.getTotalByIsActiveTrueAndApprovedFalse();
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("is-active-approve-true")
	public ResponseEntity<List<PaymentSubscribe>> getByIsActiveTrueAndApprovedTrue(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentSubscribe> paymentSubscribe = paymentSubscribeService.getByIsActiveTrueAndApprovedFalse(startPosition, limit, false);
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("total-is-active-approve-true")
	public ResponseEntity<PaymentSubscribe> getTotalByIsActiveTrueAndApprovedTrue(){
		PaymentSubscribe paymentSubscribe = paymentSubscribeService.getTotalByIsActiveTrueAndApprovedTrue();
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
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
