package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.BookmarkDao;
import com.lawencon.lawenconcommunity.dao.CommentDao;
import com.lawencon.lawenconcommunity.model.Bookmark;
import com.lawencon.lawenconcommunity.model.Comment;

public class CommentService extends BaseCoreService{
	
	@Autowired
	private CommentDao commentDao;
	
	public List<Comment> getAll(Integer startPosition, Integer limitPage){
		List<Comment> bookmarks = commentDao.getAll(Comment.class, startPosition, limitPage);
		
		return bookmarks;
	}
	
	public List<Comment> getByUser(String userId){
		List<Comment> bookmarks = commentDao.getByUser(userId);
		
		return bookmarks;
	}
	
	public List<Comment> getByPost(String postId){
		List<Comment> bookmarks = commentDao.getByPost(postId);
		
		return bookmarks;
	}
}
