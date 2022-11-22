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
import com.lawencon.lawenconcommunity.model.Like;
import com.lawencon.lawenconcommunity.service.LikeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("likes")
public class LikesController {
	@Autowired
	private LikeService likeService;
	
	@GetMapping
	public ResponseEntity<List<Like>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Like> likes = likeService.getByIsActive(startPosition, limitPage);
		
		return new ResponseEntity<>(likes,HttpStatus.OK);
	}
	
	@GetMapping("users")
	public ResponseEntity<List<Like>> getByUser(@RequestParam("userId") String userId){
		final List<Like> likes = likeService.getByUser(userId);
		
		return new ResponseEntity<>(likes,HttpStatus.OK);
	}
	
	@GetMapping("posts")
	public ResponseEntity<List<Like>> getByPost(@RequestParam("postId") String postId){
		final List<Like> likes = likeService.getByPost(postId);
		
		return new ResponseEntity<>(likes,HttpStatus.OK);
	}
	
	@GetMapping("total-posts")
	public ResponseEntity<Integer> getTotalByPost(@RequestParam("postId") String postId){
		final int total = likeService.getTotalByPost(postId);
		
		return new ResponseEntity<>(total,HttpStatus.OK);
	}
	
	@GetMapping("total-users")
	public ResponseEntity<Integer> getTotalByUser(@RequestParam("userId") String userId){
		final int total = likeService.getTotalByUser(userId);
		
		return new ResponseEntity<>(total,HttpStatus.OK);
	}
	
	@GetMapping("user-like")
	public ResponseEntity<Like> getUserLikePost(@RequestParam("postId") String postId,@RequestParam("userId") String userId){
		final Like like = likeService.getUserLikePost(userId,postId);
		
		return new ResponseEntity<>(like,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Like data){
		final ResponseMessageDto responseMessageDto = likeService.insert(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
	}
}
