package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.LikeDao;
import com.lawencon.lawenconcommunity.dao.PostDao;
import com.lawencon.lawenconcommunity.dao.UserDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Like;
import com.lawencon.lawenconcommunity.model.Post;
import com.lawencon.lawenconcommunity.model.User;

@Service
public class LikeService extends BaseCoreService{

	@Autowired
	private LikeDao likeDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	public List<Like> getAll(Integer startPosition, Integer limitPage){
		List<Like> likes = likeDao.getAll(Like.class, startPosition, limitPage);
		
		return likes;
	}
	
	public List<Like> getByUser(String userId){
		List<Like> likes = likeDao.getByUser(userId);
		
		return likes;
	}
	
	public List<Like> getByPost(String postId){
		List<Like> likes = likeDao.getByPost(postId);
		
		return likes;
	}
	
	public int getTotalByUser(String userId){
		int likes = likeDao.getTotalByUser(userId);
		
		return likes;
	}
	
	public int getTotalByPost(String postId){
		int likes = likeDao.getTotalByPost(postId);
		
		return likes;
	}
	
	public Like getUserLikePost(String userId,String postId) {
		return likeDao.userLikePost(userId, postId).get();
	}
	
	public ResponseMessageDto insert(Like data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Like Failed!");
		try {
			likeDao.save(data);
			responseMessageDto.setMessage("Like Success!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Like Failed!");
			e.printStackTrace();
		}
		return responseMessageDto;
	}
	
	public void valInsert(Like data) {
		valFk(data);
	}
	
	public void valFk(Like data) {
		if(userDao.getByIdAndDetach(User.class, data.getUser().getId()) == null) {
			throw new RuntimeException("Incompatible users!");
		}
		
		if(postDao.getByIdAndDetach(Post.class, data.getPost().getId()) == null) {
			throw new RuntimeException("Incompatible post!");
		}
	}
	
	public void valIdNull(Like data) {
		if (data.getId() != null) {
			throw new RuntimeException("Id must be null!");
		}
	}
	
	
}
