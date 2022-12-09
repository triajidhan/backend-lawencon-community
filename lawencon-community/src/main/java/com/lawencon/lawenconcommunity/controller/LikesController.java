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
import com.lawencon.lawenconcommunity.model.Like;
import com.lawencon.lawenconcommunity.service.LikeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("likes")
public class LikesController {
	@Autowired
	private LikeService likeService;
	
	@GetMapping("id/{id}")
	public ResponseEntity<Like> getById(@PathVariable("id") String id){
		final Like like = likeService.getById(id);
		
		return new ResponseEntity<>(like,HttpStatus.OK);
	}
	
	@GetMapping("users-order")
	public ResponseEntity<List<Like>> getByUser(@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		final List<Like> likes = likeService.getByUser(userId,startPosition,limit,ascending);
		
		return new ResponseEntity<>(likes,HttpStatus.OK);
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
	
	@PutMapping
	public ResponseEntity<ResponseMessageDto> update(@RequestBody Like data){
		final ResponseMessageDto responseMessageDto = likeService.update(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
	}
}
