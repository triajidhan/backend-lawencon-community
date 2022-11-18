package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Bookmark;
import com.lawencon.lawenconcommunity.service.BookmarkService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("bookmarks")
public class BookmarkController {

	@Autowired
	private BookmarkService bookmarkService;
	
	@GetMapping
	public ResponseEntity<List<Bookmark>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Bookmark> bookmarks = bookmarkService.getAll(startPosition, limitPage);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}
	
	@GetMapping("users")
	public ResponseEntity<List<Bookmark>> getByUser(@RequestParam("userId") String userId){
		final List<Bookmark> bookmarks = bookmarkService.getByUser(userId);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}
	
	@GetMapping("posts")
	public ResponseEntity<List<Bookmark>> getByPost(@RequestParam("postId") String postId){
		final List<Bookmark> bookmarks = bookmarkService.getByPost(postId);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Bookmark data){
		final ResponseMessageDto responseMessageDto = bookmarkService.insert(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
	}

