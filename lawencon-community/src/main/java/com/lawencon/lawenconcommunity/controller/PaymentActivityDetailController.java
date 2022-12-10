package com.lawencon.lawenconcommunity.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.PaymentPartisipationMemberDto;
import com.lawencon.lawenconcommunity.dto.PaymentPartisipationSuperDto;
import com.lawencon.lawenconcommunity.dto.PaymentTotalIncomeMemberDto;
import com.lawencon.lawenconcommunity.dto.PaymentTotalIncomeSuperDto;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.PaymentActivityDetail;
import com.lawencon.lawenconcommunity.service.PaymentActivityDetailService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("payment-activity-details")
public class PaymentActivityDetailController {

	@Autowired
	private PaymentActivityDetailService paymentActivityDetailService;
	
	@PreAuthorize("hasAuthority('A')")
	@GetMapping("id/{id}")
	public ResponseEntity<PaymentActivityDetail> getById(@PathVariable("id") String paymentActivityId){
		final PaymentActivityDetail paymentActivity = paymentActivityDetailService.getById(paymentActivityId);
		
		return new ResponseEntity<>(paymentActivity,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("users")
	public ResponseEntity<List<PaymentActivityDetail>> getByUser(@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = paymentActivityDetailService.getByUser(userId,startPosition, limit,ascending);
		
		return new ResponseEntity<>(paymentActivityDetails,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("activity-type-user")
	public ResponseEntity<List<PaymentActivityDetail>> getByActivityTypeAndUser(@RequestParam("activityTypeId") String activityId,@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = paymentActivityDetailService.getByActivityTypeAndUserOrder(activityId,userId,startPosition, limit, ascending);
		
		return new ResponseEntity<>(paymentActivityDetails,HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('A')")
	@GetMapping("is-active-approve-false")
	public ResponseEntity<List<PaymentActivityDetail>> getByIsActiveTrueAndApprovedFalse(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit, @RequestParam("asc") boolean isAscending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getByIsActiveTrueAndApprovedFalse(startPosition,limit, isAscending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('A')")
	@GetMapping("total-is-active-approve-false")
	public ResponseEntity<PaymentActivityDetail> getTotalByIsActiveTrueAndApprovedFalse(){
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailService.getTotalByIsActiveTrueAndApprovedFalse();
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('A')")
	@GetMapping("payment-approved")
	public ResponseEntity<List<PaymentActivityDetail>> getByPaymentActivityApprove(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit, @RequestParam("asc") boolean isAscending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getByPaymentActivityApprove(startPosition,limit, isAscending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('A')")
	@GetMapping("total-payment-approved")
	public ResponseEntity<PaymentActivityDetail> getTotalByPaymentActivityApprove(){
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailService.getTotalByPaymentActivityApprove();
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('A')")
	@GetMapping("total-payment-approved-activity-type")
	public ResponseEntity<PaymentActivityDetail> getTotalByPaymentActivityApproveAndActivityType(@RequestParam("activityType") String activityTypeId){
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailService.getTotalByPaymentActivityApproveAndActivityType(activityTypeId);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('A')")
	@GetMapping("payment-reject")
	public ResponseEntity<List<PaymentActivityDetail>> getByPaymentActivityReject(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit, @RequestParam("asc") boolean isAscending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getByPaymentActivityReject(startPosition,limit, isAscending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('A')")
	@GetMapping("total-payment-reject")
	public ResponseEntity<PaymentActivityDetail> getTotalByPaymentActivityReject(){
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailService.getTotalByPaymentActivityReject();
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("partisipatian-member")
	public ResponseEntity<List<PaymentActivityDetail>> getReportPartisipation(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportPartisipationMember(beginDate, finishDate,startPosition,limit,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("total-partisipatian-member")
	public ResponseEntity<PaymentActivityDetail> getTotalByReportPartisipation(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate){
		
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailService.getTotalByReportPartisipationMember(beginDate, finishDate);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SA')")
	@GetMapping("partisipatian-super")
	public ResponseEntity<List<PaymentActivityDetail>> getReportPartisipationSuper(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportPartisipationSuper(beginDate, finishDate,startPosition,limit,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SA')")
	@GetMapping("total-partisipatian-super")
	public ResponseEntity<PaymentActivityDetail> getTotalByReportPartisipationSuper(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate){
		
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailService.getTotalByReportPartisipationSuper(beginDate, finishDate);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("total-income-member")
	public ResponseEntity<List<PaymentActivityDetail>> getReportIncome(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportIncomeMember(beginDate, finishDate,startPosition,limit,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("total-total-income-member")
	public ResponseEntity<PaymentActivityDetail> getTotalByReportIncomeMember(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate){
		
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailService.getTotalByReportIncomeMember(beginDate, finishDate);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SA')")
	@GetMapping("total-income-super")
	public ResponseEntity<List<PaymentActivityDetail>> getReportIncomeSuper(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportIncomeSuper(beginDate, finishDate,startPosition,limit,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SA')")
	@GetMapping("total-total-income-super")
	public ResponseEntity<PaymentActivityDetail> getTotalByReportIncomeSuper(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate){
		
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailService.getTotalByReportIncomeSuper(beginDate, finishDate);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("report-partisipatian-member")
	public ResponseEntity<List<PaymentPartisipationMemberDto>> getReportPaymentPartisipationMemberDto(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		
		List<PaymentPartisipationMemberDto> paymentActivityDetail = paymentActivityDetailService.getReportPaymentPartisipationMemberDto(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SA')")
	@GetMapping("report-partisipatian-super")
	public ResponseEntity<List<PaymentPartisipationSuperDto>> getReportPaymentPartisipationSuperDto(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		
		List<PaymentPartisipationSuperDto> paymentActivityDetail = paymentActivityDetailService.getReportPaymentPartisipationSuperDto(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('M')")
	@GetMapping("report-total-income-member")
	public ResponseEntity<List<PaymentTotalIncomeMemberDto>> getPaymentTotalIncomeMemberDto(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		List<PaymentTotalIncomeMemberDto> paymentActivityDetail = paymentActivityDetailService.getPaymentTotalIncomeMemberDto(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SA')")
	@GetMapping("report-total-income-super")
	public ResponseEntity<List<PaymentTotalIncomeSuperDto>> getPaymentTotalIncomeSuperDto(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		List<PaymentTotalIncomeSuperDto> paymentActivityDetail = paymentActivityDetailService.getPaymentTotalIncomeSuperDto(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody PaymentActivityDetail data){
		final ResponseMessageDto paymentSubscribeInsertResDto = paymentActivityDetailService.insert(data);
		return new ResponseEntity<>(paymentSubscribeInsertResDto, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('A')")
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody PaymentActivityDetail data){
		final ResponseMessageDto paymentSubscribeUpdatetResDto = paymentActivityDetailService.update(data);
		return new ResponseEntity<>(paymentSubscribeUpdatetResDto, HttpStatus.CREATED);
	}
}
