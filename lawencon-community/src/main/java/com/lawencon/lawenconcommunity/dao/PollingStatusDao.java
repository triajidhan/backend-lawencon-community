package com.lawencon.lawenconcommunity.dao;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Polling;
import com.lawencon.lawenconcommunity.model.PollingStatus;

@Repository
public class PollingStatusDao extends AbstractJpaDao {
	
	
	public PollingStatus getByPolling(String pollingId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ")
		.append("tps.id, polling_id,")
		.append("tp.poll_content, tp.total_poll,")
		.append("tps.created_by, tps.created_at, tps.versions, tps.is_active ")
		.append("FROM tb_polling_status tps ")
		.append("INNER JOIN tb_polling ON tp.id = tps.polling_id ")
		.append("WHERE tp.id = :pollingId AND tps.is_active = true ");
		
		Object objPolling = null;
		PollingStatus pollingStatus = new PollingStatus();
		
		try {
			objPolling = ConnHandler.getManager()
					.createNativeQuery(sql.toString(), PollingStatus.class)
					.setParameter("pollingId", pollingId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		if(objPolling != null) {
			Object[] objArr = (Object[]) objPolling;
			
			Polling polling = new Polling();
			
			
			pollingStatus.setId(objArr[0].toString());
			
			
			polling.setId(objArr[1].toString());
			polling.setPollContent(objArr[2].toString());
			polling.setTotalPoll(Integer.parseInt(objArr[3].toString()));
			

			pollingStatus.setCreatedBy(objArr[4].toString());
			pollingStatus.setCreatedAt(Timestamp.valueOf(objArr[5].toString()).toLocalDateTime());
			pollingStatus.setVersion(Integer.parseInt(objArr[6].toString()));
			pollingStatus.setIsActive(Boolean.valueOf(objArr[7].toString()));
			
			pollingStatus.setPolling(polling);
		}
		
		
		return pollingStatus;
	}
	
	
	public PollingStatus getByUser(String userId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ")
		.append("tps.id, polling_id,")
		.append("tp.poll_content, tp.total_poll,")
		.append("tps.created_by, tps.created_at, tps.versions, tps.is_active ")
		.append("FROM tb_polling_status tps ")
		.append("INNER JOIN tb_polling tp ON tp.id = tps.polling_id ")
		.append("WHERE tps.created_by = :userId AND tps.is_active = true ");
		
		Object objPolling = null;
		PollingStatus pollingStatus = null;
		
		try {
			objPolling = ConnHandler.getManager()
					.createNativeQuery(sql.toString())
					.setParameter("userId", userId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		if(objPolling != null) {
			pollingStatus = new PollingStatus();
			Object[] objArr = (Object[]) objPolling;
			
			Polling polling = new Polling();
			
			
			pollingStatus.setId(objArr[0].toString());
			
			
			polling.setId(objArr[1].toString());
			polling.setPollContent(objArr[2].toString());
			polling.setTotalPoll(Integer.parseInt(objArr[3].toString()));
			

			pollingStatus.setCreatedBy(objArr[4].toString());
			pollingStatus.setCreatedAt(Timestamp.valueOf(objArr[5].toString()).toLocalDateTime());
			pollingStatus.setVersion(Integer.parseInt(objArr[6].toString()));
			pollingStatus.setIsActive(Boolean.valueOf(objArr[7].toString()));
			
			pollingStatus.setPolling(polling);
		}
		
		
		return pollingStatus;
	}
	
	
	public PollingStatus getByUserAndPosting(String userId, String postingId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ")
		.append("tps.id, polling_id,")
		.append("tp.poll_content, tp.total_poll,")
		.append("tps.created_by, tps.created_at, tps.versions, tps.is_active ")
		.append("FROM tb_polling_status tps ")
		.append("INNER JOIN tb_polling tp ON tp.id = tps.polling_id ")
		.append("INNER JOIN tb_post tpost ON tpost.id = tp.post_id ")
		.append("WHERE tps.created_by = :userId AND tpost.id =:postingId ")
		.append("AND tps.is_active = true");
		
		Object objPolling = null;
		PollingStatus pollingStatus = null;
		
		try {
			objPolling = ConnHandler.getManager()
					.createNativeQuery(sql.toString())
					.setParameter("userId", userId)
					.setParameter("postingId", postingId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		if(objPolling != null) {
			pollingStatus = new PollingStatus();
			Object[] objArr = (Object[]) objPolling;
			Polling polling = new Polling();
			
			
			pollingStatus.setId(objArr[0].toString());
			
			
			polling.setId(objArr[1].toString());
			polling.setPollContent(objArr[2].toString());
			polling.setTotalPoll(Integer.parseInt(objArr[3].toString()));
			

			pollingStatus.setCreatedBy(objArr[4].toString());
			pollingStatus.setCreatedAt(Timestamp.valueOf(objArr[5].toString()).toLocalDateTime());
			pollingStatus.setVersion(Integer.parseInt(objArr[6].toString()));
			pollingStatus.setIsActive(Boolean.valueOf(objArr[7].toString()));
			
			pollingStatus.setPolling(polling);
		}
		
		
		return pollingStatus;
	}
}
