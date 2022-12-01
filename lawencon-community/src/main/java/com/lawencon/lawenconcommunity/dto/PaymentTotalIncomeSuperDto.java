package com.lawencon.lawenconcommunity.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentTotalIncomeSuperDto {

	private String activityTypeCode;
	private String activityTypeName;
	
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
