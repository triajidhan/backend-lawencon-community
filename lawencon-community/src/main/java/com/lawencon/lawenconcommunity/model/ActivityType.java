package com.lawencon.lawenconcommunity.model;

import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ActivityType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8584310399814329816L;
	private String activityTypeCode;
	private String activityTypeName;
}
