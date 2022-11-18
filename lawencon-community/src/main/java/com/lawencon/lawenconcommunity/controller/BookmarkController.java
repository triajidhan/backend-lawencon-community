package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawencon.lawenconcommunity.model.Bookmark;
import com.lawencon.lawenconcommunity.service.BookmarkService;

public class BookmarkController {

	@Autowired
	private BookmarkService bookmarkService;
	
	@GetMapping
	public ResponseEntity<List<Bookmark>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Bookmark> bookmarks = bookmarkService.getAll(startPosition, limitPage);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}
}
