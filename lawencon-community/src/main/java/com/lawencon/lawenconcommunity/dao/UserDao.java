package com.lawencon.lawenconcommunity.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.Industry;
import com.lawencon.lawenconcommunity.model.Position;
import com.lawencon.lawenconcommunity.model.Role;
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.lawenconcommunity.model.Verification;

@Repository
public class UserDao extends AbstractJpaDao{
	
	public Optional<User> getByEmail(final String email){
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT tu.id, full_name, email, pass, company, ")
		.append("ti.id, ti.industry_code, ti.industry_name, ")
		.append("tp.id, tp.position_code, tp.position_name, ")
		.append("tr.id, tr.role_code, tr.role_name, ")
		.append("file_id, ")
		.append("tb.id,tb.total_balance, ")
		.append("tss.id,tss.status_subscribe_code,tss.status_subscribe_name, ")
		.append("tv.id, tv.verification_status, ")
		.append("tu.versions, tu.is_active FROM tb_user tu ")
		.append("INNER JOIN tb_industry ti ON tu.industry_id = ti.id ")
		.append("INNER JOIN tb_position tp ON tu.position_id = tp.id ")
		.append("INNER JOIN tb_role tr ON tu.role_id = tr.id ")
		.append("INNER JOIN tb_file tf ON tu.file_id = tf.id ")
		.append("INNER JOIN tb_balance tb ON tu.balance_id = tf.id ")
		.append("INNER JOIN tb_verification tv ON tu.verification_id = tv.id ")
		.append("WHERE email = :email");
		
		Object objUser = null; 
		Optional<User> objOpt = null;
		try {
			objUser = this.createNativeQuery(sql.toString())
			.setParameter("email", email)
			.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objUser != null) {
			Object[] objArr = (Object[]) objUser;
			
			
			final User user = new User();
			final Industry industry = new Industry();
			final Position position = new Position();
			final File file = new File();
			final Role role = new Role();
			final Balance balance = new Balance();
			final Verification verification = new Verification();
			
			user.setId(objArr[0].toString());
			user.setFullname(objArr[1].toString());
			user.setPass(objArr[2].toString());
			user.setCompany(objArr[3].toString());
			
			
			industry.setId(objArr[4].toString());
			industry.setIndustryCode(objArr[5].toString());
			industry.setIndustryName(objArr[6].toString());
			
			position.setId(objArr[7].toString());
			position.setPositionCode(objArr[8].toString());
			position.setPositionName(objArr[9].toString());
			
			role.setId(objArr[9].toString());
			role.setRoleCode(objArr[10].toString());
			role.setRoleName(objArr[11].toString());
			
			file.setId(objArr[12].toString());
			
			balance.setId(objArr[13].toString());
			
			balance.setTotalBalance(BigDecimal.valueOf(Double.valueOf(objArr[14].toString())));
			
			verification.setId(objArr[15].toString());
			verification.setVerificationStatus(objArr[16].toString());
			
			user.setIndustry(industry);
			user.setPosition(position);
			user.setRole(role);
			user.setBalance(balance);
			user.setFile(file);
			user.setVerification(verification);
			
			user.setVersion(Integer.valueOf(objArr[17].toString()));
			user.setIsActive(Boolean.valueOf(objArr[18].toString()));
			
		}
		
		return null;
	}
	
	public List<User> getByRole(String roleCode){
		return null;
	}
	

}
