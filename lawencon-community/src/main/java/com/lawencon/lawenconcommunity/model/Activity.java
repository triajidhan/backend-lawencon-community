package com.lawencon.lawenconcommunity.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_activity",uniqueConstraints = {
		@UniqueConstraint(columnNames = "activity_code")
})
@Getter
@Setter
public class Activity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8247996102030879161L;

	@Column(name="activity_code",length=5)
	private String activityCode;
	
	@Column(name="title",length = 50)
	private String title;
	
	@Column(name="provider",length=100)
	private String provider;
	
	@Column(name="locations",length=100)
	private String location;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'+07:00'")
	@Column(name="begin_schedule")
	private LocalDateTime beginSchedule;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'+07:00'")
	@Column(name="finish_schedule")
	private LocalDateTime finishSchedule;
	
	@Column(name="price")
	private BigDecimal price;
	
	@OneToOne
	@JoinColumn(name="activity_type_id")
	private ActivityType activityType;
	
	@OneToOne
	@JoinColumn(name="file_id")
	private File file;
	
	@Transient
	private Integer countOfActivity;
	
	@Transient
	private PaymentActivityDetail paymentActivityDetail;
}
