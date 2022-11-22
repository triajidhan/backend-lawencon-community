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
		
		sql.append("SELECT * FROM tb_payment_activity_detail ")
		.append("WHERE is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentActivityDetail> paymentActivityDetail = ConnHandler.getManager()
				.createNativeQuery(sql.toString())
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentActivityDetail;
	}
}
