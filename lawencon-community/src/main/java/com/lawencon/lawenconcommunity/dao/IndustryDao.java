package com.lawencon.lawenconcommunity.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Industry;

@Repository
public class IndustryDao extends AbstractJpaDao{

	public Optional<Industry> getByIndustryCode(final String code){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT id, versions, industry_code, industry_name, is_active ")
		.append("FROM tb_industry tr ")
		.append("WHERE industry_code = :code is_active = true");
		
		Object objIndustry = null; 
		Optional<Industry> objOpt = Optional.ofNullable(null);
		try {
			objIndustry = this.createNativeQuery(sql.toString())
			.setParameter("code", code)
			.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objIndustry != null) {
			final Object[] objArr = (Object[]) objIndustry;
			
			final Industry industry = new Industry();
			
			industry.setId(objArr[0].toString());
			industry.setVersion(Integer.parseInt(objArr[1].toString()));
			industry.setIndustryCode(objArr[2].toString());
			industry.setIndustryName(objArr[3].toString());
			
			objOpt = Optional.ofNullable(industry);
		}
		
		return objOpt;
	}
	
	public Industry getTotalIndustry() {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) from tb_industry ")
		.append("WHERE is_active = true");
		
		Object objIndustry = null;
		Industry industry = new Industry();
		try {
			objIndustry = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objIndustry != null) {
			Object obj = (Object) objIndustry;
			
			industry.setCountOfIndustry(Integer.parseInt(obj.toString()));
		}
		
		return industry;
	}
	
	@SuppressWarnings("unchecked")
	public List<Industry> getByIsActive(int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_industry tp ")
		.append("WHERE is_active = true ")
		.append("ORDER BY created_at DESC ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Industry> objResultIndustries = ConnHandler.getManager().createNativeQuery(sql.toString(),Industry.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResultIndustries;
	}
	
	@SuppressWarnings("unchecked")
	public List<Industry> getByIsActive(){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_industry tp ")
		.append("ORDER BY industry_name ")
		.append("WHERE is_active = true ");
		
		final List<Industry> objResultIndustries = ConnHandler.getManager().createNativeQuery(sql.toString(),Industry.class)
				.getResultList();
		
		return objResultIndustries;
	}
}
