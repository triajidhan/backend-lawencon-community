package com.lawencon.lawenconcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.ActivityType;

@Repository
public class ActivityTypeDao extends AbstractJpaDao{

	@SuppressWarnings("unchecked")
	public List<ActivityType> getByIsActive(int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_activity_type ")
		.append("WHERE is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<ActivityType> objResultActivities = ConnHandler.getManager().createNativeQuery(sql.toString(),ActivityType.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResultActivities;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityType> getByIsActive(){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_activity_type ")
		.append("WHERE is_active = true ");
		
		final List<ActivityType> objResultActivities = ConnHandler.getManager().createNativeQuery(sql.toString(),ActivityType.class)
				.getResultList();
		
		return objResultActivities;
	}
	
	
	public ActivityType getTotalActivityType(){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) ")
		.append("FROM tb_activity_type ");
		
		Object objActivityType = null; 
		ActivityType activityType = new ActivityType();
		try {
			objActivityType = ConnHandler.getManager().createNativeQuery(sql.toString())
			.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objActivityType != null) {
			Object objArr = (Object) objActivityType;
			
			activityType.setCountOfActivityType(Integer.parseInt(objArr.toString()));
		}
		
		return activityType;
	}
}
