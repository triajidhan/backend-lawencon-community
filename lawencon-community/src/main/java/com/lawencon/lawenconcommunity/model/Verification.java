package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_verification",uniqueConstraints = {
	@UniqueConstraint(columnNames = "verification_code")
})
@Getter
@Setter
public class Verification extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7454022226573740666L;

	@Column(name = "verification_code", unique = true, length = 5)
	private String verificationCode;
	
	@Column(name = "verification_status")
	private String verificationStatus;
}
