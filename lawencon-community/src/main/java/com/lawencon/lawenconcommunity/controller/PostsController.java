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
import com.lawencon.lawenconcommunity.model.Post;
import com.lawencon.lawenconcommunity.service.PostService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("posts")
public class PostsController {

	@Autowired
	private PostService postService;
	
	@GetMapping("users-order")
	public ResponseEntity<List<Post>> getByUser(@RequestParam("userId") String userId,@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limit,@RequestParam("asc") boolean ascending){
		final List<Post> posts = postService.getByUser(userId,startPosition,limit,ascending);
		
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Post> getById(@PathVariable("id") String id){
		final Post post = postService.getById(id);
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@GetMapping("is-active-order")
	public ResponseEntity<List<Post>> getByIsActiveAndOrder(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage,@RequestParam("asc") boolean ascending){
		final List<Post> posts = postService.getByIsActiveAndOrder(startPosition, limitPage,ascending);
		
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Post data){
		final ResponseMessageDto responseMessageDto = postService.insert(data);
		return new ResponseEntity<>(responseMessageDto,HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody Post data){
		final ResponseMessageDto responseMessageDto = postService.update(data);
		return new ResponseEntity<>(responseMessageDto,HttpStatus.OK);
	}
}
