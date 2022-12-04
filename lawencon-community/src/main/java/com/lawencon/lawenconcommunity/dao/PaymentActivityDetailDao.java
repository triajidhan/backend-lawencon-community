package com.lawencon.lawenconcommunity.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Activity;
import com.lawencon.lawenconcommunity.model.ActivityType;
import com.lawencon.lawenconcommunity.model.Industry;
import com.lawencon.lawenconcommunity.model.PaymentActivityDetail;
import com.lawencon.lawenconcommunity.model.Position;
import com.lawencon.lawenconcommunity.model.User;

@Repository
public class PaymentActivityDetailDao extends AbstractJpaDao{

	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByIsActive(int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = true ")
		.append("ORDER BY tpad.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentActivityDetail.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByIsActive(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = true ");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentActivityDetail.class)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	public PaymentActivityDetail getTotalPaymentActivityDetail() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = true ");
		
		Object objPaymentActivity = null;
		PaymentActivityDetail paymentActivity = null;
		
		try {
			objPaymentActivity = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentActivity != null) {
			Object obj = (Object) objPaymentActivity;
			
			paymentActivity = new PaymentActivityDetail();
			
			paymentActivity.setCountOfPaymentActivity(Integer.parseInt(obj.toString()));
		}
		
		return paymentActivity;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByIsActiveFalse(int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = false ")
		.append("ORDER BY tpad.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentActivityDetail.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByIsActiveTrueAndApprovedFalse(int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = true AND tpad.approve = false ")
		.append("ORDER BY tpad.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentActivityDetail.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	public PaymentActivityDetail getTotalByIsActiveTrueAndApprovedFalse() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = true AND tpad.approve = false ");
		
		Object objPaymentActivity = null;
		PaymentActivityDetail paymentActivity = null;
		
		try {
			objPaymentActivity = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentActivity != null) {
			Object obj = (Object) objPaymentActivity;
			
			paymentActivity = new PaymentActivityDetail();
			
			paymentActivity.setCountOfPaymentActivity(Integer.parseInt(obj.toString()));
		}
		
		return paymentActivity;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByIsActiveTrueAndApprovedTrue(int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = true AND tpad.approve = true ")
		.append("ORDER BY tpad.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentActivityDetail.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	public PaymentActivityDetail getTotalByIsActiveTrueAndApprovedTrue() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = true AND tpad.approve = true ");
		
		Object objPaymentActivity = null;
		PaymentActivityDetail paymentActivity = null;
		
		try {
			objPaymentActivity = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentActivity != null) {
			Object obj = (Object) objPaymentActivity;
			
			paymentActivity = new PaymentActivityDetail();
			
			paymentActivity.setCountOfPaymentActivity(Integer.parseInt(obj.toString()));
		}
		
		return paymentActivity;
	}
	
		
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByActivity(String activityId,int startPosition,int limit){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = true ")
		.append("AND activity_id = :activityId ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString())
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.setParameter("activityId",activityId)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByActivityTypeAndUser(String activityTypeId,String userId,int startPosition,int limit){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON ta.activity_type_id = tat.id ")
		.append("WHERE tpad.is_active = true ")
		.append("AND activity_type_id = :activityId ")
		.append("AND tpad.created_by = :userId ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentActivityDetail.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.setParameter("activityId",activityTypeId)
				.setParameter("userId",userId)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByActivityTypeAndUser(String activityTypeId,String userId,int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending)? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON ta.activity_type_id = tat.id ")
		.append("WHERE tpad.is_active = true ")
		.append("AND activity_type_id = :activityId ")
		.append("AND tpad.created_by = :userId ")
		.append("ORDER BY tpad.created_by ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentActivityDetail.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.setParameter("activityId",activityTypeId)
				.setParameter("userId",userId)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByActivityTypeCodeAndUser(String activityTypeCode,String userId,int startPosition,int limit){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON ta.activity_type_id = tat.id ")
		.append("WHERE tpad.is_active = true ")
		.append("AND tat.activity_type_code = :activityCode ")
		.append("AND tpad.created_by = :userId ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentActivityDetail.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.setParameter("activityCode",activityTypeCode)
				.setParameter("userId",userId)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByActivityTypeCodeAndUser(String activityTypeCode,String userId,int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending)? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON ta.activity_type_id = tat.id ")
		.append("WHERE tpad.is_active = true ")
		.append("AND activity_type_id = :activityCode ")
		.append("AND tpad.created_by = :userId ")
		.append("ORDER BY tpad.created_by ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(), PaymentActivityDetail.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.setParameter("activityCode",activityTypeCode)
				.setParameter("userId",userId)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByUser(String userId,int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending)? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = true ")
		.append("AND tpad.created_by = :userId ")
		.append("ORDER BY tpad.created_by ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentActivityDetail.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.setParameter("userId",userId)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByActivity(String activityId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE tpad.is_active = true ")
		.append("AND activity_id = :activityId");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentActivityDetail.class)
				.setParameter("activityId",activityId)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	public PaymentActivityDetail getTotalByActivity(String activityId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(ta.id) FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE is_active = true ")
		.append("AND tpad.activity_id = :activityId ");
		
		Object objPaymentActivity = null;
		PaymentActivityDetail paymentActivity = null;
		try {
			objPaymentActivity = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentActivity != null) {
			Object obj = (Object) objPaymentActivity;
			
			paymentActivity = new PaymentActivityDetail();
			
			paymentActivity.setCountOfPaymentActivity(Integer.parseInt(obj.toString()));
		}
		
		return paymentActivity;
	}
	
	
	
	
	public List<PaymentActivityDetail> getReportPartisipationMember(LocalDateTime beginDate,LocalDateTime finishDate,String userCreated,boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending =(isAscending)? "ASC ": "DESC ";
		
		sql.append("SELECT title,tu.full_name as member_create, activity_type_name, provider, begin_schedule, partisipant, tu.id, ")
		.append("tu.company, ")
		.append("tp.position_code, tp.position_name, ")
		.append("ti.industry_code, ti.industry_name, ")
		.append("activity_type_code, ")
		.append("activity_code, locations, finish_schedule, price ")
		.append("from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, count(activity_id) as partisipant ")
		.append("FROM (")
		.append("select ta.id as activity_id ")
		.append("FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_partisipant ON ta_prime.id = tb_partisipant.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id ")
		.append("INNER JOIN tb_user tu ON ta_prime.created_by = tu.id ")
		.append("INNER JOIN tb_position tp ON tp.id = tu.position_id ")
		.append("INNER JOIN tb_industry ti ON ti.id = tu.industry_id ")
		.append("WHERE ta_prime.created_by = :userCreate ")
		.append("ORDER BY ta_prime.created_at ")
		.append(ascending);
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<?> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.setParameter("userCreate", userCreated)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			ActivityType activityType = new ActivityType();
			User user = new User();
			Position position = new Position();
			Industry industry = new Industry();
			
			
			activity.setTitle(objArr[0].toString());
			user.setFullName(objArr[1].toString());
			activityType.setActivityTypeName(objArr[2].toString());
			
			activity.setProvider(objArr[3].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[4].toString()).toLocalDateTime());
			
			paymentActivityDetail.setPartisipation(Integer.parseInt(objArr[5].toString()));
			activity.setCreatedBy(objArr[6].toString());
			user.setId(objArr[6].toString());
			
			user.setCompany(objArr[7].toString());
			position.setPositionCode(objArr[8].toString());
			position.setPositionName(objArr[9].toString());
			
			industry.setIndustryCode(objArr[10].toString());
			industry.setIndustryName(objArr[11].toString());
			
			activityType.setActivityTypeCode(objArr[12].toString());
			
			activity.setActivityCode(objArr[13].toString());
			activity.setLocation(objArr[14].toString());
			activity.setFinishSchedule(Timestamp.valueOf(objArr[15].toString()).toLocalDateTime());
			activity.setPrice(BigDecimal.valueOf(Double.valueOf(objArr[16].toString())));
		
			user.setPosition(position);
			user.setIndustry(industry);
			paymentActivityDetail.setUser(user);
			activity.setActivityType(activityType);
			paymentActivityDetail.setActivity(activity);
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
	
	public List<PaymentActivityDetail> getReportPartisipationMember(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit,String userCreated,boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ": "DESC ";
		
		sql.append("SELECT title,tu.full_name, activity_type_name, provider, begin_schedule, partisipant, tu.id, ")
		.append("tu.company, ")
		.append("tp.position_code, tp.position_name, ")
		.append("ti.industry_code, ti.industry_name, ")
		.append("activity_type_code, ")
		.append("activity_code, locations, finish_schedule, price ")
		.append("from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, count(activity_id) as partisipant ")
		.append("FROM (")
		.append("select ta.id as activity_id ")
		.append("FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_partisipant ON ta_prime.id = tb_partisipant.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id ")
		.append("INNER JOIN tb_user tu ON ta_prime.created_by = tu.id ")
		.append("INNER JOIN tb_position tp ON tp.id = tu.position_id ")
		.append("INNER JOIN tb_industry ti ON ti.id = tu.industry_id ")
		.append("WHERE ta_prime.created_by = :userCreate ")
		.append("ORDER BY ta_prime.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<?> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.setParameter("startPosition", startPosition)
		.setParameter("limit", limit)
		.setParameter("userCreate", userCreated)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			ActivityType activityType = new ActivityType();
			User user = new User();
			Position position = new Position();
			Industry industry = new Industry();
			
			
			activity.setTitle(objArr[0].toString());
			paymentActivityDetail.setMemberCreate(objArr[1].toString());
			user.setFullName(objArr[1].toString());
			
			activityType.setActivityTypeName(objArr[2].toString());
			
			activity.setProvider(objArr[3].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[4].toString()).toLocalDateTime());
			
			paymentActivityDetail.setPartisipation(Integer.parseInt(objArr[5].toString()));
			activity.setCreatedBy(objArr[6].toString());
			user.setId(objArr[6].toString());
			
			user.setCompany(objArr[7].toString());
			position.setPositionCode(objArr[8].toString());
			position.setPositionName(objArr[9].toString());
			
			industry.setIndustryCode(objArr[10].toString());
			industry.setIndustryName(objArr[11].toString());
			
			activityType.setActivityTypeCode(objArr[12].toString());
			
			activity.setActivityCode(objArr[13].toString());
			activity.setLocation(objArr[14].toString());
			activity.setFinishSchedule(Timestamp.valueOf(objArr[15].toString()).toLocalDateTime());
			activity.setPrice(BigDecimal.valueOf(Double.valueOf(objArr[16].toString())));
		
			user.setPosition(position);
			user.setIndustry(industry);
			paymentActivityDetail.setUser(user);
			activity.setActivityType(activityType);
			paymentActivityDetail.setActivity(activity);
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
	
	public PaymentActivityDetail getTotalByReportPartisipationMember(LocalDateTime beginDate,LocalDateTime finishDate,String userId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) ")
		.append("from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, count(activity_id) as partisipant ")
		.append("FROM (")
		.append("select ta.id as activity_id ")
		.append("FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_partisipant ON ta_prime.id = tb_partisipant.activity_id ")
		.append("WHERE ta_prime.created_by = :userCreate ");
		
		Object objPaymentActivity = null;
		PaymentActivityDetail paymentActivity = null;
		try {
			objPaymentActivity = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("beginDate", beginDate)
					.setParameter("finishDate", finishDate)
					.setParameter("userCreate", userId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentActivity != null) {
			Object obj = (Object) objPaymentActivity;
			
			paymentActivity = new PaymentActivityDetail();
			
			paymentActivity.setCountOfPaymentActivity(Integer.parseInt(obj.toString()));
		}
		
		return paymentActivity;
	}
	
	
	public List<PaymentActivityDetail> getReportPartisipationSuper(LocalDateTime beginDate,LocalDateTime finishDate,boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending)? "ASC ":"DESC ";
		
		sql.append("SELECT title,tu.full_name, activity_type_name, provider, begin_schedule, partisipant, tu.id, ")
		.append("tu.company, ")
		.append("tp.position_code, tp.position_name, ")
		.append("ti.industry_code, ti.industry_name, ")
		.append("activity_type_code, ")
		.append("activity_code, locations, finish_schedule, price ")
		.append("from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, count(activity_id) as partisipant ")
		.append("FROM (")
		.append("select ta.id as activity_id ")
		.append("FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_partisipant ON ta_prime.id = tb_partisipant.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id ")
		.append("INNER JOIN tb_user tu ON ta_prime.created_by = tu.id ")
		.append("INNER JOIN tb_position tp ON tp.id = tu.position_id ")
		.append("INNER JOIN tb_industry ti ON ti.id = tu.industry_id ")
		.append("ORDER BY ta_prime.created_at ")
		.append(ascending);
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<?> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			ActivityType activityType = new ActivityType();
			User user = new User();
			Position position = new Position();
			Industry industry = new Industry();
						
			activity.setTitle(objArr[0].toString());
			user.setFullName(objArr[1].toString());
			activityType.setActivityTypeName(objArr[2].toString());
			
			activity.setProvider(objArr[3].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[4].toString()).toLocalDateTime());
			
			paymentActivityDetail.setCountOfPaymentActivity(Integer.parseInt(objArr[5].toString()));
			activity.setCreatedBy(objArr[6].toString());
			user.setId(objArr[6].toString());
			
			user.setCompany(objArr[7].toString());
			position.setPositionCode(objArr[8].toString());
			position.setPositionName(objArr[9].toString());
			
			industry.setIndustryCode(objArr[10].toString());
			industry.setIndustryName(objArr[11].toString());
			
			activityType.setActivityTypeCode(objArr[12].toString());
			
			activity.setActivityCode(objArr[13].toString());
			activity.setLocation(objArr[14].toString());
			activity.setFinishSchedule(Timestamp.valueOf(objArr[15].toString()).toLocalDateTime());
			activity.setPrice(BigDecimal.valueOf(Double.valueOf(objArr[16].toString())));
			user.setId(objArr[17].toString());
		
			user.setPosition(position);
			user.setIndustry(industry);
			paymentActivityDetail.setUser(user);
			activity.setActivityType(activityType);
			paymentActivityDetail.setActivity(activity);
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
	
	public List<PaymentActivityDetail> getReportPartisipationSuper(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ": "DESC ";
		
		sql.append("SELECT title,tu.full_name as member_create, activity_type_name, provider, begin_schedule, partisipant, tu.id as created_by, ")
		.append("tu.company, ")
		.append("tp.position_code, tp.position_name, ")
		.append("ti.industry_code, ti.industry_name, ")
		.append("activity_type_code, ")
		.append("activity_code, locations, finish_schedule, price ")
		.append("from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, count(activity_id) as partisipant ")
		.append("FROM (")
		.append("select ta.id as activity_id ")
		.append("FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_partisipant ON ta_prime.id = tb_partisipant.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id ")
		.append("INNER JOIN tb_user tu ON ta_prime.created_by = tu.id ")
		.append("INNER JOIN tb_position tp ON tp.id = tu.position_id ")
		.append("INNER JOIN tb_industry ti ON ti.id = tu.industry_id ")
		.append("ORDER BY ta_prime.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<?> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.setParameter("startPosition", startPosition)
		.setParameter("limit", limit)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			ActivityType activityType = new ActivityType();
			User user = new User();
			Position position = new Position();
			Industry industry = new Industry();
			
			activity.setTitle(objArr[0].toString());
			user.setFullName(objArr[1].toString());
			activityType.setActivityTypeName(objArr[2].toString());
			
			activity.setProvider(objArr[3].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[4].toString()).toLocalDateTime());
			
			paymentActivityDetail.setCountOfPaymentActivity(Integer.parseInt(objArr[5].toString()));
			activity.setCreatedBy(objArr[6].toString());
			user.setId(objArr[6].toString());
			
			user.setCompany(objArr[7].toString());
			position.setPositionCode(objArr[8].toString());
			position.setPositionName(objArr[9].toString());
			
			industry.setIndustryCode(objArr[10].toString());
			industry.setIndustryName(objArr[11].toString());
			
			activityType.setActivityTypeCode(objArr[12].toString());
			
			activity.setActivityCode(objArr[13].toString());
			activity.setLocation(objArr[14].toString());
			activity.setFinishSchedule(Timestamp.valueOf(objArr[15].toString()).toLocalDateTime());
			activity.setPrice(BigDecimal.valueOf(Double.valueOf(objArr[16].toString())));
		
			user.setPosition(position);
			user.setIndustry(industry);
			paymentActivityDetail.setUser(user);
			activity.setActivityType(activityType);
			paymentActivityDetail.setActivity(activity);
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
	
	public PaymentActivityDetail getTotalByReportPartisipationSuper(LocalDateTime beginDate,LocalDateTime finishDate){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) ")
		.append("from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, count(activity_id) as partisipant ")
		.append("FROM (")
		.append("select ta.id as activity_id ")
		.append("FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_partisipant ON ta_prime.id = tb_partisipant.activity_id ");
		
		Object objPaymentActivity = null;
		PaymentActivityDetail paymentActivity = null;
		try {
			objPaymentActivity = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("beginDate", beginDate)
					.setParameter("finishDate", finishDate)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentActivity != null) {
			Object obj = (Object) objPaymentActivity;
			
			paymentActivity = new PaymentActivityDetail();
			
			paymentActivity.setCountOfPaymentActivity(Integer.parseInt(obj.toString()));
		}
		
		return paymentActivity;
	}
	
	
	public List<PaymentActivityDetail> getReportIncomeMember(LocalDateTime beginDate,LocalDateTime finishDate,String userId,boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ": "DESC ";
		
		sql.append("select title, activity_type_name, begin_schedule, total_income, tu.id, ")
		.append("tu.company, ")
		.append("tp.position_code, tp.position_name, ")
		.append("ti.industry_code, ti.industry_name, ")
		.append("activity_type_code, ")
		.append("activity_code, locations, finish_schedule, price, provider, tu.fullName ")
		.append("from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, sum(net) as total_income ")
		.append("FROM (")
		.append("SELECT ta.id as activity_id, net ")
		.append("FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_income ON ta_prime.id = tb_income.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id ")
		.append("INNER JOIN tb_user tu ON ta_prime.created_by = tu.id ")
		.append("INNER JOIN tb_position tp ON tp.id = tu.position_id ")
		.append("INNER JOIN tb_industry ti ON ti.id = tu.industry_id ")
		.append("WHERE ta_prime.created_by = :userId ")
		.append("ORDER BY ta_prime.created_at ")
		.append(ascending);
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<?> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.setParameter("userId", userId)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			ActivityType activityType = new ActivityType();
			
			User user = new User();
			Position position = new Position();
			Industry industry = new Industry();
			
			activity.setTitle(objArr[0].toString());
			activityType.setActivityTypeName(objArr[1].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[2].toString()).toLocalDateTime());

			BigDecimal bigDecimal = new BigDecimal(objArr[3].toString());
			paymentActivityDetail.setNet(bigDecimal);
			activity.setCreatedBy(objArr[4].toString());
			user.setId(objArr[4].toString());
			
			user.setCompany(objArr[5].toString());
			position.setPositionCode(objArr[6].toString());
			position.setPositionName(objArr[7].toString());
			
			industry.setIndustryCode(objArr[8].toString());
			industry.setIndustryName(objArr[9].toString());
			
			activityType.setActivityTypeCode(objArr[10].toString());
			
			activity.setActivityCode(objArr[11].toString());
			activity.setLocation(objArr[12].toString());
			activity.setFinishSchedule(Timestamp.valueOf(objArr[13].toString()).toLocalDateTime());
			activity.setPrice(BigDecimal.valueOf(Double.valueOf(objArr[14].toString())));
			activity.setProvider(objArr[15].toString());
			
			user.setFullName(objArr[16].toString());

			user.setPosition(position);
			user.setIndustry(industry);
			
			paymentActivityDetail.setUser(user);
			activity.setActivityType(activityType);
			paymentActivityDetail.setActivity(activity);
			
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
	
	public List<PaymentActivityDetail> getReportIncomeMember(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit,String UserId,boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ": "DESC ";
		
		sql.append("select title, activity_type_name, begin_schedule, total_income, tu.id, ")
		.append("tu.company, ")
		.append("tp.position_code, tp.position_name, ")
		.append("ti.industry_code, ti.industry_name, ")
		.append("activity_type_code, ")
		.append("activity_code, locations, finish_schedule, price, provider ")
		.append("from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, sum(net) as total_income ")
		.append("FROM (")
		.append("SELECT ta.id as activity_id, net ")
		.append("FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_income ON ta_prime.id = tb_income.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id ")
		.append("INNER JOIN tb_user tu ON ta_prime.created_by = tu.id ")
		.append("INNER JOIN tb_position tp ON tp.id = tu.position_id ")
		.append("INNER JOIN tb_industry ti ON ti.id = tu.industry_id ")
		.append("WHERE ta_prime.created_by = :userId ")
		.append("ORDER BY ta_prime.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<?> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.setParameter("startPosition", startPosition)
		.setParameter("limit", limit)
		.setParameter("userId", UserId)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			ActivityType activityType = new ActivityType();
			
			User user = new User();
			Position position = new Position();
			Industry industry = new Industry();
			
			activity.setTitle(objArr[0].toString());
			activityType.setActivityTypeName(objArr[1].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[2].toString()).toLocalDateTime());

			BigDecimal bigDecimal = new BigDecimal(objArr[3].toString());
			paymentActivityDetail.setNet(bigDecimal);
			activity.setCreatedBy(objArr[4].toString());
			user.setId(objArr[4].toString());
			
			user.setCompany(objArr[5].toString());
			position.setPositionCode(objArr[6].toString());
			position.setPositionName(objArr[7].toString());
			
			industry.setIndustryCode(objArr[8].toString());
			industry.setIndustryName(objArr[9].toString());
			
			activityType.setActivityTypeCode(objArr[10].toString());
			
			activity.setActivityCode(objArr[11].toString());
			activity.setLocation(objArr[12].toString());
			activity.setFinishSchedule(Timestamp.valueOf(objArr[13].toString()).toLocalDateTime());
			activity.setPrice(BigDecimal.valueOf(Double.valueOf(objArr[14].toString())));
			activity.setProvider(objArr[15].toString());
		
			user.setPosition(position);
			user.setIndustry(industry);
			paymentActivityDetail.setUser(user);
			
			activity.setActivityType(activityType);
			paymentActivityDetail.setActivity(activity);
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
	
	public PaymentActivityDetail getTotalByReportIncomeMember(LocalDateTime beginDate,LocalDateTime finishDate,String userId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(*) ")
		.append("from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, sum(net) as total_income ")
		.append("FROM (")
		.append("SELECT ta.id as activity_id, net ")
		.append("FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_income ON ta_prime.id = tb_income.activity_id ")
		.append("WHERE ta_prime.created_by = :userId ");
		
		Object objPaymentActivity = null;
		PaymentActivityDetail paymentActivity = null;
		try {
			objPaymentActivity = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("beginDate", beginDate)
					.setParameter("finishDate", finishDate)
					.setParameter("userCreate", userId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentActivity != null) {
			Object obj = (Object) objPaymentActivity;
			
			paymentActivity = new PaymentActivityDetail();
			
			paymentActivity.setCountOfPaymentActivity(Integer.parseInt(obj.toString()));
		}
		
		return paymentActivity;
	}
	
	
	public List<PaymentActivityDetail> getReportIncomeSuper(LocalDateTime beginDate,LocalDateTime finishDate,boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ": "DESC ";
		
		sql.append("SELECT full_name,tat.activity_type_name, total_income, tb_income_group.created_by, ")
		.append("company, ")
		.append("tp.position_code, tp.position_name, ")
		.append("ti.industry_code, ti.industry_name, tb_activity_user_type.id,tat.activity_type_code ")
		.append("FROM tb_user AS tb_activity_user_type ")
		.append("INNER JOIN (")
		.append("SELECT created_by,activity_type_id, sum(total_income) as total_income from tb_activity as tb_activity_grouping_user_type ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, sum(net) as total_income ")
		.append("FROM ( ")
		.append("select ta.id as activity_id, net ")
		.append("from tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true ")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_income ON tb_activity_grouping_user_type.id = tb_income.activity_id ")
		.append("GROUP BY created_by, activity_type_id ")
		.append(") tb_income_group ON tb_activity_user_type.id = tb_income_group.created_by ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = tb_income_group.activity_type_id ")
		.append("INNER JOIN tb_position tp ON tp.id = tb_activity_user_type.position_id ")
		.append("INNER JOIN tb_industry ti ON ti.id = tb_activity_user_type.industry_id ")
		.append("ORDER BY tb_activity_user_type.created_at ")
		.append(ascending);
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<?> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			ActivityType activityType = new ActivityType();
			
			User user = new User();
			Position position = new Position();
			Industry industry = new Industry();
			
			user.setFullName(objArr[0].toString());
			activityType.setActivityTypeName(objArr[1].toString());

			BigDecimal bigDecimal = new BigDecimal(objArr[2].toString());
			paymentActivityDetail.setNet(bigDecimal);
			activity.setCreatedBy(objArr[3].toString());
			
			user.setCompany(objArr[4].toString());
			position.setPositionCode(objArr[5].toString());
			position.setPositionName(objArr[6].toString());
			
			industry.setIndustryCode(objArr[7].toString());
			industry.setIndustryName(objArr[8].toString());
			
			user.setId(objArr[9].toString());
			activityType.setActivityTypeCode(objArr[10].toString());
			
			user.setPosition(position);
			user.setIndustry(industry);
			paymentActivityDetail.setUser(user);
			
			activity.setActivityType(activityType);
			paymentActivityDetail.setActivity(activity);
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
	
	public List<PaymentActivityDetail> getReportIncomeSuper(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ": "DESC ";
		
		sql.append("SELECT full_name,tat.activity_type_name, total_income, tb_income_group.created_by, ")
		.append("company, ")
		.append("tp.position_code, tp.position_name, ")
		.append("ti.industry_code, ti.industry_name, tb_activity_user_type.id ")
		.append("FROM tb_user AS tb_activity_user_type ")
		.append("INNER JOIN (")
		.append("SELECT created_by,activity_type_id, sum(total_income) as total_income from tb_activity as tb_activity_grouping_user_type ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, sum(net) as total_income ")
		.append("FROM ( ")
		.append("select ta.id as activity_id, net ")
		.append("from tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true ")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_income ON tb_activity_grouping_user_type.id = tb_income.activity_id ")
		.append("GROUP BY created_by, activity_type_id ")
		.append(") tb_income_group ON tb_activity_user_type.id = tb_income_group.created_by ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = tb_income_group.activity_type_id ")
		.append("INNER JOIN tb_position tp ON tp.id = tb_activity_user_type.position_id ")
		.append("INNER JOIN tb_industry ti ON ti.id = tb_activity_user_type.industry_id ")
		.append("ORDER BY tb_activity_user_type.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<?> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.setParameter("startPosition", startPosition)
		.setParameter("limit", limit)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			ActivityType activityType = new ActivityType();
			
			User user = new User();
			Position position = new Position();
			Industry industry = new Industry();
			
			user.setFullName(objArr[0].toString());
			activityType.setActivityTypeName(objArr[1].toString());

			BigDecimal bigDecimal = new BigDecimal(objArr[2].toString());
			paymentActivityDetail.setNet(bigDecimal);
			activity.setCreatedBy(objArr[3].toString());
			
			user.setCompany(objArr[4].toString());
			position.setPositionCode(objArr[5].toString());
			position.setPositionName(objArr[6].toString());
			
			industry.setIndustryCode(objArr[7].toString());
			industry.setIndustryName(objArr[8].toString());
			
			user.setId(objArr[9].toString());
			
			user.setPosition(position);
			user.setIndustry(industry);
			paymentActivityDetail.setUser(user);
			
			activity.setActivityType(activityType);
			paymentActivityDetail.setActivity(activity);
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}

	public PaymentActivityDetail getTotalByReportIncomeSuper(LocalDateTime beginDate,LocalDateTime finishDate){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) ")
		.append("FROM tb_user AS tb_activity_user_type ")
		.append("INNER JOIN (")
		.append("SELECT created_by,activity_type_id, sum(total_income) as total_income from tb_activity as tb_activity_grouping_user_type ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, sum(net) as total_income ")
		.append("FROM ( ")
		.append("select ta.id as activity_id, net ")
		.append("from tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true ")
		.append(") tb_pay ")
		.append("GROUP BY activity_id ")
		.append(") tb_income ON tb_activity_grouping_user_type.id = tb_income.activity_id ")
		.append("GROUP BY created_by, activity_type_id ")
		.append(") tb_income_group ON tb_activity_user_type.id = tb_income_group.created_by ");
		
		Object objPaymentActivity = null;
		PaymentActivityDetail paymentActivity = null;
		try {
			objPaymentActivity = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("beginDate", beginDate)
					.setParameter("finishDate", finishDate)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentActivity != null) {
			Object obj = (Object) objPaymentActivity;
			
			paymentActivity = new PaymentActivityDetail();
			
			paymentActivity.setCountOfPaymentActivity(Integer.parseInt(obj.toString()));
		}
		
		return paymentActivity;
	}
}
