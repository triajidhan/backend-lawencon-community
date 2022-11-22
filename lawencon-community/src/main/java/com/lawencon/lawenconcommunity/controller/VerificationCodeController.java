package com.lawencon.lawenconcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.dto.VerificationCodeDto;
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.lawenconcommunity.service.VerificationCodeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("verification-code")
public class VerificationCodeController {
	@Autowired
	private VerificationCodeService verificationCodeService;

	@PostMapping("/generate")
	public ResponseEntity<ResponseMessageDto> generateCode(@RequestBody User data) {
		final ResponseMessageDto responseDto =verificationCodeService.generateVerificationCode(data.getFullName(),data.getEmail());
		return new ResponseEntity<ResponseMessageDto>(responseDto, HttpStatus.OK);
	}
	@PostMapping("/validate")
	public ResponseEntity<ResponseMessageDto> validateCode(@RequestBody VerificationCodeDto data) {
		final ResponseMessageDto responseDto = verificationCodeService.validateCode(data);
		return new ResponseEntity<ResponseMessageDto>(responseDto, HttpStatus.OK);
	}
}
