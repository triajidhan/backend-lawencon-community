package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.PollingDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Polling;

@Service
public class PollingService extends BaseCoreService{

	@Autowired
	private PollingDao pollingDao;
	
	public List<Polling> getAll(Integer startPosition, Integer limitPage){
		List<Polling> pollings = pollingDao.getAll(Polling.class, startPosition, limitPage);
		
		return pollings;
	}
	
	
	
	public List<Polling> getByUser(String userId){
		List<Polling> pollings = pollingDao.getByUser(userId);
		
		return pollings;
	}
	
	public List<Polling> getByPost(String postId){
		List<Polling> pollings = pollingDao.getByPost(postId);
		
		return pollings;
	}
	
	public Polling getById(String id) {
		Polling polling = pollingDao.getByIdAndDetach(Polling.class, id);
		return polling;
	}
	
	
	public void valUpdate(Polling data){
		if(pollingDao.getByIdAndDetach(Polling.class, data.getId())== null ) {
			throw new RuntimeException("Polling is not found!");
		}
	}
	
	public ResponseMessageDto update(Polling data) {
		valUpdate(data);
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Polling Failed!");
		Polling pollingUpdate = new Polling();
		Polling poling = pollingDao.getByIdAndDetach(Polling.class, data.getId());
		begin();
		try {
			pollingUpdate = poling;
			pollingUpdate.setTotalPoll(pollingUpdate.getTotalPoll()+1);
			responseMessageDto.setMessage("Polling Success!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Polling Failed!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}
}
