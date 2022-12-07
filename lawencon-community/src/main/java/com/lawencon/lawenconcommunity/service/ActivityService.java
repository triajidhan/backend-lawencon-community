package com.lawencon.lawenconcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.ActivityDao;
import com.lawencon.lawenconcommunity.dao.ActivityTypeDao;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Activity;
import com.lawencon.lawenconcommunity.model.ActivityType;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.security.principal.PrincipalService;

@Service
public class ActivityService extends BaseCoreService{

	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private ActivityTypeDao activityTypeDao;
	@Autowired 
	private FileDao fileDao;
	@Autowired
	private GenerateService generateService;
	@Autowired
	private PrincipalService principalService;
	
	public List<Activity> getAll(){
		final List<Activity> activities = activityDao.getAll(Activity.class);
		
		return activities;
	}
	
	public List<Activity> getAll(Integer startPosition, Integer limitPage){
		final List<Activity> activities = activityDao.getAll(Activity.class, startPosition, limitPage);
		
		return activities;
	}
	
	public Activity getById(String activityId) {
		return activityDao.getByIdAndDetach(Activity.class,activityId);
	}
	
	public Activity getByActivityCode(String activityCode) {
		return activityDao.getByActivityCode(activityCode).get();
	}
	
	public Activity getTotalByActivity() {
		return activityDao.getTotalByActivity();
	}
	
	public List<Activity> getByActivityType(String activityTypeId,int startPosition,int limit){
		final List<Activity> activities = activityDao.getByActivityType(activityTypeId,startPosition,limit);
		
		return activities;
	}
	
	public List<Activity> getByActivityType(String activityTypeId,int startPosition,int limit,boolean ascending){
		final List<Activity> activities = activityDao.getByActivityType(activityTypeId,startPosition,limit,ascending);
		
		return activities;
	}
	
	public Activity getTotalByActivityType(String activityTypeId){
		return activityDao.getTotalByActivityType(activityTypeId);
	}
	
	public List<Activity> getByUser(String userId,int startPosition,int limit,boolean ascending){
		final List<Activity> activities = activityDao.getByUser(userId,startPosition,limit,ascending);
		
		return activities;
	}
	
	public List<Activity> getByUserAndActivityTypeCode(String userId,String activityTypeCode,int startPosition,int limit,boolean ascending){
		final List<Activity> activities = activityDao.getByUserAndActivityTypeCode(userId,activityTypeCode,startPosition,limit,ascending);
		
		return activities;
	}
	
	public List<Activity> getByIsActive(int startPosition,int limit){
		final List<Activity> activities = activityDao.getByIsActive(startPosition, limit);
		
		return activities;
	}
	
	public List<Activity> getByIsActive(){
		final List<Activity> activities = activityDao.getByIsActive();
		
		return activities;
	}
	
	public List<Activity> getByIsActiveAndOrder(int startPosition,int limit,boolean ascending){
		
		List<Activity> activities = null;
		try {
			activities = activityDao.getByIsActiveAndOrder(startPosition,limit,ascending,this.principalService.getAuthPrincipal());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return activities;
	}
	
	public Activity getTotalByIsActive(){
		Activity activity = new Activity();
		try {
			activity =  activityDao.getTotalByIsActive(this.principalService.getAuthPrincipal());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activity;
	}
	
	public Activity getTotalByActivityTypeCode(String activityCode) {
		return activityDao.getTotalByActivityTypeCode(activityCode);
	}
	
	public List<Activity> getByActivityTypeCode(String activityTypeCode,int startPosition,int limit){
		final List<Activity> activities = activityDao.getByActivityTypeCode(activityTypeCode,startPosition,limit);
		
		return activities;
	}
	
	public List<Activity> getByActivityTypeCode(String activityTypeCode,int startPosition,int limit,boolean ascending){
		List<Activity> activities = null;
		try {
			activities = activityDao.getByActivityTypeCode(activityTypeCode,startPosition,limit,ascending,this.principalService.getAuthPrincipal());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return activities;
	}
	
	public ResponseMessageDto insert(Activity data) {
		File fileInsert = new File();
		ResponseMessageDto reponseMessageDto = new ResponseMessageDto();
		reponseMessageDto.setMessage("Create activity failed!");
		begin();
		try {
			if(data.getFile()!=null) {
				fileInsert = fileDao.save(data.getFile());
				data.setFile(fileInsert);				
			}
			data.setActivityCode(generateService.generate(5));
			activityDao.save(data);
			reponseMessageDto.setMessage("Create activity success!");
		} catch (Exception e) {
			reponseMessageDto.setMessage("Create activity failed!");
			e.printStackTrace();
		}
		commit();
		return reponseMessageDto;
	}
	
	public void valInsert(Activity data) {
		valNullId(data);
		valFk(data);
	}
	
	public void valNullId(Activity data) {
		if(data.getId()!= null) {
			throw new RuntimeException("Id must be null!");
		}
		if (data.getFile() != null) {
			if(data.getFile().getId() != null) {
				throw new RuntimeException("Id Must Be Empty!");				
			}
		}
	}	
	
	public void valFk(Activity data) {
		if(activityTypeDao.getByIdAndDetach(ActivityType.class, data.getActivityType().getId())==null) {
			throw new RuntimeException("Type Not Found!");
		}	
	}
	
	public void valFoundId(Activity data) {
		if(activityDao.getById(Activity.class, data.getId()) == null) {
			throw new RuntimeException("Activity not found!");
		}
	}
}
