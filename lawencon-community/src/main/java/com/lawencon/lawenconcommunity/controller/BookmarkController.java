package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping
	public ResponseEntity<List<Bookmark>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Bookmark> bookmarks = bookmarkService.getAll(startPosition, limitPage);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Bookmark> getById(@PathVariable("id") String bookmarkId){
		final Bookmark bookmark = bookmarkService.getById(bookmarkId);
		
		return new ResponseEntity<>(bookmark,HttpStatus.OK);
	}
	
	@GetMapping("users")
	public ResponseEntity<List<Bookmark>> getByUser(@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit){
		final List<Bookmark> bookmarks = bookmarkService.getByUser(userId,startPosition,limit);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}
	
	@GetMapping("users-order")
	public ResponseEntity<List<Bookmark>> getByUser(@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		final List<Bookmark> bookmarks = bookmarkService.getByUser(userId,startPosition,limit,ascending);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}
	
	@GetMapping("users-all")
	public ResponseEntity<List<Bookmark>> getByUser(@RequestParam("userId") String userId){
		final List<Bookmark> bookmarks = bookmarkService.getByUser(userId);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}
	
	@GetMapping("posts")
	public ResponseEntity<List<Bookmark>> getByPost(@RequestParam("postId") String postId){
		final List<Bookmark> bookmarks = bookmarkService.getByPost(postId);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}
	
	@GetMapping("total-user")
	public ResponseEntity<Bookmark> getTotalByUser(@RequestParam("userId") String userId){
		Bookmark bookmark = bookmarkService.getTotalByUser(userId);
		
		return new ResponseEntity<>(bookmark,HttpStatus.OK);
	}
	
	@GetMapping("total-post")
	public ResponseEntity<Bookmark> getTotalByPost(@RequestParam("postId") String postId){
		Bookmark bookmark = bookmarkService.getTotalByPost(postId);
		
		return new ResponseEntity<>(bookmark,HttpStatus.OK);
	}
	
	
	@GetMapping("user-bookmark")
	public ResponseEntity<Bookmark> getUserBookmarkPost(@RequestParam("postId") String postId,@RequestParam("userId") String userId){
		final Bookmark bookmark = bookmarkService.getUserBookmarkPost(userId,postId);
		
		return new ResponseEntity<>(bookmark,HttpStatus.OK);
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<Bookmark>> getByIsActive(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Bookmark> bookmarks = bookmarkService.getByIsActive(startPosition, limitPage);
		
		return new ResponseEntity<>(bookmarks,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Bookmark data){
		final ResponseMessageDto responseMessageDto = bookmarkService.insert(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ResponseMessageDto> update(@RequestBody Bookmark data){
		final ResponseMessageDto responseMessageDto = bookmarkService.update(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.OK);
	}
}

