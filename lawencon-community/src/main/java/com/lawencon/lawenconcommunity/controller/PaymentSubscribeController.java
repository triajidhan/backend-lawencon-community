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
	
	@GetMapping("id/{id}")
	public ResponseEntity<PaymentSubscribe> getById(@PathVariable("id") String paymentSubscribeId){
		final PaymentSubscribe paymentSubscribe = paymentSubscribeService.getById(paymentSubscribeId);
		
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
	
	
	@GetMapping("payment-approved")
	public ResponseEntity<List<PaymentSubscribe>> getByPaymentApproved(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentSubscribe> paymentSubscribe = paymentSubscribeService.getByPaymentApproved(startPosition, limit, false);
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("total-payment-approved")
	public ResponseEntity<PaymentSubscribe> getTotalByPaymentApproved(){
		PaymentSubscribe paymentSubscribe = paymentSubscribeService.getTotalByPaymentApproved();
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("payment-reject")
	public ResponseEntity<List<PaymentSubscribe>> getByPaymentReject(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentSubscribe> paymentSubscribe = paymentSubscribeService.getByPaymentReject(startPosition, limit, false);
		
		return new ResponseEntity<>(paymentSubscribe,HttpStatus.OK);
	}
	
	@GetMapping("total-payment-reject")
	public ResponseEntity<PaymentSubscribe> getTotalByPaymentReject(){
		PaymentSubscribe paymentSubscribe = paymentSubscribeService.getTotalByPaymentReject();
		
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
