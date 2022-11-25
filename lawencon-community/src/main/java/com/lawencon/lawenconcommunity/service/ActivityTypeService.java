package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.ActivityTypeDao;
import com.lawencon.lawenconcommunity.model.ActivityType;

@Service
public class ActivityTypeService extends BaseCoreService{
	
	@Autowired
	private ActivityTypeDao activityTypeDao;
	
	public List<ActivityType> getAll(){
		return activityTypeDao.getAll(ActivityType.class);
	}
	
	public List<ActivityType> getByIsActive(){
		return activityTypeDao.getByIsActive();
	}
	
	public ActivityType getById(String id) {
		return activityTypeDao.getByIdAndDetach(ActivityType.class,id);
	}
}
