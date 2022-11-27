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
import com.lawencon.lawenconcommunity.model.PaymentActivityDetail;

@Repository
public class PaymentActivityDetailDao extends AbstractJpaDao{

	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByIsActive(int startPosition,int limit){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString())
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
		.append("WHERE is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString())
				.getResultList();
		
		return paymentActivityDetail;
	}
		
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getByActivity(String activityId,int startPosition,int limit){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE is_active = true ")
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
	public List<PaymentActivityDetail> getByActivity(String activityId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE is_active = true ")
		.append("AND activity_id = :activityId ");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString())
				.setParameter("activityId",activityId)
				.getResultList();
		
		return paymentActivityDetail;
	}
	
	public PaymentActivityDetail getTotalByActivity(String activityId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(ta.id) FROM tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON ta.id = tpad.activity_id ")
		.append("WHERE is_active = true ")
		.append("AND activity_id = :activityId ");
		
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
	public List<PaymentActivityDetail> getReportPartisipation(LocalDateTime beginDate,LocalDateTime finishDate){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select title,tu.full_name as member_create, activity_type_name, begin_schedule, partisipant from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, count(activity_id) as partisipant ")
		.append("FROM (")
		.append("select ta.id as activity_id ")
		.append("from tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY 1 ")
		.append(") tb_partisipant ON ta_prime.id = tb_partisipant.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id ")
		.append("INNER JOIN tb_user tu ON ta_prime.created_by = tu.id");
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<Object> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			
			activity.setTitle(objArr[0].toString());
			paymentActivityDetail.setMemberCreate(objArr[1].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[3].toString()).toLocalDateTime());
			
			paymentActivityDetail.setCountOfPaymentActivity(Integer.parseInt(objArr[4].toString()));
			
			paymentActivityDetail.setActivity(activity);
			
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getReportPartisipation(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select title,tu.full_name as member_create, activity_type_name, begin_schedule, partisipant from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, count(activity_id) as partisipant ")
		.append("FROM (")
		.append("select ta.id as activity_id ")
		.append("from tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY 1 ")
		.append(") tb_partisipant ON ta_prime.id = tb_partisipant.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id ")
		.append("INNER JOIN tb_user tu ON ta_prime.created_by = tu.id ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<Object> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.setParameter("startPosition", startPosition)
		.setParameter("limit", limit)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			
			activity.setTitle(objArr[0].toString());
			paymentActivityDetail.setMemberCreate(objArr[1].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[3].toString()).toLocalDateTime());
			
			paymentActivityDetail.setCountOfPaymentActivity(Integer.parseInt(objArr[4].toString()));
			
			paymentActivityDetail.setActivity(activity);
			
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getReportIncome(LocalDateTime beginDate,LocalDateTime finishDate){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select title,tu.full_name as member_create, activity_type_name, begin_schedule, total_income from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, sum(net) as total_income ")
		.append("FROM (")
		.append("select ta.id as activity_id, net ")
		.append("from tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY 1 ")
		.append(") tb_partisipant ON ta_prime.id = tb_partisipant.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id ")
		.append("INNER JOIN tb_user tu ON ta_prime.created_by = tu.id");
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<Object> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			
			activity.setTitle(objArr[0].toString());
			paymentActivityDetail.setMemberCreate(objArr[1].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[3].toString()).toLocalDateTime());

			BigDecimal bigDecimal = new BigDecimal(objArr[4].toString());
			paymentActivityDetail.setNet(bigDecimal);
			
			paymentActivityDetail.setActivity(activity);
			
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentActivityDetail> getReportIncome(LocalDateTime beginDate,LocalDateTime finishDate,int startPosition,int limit){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select title,tu.full_name as member_create, activity_type_name, begin_schedule, total_income from tb_activity as ta_prime ")
		.append("INNER JOIN (")
		.append("SELECT activity_id, sum(net) as total_income ")
		.append("FROM (")
		.append("select ta.id as activity_id, net ")
		.append("from tb_payment_activity_detail tpad ")
		.append("INNER JOIN tb_activity ta ON tpad.activity_id = ta.id ")
		.append("WHERE (begin_schedule between :beginDate AND :finishDate) ")
		.append("AND tpad.approve = true")
		.append(") tb_pay ")
		.append("GROUP BY 1 ")
		.append(") tb_partisipant ON ta_prime.id = tb_partisipant.activity_id ")
		.append("INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id ")
		.append("INNER JOIN tb_user tu ON ta_prime.created_by = tu.id ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetails = new ArrayList<>();
		
		List<Object> objPayments = ConnHandler.getManager().createNativeQuery(sql.toString())
		.setParameter("beginDate", beginDate)
		.setParameter("finishDate", finishDate)
		.setParameter("startPosition", startPosition)
		.setParameter("limit", limit)
		.getResultList();
		
		objPayments.forEach(objPayment ->{
			Object[] objArr = (Object[]) objPayment;
			
			PaymentActivityDetail paymentActivityDetail = new PaymentActivityDetail();
			Activity activity = new Activity();
			
			activity.setTitle(objArr[0].toString());
			paymentActivityDetail.setMemberCreate(objArr[1].toString());
			activity.setBeginSchedule(Timestamp.valueOf(objArr[3].toString()).toLocalDateTime());

			BigDecimal bigDecimal = new BigDecimal(objArr[4].toString());
			paymentActivityDetail.setNet(bigDecimal);
			
			paymentActivityDetail.setActivity(activity);
			
			paymentActivityDetails.add(paymentActivityDetail);
		});
	
		return paymentActivityDetails;
	}
}
