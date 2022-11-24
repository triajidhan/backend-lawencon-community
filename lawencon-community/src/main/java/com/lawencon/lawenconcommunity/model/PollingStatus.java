package com.lawencon.lawenconcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_polling_status")
@Getter
@Setter
public class PollingStatus extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9129639284305768736L;
	
	@OneToOne
	@JoinColumn(name="polling_id")
	private Polling polling;
}
