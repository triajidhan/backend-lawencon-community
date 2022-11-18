package com.lawencon.lawenconcommunity.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.dto.VerificationCodeDto;
import com.lawencon.util.MailUtil;
import com.lawencon.util.VerificationCodeUtil;

@Service
public class VerificationCodeService extends BaseCoreService {
	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private VerificationCodeUtil verificationCodeUtil;
	
	@Autowired
	private GenerateService generateService;

	public ResponseMessageDto generateVerificationCode(String fullName, String email) {
		final Map<String, Object> template = new HashMap<>();
		final String code = generateService.generate(5);
		template.put("fullName", fullName);
		template.put("verificationCode", code);
		final String subject = "Verification Code for Activating Account";
		try {
			mailUtil.sendMessageFreeMarker(email, subject, template, "verification-code-template.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
		verificationCodeUtil.addVerificationCode(email, code);
		ResponseMessageDto responseDto = new ResponseMessageDto();
		responseDto.setMessage("Verification Code Sent to your email. Please Check your Email.");
		return responseDto;
	}
	
	public ResponseMessageDto validateCode(VerificationCodeDto data) {
		final ResponseMessageDto responseDto = new ResponseMessageDto();
			verificationCodeUtil.validateVerificationCode(data.getEmail(), data.getCode());
			responseDto.setMessage("Verification Success");
		return responseDto;
		
	}
}

