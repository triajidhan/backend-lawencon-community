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
	
	public List<Bookmark> getAll(){
		List<Bookmark> bookmarks = bookmarkDao.getAll(Bookmark.class);
		
		return bookmarks;
	}
	
	public List<Bookmark> getAll(Integer startPosition, Integer limitPage){
		List<Bookmark> bookmarks = bookmarkDao.getAll(Bookmark.class, startPosition, limitPage);
		
		return bookmarks;
	}
	
	public Bookmark getById(String id) {
		return bookmarkDao.getByIdAndDetach(Bookmark.class, id);
	}
	
	public List<Bookmark> getByUser(String userId){
		List<Bookmark> bookmarks = bookmarkDao.getByUser(userId);
		
		return bookmarks;
	}
	
	public List<Bookmark> getByUser(String userId,int startPosition,int limit){
		List<Bookmark> bookmarks = bookmarkDao.getByUser(userId,startPosition,limit);
		
		return bookmarks;
	}
	
	public List<Bookmark> getByUser(String userId,int startPosition,int limit,boolean ascending){
		List<Bookmark> bookmarks = bookmarkDao.getByUser(userId,startPosition,limit,ascending);
		
		return bookmarks;
	}
	
	public List<Bookmark> getByPost(String postId){
		List<Bookmark> bookmarks = bookmarkDao.getByPost(postId);
		
		return bookmarks;
	}
	
	public Bookmark getTotalByUser(String userId){
		Bookmark bookmark = bookmarkDao.getTotalByUser(userId);
		
		return bookmark;
	}
	
	public Bookmark getTotalByPost(String postId){
		Bookmark bookmark = bookmarkDao.getTotalByPost(postId);
		
		return bookmark;
	}
	
	public Bookmark getUserBookmarkPost(String userId,String postId) {
		return bookmarkDao.getUserBookmarkPost(userId, postId).get();
	}
	
	public List<Bookmark> getByIsActive(Integer startPosition, Integer limitPage){
		List<Bookmark> bookmarks = bookmarkDao.getByIsActive(startPosition, limitPage);
		
		return bookmarks;
	}
	
	public List<Bookmark> getByIsActive(){
		List<Bookmark> bookmarks = bookmarkDao.getByIsActive();
		
		return bookmarks;
	}
	
	public ResponseMessageDto insert(Bookmark data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		begin();
		try {
			User user = new User();
			user.setId(principalService.getAuthPrincipal());
			data.setUser(user);
			Bookmark bookmarkInsert = new Bookmark();
			bookmarkInsert = bookmarkDao.save(data);
			responseMessageDto.setId(bookmarkInsert.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}
	
	public ResponseMessageDto update(Bookmark data) {
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		valId(data);
		Bookmark Bookmark = bookmarkDao.getByIdAndDetach(Bookmark.class, data.getId());
		Bookmark BookmarkUpdate = Bookmark;
		begin();
		try {
			BookmarkUpdate.setIsActive(data.getIsActive());
			bookmarkDao.save(BookmarkUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		commit();
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
