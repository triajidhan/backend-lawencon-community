package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.PositionDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
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
	
	public void valIdNull(Position data) {
		if(data.getId()!=null) {
			throw new RuntimeException("Id must be null!");
		}
	}
	
	public ResponseMessageDto insert(Position data) {
		valIdNull(data);
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed Add Position!");
		begin();
		try {
			positionDao.save(data);
			responseMessageDto.setMessage("Success Add Position!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Failed Add Position!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}
	
	public ResponseMessageDto update(Position data) {
		valIdFound(data);
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed Add Position!");
		Position Position = positionDao.getById(Position.class, data.getId());
		Position PositionUpdate = Position;
		begin();
		try {
			if(data.getPositionName() != null) {
				PositionUpdate.setPositionName(data.getPositionName());
			}
			
			if(data.getIsActive() != null) {
				PositionUpdate.setIsActive(data.getIsActive());
			}

			positionDao.save(PositionUpdate);
			responseMessageDto.setMessage("Success Add Position!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Failed Add Position!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}
	
	public void valIdFound(Position data) {
		if(positionDao.getByIdAndDetach(Position.class, data.getId()) == null) {
			throw new RuntimeException("Position Not Found!");
		}
	}
}
