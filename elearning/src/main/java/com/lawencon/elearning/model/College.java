package com.lawencon.elearning.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lawencon.base.BaseEntity;

@Entity
public class College extends BaseEntity {

	private static final long serialVersionUID = -8459498809514169113L;

	private String name;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
