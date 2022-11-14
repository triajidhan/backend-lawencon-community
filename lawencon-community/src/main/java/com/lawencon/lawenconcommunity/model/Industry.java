package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_industry",uniqueConstraints = {
	@UniqueConstraint(columnNames = "industry_code")
})
@Getter
@Setter
public class Industry {

	@Column(name ="industry_code",length=5)
	private String industryCode;
	
	@Column(name ="industry_name",length=50)
	private String industryName;
}
