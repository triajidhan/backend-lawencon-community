package com.lawencon.lawenconcommunity.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.dao.PaymentActivityDetailDao;
import com.lawencon.lawenconcommunity.dao.UserDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.PaymentActivityDetail;
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.security.principal.PrincipalService;

@Service
public class PaymentActivityDetailService extends BaseCoreService {
	@Autowired
	private PaymentActivityDetailDao paymentActivityDetailDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FileDao fileDao;

	@Autowired
	private PrincipalService principalService;

	public ResponseMessageDto insert(PaymentActivityDetail data) {
		valInsert(data);
		File fileInsert = new File();
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Payment is Failed!");
		begin();
		try {
			if (data.getFile() != null) {
				fileInsert = fileDao.save(data.getFile());
				data.setFile(fileInsert);
			}
			paymentActivityDetailDao.save(data);
			responseMessageDto.setMessage("Payment is Success!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Payment is Failed!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}

	public void valInsert(PaymentActivityDetail data) {
		valIdFk(data);
		valIdNull(data);
	}

	public void valIdFk(PaymentActivityDetail data) {
		try {
			if (userDao.getByIdAndDetach(User.class, principalService.getAuthPrincipal()) == null) {
				throw new RuntimeException("Please Login First!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void valIdNull(PaymentActivityDetail data) {
		if (data.getId() != null) {
			throw new RuntimeException("This Field Must Be Empty!");
		}
	}

	public void valUpdate(PaymentActivityDetail data) {
		if (paymentActivityDetailDao.getByIdAndDetach(PaymentActivityDetail.class, data.getId()) == null) {
			throw new RuntimeException("Cant found the payment!");
		}
	}

	public ResponseMessageDto update(PaymentActivityDetail data) {
		valUpdate(data);
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Approving Failed!");
		User user = userDao.getById(User.class, data.getCreatedBy());
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailDao.getById(PaymentActivityDetail.class,
				data.getId());
		PaymentActivityDetail paymentApproving = paymentActivityDetail;
		begin();
		try {
			paymentApproving.setApprove(true);
			paymentActivityDetailDao.saveAndFlush(paymentApproving);
			BigDecimal totalBalance = data.getNet().add(user.getBalance().getTotalBalance());
			Balance balance = new Balance();
			balance = user.getBalance();
			balance.setTotalBalance(totalBalance);
			user.setBalance(balance);
			userDao.save(user);
			responseMessageDto.setMessage("Approving Success!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Approving Failed!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}

}
