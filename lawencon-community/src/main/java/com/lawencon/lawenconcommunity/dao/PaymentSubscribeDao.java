package com.lawencon.lawenconcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.PaymentSubscribe;

@Repository
public class PaymentSubscribeDao extends AbstractJpaDao{

	@SuppressWarnings("unchecked")
	public List<PaymentSubscribe> getByIsActive(int startPosition,int limit){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_payment_subscribe ")
		.append("WHERE is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentSubscribe> paymentSubscribes = ConnHandler.getManager()
				.createNativeQuery(sql.toString())
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentSubscribes;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentSubscribe> getByIsActive(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_payment_subscribe ")
		.append("WHERE is_active = true ");
		
		List<PaymentSubscribe> paymentSubscribes = ConnHandler.getManager()
				.createNativeQuery(sql.toString())
				.getResultList();
		
		return paymentSubscribes;
	}
}
