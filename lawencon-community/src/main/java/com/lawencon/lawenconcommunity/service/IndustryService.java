package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.IndustryDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Industry;

@Service
public class IndustryService extends BaseCoreService{

	@Autowired
	private IndustryDao industryDao;
	
	@Autowired
	private GenerateService generateService;
	
	public Industry getTotalIndustry() {
		return industryDao.getTotalIndustry();
	}
	
	public List<Industry> getAll(){
		return industryDao.getAll(Industry.class);
	}
	
	public Industry getById(String id){
		return industryDao.getByIdAndDetach(Industry.class,id);
	}
	
	public List<Industry> getByIsActive(int startPosition,int limit){
		return industryDao.getByIsActive(startPosition, limit);
	}
	
	public List<Industry> getByIsActive(){
		return industryDao.getByIsActive();
	}
	
	public void valIdNull(Industry data) {
		if(data.getId()!=null) {
			throw new RuntimeException("Id must be null!");
		}
	}
	
	public ResponseMessageDto insert(Industry data) {
		valIdNull(data);
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed Add industry!");
		begin();
		try {
			data.setIndustryCode("I-"+generateService.generate(3));
			industryDao.save(data);
			responseMessageDto.setMessage("Success Add industry!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Failed Add industry!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}
	
	public ResponseMessageDto update(Industry data) {
		valIdFound(data);
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed!");
		Industry industry = industryDao.getById(Industry.class, data.getId());
		Industry industryUpdate = industry;
		begin();
		try {
			if (data.getIndustryName() != null) {
				industryUpdate.setIndustryName(data.getIndustryName());
			}

			if (data.getIsActive() != null) {
				industryUpdate.setIsActive(data.getIsActive());
			}

			industryDao.save(industryUpdate);
			responseMessageDto.setMessage("Success!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Failed!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}

	public void valIdFound(Industry data) {
		if (industryDao.getByIdAndDetach(Industry.class, data.getId()) == null) {
			throw new RuntimeException("Industry Not Found!");
		}
	}
}
