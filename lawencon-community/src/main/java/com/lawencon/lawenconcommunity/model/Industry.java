package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_industry",uniqueConstraints = {
	@UniqueConstraint(columnNames = "industry_code")
})
@Getter
@Setter
public class Industry extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8858317709695685154L;

	@Column(name ="industry_code",length=5)
	private String industryCode;
	
	@Column(name ="industry_name",length=50)
	private String industryName;
	
	@Transient
	private Integer countOfIndustry;
}
