package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.BookmarkDao;
import com.lawencon.lawenconcommunity.dao.LikeDao;
import com.lawencon.lawenconcommunity.model.Bookmark;
import com.lawencon.lawenconcommunity.model.Like;

public class LikeService extends BaseCoreService{

	@Autowired
	private LikeDao likeDao;
	
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
	
	
}
