package com.lawencon.lawenconcommunity.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_balance")
@Getter
@Setter
public class Balance extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2262251088379949541L;


	@Column(name="total_balance")
	private BigDecimal totalBalance;
	
}
