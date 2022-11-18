package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.BookmarkDao;
import com.lawencon.lawenconcommunity.dao.PostDao;
import com.lawencon.lawenconcommunity.dao.UserDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Bookmark;
import com.lawencon.lawenconcommunity.model.Like;
import com.lawencon.lawenconcommunity.model.Post;
import com.lawencon.lawenconcommunity.model.User;

@Service
public class BookmarkService extends BaseCoreService{

	@Autowired
	private BookmarkDao bookmarkDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	public List<Bookmark> getAll(Integer startPosition, Integer limitPage){
		List<Bookmark> bookmarks = bookmarkDao.getAll(Bookmark.class, startPosition, limitPage);
		
		return bookmarks;
	}
	
	public List<Bookmark> getByUser(String userId){
		List<Bookmark> bookmarks = bookmarkDao.getByUser(userId);
		
		return bookmarks;
	}
	
	public List<Bookmark> getByPost(String postId){
		List<Bookmark> bookmarks = bookmarkDao.getByPost(postId);
		
		return bookmarks;
	}
	
	public ResponseMessageDto insert(Bookmark data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Like Failed!");
		try {
			bookmarkDao.save(data);
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
