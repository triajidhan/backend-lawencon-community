package com.lawencon.elearning.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(uniqueConstraints = { 
		@UniqueConstraint(
				name = "student_ck", 
				columnNames = { "name", "college_id" }
		) 
})
public class Student extends BaseEntity {

	private static final long serialVersionUID = 1531754029191059494L;

	private String name;
	private LocalDateTime timeIn;

	@ManyToOne
	@JoinColumn(name = "college_id")
	private College college;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(LocalDateTime timeIn) {
		this.timeIn = timeIn;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

}
