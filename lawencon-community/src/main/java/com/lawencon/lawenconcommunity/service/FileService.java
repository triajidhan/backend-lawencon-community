package com.lawencon.lawenconcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.model.File;

@Service
public class FileService extends BaseCoreService{

	@Autowired
	private FileDao fileDao;
	
	public File getById(String id) {
		return fileDao.getByIdAndDetach(File.class, id);
	}
}
