
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
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CommentService extends BaseCoreService{
	
	@Autowired
	private CommentDao commentDao;
	@Autowired 
	private PostDao postingDao;
	@Autowired
	private PrincipalService principalService;
	
	public List<Comment> getAll(Integer startPosition, Integer limitPage){
		List<Comment> comments = commentDao.getAll(Comment.class, startPosition, limitPage);
		
		return comments;
	}
	
	public List<Comment> getAll(){
		List<Comment> comments = commentDao.getAll(Comment.class);
		
		return comments;
	}
	
	public Comment getTotal() {
		return commentDao.getTotalComment();
	}
	
	public List<Comment> getByUser(String userId){
		List<Comment> comments = commentDao.getByUser(userId);
		
		return comments;
	}
	
	public List<Comment> getByUserAndOrder(String userId,boolean ascending){
		List<Comment> comments = commentDao.getByUserAndOrder(userId,ascending);
		
		return comments;
	}
	
	public List<Comment> getByUserAndOrder(String userId,int startPosition,int limit,boolean ascending){
		List<Comment> comments = commentDao.getByUserAndOrder(userId,startPosition,limit,ascending);
		
		return comments;
	}
	
	public Comment getTotalByUser(String userId) {
		return commentDao.getTotalByUser(userId);
	}
	
	public List<Comment> getByPost(String postId){
		List<Comment> comments = commentDao.getByPost(postId);
		
		return comments;
	}
	
	public List<Comment> getByPostAndOrder(String postId,boolean ascending){
		List<Comment> comments = commentDao.getByPostAndOrder(postId,ascending);
		
		return comments;
	}
	
	public List<Comment> getByPostAndOrder(String postId,int startPosition,int limit,boolean ascending){
		List<Comment> comments = commentDao.getByPostAndOrder(postId,startPosition,limit,ascending);
		
		return comments;
	}
	
	public Comment getTotalByPost(String postId) {
		return commentDao.getTotalByUser(postId);
	}
	
	public Comment getById(String id) {
		return commentDao.getByIdAndDetach(Comment.class, id);
	}
	
	public List<Comment> getByIsActive(Integer startPosition, Integer limitPage){
		return commentDao.getByIsActive(startPosition, limitPage);
	}
	
	public List<Comment> getByIsActive(){
		return commentDao.getByIsActive();
	}
	
	public ResponseMessageDto insert(Comment data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		valInsert(data);
		begin();
		try {
			User user = new User();
			user.setId(principalService.getAuthPrincipal());
			data.setUser(user);
			Comment commentInsert = new Comment();
			
			commentInsert = commentDao.save(data);
			responseMessageDto.setId(commentInsert.getId());
		} catch (Exception e) {
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
			responseMessageDto.setId(commentUpdate.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}

}
