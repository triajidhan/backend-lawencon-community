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
import com.lawencon.security.principal.PrincipalService;

@Service
public class LikeService extends BaseCoreService{

	@Autowired
	private LikeDao likeDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PrincipalService principalService;
	
	public List<Like> getAll(Integer startPosition, Integer limitPage){
		List<Like> likes = likeDao.getAll(Like.class, startPosition, limitPage);
		
		return likes;
	}
	
	public List<Like> getAll(){
		List<Like> likes = likeDao.getAll(Like.class);
		
		return likes;
	}
	
	public Like getById(String id) {
		return likeDao.getByIdAndDetach(Like.class, id);
	}
	
	public List<Like> getByUser(String userId){
		List<Like> likes = likeDao.getByUser(userId);
		
		return likes;
	}
	
	public List<Like> getByUser(String userId,int startPosition,int limit){
		List<Like> likes = likeDao.getByUser(userId,startPosition,limit);
		
		return likes;
	}
	
	public List<Like> getByUser(String userId,int startPosition,int limit,boolean ascending){
		List<Like> likes = likeDao.getByUser(userId,startPosition,limit,ascending);
		return likes;
	}
	
	public List<Like> getByPost(String postId){
		List<Like> likes = likeDao.getByPost(postId);
		
		return likes;
	}
	
	public Like getTotalByUser(String userId){
		Like likes = likeDao.getTotalByUser(userId);
		
		return likes;
	}
	
	public Like getTotalByPost(String postId){
		Like likes = likeDao.getTotalByPost(postId);
		
		return likes;
	}
	
	public Like getUserLikePost(String userId,String postId) {
		Like data = likeDao.userLikePost(userId, postId).get();
;
		return data;
	}
	
	public List<Like> getByIsActive(Integer startPosition, Integer limitPage){
		List<Like> likes = likeDao.getByIsActive(startPosition, limitPage);
		
		return likes;
	}
	
	public List<Like> getByIsActive(){
		List<Like> likes = likeDao.getByIsActive();
		
		return likes;
	}
	
	public ResponseMessageDto insert(Like data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Like Failed!");
		begin();
		try {
			Like likeInsert= new Like();
			User user = new User();
			user.setId(principalService.getAuthPrincipal());
			data.setUser(user);
			likeInsert = likeDao.save(data);
			responseMessageDto.setMessage("Like Success!");		
			responseMessageDto.setId(likeInsert.getId());
		} catch (Exception e) {
			responseMessageDto.setMessage("Like Failed!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}
	
	public ResponseMessageDto update(Like data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed!");
		valId(data);
		Like like = likeDao.getByIdAndDetach(Like.class, data.getId());
		Like likeUpdate = like;
		begin();
		try {
			likeUpdate.setIsActive(data.getIsActive());
			likeDao.save(likeUpdate);
			responseMessageDto.setMessage("Success!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Failed!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}
	
	public void valInsert(Like data) {
		valFk(data);
	}
	
	public void valId(Like data) {
		if(likeDao.getById(Like.class, data.getId()) == null) {
			throw new RuntimeException("Id not found!");
		}
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
