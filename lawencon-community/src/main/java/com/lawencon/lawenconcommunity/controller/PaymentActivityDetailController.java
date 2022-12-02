package com.lawencon.lawenconcommunity.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	
	@GetMapping()
	public ResponseEntity<List<PaymentActivityDetail>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<PaymentActivityDetail> paymentActivityDetails = paymentActivityDetailService.getAll(startPosition, limit);
		
		return new ResponseEntity<>(paymentActivityDetails,HttpStatus.OK);
	}
	
	@GetMapping("total")
	public ResponseEntity<PaymentActivityDetail> getTotalPaymentActivity(){
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailService.getTotalPaymentActivityDetail();
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<PaymentActivityDetail> getById(@PathVariable("id") String paymentActivityId){
		final PaymentActivityDetail paymentActivity = paymentActivityDetailService.getById(paymentActivityId);
		
		return new ResponseEntity<>(paymentActivity,HttpStatus.OK);
	}
	
	@GetMapping("activity-all")
	public ResponseEntity<List<PaymentActivityDetail>> getByActivity(@RequestParam("activityId") String activityId){
		List<PaymentActivityDetail> paymentActivityDetails = paymentActivityDetailService.getByActivity(activityId);
		
		return new ResponseEntity<>(paymentActivityDetails,HttpStatus.OK);
	}
	
	@GetMapping("activity")
	public ResponseEntity<List<PaymentActivityDetail>> getByActivity(@RequestParam("activityId") String activityId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		List<PaymentActivityDetail> paymentActivityDetails = paymentActivityDetailService.getByActivity(activityId,startPosition, limit);
		
		return new ResponseEntity<>(paymentActivityDetails,HttpStatus.OK);
	}
	
	@GetMapping("users")
	public ResponseEntity<List<PaymentActivityDetail>> getByUser(@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = paymentActivityDetailService.getByUser(userId,startPosition, limit,ascending);
		
		return new ResponseEntity<>(paymentActivityDetails,HttpStatus.OK);
	}
	
	@GetMapping("activity-type-user")
	public ResponseEntity<List<PaymentActivityDetail>> getByActivityTypeAndUser(@RequestParam("activityTypeId") String activityId,@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = paymentActivityDetailService.getByActivityTypeAndUserOrder(activityId,userId,startPosition, limit, ascending);
		
		return new ResponseEntity<>(paymentActivityDetails,HttpStatus.OK);
	}
	
	@GetMapping("activity-type-code-user")
	public ResponseEntity<List<PaymentActivityDetail>> getByActivityTypeCodeAndUser(@RequestParam("activityTypeCode") String activityCode,@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = paymentActivityDetailService.getByActivityTypeCodeAndUserOrder(activityCode,userId,startPosition, limit, ascending);
		
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
	
	@GetMapping("is-active-all")
	public ResponseEntity<List<PaymentActivityDetail>> getByIsActive(){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getByIsActive();
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("is-active-approve-false")
	public ResponseEntity<List<PaymentActivityDetail>> getByIsActiveTrueAndApprovedFalse(int startPosition,int limit, boolean isAscending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getByIsActiveTrueAndApprovedFalse(startPosition,limit, isAscending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("is-active-false")
	public ResponseEntity<List<PaymentActivityDetail>> getByIsActiveFalse(int startPosition,int limit, boolean isAscending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getByIsActiveFalse(startPosition,limit, isAscending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("partisipatian-member-all")
	public ResponseEntity<List<PaymentActivityDetail>> getReportPartisipation(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportPartisipationMember(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("partisipatian-member")
	public ResponseEntity<List<PaymentActivityDetail>> getReportPartisipation(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportPartisipationMember(beginDate, finishDate,startPosition,limit,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	
	@GetMapping("partisipatian-super-all")
	public ResponseEntity<List<PaymentActivityDetail>> getReportPartisipationSuper(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportPartisipationSuper(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("partisipatian-super")
	public ResponseEntity<List<PaymentActivityDetail>> getReportPartisipationSuper(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportPartisipationMember(beginDate, finishDate,startPosition,limit,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	
	@GetMapping("total-income-member-all")
	public ResponseEntity<List<PaymentActivityDetail>> getReportIncome(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportIncomeMember(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("total-income-member")
	public ResponseEntity<List<PaymentActivityDetail>> getReportIncome(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportIncomeMember(beginDate, finishDate,startPosition,limit,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	
	@GetMapping("total-income-super-all")
	public ResponseEntity<List<PaymentActivityDetail>> getReportIncomeSuper(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportIncomeSuper(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("total-income-super")
	public ResponseEntity<List<PaymentActivityDetail>> getReportIncomeSuper(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetail = paymentActivityDetailService.getReportIncomeSuper(beginDate, finishDate,startPosition,limit,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("report-partisipatian-member")
	public ResponseEntity<List<PaymentPartisipationMemberDto>> getReportPaymentPartisipationMemberDto(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		
		List<PaymentPartisipationMemberDto> paymentActivityDetail = paymentActivityDetailService.getReportPaymentPartisipationMemberDto(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("report-partisipatian-super")
	public ResponseEntity<List<PaymentPartisipationSuperDto>> getReportPaymentPartisipationSuperDto(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		
		List<PaymentPartisipationSuperDto> paymentActivityDetail = paymentActivityDetailService.getReportPaymentPartisipationSuperDto(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	
	@GetMapping("report-total-income-member")
	public ResponseEntity<List<PaymentTotalIncomeMemberDto>> getPaymentTotalIncomeMemberDto(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		List<PaymentTotalIncomeMemberDto> paymentActivityDetail = paymentActivityDetailService.getPaymentTotalIncomeMemberDto(beginDate, finishDate,ascending);
		
		return new ResponseEntity<>(paymentActivityDetail,HttpStatus.OK);
	}
	
	@GetMapping("report-total-income-super")
	public ResponseEntity<List<PaymentTotalIncomeSuperDto>> getPaymentTotalIncomeSuperDto(@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate,@RequestParam("asc") boolean ascending){
		List<PaymentTotalIncomeSuperDto> paymentActivityDetail = paymentActivityDetailService.getPaymentTotalIncomeSuperDto(beginDate, finishDate,ascending);
		
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
