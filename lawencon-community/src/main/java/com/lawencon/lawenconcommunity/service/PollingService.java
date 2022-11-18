package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.PollingDao;
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
}
