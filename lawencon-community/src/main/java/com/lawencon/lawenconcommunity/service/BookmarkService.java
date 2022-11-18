package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.BookmarkDao;
import com.lawencon.lawenconcommunity.model.Bookmark;

public class BookmarkService extends BaseCoreService{

	@Autowired
	private BookmarkDao bookmarkDao;
	
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
}
