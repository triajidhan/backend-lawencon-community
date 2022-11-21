package com.lawencon.lawenconcommunity.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_payment_subscribe",uniqueConstraints = {
	@UniqueConstraint(columnNames = "payment_code")	
})
@Getter
@Setter
public class PaymentSubscribe extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1017533914499728502L;

	@Column(name = "payment_code",length=5)
	private String paymentCode;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "approve")
	private Boolean approve;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name="file_id")
	private File file;
	
}
