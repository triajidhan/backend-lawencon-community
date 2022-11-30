package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.PollingStatusDao;
import com.lawencon.lawenconcommunity.model.PollingStatus;

@Service
public class PollingStatusService extends BaseCoreService{

	@Autowired
	private PollingStatusDao pollingStatusDao;
	
	public List<PollingStatus> getAll(){
		return pollingStatusDao.getAll(PollingStatus.class);
	}
	
	public PollingStatus getId(String id) {
		return pollingStatusDao.getByIdAndDetach(PollingStatus.class, id);
	}
	
	public PollingStatus getByPolling(String pollingId){
		return pollingStatusDao.getByPolling(pollingId);
	}
	
	public PollingStatus getByUser(String userId){
		return pollingStatusDao.getByUser(userId);
	}
	
	public PollingStatus getByUserAndPosting(String userId, String postingId){
		return pollingStatusDao.getByUserAndPosting(userId, postingId);
	}
}
