package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.lawenconcommunity.dao.IndustryDao;
import com.lawencon.lawenconcommunity.model.Industry;

@Service
public class IndustryService {

	@Autowired
	private IndustryDao industryDao;
	
	public int getTotalIndustry() {
		return industryDao.totalIndustry();
	}
	
	public List<Industry> getIsActive(int startPosition,int limit){
		return industryDao.getByIsActive(startPosition, limit);
	}
}
