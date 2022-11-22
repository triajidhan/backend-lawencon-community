package com.lawencon.lawenconcommunity.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Role;

@Repository
public class RoleDao extends AbstractJpaDao{
	
	public Role getByRoleCode(String roleCode) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ")
		.append("id ,role_code, role_name, created_by,versions,is_active ")
		.append("FROM tb_role ")
		.append("WHERE role_code = :roleCode ");
		
		Object objRole = null;
		Role role = null;
		try {
			objRole = ConnHandler.getManager()
					.createNativeQuery(sql.toString())
					.setParameter("roleCode", roleCode)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objRole != null) {
			Object[] objArr = (Object[]) objRole;
			
			role = new Role();
			
			role.setId(objArr[0].toString());
			role.setRoleCode(objArr[1].toString());
			role.setRoleName(objArr[2].toString());
			role.setCreatedBy(objArr[3].toString());
			role.setVersion(Integer.parseInt(objArr[4].toString()));
			role.setIsActive(Boolean.valueOf(objArr[5].toString()));
			
		}
		
		return role;
	}
}
