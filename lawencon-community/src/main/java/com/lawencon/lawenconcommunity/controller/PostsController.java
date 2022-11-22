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
	
	@GetMapping()
	public ResponseEntity<List<Post>> getAll(@RequestParam("startPostion") int startPosition, @RequestParam("limit") int limit){
		final List<Post> posts = postService.getByIsActive(startPosition, limit);
		
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
	
	@GetMapping("{id}/get")
	public ResponseEntity<Post> getById(@PathVariable("id") String id){
		final Post post = postService.getById(id);
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@GetMapping("total-post-user")
	public ResponseEntity<Post> getTotalByUser(@RequestParam("userId") String userId){
		Post post = postService.getTotalByUser(userId);
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@GetMapping("total-post-type")
	public ResponseEntity<Post> getTotalByPostType(@RequestParam("postTypeId") String postTypeId){
		Post post = postService.getTotalByPostType(postTypeId);
		
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@GetMapping("total-post")
	public ResponseEntity<Long> getTotalPost(){
		final Long totalPost = postService.getTotal();
		
		return new ResponseEntity<>(totalPost,HttpStatus.OK);
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
