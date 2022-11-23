package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.RoleDao;
import com.lawencon.lawenconcommunity.model.Role;

@Service
public class RoleService extends BaseCoreService{

	@Autowired
	private RoleDao roleDao;
	
	public List<Role> getAll(){
		return roleDao.getAll(Role.class);
	}
	
	public Role getByRoleCode(String roleCode) {
		return roleDao.getByRoleCode(roleCode);
	}
	
}
