package com.lawencon.lawenconcommunity.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.lawenconcommunity.model.Industry;

@Repository
public class IndustryDao extends AbstractJpaDao{

	public Optional<Industry> getByIndustryCode(final String code){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT id, versions, industry_code, industry_name ")
		.append("FROM tb_industry tr ")
		.append("WHERE industry_code = :code");
		
		Object objIndustry = null; 
		Optional<Industry> objOpt = null;
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
}
