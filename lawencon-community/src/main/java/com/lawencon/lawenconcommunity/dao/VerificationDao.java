package com.lawencon.lawenconcommunity.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.lawenconcommunity.model.Verification;

@Repository
public class VerificationDao extends AbstractJpaDao{

	public Optional<Verification> getByVerificationCode(String code){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT id, versions, verification_code, verification_status ")
		.append("FROM tb_verification tv ")
		.append("WHERE verification_code = :code");
		
		Object objVerification = null; 
		Optional<Verification> objOpt = null;
		try {
			objVerification = this.createNativeQuery(sql.toString())
			.setParameter("code", code)
			.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objVerification != null) {
			final Object[] objArr = (Object[]) objVerification;
			
			final Verification verification = new Verification();
			
			verification.setId(objArr[0].toString());
			verification.setVersion(Integer.parseInt(objArr[1].toString()));
			verification.setVerificationCode(objArr[2].toString());
			verification.setVerificationStatus(objArr[3].toString());
			
			objOpt = Optional.ofNullable(verification);
		}
		
		return objOpt;
	}
}
