package com.lawencon.elearning.model;

import javax.persistence.Entity;

import com.lawencon.base.BaseEntity;

@Entity
public class City extends BaseEntity {

	private static final long serialVersionUID = 2240552047700677738L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
