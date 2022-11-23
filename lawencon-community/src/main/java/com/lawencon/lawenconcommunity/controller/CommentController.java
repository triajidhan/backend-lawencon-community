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
import com.lawencon.lawenconcommunity.model.Comment;
import com.lawencon.lawenconcommunity.service.CommentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public ResponseEntity<List<Comment>> getAll(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Comment> comments = commentService.getAll(startPosition, limitPage);
		
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@GetMapping("{id}/get")
	public ResponseEntity<Comment> getById(@PathVariable("id") String id){
		Comment comment = commentService.getById(id);
		
		return new ResponseEntity<>(comment,HttpStatus.OK);
	}
	
	@GetMapping("users")
	public ResponseEntity<List<Comment>> getByUser(@RequestParam String userId){
		List<Comment> comments = commentService.getByUser(userId);
		
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@GetMapping("total-user")
	public ResponseEntity<Comment> getTotalByUser(@RequestParam String userId){
		Comment comment = commentService.getTotalByUser(userId);
		
		return new ResponseEntity<>(comment,HttpStatus.OK);
	}
	
	@GetMapping("posts")
	public ResponseEntity<List<Comment>> getByPost(@RequestParam String postId){
		List<Comment> comments = commentService.getByPost(postId);
		
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@GetMapping("total-post")
	public ResponseEntity<Comment> getTotalByPost(@RequestParam String postId){
		Comment comment = commentService.getTotalByPost(postId);
		
		return new ResponseEntity<>(comment,HttpStatus.OK);
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<Comment>> getByIsActive(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage){
		final List<Comment> comments = commentService.getByIsActive(startPosition, limitPage);
		
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Comment data){
		final ResponseMessageDto responseMessageDto = commentService.insert(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody Comment data){
		final ResponseMessageDto responseMessageDto = commentService.update(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.OK);
	}
}
