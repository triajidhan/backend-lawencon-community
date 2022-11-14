package com.lawencon.elearning.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudentUpdateReq {

	private String id;
	private String name;
	private Boolean isActive;
	private Integer version;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private String timeInStr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTimeInStr() {
		return timeInStr;
	}

	public void setTimeInStr(String timeInStr) {
		this.timeInStr = timeInStr;
	}

}
