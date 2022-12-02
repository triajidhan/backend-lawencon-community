package com.lawencon.lawenconcommunity.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.PaymentPartisipationMemberDto;
import com.lawencon.lawenconcommunity.service.PaymentActivityDetailService;
import com.lawencon.util.JasperUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("reports")
public class ReportController {
	@Autowired
	private JasperUtil jasperUtil;
	
	@Autowired
	private PaymentActivityDetailService paymentActivityDetailService;
	
	@GetMapping("payment-partisipation-member")
	public ResponseEntity<?> PaymentPartisipationMember(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate) throws Exception {
		final List<PaymentPartisipationMemberDto> data = paymentActivityDetailService.getReportPaymentPartisipationMemberDto(beginDate, finishDate, false);
		final byte[] out = jasperUtil.responseToByteArray(data, null, "PaymentPartisipationMember");
		final String fileName = "PaymentPartisipationMember";
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName+ "\"")
				.body(out);
	}
}
