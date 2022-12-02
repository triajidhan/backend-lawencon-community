package com.lawencon.lawenconcommunity.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_payment_activity_detail")
@Getter
@Setter
public class PaymentActivityDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8868128362652204347L;

	@Column(name="payment_code",length=5)
	private String paymentCode;
	
	@Column(name="net")
	private BigDecimal net;
	
	@Column(name="approve")
	private Boolean approve;
	
	@OneToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;
	
	@OneToOne
	@JoinColumn(name="file_id")
	private File file;
	
	@Transient
	private Integer countOfPaymentActivity;
	
	@Transient
	private String memberCreate;
	
	@Transient
	private User user;
	
	@Transient
	private Integer partisipation;
}


