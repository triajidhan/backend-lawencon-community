package com.lawencon.lawenconcommunity.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.dao.PaymentActivityDetailDao;
import com.lawencon.lawenconcommunity.dao.UserDao;
import com.lawencon.lawenconcommunity.dto.PaymentPartisipationMemberDto;
import com.lawencon.lawenconcommunity.dto.PaymentPartisipationSuperDto;
import com.lawencon.lawenconcommunity.dto.PaymentTotalIncomeMemberDto;
import com.lawencon.lawenconcommunity.dto.PaymentTotalIncomeSuperDto;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Activity;
import com.lawencon.lawenconcommunity.model.ActivityType;
import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.Industry;
import com.lawencon.lawenconcommunity.model.PaymentActivityDetail;
import com.lawencon.lawenconcommunity.model.Position;
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
	
	public List<PaymentActivityDetail> getByActivityTypeAndUser(String activityTypeId,String userId,int startPosition,int limit){
		return paymentActivityDetailDao.getByActivityTypeAndUser(activityTypeId, userId, startPosition, limit);
	}
	
	public List<PaymentActivityDetail> getByActivityTypeAndUserOrder(String activityTypeId,String userId,int startPosition,int limit,boolean ascending){
		return paymentActivityDetailDao.getByActivityTypeAndUser(activityTypeId, userId, startPosition, limit,ascending);
	}
	
	public List<PaymentActivityDetail> getByActivityTypeCodeAndUser(String activityTypeCode,String userId,int startPosition,int limit){
		return paymentActivityDetailDao.getByActivityTypeCodeAndUser(activityTypeCode, userId, startPosition, limit);
	}
	
	public List<PaymentActivityDetail> getByActivityTypeCodeAndUserOrder(String activityTypeCode,String userId,int startPosition,int limit,boolean ascending){
		return paymentActivityDetailDao.getByActivityTypeCodeAndUser(activityTypeCode, userId, startPosition, limit,ascending);
	}
	
	
	
	public List<PaymentActivityDetail> getByIsActiveTrueAndApprovedFalse(int startPosition,int limit, boolean isAscending){
		return paymentActivityDetailDao.getByIsActiveTrueAndApprovedFalse(startPosition,limit, isAscending);
	}
	
	public List<PaymentActivityDetail> getByIsActiveFalse(int startPosition,int limit, boolean isAscending){
		return paymentActivityDetailDao.getByIsActiveFalse(startPosition,limit, isAscending);
	}
	
	
	public List<PaymentActivityDetail> getReportPartisipationMember(LocalDateTime beginDate,LocalDateTime finishDate,boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = null;
		try {
			paymentActivityDetails = paymentActivityDetailDao.getReportPartisipationMember(beginDate, finishDate,principalService.getAuthPrincipal(),ascending);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return paymentActivityDetails;
	}
	
	public List<PaymentActivityDetail> getReportPartisipationMember(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit, boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = null;
		try {
			paymentActivityDetails = paymentActivityDetailDao.getReportPartisipationMember(beginDate, finishDate,startPosition,limit,principalService.getAuthPrincipal(),ascending);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return paymentActivityDetails;
	}
	
	
	public List<PaymentActivityDetail> getReportPartisipationSuper(LocalDateTime beginDate,LocalDateTime finishDate,boolean ascending){
		return paymentActivityDetailDao.getReportPartisipationSuper(beginDate, finishDate,ascending);
	}
	
	public List<PaymentActivityDetail> getReportPartisipationSuper(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit, boolean ascending){
		return paymentActivityDetailDao.getReportPartisipationSuper(beginDate, finishDate,startPosition,limit,ascending);
	}
	
	public List<PaymentActivityDetail> getReportIncomeMember(LocalDateTime beginDate,LocalDateTime finishDate,boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = null;
		try {
			paymentActivityDetails = paymentActivityDetailDao.getReportIncomeMember(beginDate, finishDate,principalService.getAuthPrincipal(),ascending);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return paymentActivityDetails;
	}
	
	public List<PaymentActivityDetail> getReportIncomeMember(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit, boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = null;
		try {
			paymentActivityDetails = paymentActivityDetailDao.getReportIncomeMember(beginDate, finishDate,startPosition,limit,principalService.getAuthPrincipal(),ascending);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return paymentActivityDetails;
	}
	
	public List<PaymentActivityDetail> getReportIncomeSuper(LocalDateTime beginDate,LocalDateTime finishDate, boolean ascending){
		return paymentActivityDetailDao.getReportIncomeSuper(beginDate, finishDate,ascending);
	}
	
	public List<PaymentActivityDetail> getReportIncomeSuper(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit, boolean ascending){
		return paymentActivityDetailDao.getReportIncomeSuper(beginDate, finishDate,startPosition,limit, ascending);
	}
	
	
	
	public List<PaymentPartisipationMemberDto> getReportPaymentPartisipationMemberDto(LocalDateTime beginDate,LocalDateTime finishDate, boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = null;
		
		List<PaymentPartisipationMemberDto> partisipationMemberDtos = new ArrayList<>();
		
		try {
			paymentActivityDetails = paymentActivityDetailDao.getReportPartisipationMember(beginDate, finishDate,principalService.getAuthPrincipal(),ascending);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < paymentActivityDetails.size();i++) {
			PaymentActivityDetail paymentActivityDetail =  paymentActivityDetails.get(i);
			
			PaymentPartisipationMemberDto partisipationMemberDto = new PaymentPartisipationMemberDto();
			
			ActivityType activityType =  paymentActivityDetail.getActivity().getActivityType();
			partisipationMemberDto.setActivityTypeCode(activityType.getActivityTypeCode());
			partisipationMemberDto.setActivityTypeName(activityType.getActivityTypeName());
			
			Activity activity =  paymentActivityDetail.getActivity();
			partisipationMemberDto.setActivityCode(activity.getActivityCode());
			partisipationMemberDto.setTitle(activity.getTitle());
			partisipationMemberDto.setBeginSchedule(activity.getBeginSchedule());
			partisipationMemberDto.setFinishSchedule(activity.getFinishSchedule());
			partisipationMemberDto.setLocation(activity.getLocation());
			partisipationMemberDto.setPrice(activity.getPrice());
			partisipationMemberDto.setProvider(activity.getProvider());
			
			User user = paymentActivityDetail.getUser();
			partisipationMemberDto.setId(user.getId());
			partisipationMemberDto.setFullName(user.getFullName());
			partisipationMemberDto.setCompany(user.getCompany());
			
			Position position = paymentActivityDetail.getUser().getPosition();
			partisipationMemberDto.setPositionCode(position.getPositionCode());
			partisipationMemberDto.setPositionName(position.getPositionName());
			
			Industry industry = paymentActivityDetail.getUser().getIndustry();
			partisipationMemberDto.setIndustryCode(industry.getIndustryCode());
			partisipationMemberDto.setIndustryName(industry.getIndustryName());
			
			partisipationMemberDto.setPartisipant(paymentActivityDetail.getPartisipation());
			
			partisipationMemberDto.setCreatedBy(paymentActivityDetail.getActivity().getCreatedBy());
			
			partisipationMemberDtos.add(partisipationMemberDto);
		}
		
		return partisipationMemberDtos;
	}
	
	
	public List<PaymentPartisipationSuperDto> getReportPaymentPartisipationSuperDto(LocalDateTime beginDate,LocalDateTime finishDate,boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails =  paymentActivityDetailDao.getReportPartisipationSuper(beginDate, finishDate,ascending);
		
		List<PaymentPartisipationSuperDto> partisipationSuperDtos = new ArrayList<>();
		
		for(int i = 0; i < paymentActivityDetails.size();i++) {
			PaymentActivityDetail paymentActivityDetail =  paymentActivityDetails.get(i);
			
			PaymentPartisipationSuperDto partisipationSuperDto = new PaymentPartisipationSuperDto();
			
			ActivityType activityType =  paymentActivityDetail.getActivity().getActivityType();
			partisipationSuperDto.setActivityTypeCode(activityType.getActivityTypeCode());
			partisipationSuperDto.setActivityTypeName(activityType.getActivityTypeName());
			
			Activity activity =  paymentActivityDetail.getActivity();
			partisipationSuperDto.setActivityCode(activity.getActivityCode());
			partisipationSuperDto.setTitle(activity.getTitle());
			partisipationSuperDto.setBeginSchedule(activity.getBeginSchedule());
			partisipationSuperDto.setFinishSchedule(activity.getFinishSchedule());
			partisipationSuperDto.setLocation(activity.getLocation());
			partisipationSuperDto.setPrice(activity.getPrice());
			partisipationSuperDto.setProvider(activity.getProvider());
			
			User user = paymentActivityDetail.getUser();
			partisipationSuperDto.setId(user.getId());
			partisipationSuperDto.setFullName(user.getFullName());
			partisipationSuperDto.setCompany(user.getCompany());
			
			Position position = paymentActivityDetail.getUser().getPosition();
			partisipationSuperDto.setPositionCode(position.getPositionCode());
			partisipationSuperDto.setPositionName(position.getPositionName());
			
			Industry industry = paymentActivityDetail.getUser().getIndustry();
			partisipationSuperDto.setIndustryCode(industry.getIndustryCode());
			partisipationSuperDto.setIndustryName(industry.getIndustryName());
			
			partisipationSuperDto.setPartisipant(paymentActivityDetail.getPartisipation());
			
			partisipationSuperDto.setCreatedBy(paymentActivityDetail.getActivity().getCreatedBy());
			
			partisipationSuperDtos.add(partisipationSuperDto);
		}
		
		return partisipationSuperDtos;
	}
	
	public List<PaymentTotalIncomeMemberDto> getPaymentTotalIncomeMemberDto(LocalDateTime beginDate,LocalDateTime finishDate,boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails = null;
		try {
			paymentActivityDetails = paymentActivityDetailDao.getReportIncomeMember(beginDate, finishDate,principalService.getAuthPrincipal(),ascending);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<PaymentTotalIncomeMemberDto> totalIncomeMemberDtos = new ArrayList<>();
		
		for(int i = 0; i < paymentActivityDetails.size();i++) {
			PaymentActivityDetail paymentActivityDetail =  paymentActivityDetails.get(i);
			
			PaymentTotalIncomeMemberDto totalIncomeMemberDto = new PaymentTotalIncomeMemberDto();
			
			ActivityType activityType =  paymentActivityDetail.getActivity().getActivityType();
			totalIncomeMemberDto.setActivityTypeCode(activityType.getActivityTypeCode());
			totalIncomeMemberDto.setActivityTypeName(activityType.getActivityTypeName());
			
			Activity activity =  paymentActivityDetail.getActivity();
			totalIncomeMemberDto.setActivityCode(activity.getActivityCode());
			totalIncomeMemberDto.setTitle(activity.getTitle());
			totalIncomeMemberDto.setBeginSchedule(activity.getBeginSchedule());
			totalIncomeMemberDto.setFinishSchedule(activity.getFinishSchedule());
			totalIncomeMemberDto.setLocation(activity.getLocation());
			totalIncomeMemberDto.setPrice(activity.getPrice());
			totalIncomeMemberDto.setProvider(activity.getProvider());
			
			User user = paymentActivityDetail.getUser();
			totalIncomeMemberDto.setId(user.getId());
			totalIncomeMemberDto.setFullName(user.getFullName());
			totalIncomeMemberDto.setCompany(user.getCompany());
			
			Position position = paymentActivityDetail.getUser().getPosition();
			totalIncomeMemberDto.setPositionCode(position.getPositionCode());
			totalIncomeMemberDto.setPositionName(position.getPositionName());
			
			Industry industry = paymentActivityDetail.getUser().getIndustry();
			totalIncomeMemberDto.setIndustryCode(industry.getIndustryCode());
			totalIncomeMemberDto.setIndustryName(industry.getIndustryName());
			
			totalIncomeMemberDto.setTotalIncome(paymentActivityDetail.getNet());
			
			totalIncomeMemberDto.setCreatedBy(paymentActivityDetail.getActivity().getCreatedBy());
			
			totalIncomeMemberDtos.add(totalIncomeMemberDto);
		}
		
		return totalIncomeMemberDtos;
	}
	
	public List<PaymentTotalIncomeSuperDto> getPaymentTotalIncomeSuperDto(LocalDateTime beginDate,LocalDateTime finishDate,boolean ascending){
		List<PaymentActivityDetail> paymentActivityDetails =  paymentActivityDetailDao.getReportIncomeSuper(beginDate, finishDate,ascending);
		
		List<PaymentTotalIncomeSuperDto> totalIncomeMemberDtos = new ArrayList<>();
		
		for(int i = 0; i < paymentActivityDetails.size();i++) {
			PaymentActivityDetail paymentActivityDetail =  paymentActivityDetails.get(i);
			
			PaymentTotalIncomeSuperDto totalIncomeSuperDto = new PaymentTotalIncomeSuperDto();
			
			ActivityType activityType =  paymentActivityDetail.getActivity().getActivityType();
			totalIncomeSuperDto.setActivityTypeCode(activityType.getActivityTypeCode());
			totalIncomeSuperDto.setActivityTypeName(activityType.getActivityTypeName());
			
			User user = paymentActivityDetail.getUser();
			totalIncomeSuperDto.setId(user.getId());
			totalIncomeSuperDto.setFullName(user.getFullName());
			totalIncomeSuperDto.setCompany(user.getCompany());
			
			Position position = paymentActivityDetail.getUser().getPosition();
			totalIncomeSuperDto.setPositionCode(position.getPositionCode());
			totalIncomeSuperDto.setPositionName(position.getPositionName());
			
			Industry industry = paymentActivityDetail.getUser().getIndustry();
			totalIncomeSuperDto.setIndustryCode(industry.getIndustryCode());
			totalIncomeSuperDto.setIndustryName(industry.getIndustryName());
			
			totalIncomeSuperDto.setTotalIncome(paymentActivityDetail.getNet());
			
			totalIncomeSuperDto.setCreatedBy(paymentActivityDetail.getActivity().getCreatedBy());
			
			totalIncomeMemberDtos.add(totalIncomeSuperDto);
		}
		
		return totalIncomeMemberDtos;
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
