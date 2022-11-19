package com.lawencon.lawenconcommunity.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.lawenconcommunity.model.Position;

@Repository
public class PositionDao extends AbstractJpaDao
{

	public Optional<Position> getByIndustryCode(String code){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT id, versions, position_code, position_name ")
		.append("FROM tb_position, is_active tp ")
		.append("WHERE position_code = :code AND is_active = true");
		
		Object objPosition = null; 
		Optional<Position> objOpt = null;
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
}
