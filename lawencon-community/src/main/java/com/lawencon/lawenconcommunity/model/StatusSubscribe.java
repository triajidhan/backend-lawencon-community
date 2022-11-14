package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_status_subscribe",uniqueConstraints = {
		@UniqueConstraint(columnNames = "status_subscribe_code")
})
@Getter
@Setter
public class StatusSubscribe extends BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1765676914257014478L;

	@Column(name = "status_subscribe_code", unique = true,length=5)
	private String statusSubscribeCode;
	
	@Column(name ="status_subscribe_name",length=50)
	private String statusSubscribeName;
}
