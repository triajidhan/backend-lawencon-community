package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.PostTypeDao;
import com.lawencon.lawenconcommunity.model.PostType;

@Service
public class PostTypeService extends BaseCoreService{

	@Autowired
	private PostTypeDao postTypeDao;
	
	public List<PostType> getAll(){
		return postTypeDao.getAll(PostType.class);
	}
	
	public PostType getById(String id) {
		return postTypeDao.getByIdAndDetach(PostType.class, id);
	}
	
	public PostType getByPostTypeCode(String postTypeCode) {
		return postTypeDao.getByPostTypeCode(postTypeCode);
	}
	
	
}
