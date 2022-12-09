package com.lawencon.lawenconcommunity.controller;

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
	
	@PreAuthorize("hasAuthority('M')")
	@GetMapping("id/{id}")
	public ResponseEntity<Comment> getById(@PathVariable("id") String id){
		Comment comment = commentService.getById(id);
		
		return new ResponseEntity<>(comment,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Comment data){
		final ResponseMessageDto responseMessageDto = commentService.insert(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('M')")
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody Comment data){
		final ResponseMessageDto responseMessageDto = commentService.update(data);
		return new ResponseEntity<ResponseMessageDto>(responseMessageDto, HttpStatus.OK);
	}
}
