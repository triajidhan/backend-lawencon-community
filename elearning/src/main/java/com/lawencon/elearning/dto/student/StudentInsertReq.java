package com.lawencon.elearning.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudentInsertReq {

	private String name;
	private Boolean isActive;
	private String collegeId;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private String timeInStr;

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

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public String getTimeInStr() {
		return timeInStr;
	}

	public void setTimeInStr(String timeInStr) {
		this.timeInStr = timeInStr;
	}

}
