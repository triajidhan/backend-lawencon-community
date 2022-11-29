package com.lawencon.lawenconcommunity.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
	
	@Autowired
	private GenerateService generateService;
	
	public List<PaymentActivityDetail> getAll(int startPosition,int limit){
		return paymentActivityDetailDao.getAll(PaymentActivityDetail.class,startPosition, limit);
	}
	
	public PaymentActivityDetail getById(String id) {
		return paymentActivityDetailDao.getByIdAndDetach(PaymentActivityDetail.class, id);
	}
	
	public PaymentActivityDetail getTotalPaymentActivityDetail(){
		return paymentActivityDetailDao.getTotalPaymentActivityDetail();
	}
	
	public List<PaymentActivityDetail> getByActivity(String activityId){
		return paymentActivityDetailDao.getByActivity(activityId);
	}
	
	public List<PaymentActivityDetail> getByActivity(String activityId,int startPosition,int limit){
		return paymentActivityDetailDao.getByActivity(activityId,startPosition, limit);
	}
	
	public PaymentActivityDetail getTotalByActivity(String activityId){
		return paymentActivityDetailDao.getTotalByActivity(activityId);
	}
	
	public List<PaymentActivityDetail> getByIsActive(){
		return paymentActivityDetailDao.getByIsActive();
	}
	
	public List<PaymentActivityDetail> getByIsActive(int startPosition,int limit){
		return paymentActivityDetailDao.getByIsActive(startPosition, limit);
	}
	
	public List<PaymentActivityDetail> getByUser(String userId,int startPosition,int limit,boolean ascending){
		return paymentActivityDetailDao.getByUser(userId, startPosition, limit,ascending);
	}
	
	public List<PaymentActivityDetail> getByActivityTypeAndUser(String activityTypeId,String userId,int startPosition,int limit,boolean ascending){
		return paymentActivityDetailDao.getByActivityTypeAndUser(activityTypeId, userId, startPosition, limit,ascending);
	}
	
	public List<PaymentActivityDetail> getByActivityTypeAndUserOrder(String activityTypeId,String userId,int startPosition,int limit,boolean ascending){
		return paymentActivityDetailDao.getByActivityTypeAndUser(activityTypeId, userId, startPosition, limit,ascending);
	}
	
	public List<PaymentActivityDetail> getReportPartisipation(LocalDateTime beginDate,LocalDateTime finishDate){
		return paymentActivityDetailDao.getReportPartisipation(beginDate, finishDate);
	}
	
	public List<PaymentActivityDetail> getReportPartisipation(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit){
		return paymentActivityDetailDao.getReportPartisipation(beginDate, finishDate,startPosition,limit);
	}
	
	public List<PaymentActivityDetail> getReportIncome(LocalDateTime beginDate,LocalDateTime finishDate){
		return paymentActivityDetailDao.getReportIncome(beginDate, finishDate);
	}
	
	public List<PaymentActivityDetail> getReportIncome(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit){
		return paymentActivityDetailDao.getReportIncome(beginDate, finishDate,startPosition,limit);
	}

	public ResponseMessageDto insert(PaymentActivityDetail data) {
		valInsert(data);
		File fileInsert = new File();
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Payment is Failed!");
		begin();
		try {
			if (data.getFile() != null) {
				fileInsert = fileDao.save(data.getFile());
				BigDecimal administrativeCosts = new BigDecimal("5000");
				data.setNet(data.getNet().subtract(administrativeCosts));
				data.setFile(fileInsert);
			}
			data.setPaymentCode(generateService.generate(5));
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
		PaymentActivityDetail paymentActivityDetail = paymentActivityDetailDao.getById(PaymentActivityDetail.class,
				data.getId());
		if(paymentActivityDetail.getApprove() == false) {
			
			User user = userDao.getById(User.class, paymentActivityDetail.getActivity().getCreatedBy());
			PaymentActivityDetail paymentApproving = paymentActivityDetail;
			User userSystem = userDao.getById(User.class,userDao.getSystem("SYS").get().getId());
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
				
				BigDecimal totalBalanceSystem = userSystem.getBalance().getTotalBalance().add(new BigDecimal(5000));
				Balance sysBalance = new Balance();
				sysBalance = userSystem.getBalance();
				sysBalance.setTotalBalance(totalBalanceSystem);
				userSystem.setBalance(sysBalance);
				userDao.save(userSystem);
				
				responseMessageDto.setMessage("Approving Success!");
			} catch (Exception e) {
				responseMessageDto.setMessage("Approving Failed!");
				e.printStackTrace();
			}
			commit();
		}else {
			throw new RuntimeException("Approving can only be done once!");
		}
		return responseMessageDto;
	}

}
