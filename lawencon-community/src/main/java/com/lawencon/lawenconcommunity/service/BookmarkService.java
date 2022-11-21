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
import com.lawencon.lawenconcommunity.model.Post;
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.security.principal.PrincipalService;

@Service
public class BookmarkService extends BaseCoreService{

	@Autowired
	private BookmarkDao bookmarkDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PrincipalService principalService;
	
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
	
	public List<Bookmark> getByIsActive(Integer startPosition, Integer limitPage){
		List<Bookmark> bookmarks = bookmarkDao.getByIsActive(startPosition, limitPage);
		
		return bookmarks;
	}
	
	public int getTotalByUser(String userId){
		int totalBookmarks = bookmarkDao.getTotalByUser(userId);
		
		return totalBookmarks;
	}
	
	public int getTotalByPost(String postId){
		int totalBookmarks = bookmarkDao.getTotalByPost(postId);
		
		return totalBookmarks;
	}
	
	public Bookmark getUserBookmarkPost(String userId,String postId) {
		return bookmarkDao.getUserBookmarkPost(userId, postId).get();
	}
	
	public ResponseMessageDto insert(Bookmark data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Bookmark Failed!");
		try {
			User user = new User();
			user.setId(principalService.getAuthPrincipal());
			data.setUser(user);
			bookmarkDao.save(data);
			responseMessageDto.setMessage("Bookmark Success!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Bookmark Failed!");
			e.printStackTrace();
		}
		return responseMessageDto;
	}
	
	public ResponseMessageDto update(Bookmark data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Unbookmark Failed!");
		valId(data);
		Bookmark Bookmark = bookmarkDao.getById(Bookmark.class, data.getId());
		Bookmark BookmarkUpdate = Bookmark;
		try {
			BookmarkUpdate.setIsActive(false);
			responseMessageDto.setMessage("Unbookmark Success!");
		} catch (Exception e) {
			responseMessageDto.setMessage("Unbookmark Failed!");
			e.printStackTrace();
		}
		return responseMessageDto;
	}
	
	public void valId(Bookmark data) {
		if(bookmarkDao.getById(Bookmark.class, data.getId()) == null) {
			throw new RuntimeException("Id not found!");
		}
	}
	
	public void valInsert(Bookmark data) {
		valFk(data);
	}
	
	public void valFk(Bookmark data) {
		if(userDao.getByIdAndDetach(User.class, data.getUser().getId()) == null) {
			throw new RuntimeException("Incompatible users!");
		}
		
		if(postDao.getByIdAndDetach(Post.class, data.getPost().getId()) == null) {
			throw new RuntimeException("Incompatible post!");
		}
	}
	
	public void valIdNull(Bookmark data) {
		if (data.getId() != null) {
			throw new RuntimeException("Id must be null!");
		}
	}
}
