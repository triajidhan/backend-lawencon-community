package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.CommentDao;
import com.lawencon.lawenconcommunity.dao.PostDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Comment;
import com.lawencon.lawenconcommunity.model.Post;

public class CommentService extends BaseCoreService{
	
	@Autowired
	private CommentDao commentDao;
	@Autowired 
	private PostDao postingDao;
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
	
	public ResponseMessageDto insert(Comment data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Comment Failed!");
		valFk(data);
		begin();
		try {
			commentDao.save(data);
			responseMessageDto.setMessage("Comment Success");
		} catch (Exception e) {
			responseMessageDto.setMessage("Comment Failed!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}

	public void valInsert(Comment data) {
		valNullId(data);
		valFk(data);
	}
	
	public void valNullId(Comment data) {
		if(data.getId() != null) {
			throw new RuntimeException("ID must be empty!");
		}
	}
	public void valFk(Comment data) {
		if(postingDao.getById(Post.class, data.getPost().getId()) == null) {
			throw new RuntimeException("There are no poll posts that you voted for!");
		}
	}
	

}
