package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.PositionDao;
import com.lawencon.lawenconcommunity.model.Position;

@Service
public class PositionService extends BaseCoreService{

	@Autowired
	private PositionDao positionDao;
	
	public int getTotalPosition() {
		return positionDao.totalPosition();
	}
	
	public List<Position> getIsActive(int startPosition,int limit){
		return positionDao.getByIsActive(startPosition, limit);
	}
}
