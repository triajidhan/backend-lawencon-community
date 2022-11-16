package com.lawencon.lawenconcommunity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.config.ApiConfiguration;
import com.lawencon.lawenconcommunity.dao.BalanceDao;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.dao.RoleDao;
import com.lawencon.lawenconcommunity.dao.UserDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.Role;
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.security.principal.PrincipalService;

@Service
public class UserSevice extends BaseCoreService implements UserDetailsService{
	private final UserDao userDao;
	private final RoleDao roleDao;
	private final FileDao fileDao;
	private final BalanceDao balanceDao;
	private final ApiConfiguration apiConfiguration;
	private final PrincipalService principalService;
	public UserSevice(UserDao userDao, RoleDao roleDao, FileDao fileDao, BalanceDao balanceDao, ApiConfiguration apiConfiguration, PrincipalService principalService) {
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.fileDao = fileDao;
		this.balanceDao = balanceDao;
		this.apiConfiguration = apiConfiguration;
		this.principalService = principalService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<User> userOptional = userDao.getByEmail(username);
		
		if(userOptional.isPresent()) {
			return new org.springframework.security.core.userdetails.User(username, userOptional.get().getPass(), new ArrayList<>()); 
		}
		throw new UsernameNotFoundException("Email And Password Wrong");
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
	
	public int getTotalUser() {
		return userDao.getTotalUser();
	}
	
	public int getTotalUserByRole(String role) {
		return userDao.getTotalByRoleCode(role);
	}
	
	
	public ResponseMessageDto insertWithLogin (User data) {
		File fileInsert = new File();
		Balance balanceInsert = new Balance();
		valInsert(data);
		try {
			begin();
			fileInsert = fileDao.save(data.getFile());
			balanceInsert = balanceDao.save(balanceInsert);
			String hashPassword = apiConfiguration.passwordEncoder().encode((data.getPass()));
			data.setPass(hashPassword);
			data.setFile(fileInsert);
			data.setBalance(balanceInsert);
			userDao.save(data);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseMessageDto userInsertResDto = new ResponseMessageDto();
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
	
	public void bkNotNull(User data) {
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

	public  ResponseMessageDto update(User data) {
		User user = userDao.getById(User.class, data.getId());
		User userUpdate = new User();
		if(user != null) {
			userUpdate = user;
			
			if(data.getFullname() != null) {
				userUpdate.setFullname(data.getFullname());
			}
			
			if(data.getRole() != null) {
				userUpdate.setRole(data.getRole());
			}
			
			if(data.getCompany() !=null) {
				userUpdate.setCompany(data.getCompany());
			}
			
			if(data.getIndustry() != null) {
				userUpdate.setIndustry(data.getIndustry());
			}
			
			if(data.getPosition()!=null) {
				userUpdate.setPosition(data.getPosition());
			}
			
			if(data.getStatusSubscribe()!=null) {
				userUpdate.setStatusSubscribe(data.getStatusSubscribe());
			}
			
			if (data.getFile() != null) {
				File file = new File();
				try {
					file = fileDao.save(data.getFile());
				} catch (Exception e) {
					e.printStackTrace();
				}
				userUpdate.setFile(file);
			}
			
			if(data.getIsActive() != null) {
				userUpdate.setIsActive(data.getIsActive());
			}
			
			if(data.getVersion() !=null) {
				userUpdate.setVersion(data.getVersion());
			}
			
			try {
				valUpdate(userUpdate);
				userUpdate.setUpdatedBy(principalService.getAuthPrincipal());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Success");
		try {
			userUpdate = userDao.save(userUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseMessageDto; 
	}
	public void valUpdate(User data) {
		idAvailableUpdate(data);
		fkFound(data);
	}
	
	public void idAvailableUpdate(User data) {
		if(userDao.getByIdAndDetach(User.class, data.getId()) == null) {
			throw new RuntimeException("No User Found!");
		}
	}
}
