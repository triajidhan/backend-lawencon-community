package com.lawencon.lawenconcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.dao.PaymentSubscribeDao;
import com.lawencon.lawenconcommunity.dao.UserDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.PaymentSubscribe;
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.security.principal.PrincipalService;

@Service
public class PaymentSubscribeService extends BaseCoreService{
	@Autowired
	private PaymentSubscribeDao paymentSubscribeDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FileDao fileDao; 
	
	@Autowired
	private PrincipalService principalService;
	
	public ResponseMessageDto insert(PaymentSubscribe data) {
		valInsert(data);
		File fileInsert = new File();
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Payment is Failed!");
		try {
			begin();
			if(data.getFile() != null) {
				fileInsert = fileDao.save(data.getFile());	
				data.setFile(fileInsert);
			}
			User user = new User();
			user.setId(principalService.getAuthPrincipal());
			data.setUser(user);
			paymentSubscribeDao.save(data);
			responseMessageDto.setMessage("Payment is Succes!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Payment is Failed!");
			e.printStackTrace();
		}
		return responseMessageDto;
	}
	
	public void valInsert(PaymentSubscribe data) {
		valIdFk(data);
		valIdNull(data);
	}
	
	public void valIdFk(PaymentSubscribe data) {
		try {
			if(userDao.getByIdAndDetach(User.class, principalService.getAuthPrincipal()) == null) {
				throw new RuntimeException("Please Login First!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void valIdNull(PaymentSubscribe data) {
		if(data.getId() != null) {
			throw new RuntimeException("This Field Must Be Empty!");
		}
	}
	
	public void valUpdate (PaymentSubscribe data) {
		if(paymentSubscribeDao.getByIdAndDetach(PaymentSubscribe.class, data.getId()) == null) {
			throw new RuntimeException("Cant Found the payment!");
		}
	}
	
	public ResponseMessageDto update(PaymentSubscribe data) {
		valUpdate(data);
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Approving Failed!");
		PaymentSubscribe paymentSubscribe = paymentSubscribeDao.getById(PaymentSubscribe.class,data.getId());
		PaymentSubscribe paymentApproving = paymentSubscribe;
		try {
			paymentApproving.setApprove(true);
			paymentSubscribeDao.saveAndFlush(paymentApproving);
			responseMessageDto.setMessage("Approving Success!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Approving Failed!");
			e.printStackTrace();
		}
		
		return responseMessageDto;
	}
}