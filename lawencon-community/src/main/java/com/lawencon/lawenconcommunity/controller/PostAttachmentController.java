package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.model.PostAttachment;
import com.lawencon.lawenconcommunity.service.PostAttachmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("post-attachments")
public class PostAttachmentController {

	@Autowired
	private PostAttachmentService postAttachmentService;
	
	@GetMapping()
	public ResponseEntity<List<PostAttachment>> getAll(){
		List<PostAttachment> postAttachments = postAttachmentService.getByAll();
		
		return new ResponseEntity<>(postAttachments,HttpStatus.OK);
	}
	
	@GetMapping("posts")
	public ResponseEntity<List<PostAttachment>> getByPost(@RequestParam("postId") String postId){
		List<PostAttachment> postAttachments = postAttachmentService.getByPost(postId);
		
		return new ResponseEntity<>(postAttachments,HttpStatus.OK);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<PostAttachment> getById(@PathVariable("id") String id){
		PostAttachment postAttachment = postAttachmentService.getById(id);
		
		return new ResponseEntity<>(postAttachment,HttpStatus.OK);
	}
}
