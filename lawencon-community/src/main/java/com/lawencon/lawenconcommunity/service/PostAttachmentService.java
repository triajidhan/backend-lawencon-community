package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.PostAttachmentDao;
import com.lawencon.lawenconcommunity.model.PostAttachment;

@Service
public class PostAttachmentService extends BaseCoreService{

	@Autowired
	private PostAttachmentDao postAttachmentDao;
	
	public List<PostAttachment> getByAll() {
		return postAttachmentDao.getAll(PostAttachment.class); 
	}
	
	public List<PostAttachment> getByPost(String postId){
		return postAttachmentDao.getByPost(postId); 
	}
	
	public PostAttachment getById(String id) {
		return postAttachmentDao.getByIdAndDetach(PostAttachment.class,id); 
	}
}
