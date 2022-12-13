package com.lawencon.lawenconcommunity.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Position;

@Repository
public class PositionDao extends AbstractJpaDao
{

	public Optional<Position> getByIndustryCode(String code){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT id, versions, created_by, position_code, position_name, is_active ")
		.append("FROM tb_position tp ")
		.append("WHERE position_code = :code AND is_active = true");
		
		Object objPosition = null; 
		Optional<Position> objOpt = Optional.ofNullable(null);
		try {
			objPosition = this.createNativeQuery(sql.toString())
			.setParameter("code", code)
			.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPosition != null) {
			final Object[] objArr = (Object[]) objPosition;
			
			final Position position = new Position();
			
			position.setId(objArr[0].toString());
			position.setVersion(Integer.parseInt(objArr[1].toString()));
			position.setPositionCode(objArr[2].toString());
			position.setPositionName(objArr[3].toString());
			
			objOpt = Optional.ofNullable(position);
		}
		
		return objOpt;
	}
	
	public Position getTotalPosition() {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) from tb_position ")
		.append("WHERE is_active = true");
		
		
		Object objPosition = null;
		Position position = new Position();
		try {
			objPosition = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPosition != null) {
			Object obj = (Object) objPosition;
			
			position.setCountOfPosition(Integer.parseInt(obj.toString()));
		}
		
		return position;
	}
	
	@SuppressWarnings("unchecked")
	public List<Position> getByIsActive(int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_position tp ")
		.append("WHERE is_active = true ")
		.append("ORDER BY created_at DESC ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Position> objResultPositions = ConnHandler.getManager().createNativeQuery(sql.toString(),Position.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResultPositions;
	}
	
	@SuppressWarnings("unchecked")
	public List<Position> getByIsActive(){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_position tp ")
		.append("WHERE is_active = true ")
		.append("ORDER BY position_name ");
		
		final List<Position> objResultPositions = ConnHandler.getManager().createNativeQuery(sql.toString(),Position.class)
				.getResultList();
		
		return objResultPositions;
	}
}
