package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.model.Post;
import com.lawencon.lawenconcommunity.service.PostService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("posts")
public class PostsController {

	@Autowired
	private PostService postService;
	
	@GetMapping()
	public ResponseEntity<List<Post>> getAll(@RequestParam("startPostion") int startPosition, @RequestParam("limit") int limit){
		final List<Post> posts = postService.getAll(startPosition, limit);
		
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("user")
	public ResponseEntity<List<Post>> getByUser(@RequestParam("userId") String userId){
		final List<Post> posts = postService.getByUser(userId);
		
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("post-code")
	public ResponseEntity<Post> getByPostCode(@RequestParam("postCode") String postCode){
		final Post post = postService.getByPostCode(postCode);
		
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
}
