package com.lawencon.lawenconcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
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
}
