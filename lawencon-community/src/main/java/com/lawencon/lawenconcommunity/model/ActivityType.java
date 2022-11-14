package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;
@Table(name ="tb_activity_type")
@Entity
@Getter
@Setter
public class ActivityType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8584310399814329816L;
	@Column(name = "activity_type_code", unique = true, length = 5)
	private String activityTypeCode;
	
	@Column(name = "activity_type_name", length = 50)
	private String activityTypeName;
}
