package com.lawencon.lawenconcommunity.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.Role;
import com.lawencon.lawenconcommunity.model.User;

@Service
public class UserSevice extends BaseCoreService implements UserDetailsService{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private BalanceDao balanceDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
		balanceInsert.setTotalBalance(new BigDecimal(0));
		ResponseMessageDto userInsertResDto = new ResponseMessageDto();
		userInsertResDto.setMessage("Registration is Failed");
		valInsert(data);
		
		try {
			begin();
			fileInsert = fileDao.save(data.getFile());
			balanceInsert = balanceDao.save(balanceInsert);
			String hashPassword = passwordEncoder.encode((data.getPass()));
			data.setPass(hashPassword);
			data.setFile(fileInsert);
			data.setBalance(balanceInsert);
			userDao.save(data);
			userInsertResDto.setMessage("Registration is successful");
			commit();
		} catch (Exception e) {
			userInsertResDto.setMessage("Registration is Failed");
			e.printStackTrace();
		}
		return userInsertResDto;
	}
	
	public ResponseMessageDto insertWithoutLogin (User data) {
		File fileInsert = new File();
		Balance balanceInsert = new Balance();
		balanceInsert.setTotalBalance(new BigDecimal(0));
		ResponseMessageDto userInsertResDto = new ResponseMessageDto();
		userInsertResDto.setMessage("Registration is Failed");
		valInsert(data);
		try {
			begin();
			fileInsert = fileDao.saveNoLogin(data.getFile(), () -> userDao.getSystem("SYS").get().getId());
			balanceInsert = balanceDao.saveNoLogin(balanceInsert, () -> userDao.getSystem("SYS").get().getId());
			String hashPassword = passwordEncoder.encode((data.getPass()));
			data.setPass(hashPassword);
			data.setFile(fileInsert);
			data.setBalance(balanceInsert);
			userDao.saveNoLogin(data, ()->userDao.getSystem("SYS").get().getId());
			userInsertResDto.setMessage("Registration is successful");
			commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed!");
		begin();
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
			try {
				if (data.getFile() != null) {
					File file = new File();
					file = fileDao.save(data.getFile());
					userUpdate.setFile(file);
				}
				
				if(data.getIsActive() != null) {
					userUpdate.setIsActive(data.getIsActive());
				}
				
				if(data.getVersion() !=null) {
					userUpdate.setVersion(data.getVersion());
				}
				userUpdate = userDao.save(userUpdate);
				responseMessageDto.setMessage("Success");		
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		commit();
		
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
