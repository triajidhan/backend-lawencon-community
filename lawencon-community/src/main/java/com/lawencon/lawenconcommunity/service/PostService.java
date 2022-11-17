package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.PostDao;
import com.lawencon.lawenconcommunity.model.Post;

@Service
public class PostService extends BaseCoreService{
	
	@Autowired
	private PostDao postDao;
	
	public List<Post> getAll(Integer startPosition, Integer limitPage){
		final List<Post> posts = postDao.getAll(Post.class, startPosition, limitPage);
		
		return posts;
	}
	
	public List<Post> getByUser(String userId){
		final List<Post> posts = postDao.getByUser(userId);
		
		return posts;
	}
	
	public Post getByPostCode(String postCode) {
		return postDao.getByPostCode(postCode).get();
	}
	
	
	
}
