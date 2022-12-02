package com.lawencon.lawenconcommunity.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.dao.PaymentSubscribeDao;
import com.lawencon.lawenconcommunity.dao.UserDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.PaymentSubscribe;
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.security.principal.PrincipalService;

@Service
public class PaymentSubscribeService extends BaseCoreService {
	@Autowired
	private PaymentSubscribeDao paymentSubscribeDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private FileDao fileDao;

	@Autowired
	private PrincipalService principalService;

	@Autowired
	private GenerateService generateService;

	public List<PaymentSubscribe> getAll(int startPosition, int limit) {
		return paymentSubscribeDao.getAll(PaymentSubscribe.class, startPosition, limit);
	}

	public PaymentSubscribe getById(String id) {
		return paymentSubscribeDao.getByIdAndDetach(PaymentSubscribe.class, id);
	}

	public List<PaymentSubscribe> getByIsActive(int startPosition, int limit) {
		return paymentSubscribeDao.getByIsActive(startPosition, limit);
	}

	public List<PaymentSubscribe> getByIsActive() {
		return paymentSubscribeDao.getByIsActive();
	}

	public PaymentSubscribe getTotalPaymentSubscribe() {
		return paymentSubscribeDao.getTotalPaymentSubscribe();
	}
	
	public List<PaymentSubscribe> getByIsActiveFalse(int startPosition,int limit, boolean isAscending){
		return paymentSubscribeDao.getByIsActiveFalse(startPosition, limit, isAscending);
	}
	
	public List<PaymentSubscribe> getByIsActiveTrueAndApprovedFalse(int startPosition,int limit, boolean isAscending){
		return paymentSubscribeDao.getByIsActiveTrueAndApprovedFalse(startPosition, limit, isAscending);
	}
	
	public ResponseMessageDto insert(PaymentSubscribe data) {
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
			data.setUser(userDao.getById(User.class, principalService.getAuthPrincipal()));
			data.setPaymentCode(generateService.generate(5));
			paymentSubscribeDao.save(data);
			responseMessageDto.setMessage("Payment is Succes!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Payment is Failed!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}

	public void valInsert(PaymentSubscribe data) {
		valIdFk(data);
		valIdNull(data);
	}

	public void valIdFk(PaymentSubscribe data) {
		try {
			if (userDao.getByIdAndDetach(User.class, principalService.getAuthPrincipal()) == null) {
				throw new RuntimeException("Please Login First!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void valIdNull(PaymentSubscribe data) {
		if (data.getId() != null) {
			throw new RuntimeException("This Field Must Be Empty!");
		}
	}

	public void valUpdate(PaymentSubscribe data) {
		if (paymentSubscribeDao.getByIdAndDetach(PaymentSubscribe.class, data.getId()) == null) {
			throw new RuntimeException("Cant Found the payment!");
		}
	}

	public ResponseMessageDto update(PaymentSubscribe data) {
		valUpdate(data);
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		PaymentSubscribe paymentSubscribe = paymentSubscribeDao.getById(PaymentSubscribe.class, data.getId());
		begin();
		if (paymentSubscribe.getApprove() == false && data.getIsActive() == true) {
			User userSystem = userDao.getById(User.class, userDao.getSystem("SYS").get().getId());
			User member = userDao.getById(User.class, data.getCreatedBy());
			PaymentSubscribe paymentApproving = paymentSubscribe;
			try {
				paymentApproving.setApprove(true);
				paymentSubscribeDao.saveAndFlush(paymentApproving);
				responseMessageDto.setMessage("Approving Success!");
				BigDecimal totalBalance = data.getPrice().add(userSystem.getBalance().getTotalBalance());
				Balance balance = new Balance();
				balance = userSystem.getBalance();
				balance.setTotalBalance(totalBalance);
				userSystem.setBalance(balance);
				userDao.save(userSystem);
				member.setStatusSubscribe(true);
				userDao.save(member);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Approving Failed!");
			}
		} else if (data.getIsActive() != null) {
			PaymentSubscribe paymentApproving = paymentSubscribe;
			paymentApproving.setIsActive(data.getIsActive());
			try {
				paymentSubscribeDao.saveAndFlush(paymentApproving);
				responseMessageDto.setMessage("Rejected");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Rejecting Failed!");
			}
		} else {
			throw new RuntimeException("Approving can only be done once!");
		}
		commit();
		return responseMessageDto;
	}
}
