package com.lawencon.lawenconcommunity.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentPartisipationSuperDto {

	private String activityTypeCode;
	private String activityTypeName;
	
	private String activityCode;
	private String title;
	private String beginSchedule;
	private String finishSchedule;
	private String location;
	private BigDecimal price;
	private String provider;
	
	private String id;
	private String fullName;
	private String company;
	
	private String positionCode;
	private String positionName;
	
	private String industryCode;
	private String industryName;
	
	private Integer partisipant;
	private String createdBy;
}
