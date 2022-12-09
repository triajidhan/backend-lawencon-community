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
import com.lawencon.lawenconcommunity.dao.IndustryDao;
import com.lawencon.lawenconcommunity.dao.PositionDao;
import com.lawencon.lawenconcommunity.dao.RoleDao;
import com.lawencon.lawenconcommunity.dao.UserDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.Industry;
import com.lawencon.lawenconcommunity.model.Position;
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

	@Autowired
	private IndustryDao industryDao;
	
	@Autowired
	private PositionDao positionDao;
	
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
	
	public List<User> getByRoleCode(String roleCode,int startPosition,int limit){
		return userDao.getByRole(roleCode,startPosition,limit);
	}
	
	public User getTotalUser() {
		return userDao.getTotalUser();
	}
	
	public User getTotalUserByRole(String role) {
		return userDao.getTotalByRoleCode(role);
	}
	
	public User getTotalUserSubscribe() {
		return userDao.getTotalBySubscribe();
	}
	
	public List<User> getByIsActive(int startPosition,int limit){
		return userDao.getByIsActive(startPosition,limit);
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
			if(data.getFile() != null) {
				if(!data.getFile().getFiles().equals("")||data.getFile().getExt().equals("")) {
					fileInsert = fileDao.save(data.getFile());	
					data.setFile(fileInsert);
				}
			}
			balanceInsert = balanceDao.save(balanceInsert);
			String hashPassword = passwordEncoder.encode((data.getPass()));
			data.setPass(hashPassword);
			data.setBalance(balanceInsert);
			data.setStatusSubscribe(true);
			data.setRole(roleDao.getByRoleCode(com.lawencon.lawenconcommunity.constant.Role.A.toString()));
			userDao.save(data);
			userInsertResDto.setMessage("Registration is successful");
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Registration is Failed");
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
			if(data.getFile() != null ) {
				if(!data.getFile().getFiles().equals("")||data.getFile().getExt().equals("")) {
					fileInsert = fileDao.saveNoLogin(data.getFile(), () -> userDao.getSystem("SYS").get().getId());	
					data.setFile(fileInsert);
				}
			}
			balanceInsert = balanceDao.saveNoLogin(balanceInsert, () -> userDao.getSystem("SYS").get().getId());
			String hashPassword = passwordEncoder.encode((data.getPass()));
			data.setPass(hashPassword);		
			data.setBalance(balanceInsert);
			data.setStatusSubscribe(false);
			if(data.getIndustryId()!=null) {				
				data.setIndustry(industryDao.getByIdAndDetach(Industry.class, data.getIndustryId()));
			}
			
			if(data.getPositionId()!=null) {				
				data.setPosition(positionDao.getByIdAndDetach(Position.class, data.getPositionId()));
			}
			
			data.setRole(roleDao.getByRoleCode(com.lawencon.lawenconcommunity.constant.Role.M.toString()));
			userDao.saveNoLogin(data, ()->userDao.getSystem("SYS").get().getId());
			userInsertResDto.setMessage("Registration is successful");
			commit();
		} catch (Exception e) {
			userInsertResDto.setMessage("Registration is Failed");
			e.printStackTrace();
		}
		return userInsertResDto;
	}
	
	public void valInsert(User data) {
		idNullInsert(data);
		bkNotDuplicate(data);
	}
	

	public void idNullInsert(User data) {
		if(data.getId()!=null) {
			throw new RuntimeException("Id Must Be Empty!");
		}
		
		if (data.getFile() != null) {
			if(data.getFile().getId() != null) {
				throw new RuntimeException("Id Must Be Empty!");				
			}
		}
	}
	
	public void bkNotDuplicate(User data) {
		if(userDao.getByEmail(data.getEmail()).isPresent()) {
			throw new RuntimeException("This email has been registered!");
		}
	}
	
	
	public void bkNotNull(User data) {
		
		if(data.getEmail()==null) {
			throw new RuntimeException("Email Required!");
		}
		
		if(data.getPass()==null) {
			throw new RuntimeException("Password Required!");
		}
		
		if(data.getFullName() == null) {
			throw new RuntimeException("Full Name Required!");
		}
	}

	public  ResponseMessageDto update(User data) {
		User user = userDao.getByIdAndDetach(User.class, data.getId());
		User userUpdate = new User();
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed!");
		valUpdate(data);
		begin();
		if(user != null) {
			userUpdate = user;
			
			if(data.getFullName() != null) {
				userUpdate.setFullName(data.getFullName());
			}
			
			if(data.getRole() != null) {
				userUpdate.setRole(data.getRole());
			}
			
			if(data.getCompany() !=null) {
				userUpdate.setCompany(data.getCompany());
			}
			
			if(data.getIndustry() != null) {
				userUpdate.setIndustry(data.getIndustry());
				userUpdate.setIndustry(industryDao.getByIdAndDetach(Industry.class, data.getIndustry().getId()));
			}
			
			if(data.getPosition()!=null) {
				userUpdate.setPosition(data.getPosition());
				userUpdate.setPosition(positionDao.getByIdAndDetach(Position.class, data.getPosition().getId()));
			}
			
			if(data.getStatusSubscribe()!=null) {
				userUpdate.setStatusSubscribe(data.getStatusSubscribe());
			}
			
			if(data.getPass()!=null) {
					userUpdate.setPass(passwordEncoder.encode(data.getPass()));
			}
			try {
				if (data.getFile() != null) {
					if(!data.getFile().getFiles().equals("")&&!data.getFile().getExt().equals("")) {
						File file = new File();
						file = fileDao.save(data.getFile());
						userUpdate.setFile(file);	
					}
					
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
	}
	
	public void idAvailableUpdate(User data) {
		if(userDao.getByIdAndDetach(User.class, data.getId()) == null) {
			throw new RuntimeException("Sorry, your password/email was incorrect.");
		}
	}
	
	
}