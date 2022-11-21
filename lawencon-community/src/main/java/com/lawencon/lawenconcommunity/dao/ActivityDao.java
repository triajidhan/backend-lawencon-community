package com.lawencon.lawenconcommunity.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Activity;
import com.lawencon.lawenconcommunity.model.ActivityType;
import com.lawencon.lawenconcommunity.model.File;

@Repository
public class ActivityDao extends AbstractJpaDao{

	public Optional<Activity> getByActivityCode(final String activityCode){
		final StringBuilder sql = new StringBuilder();

		sql.append("SELECT ")
		.append("ta.id as ta_id,ta.activity_code, ta.title,ta.provider,ta.locations,begin_schedule,finish_schedule,price, ")
		.append("file_id, ")
		.append("tat.id as tat_id,tat.activity_type_code,tat.activity_type_name, ")
		.append("ta.created_by,ta.created_at,ta.versions, ta.is_active ")
		.append("FROM tb_activity ta ")
		.append("INNER JOIN tb_activity_type tat ON ta.activity_type_id = tat.id ")
		.append("WHERE activity_code = :activityCode AND ta.is_active = true");
		
		Object activityObjs = null;
		
		Optional<Activity> activityOpt = Optional.ofNullable(null);
		
		try {
			activityObjs = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("activityCode",activityCode)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(activityObjs != null) {
			Object[] objArr = (Object[]) activityObjs;
			final Activity activity = new Activity();
			final ActivityType activityType = new ActivityType();
			final File file = new File();

			// ta.id as ta_id,ta.activity_code, ta.title,ta.provider,ta.locations,
			// begin_schedule,finish_schedule,price,
			activity.setId(objArr[0].toString());
			activity.setActivityCode(objArr[1].toString());
			activity.setTitle(objArr[2].toString());
			activity.setProvider(objArr[3].toString());
			activity.setLocation(objArr[4].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[5].toString()).toLocalDateTime());
			activity.setFinishSchedule(Timestamp.valueOf(objArr[6].toString()).toLocalDateTime());
			activity.setPrice(Float.valueOf(objArr[7].toString()));
			
			// file_id
			if(objArr[8] != null) {
				file.setId(objArr[8].toString());
			}
			
			// tat.id as tat_id,tat.activity_type_code,tat.activity_type_name,
			activityType.setId(objArr[9].toString());
			activityType.setActivityTypeCode(objArr[10].toString());
			activityType.setActivityTypeName(objArr[11].toString());
			
			activity.setCreatedBy(objArr[12].toString());
			activity.setCreatedAt(Timestamp.valueOf(objArr[13].toString()).toLocalDateTime());
			activity.setVersion(Integer.parseInt(objArr[14].toString()));
			activity.setIsActive(Boolean.valueOf(objArr[15].toString()));
			
			activity.setActivityType(activityType);
			activity.setFile(file);
			
			activityOpt = Optional.ofNullable(activity);
		}
		
		return activityOpt;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getByActivityType(final String activityId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_activity ta ")
		.append("INNER JOIN tb_activity_type tat  ON ta.activity_type_id = tat.id ")
		.append("WHERE tat.id = :activityTypeId AND ta.is_active = true");
		
		final List<Activity> objResultActivities = ConnHandler.getManager().createNativeQuery(sql.toString(),Activity.class)
				.setParameter("activityTypeId", activityId)
				.getResultList();
		
		return objResultActivities;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getByIsActive(int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_activity ta ")
		.append("INNER JOIN tb_activity_type tat  ON ta.activity_type_id = tat.id ")
		.append("WHERE ta.is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Activity> objResultActivities = ConnHandler.getManager().createNativeQuery(sql.toString(),Activity.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResultActivities;
	}
}
