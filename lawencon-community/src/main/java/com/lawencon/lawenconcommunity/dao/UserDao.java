package com.lawencon.lawenconcommunity.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.Industry;
import com.lawencon.lawenconcommunity.model.Position;
import com.lawencon.lawenconcommunity.model.Role;
import com.lawencon.lawenconcommunity.model.User;

@Repository
public class UserDao extends AbstractJpaDao{
	
	public Optional<User> getByEmail(final String email){
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT tu.id, full_name, email, pass, company, ")
		.append("ti.id as ti_id, ti.industry_code, ti.industry_name, ")
		.append("tp.id as tp_id, tp.position_code, tp.position_name, ")
		.append("tr.id as tr_id, tr.role_code, tr.role_name, ")
		.append("file_id, ")
		.append("tb.id as tb_id ,tb.total_balance, ")
		.append("tu.versions, tu.is_active FROM tb_user tu ")
		.append("LEFT JOIN tb_industry ti ON tu.industry_id = ti.id ")
		.append("LEFT JOIN tb_position tp ON tu.position_id = tp.id ")
		.append("INNER JOIN tb_role tr ON tu.role_id = tr.id ")
		.append("LEFT JOIN tb_file tf ON tu.file_id = tf.id ")
		.append("LEFT JOIN tb_balance tb ON tu.balance_id = tb.id ")
		.append("WHERE email iLike :email");
		
		Object objUser = null; 
		Optional<User> objOpt = Optional.ofNullable(null);
		try {
			objUser = ConnHandler.getManager().createNativeQuery(sql.toString())
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
			
			user.setId(objArr[0].toString());
			user.setFullname(objArr[1].toString());
			user.setEmail(objArr[2].toString());
			user.setPass(objArr[3].toString());
			
			if(objArr[4] != null) {				
				user.setCompany(objArr[4].toString());
			}
			
			if(objArr[5] != null) {
				industry.setId(objArr[5].toString());
				industry.setIndustryCode(objArr[6].toString());
				industry.setIndustryName(objArr[7].toString());
			}
			
			if(objArr[8] != null) {
				position.setId(objArr[8].toString());
				position.setPositionCode(objArr[9].toString());
				position.setPositionName(objArr[10].toString());
			}
			
			
			role.setId(objArr[11].toString());
			role.setRoleCode(objArr[12].toString());
			role.setRoleName(objArr[13].toString());
			
			if(objArr[14] != null) {
				file.setId(objArr[14].toString());
			}
			
			if(objArr[15] != null) {				
				balance.setId(objArr[15].toString());
				
				
				balance.setTotalBalance(BigDecimal.valueOf(Double.valueOf(objArr[16].toString())));
			}
			
			user.setIndustry(industry);
			user.setPosition(position);
			user.setRole(role);
			user.setBalance(balance);
			user.setFile(file);
			
			
			user.setVersion(Integer.valueOf(objArr[17].toString()));
			user.setIsActive(Boolean.valueOf(objArr[18].toString()));
			
			objOpt = Optional.ofNullable(user); 
		}
		
		return objOpt;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getByRole(String roleCode){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_user tu ")
		.append("LEFT JOIN tb_industry ti ON tu.industry_id = ti.id ")
		.append("LEFT JOIN tb_position tp ON tu.position_id = tp.id ")
		.append("INNER JOIN tb_role tr ON tu.role_id = tr.id ")
		.append("LEFT JOIN tb_file tf ON tu.file_id = tf.id ")
		.append("LEFT JOIN tb_balance tb ON tu.balance_id = tb.id ")
		.append("WHERE role_code iLike :roleCode");
		
		final List<User> objUsers = ConnHandler.getManager().createNativeQuery(sql.toString(), User.class)
		.setParameter("roleCode", roleCode)
		.getResultList();
		return objUsers;
	}
	
	public int getTotalUser() {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(tb_user.id) as total_user from tb_user ")
		.append("INNER JOIN tb_role tr ON tb_user.role_id = tr.id ");
		
		Object objUser = null; 
		int totalUser = 0;
		try {
			objUser = ConnHandler.getManager().createNativeQuery(sql.toString())
			.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objUser != null) {
			Object objArr = (Object) objUser;
			
			totalUser =  Integer.parseInt(objArr.toString());
		}
		
		return totalUser;
	}
	
	public int getTotalByRoleCode(final String roleCode) {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("select count(tb_user.id) as total_user from tb_user ")
		.append("INNER JOIN tb_role tr ON tb_user.role_id = tr.id ")
		.append("WHERE tr.role_code iLike :roleCode");
		
		Object objUser = null; 
		int totalUser = 0;
		try {
			objUser = ConnHandler.getManager().createNativeQuery(sql.toString())
			.setParameter("roleCode", roleCode)
			.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objUser != null) {
			Object objArr = (Object) objUser;
			totalUser =  Integer.parseInt(objArr.toString());
		}
		
		return totalUser;
	}
	
public Optional<User> getSystem(final String roleCode){
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT tu.id, full_name, email, pass, company, ")
		.append("ti.id as ti_id, ti.industry_code, ti.industry_name, ")
		.append("tp.id as tp_id, tp.position_code, tp.position_name, ")
		.append("tr.id as tr_id, tr.role_code, tr.role_name, ")
		.append("file_id, ")
		.append("tb.id as tb_id ,tb.total_balance, ")
		.append("tu.versions, tu.is_active FROM tb_user tu ")
		.append("LEFT JOIN tb_industry ti ON tu.industry_id = ti.id ")
		.append("LEFT JOIN tb_position tp ON tu.position_id = tp.id ")
		.append("INNER JOIN tb_role tr ON tu.role_id = tr.id ")
		.append("LEFT JOIN tb_file tf ON tu.file_id = tf.id ")
		.append("LEFT JOIN tb_balance tb ON tu.balance_id = tb.id ")
		.append("WHERE role_code iLike :roleCode");
		
		Object objUser = null; 
		Optional<User> objOpt = Optional.ofNullable(null);
		try {
			objUser = ConnHandler.getManager().createNativeQuery(sql.toString())
			.setParameter("roleCode", roleCode)
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
			
			user.setId(objArr[0].toString());
			user.setFullname(objArr[1].toString());
			user.setEmail(objArr[2].toString());
			user.setPass(objArr[3].toString());
			
			if(objArr[4] != null) {				
				user.setCompany(objArr[4].toString());
			}
			
			if(objArr[5] != null) {
				industry.setId(objArr[5].toString());
				industry.setIndustryCode(objArr[6].toString());
				industry.setIndustryName(objArr[7].toString());
			}
			
			if(objArr[8] != null) {
				position.setId(objArr[8].toString());
				position.setPositionCode(objArr[9].toString());
				position.setPositionName(objArr[10].toString());
			}
			
			
			role.setId(objArr[11].toString());
			role.setRoleCode(objArr[12].toString());
			role.setRoleName(objArr[13].toString());
			
			if(objArr[14] != null) {
				file.setId(objArr[14].toString());
			}
			
			if(objArr[15] != null) {				
				balance.setId(objArr[15].toString());
				balance.setTotalBalance(BigDecimal.valueOf(Double.valueOf(objArr[16].toString())));
			}
			
			user.setIndustry(industry);
			user.setPosition(position);
			user.setRole(role);
			user.setBalance(balance);
			user.setFile(file);
			
			user.setVersion(Integer.valueOf(objArr[17].toString()));
			user.setIsActive(Boolean.valueOf(objArr[18].toString()));
			
			objOpt = Optional.ofNullable(user); 
		}
		
		return objOpt;
	}
}
