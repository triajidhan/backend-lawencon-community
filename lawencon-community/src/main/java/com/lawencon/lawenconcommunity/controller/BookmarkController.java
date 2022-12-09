package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("id/{id}")
	public ResponseEntity<Bookmark> getById(@PathVariable("id") String bookmarkId){
		final Bookmark bookmark = bookmarkService.getById(bookmarkId);
		
		return new ResponseEntity<>(bookmark,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("users-order")
	public ResponseEntity<List<Bookmark>> getByUser(@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		final List<Bookmark> bookmarks = bookmarkService.getByUser(userId,startPosition,limit,ascending);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("posts")
	public ResponseEntity<List<Bookmark>> getByPost(@RequestParam("postId") String postId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		final List<Bookmark> bookmark = bookmarkService.getByPost(postId,startPosition,limit,ascending);
		
		return new ResponseEntity<>(bookmark,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("user-bookmark")
	public ResponseEntity<Bookmark> getUserBookmarkPost(@RequestParam("postId") String postId,@RequestParam("userId") String userId){
		final Bookmark bookmark = bookmarkService.getUserBookmarkPost(userId,postId);
		
		return new ResponseEntity<>(bookmark,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@PostMapping
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Bookmark data){
		final ResponseMessageDto responseMessageDto = bookmarkService.insert(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@PutMapping
	public ResponseEntity<ResponseMessageDto> update(@RequestBody Bookmark data){
		final ResponseMessageDto responseMessageDto = bookmarkService.update(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.OK);
	}
}

