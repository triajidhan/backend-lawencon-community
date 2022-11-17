package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.ActivityDao;
import com.lawencon.lawenconcommunity.model.Activity;

@Service
public class ActivityService extends BaseCoreService{

	@Autowired
	private ActivityDao activityDao;
	
	public List<Activity> getAll(Integer startPosition, Integer limitPage){
		final List<Activity> activities = activityDao.getAll(Activity.class, startPosition, limitPage);
		
		return activities;
	}
	
	public Activity getByActivityCode(final String activityCode) {
		return activityDao.getByActivityCode(activityCode).get();
	}
	
	public List<Activity> getByActivityType(final String activityId){
		final List<Activity> activities = activityDao.getByActivityType(activityId);
		
		return activities;
	}
}
