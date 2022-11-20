package com.lawencon.lawenconcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Polling;

@Repository
public class PollingDao extends AbstractJpaDao{

	@SuppressWarnings("unchecked")
	public List<Polling> getByPost(final String postId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_polling tpol ")
		.append("INNER JOIN tb_post tpos ON  tpos.id  = tpol.post_id ")
		.append("WHERE tpos.id = :postId AND tpol.is_active = true");
		
		List<Polling> pollings = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), Polling.class)
				.setParameter("postId",postId)
				.getResultList();
		
		return pollings;
	}
	
	@SuppressWarnings("unchecked")
	public List<Polling> getByUser(final String userId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_polling tpol ")
		.append("INNER JOIN tb_post tpos ON  tpos.id = tpol.post_id ")
		.append("WHERE tpol.created_by = :userId AND tpol.is_active = true");
		
		List<Polling> pollings = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), Polling.class)
				.setParameter("userId",userId)
				.getResultList();
		
		return pollings;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Polling> getByIsActive(int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_polling tpol ")
		.append("INNER JOIN tb_post tpos ON  tpos.id = tpol.post_id ")
		.append("WHERE tpol.is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Polling> objResultPollings = ConnHandler.getManager().createNativeQuery(sql.toString(),Polling.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResultPollings;
	}
}
