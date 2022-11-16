package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.BalanceDao;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.dao.RoleDao;
import com.lawencon.lawenconcommunity.dao.UserDao;
import com.lawencon.lawenconcommunity.dto.UserInsertResDto;
import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.Role;
import com.lawencon.lawenconcommunity.model.User;

@Service
public class UserSevice extends BaseCoreService implements UserDetailsService{
	private final UserDao userDao;
	private final RoleDao roleDao;
	private final FileDao fileDao;
	private final BalanceDao balanceDao;
	private final PasswordEncoder passwordEncoder;
	public UserSevice(UserDao userDao, RoleDao roleDao, FileDao fileDao, BalanceDao balanceDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.fileDao = fileDao;
		this.balanceDao = balanceDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<User> getAll(Integer startPosition, Integer limitPage) {
		List<User> users = userDao.getAll(User.class,startPosition,limitPage);
		return users;
	}
	
	public User getById(String id) {
		return userDao.getByIdAndDetach(User.class, id);
	}
	
	public User getByEmail(String email){
		return userDao.getByEmail(email).get();
	}
	
	public List<User> getByRoleCode(String roleCode){
		return userDao.getByRole(roleCode);
	}
	
	
	public UserInsertResDto insert (User data) {
		File fileInsert = new File();
		Balance balanceInsert = new Balance();
		valInsert(data);
		try {
			begin();
			fileInsert = fileDao.save(data.getFile());
			balanceInsert = balanceDao.save(balanceInsert);
			String hashPassword = passwordEncoder.encode(data.getPass());
			data.setPass(hashPassword);
			data.setFile(fileInsert);
			data.setBalance(balanceInsert);
			userDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserInsertResDto userInsertResDto = new UserInsertResDto();
		userInsertResDto.setMessage("Registration is successful");
		return userInsertResDto;
	}
	
	public void valInsert(User data) {
		idNullInsert(data);
		bkNotDuplicate(data);
		fkFound(data);
	}
	
	public void idNullInsert(User data) {
		if(data.getId()!=null) {
			throw new RuntimeException("Id Must Be Empty!");
		}
		
		if(data.getFile().getId()!=null) {
			throw new RuntimeException("Id Must Be Empty!");
		}
	}
	
	public void bkNotDuplicate(User data) {
		if(userDao.getByEmail(data.getEmail()).isPresent()) {
			throw new RuntimeException("This email has been registered!");
		}
	}
	
	public void fkFound(User data) {		
		if(roleDao.getById(Role.class, data.getRole().getId())==null) {
			throw new RuntimeException("Role not found!");
		}
	}
	
	public void notNull(User data) {
		if(data.getRole()==null) {
			throw new RuntimeException("Role Required!");
		}
		
		if(data.getEmail()==null) {
			throw new RuntimeException("Email Required!");
		}
		
		if(data.getPass()==null) {
			throw new RuntimeException("Password Required!");
		}
		
		if(data.getFullname() == null) {
			throw new RuntimeException("Full Name Required!");
		}
	}


}
