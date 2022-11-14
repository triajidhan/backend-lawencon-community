package com.lawencon.elearning.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class Address extends BaseEntity {

	private static final long serialVersionUID = -7160254209229132605L;

	private String name;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
