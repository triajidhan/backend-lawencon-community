package com.lawencon.lawenconcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.PaymentSubscribe;

@Repository
public class PaymentSubscribeDao extends AbstractJpaDao{

	@SuppressWarnings("unchecked")
	public List<PaymentSubscribe> getByIsActive(int startPosition,int limit,boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_subscribe ")
		.append("WHERE is_active = true ")
		.append("ORDER BY created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentSubscribe> paymentSubscribes = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentSubscribe.class)
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
				.createNativeQuery(sql.toString(),PaymentSubscribe.class)
				.getResultList();
		
		return paymentSubscribes;
	}
	
	public PaymentSubscribe getTotalPaymentSubscribe(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) FROM tb_payment_subscribe ")
		.append("WHERE is_active = true ");
		
		Object objPaymentSubscribe = null;
		PaymentSubscribe paymentSubcribe = null;
		
		try {
			objPaymentSubscribe = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentSubscribe != null) {
			Object obj = (Object) objPaymentSubscribe;
			
			paymentSubcribe = new PaymentSubscribe();
			
			paymentSubcribe.setCountOfPaymentSubscribe(Integer.parseInt(obj.toString()));
		}
		
		return paymentSubcribe;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentSubscribe> getByIsActiveFalse(int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_subscribe tps ")
		.append("WHERE tps.is_active = false ")
		.append("ORDER BY tps.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<PaymentSubscribe> paymentSubscribes = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentSubscribe.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentSubscribes;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentSubscribe> getByIsActiveTrueAndApprovedFalse(int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_subscribe tps ")
		.append("WHERE tps.is_active = true AND tps.approve = false ")
		.append("ORDER BY tps.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		
		List<PaymentSubscribe> paymentSubscribes = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentSubscribe.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentSubscribes;
	}
	
	
	public PaymentSubscribe getTotalByIsActiveTrueAndApprovedFalse(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) FROM tb_payment_subscribe tps ")
		.append("WHERE tps.is_active = true AND tps.approve = false ");
		
		Object objPaymentSubscribe = null;
		PaymentSubscribe paymentSubcribe = null;
		
		try {
			objPaymentSubscribe = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentSubscribe != null) {
			Object obj = (Object) objPaymentSubscribe;
			
			paymentSubcribe = new PaymentSubscribe();
			
			paymentSubcribe.setCountOfPaymentSubscribe(Integer.parseInt(obj.toString()));
		}
		
		return paymentSubcribe;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PaymentSubscribe> getByPaymentReject(int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_subscribe tps ")
		.append("WHERE tps.is_active = false AND tps.approve = false ")
		.append("ORDER BY tps.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		
		List<PaymentSubscribe> paymentSubscribes = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentSubscribe.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentSubscribes;
	}
	
	
	public PaymentSubscribe getTotalByPaymentReject(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) FROM tb_payment_subscribe tps ")
		.append("WHERE tps.is_active = false AND tps.approve = false ");
		
		Object objPaymentSubscribe = null;
		PaymentSubscribe paymentSubcribe = null;
		
		try {
			objPaymentSubscribe = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentSubscribe != null) {
			Object obj = (Object) objPaymentSubscribe;
			
			paymentSubcribe = new PaymentSubscribe();
			
			paymentSubcribe.setCountOfPaymentSubscribe(Integer.parseInt(obj.toString()));
		}
		
		return paymentSubcribe;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PaymentSubscribe> getByPaymentApproved(int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_subscribe tps ")
		.append("WHERE tps.is_active = true AND tps.approve = true ")
		.append("ORDER BY tps.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		
		List<PaymentSubscribe> paymentSubscribes = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentSubscribe.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentSubscribes;
	}
	
	
	public PaymentSubscribe getTotalByPaymentApproved(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) FROM tb_payment_subscribe tps ")
		.append("WHERE tps.is_active = true AND tps.approve = true ");
		
		Object objPaymentSubscribe = null;
		PaymentSubscribe paymentSubcribe = null;
		
		try {
			objPaymentSubscribe = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentSubscribe != null) {
			Object obj = (Object) objPaymentSubscribe;
			
			paymentSubcribe = new PaymentSubscribe();
			
			paymentSubcribe.setCountOfPaymentSubscribe(Integer.parseInt(obj.toString()));
		}
		
		return paymentSubcribe;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PaymentSubscribe> getByIsActiveTrueAndApprovedTrue(int startPosition,int limit, boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * FROM tb_payment_subscribe tps ")
		.append("WHERE tps.is_active = true AND tps.approve = true ")
		.append("ORDER BY tps.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		
		List<PaymentSubscribe> paymentSubscribes = ConnHandler.getManager()
				.createNativeQuery(sql.toString(),PaymentSubscribe.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return paymentSubscribes;
	}
	
	public PaymentSubscribe getTotalByIsActiveTrueAndApprovedTrue(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) FROM tb_payment_subscribe tps ")
		.append("WHERE tps.is_active = true AND tps.approve = true ");
		
		Object objPaymentSubscribe = null;
		PaymentSubscribe paymentSubcribe = null;
		
		try {
			objPaymentSubscribe = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPaymentSubscribe != null) {
			Object obj = (Object) objPaymentSubscribe;
			
			paymentSubcribe = new PaymentSubscribe();
			
			paymentSubcribe.setCountOfPaymentSubscribe(Integer.parseInt(obj.toString()));
		}
		
		return paymentSubcribe;
	}
	
}
