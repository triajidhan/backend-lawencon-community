package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.BalanceDao;
import com.lawencon.lawenconcommunity.model.Balance;

@Service
public class BalanceService extends BaseCoreService{

	@Autowired
	private BalanceDao balanceDao;
	
	public List<Balance> getAll(){
		return balanceDao.getAll(Balance.class);
	}
	
	public Balance getById(String id) {
		return balanceDao.getById(Balance.class,id); 
	}
}
