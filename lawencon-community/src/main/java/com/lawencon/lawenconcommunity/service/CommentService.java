
package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.CommentDao;
import com.lawencon.lawenconcommunity.dao.PostDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Comment;
import com.lawencon.lawenconcommunity.model.Post;

@Service
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
	
	public Comment getById(String id) {
		return commentDao.getByIdAndDetach(Comment.class, id);
	}
	
	public ResponseMessageDto insert(Comment data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Comment Failed!");
		valInsert(data);
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
		if(postingDao.getByIdAndDetach(Post.class, data.getPost().getId()) == null) {
			throw new RuntimeException("There are no poll posts that you voted for!");
		}
	}
	
	public void valUpdate(Comment data) {
		valFoundIdUpdate(data);
	}
	
	public void valFoundIdUpdate(Comment data) {
		if(commentDao.getByIdAndDetach(Comment.class, data.getId()) == null) {
			throw new RuntimeException("Comment not found!");
		}
	}
	
	public ResponseMessageDto update(Comment data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Comment Failed Update!");
		valUpdate(data);
		Comment comment = commentDao.getByIdAndDetach(Comment.class, data.getId());
		Comment commentUpdate = comment;
		begin();
		try {
			if(data.getCommentBody() != null) {				
				commentUpdate.setCommentBody(data.getCommentBody());
			}
			
			if(data.getIsActive() != null) {				
				commentUpdate.setIsActive(data.getIsActive());
			}
			commentDao.save(commentUpdate);
			responseMessageDto.setMessage("Comment Success Update!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Comment Failed Update!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}

}
