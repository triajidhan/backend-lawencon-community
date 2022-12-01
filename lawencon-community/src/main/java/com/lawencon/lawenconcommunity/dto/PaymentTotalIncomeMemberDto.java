package com.lawencon.lawenconcommunity.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentTotalIncomeMemberDto {

	private String activityTypeCode;
	private String activityTypeName;
	
	private String activityCode;
	private String title;
	private LocalDateTime beginSchedule;
	private LocalDateTime finishSchedule;
	private String location;
	private Float price;
	private String provider;
	
	private String id;
	private String fullName;
	private String company;
	
	private String positionCode;
	private String positionName;
	
	private String industryCode;
	private String industryName;
	
	private BigDecimal totalIncome;
	
	private String createdBy;
}
