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
@Table(name = "tb_position",uniqueConstraints = {
	@UniqueConstraint(columnNames = "position_code")
})
@Getter
@Setter
public class Position extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4925882511691772193L;

	@Column(name ="position_code",length=5)
	private String positionCode;
	
	@Column(name ="position_name",length=50)
	private String positionName;
	
	@Transient
	private Integer countOfPosition;
}
