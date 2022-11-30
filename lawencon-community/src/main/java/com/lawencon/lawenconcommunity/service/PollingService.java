package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.PollingDao;
import com.lawencon.lawenconcommunity.dao.PollingStatusDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Polling;
import com.lawencon.lawenconcommunity.model.PollingStatus;
import com.lawencon.security.principal.PrincipalService;

@Service
public class PollingService extends BaseCoreService{

	@Autowired
	private PollingDao pollingDao;
	
	@Autowired
	private PollingStatusDao pollingStatusDao;
	
	@Autowired
	private PrincipalService principalService;
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
		for(int i = 0 ; i<pollings.size(); i++) {
			PollingStatus pollingStatus = new PollingStatus();
			try {
				pollingStatus = pollingStatusDao.getByUserAndPosting(principalService.getAuthPrincipal(), postId);
				if(pollingStatus!= null) {					
					pollings.get(i).setPollingStatus(pollingStatus);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		Polling pollingUpdate = new Polling();
		Polling poling = pollingDao.getByIdAndDetach(Polling.class, data.getId());
		begin();
		try {
			PollingStatus validatePoling = pollingStatusDao.getByUserAndPosting(principalService.getAuthPrincipal(),poling.getPost().getId());
			if(validatePoling == null) {
				pollingUpdate = poling;
				pollingUpdate.setTotalPoll(pollingUpdate.getTotalPoll()+1);
				pollingDao.save(pollingUpdate);
				
				PollingStatus pollingStatus = new PollingStatus();
				pollingStatus.setPolling(pollingUpdate);
				pollingStatus = pollingStatusDao.save(pollingStatus);
				responseMessageDto.setId(pollingStatus.getId());
				responseMessageDto.setMessage("Polling Success!");
			}else {
				throw new RuntimeException("You can only do one poll!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Polling Failed!");
		}
		commit();
		return responseMessageDto;
	}
}
